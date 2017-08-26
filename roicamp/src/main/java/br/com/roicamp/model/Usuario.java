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

import br.com.basecamp.message.UsuarioMessage;
import br.com.roicamp.comum.BaseValidator;
import br.com.roicamp.comum.DateUtils;
import br.com.roicamp.comum.StringUtils;
import br.com.roicamp.event.UsuarioExchangeTeamEvent;
import br.com.roicamp.event.UsuarioNewEvent;
import br.com.roicamp.event.UsuarioUpdateEvent;
import br.com.roicamp.exception.DateInvalidException;
import br.com.roicamp.icomum.INewEvent;
import br.com.roicamp.icomum.IPersistenceObject;
import br.com.roicamp.icomum.ITeamEvent;
import br.com.roicamp.icomum.IUpdateEvent;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements IPersistenceObject, IUpdateEvent, ITeamEvent, INewEvent {

	private static final long serialVersionUID = 7083230057054065374L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_entity")
	private Long id;

	@Embedded
	private BaseModel baseModel;

	@Column(name = "nm_nome_completo")
	private String nome;

	@Column(name = "id_time")
	private Long timeId;

	@Column(name = "dt_nascimento")
	private Date dataNascimento;

	@Column(name = "nm_email")
	private String email;

	public Usuario() {
	}

	public Usuario(UsuarioMessage item) throws DateInvalidException {
		this.setBaseModel(BaseModel.neww());
		updateValues(item);
	}

	public Usuario updateValues(UsuarioMessage item) throws DateInvalidException {
		BaseValidator.notNull(null, item.getDataNascimento(), item.getNome(), item.getEmail(), item.getTimeId());
		getBaseModel().setAtualizacao(id);
		this.setDataNascimento(DateUtils.toDateTruncate(item.getDataNascimento()));
		this.setNome(item.getNome());
		this.setEmail(item.getEmail());
		this.setTimeId(item.getTimeId());
		return this;
	}

	public UsuarioMessage buildMessage() {
		UsuarioMessage message = new UsuarioMessage();
		message.setId(this.getId());
		message.setEmail(this.getEmail());
		message.setNome(this.getNome());
		message.setTimeId(this.getTimeId());
		message.setDataAtualizacao(DateUtils.toStringDateTime(this.getBaseModel().getDataAtualizacao()));
		message.setDataNascimento(DateUtils.toStringDateTruncate(this.getDataNascimento()));
		return message;
	}

	@Override
	public ApplicationEvent getEventExchangeTeam(Object source) {
		return new UsuarioExchangeTeamEvent(source, this);
	}

	@Override
	public ApplicationEvent getEventNewItem(Object source) {
		return new UsuarioNewEvent(source, this);
	}

	@Override
	public ApplicationEvent getEventUpdateItem(Object source) {
		return new UsuarioUpdateEvent(source, this);
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = StringUtils.truncateString(nome, 255);
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = StringUtils.truncateString(email, 100);
	}

}
