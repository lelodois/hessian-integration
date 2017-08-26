package br.com.roicamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.ApplicationEvent;

import br.com.basecamp.message.CampanhaMessage;
import br.com.roicamp.comum.BaseValidator;
import br.com.roicamp.comum.DateUtils;
import br.com.roicamp.comum.StringUtils;
import br.com.roicamp.event.CampanhaExchangeTeamEvent;
import br.com.roicamp.event.CampanhaNewEvent;
import br.com.roicamp.exception.DateInvalidException;
import br.com.roicamp.icomum.INewEvent;
import br.com.roicamp.icomum.IPersistenceObject;
import br.com.roicamp.icomum.ITeamEvent;
import br.com.roicamp.icomum.IVigencia;

@Entity
@Table(name = "tb_campanha")
public class Campanha implements IPersistenceObject, INewEvent, IVigencia, ITeamEvent, Comparable<Campanha> {

	private static final long serialVersionUID = 2553565129604818630L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_entity")
	private Long id;

	@Embedded
	private BaseModel baseModel;

	@Column(name = "nm_campanha")
	private String nome;

	@Column(name = "id_time")
	private Long timeId;

	@Column(name = "dt_inicio_vigencia")
	private Date inicioVigencia;

	@Column(name = "dt_fim_vigencia")
	private Date fimVigencia;

	public Campanha() {

	}

	public Campanha(CampanhaMessage item) throws DateInvalidException {
		this.baseModel = BaseModel.neww();
		updateValues(item);
	}

	public Campanha updateValues(CampanhaMessage item) throws DateInvalidException {
		BaseValidator.notNull(null, item.getTimeId(), item.getNome(), item.getInicioVigencia(),
				item.getInicioVigencia());
		getBaseModel().setAtualizacao(id);
		this.timeId = item.getTimeId();
		this.inicioVigencia = DateUtils.toDateTruncate(item.getInicioVigencia());
		this.fimVigencia = DateUtils.toDateTruncate(item.getFimVigencia());
		this.setNome(item.getNome());

		if (DateUtils.isSameDay(inicioVigencia, fimVigencia) == false && inicioVigencia.after(fimVigencia)) {
			throw new DateInvalidException("Vigencia inv�lida");
		}

		return this;
	}

	public CampanhaMessage buildMessage() {
		CampanhaMessage message = new CampanhaMessage();
		message.setId(this.getId());
		message.setNome(this.getNome());
		message.setTimeId(this.getTimeId());
		message.setDataAtualizacao(DateUtils.toStringDateTime(this.getBaseModel().getDataAtualizacao()));
		message.setInicioVigencia(DateUtils.toStringDateTruncate(this.getInicioVigencia()));
		message.setFimVigencia(DateUtils.toStringDateTruncate(this.getFimVigencia()));
		return message;
	}

	public boolean isTerminoVigenciaIgual(Date fimVigencia, Long campanhaNotId) {
		return getId().equals(campanhaNotId) == false && DateUtils.isSameDay(getFimVigencia(), fimVigencia);
	}

	@Override
	public ApplicationEvent getEventExchangeTeam(Object source) {
		return new CampanhaExchangeTeamEvent(source, this);
	}

	@Override
	public ApplicationEvent getEventNewItem(Object source) {
		return new CampanhaNewEvent(source, this);
	}

	@Override
	public int compareTo(Campanha other) {
		return getFimVigencia().compareTo(other.getFimVigencia());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseModel getBaseModel() {
		return baseModel;
	}

	public void setBaseModel(BaseModel baseModel) {
		this.baseModel = baseModel;
	}

	public Long getTimeId() {
		return timeId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = StringUtils.truncateString(nome, 500);
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	@Override
	public String toString() {
		return "Campanha [id=" + id + ", nome=" + nome + ", inicioVigencia=" + inicioVigencia + ", fimVigencia="
				+ fimVigencia + "]";
	}

}
