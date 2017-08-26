package br.com.roicamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BaseModel {

	@Column(name = "dt_cadastro")
	private Date dataCadastro;

	@Column(name = "dt_inativacao")
	private Date dataInativacao;

	@Column(name = "dt_ultima_alteracao")
	private Date ultimaAlteracao;

	public static BaseModel neww() {
		BaseModel baseModel = new BaseModel();
		baseModel.setDataCadastro(new Date());
		return baseModel;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInativacao() {
		return dataInativacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	public void setAtualizacao(Long id) {
		this.ultimaAlteracao = id != null ? new Date() : null;
		this.dataInativacao = null;
	}

	public Date getDataAtualizacao() {
		return (ultimaAlteracao != null ? ultimaAlteracao : dataCadastro);
	}

	public void inativar() {
		this.dataInativacao = new Date();
	}

}
