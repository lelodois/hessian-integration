package br.com.roicamp.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.roicamp.icomum.IPersistenceObject;

@Entity
@Table(name = "tb_campanha_usuario")
public class CampanhaUsuario implements IPersistenceObject {

	private static final long serialVersionUID = -6202476469732981621L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_campanha_usuario")
	private Long id;

	@Embedded
	private BaseModel baseModel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_campanha")
	private Campanha campanha;

	public CampanhaUsuario() {
	}

	public CampanhaUsuario(Campanha campanha, Usuario usuario) {
		this.baseModel = BaseModel.neww();
		this.campanha = campanha;
		this.usuario = usuario;
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

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
