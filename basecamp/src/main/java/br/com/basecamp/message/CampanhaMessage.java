package br.com.basecamp.message;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo que representa informa��es do usu�rio")
public class CampanhaMessage implements Serializable {

	private static final long serialVersionUID = 7925461175500170212L;

	@ApiModelProperty(required = false, notes = "Desconsiderado na inclus�o")
	private Long id;

	@ApiModelProperty(example = "Time torcida", required = true)
	private String nome;

	@ApiModelProperty(example = "1000", required = true, dataType = "Long")
	private Long timeId;

	@ApiModelProperty(example = "01/08/2017", allowableValues = "Data string no formato dd/MM/yyyy", required = true)
	private String inicioVigencia;

	@ApiModelProperty(example = "10/08/2017", allowableValues = "Data string no formato dd/MM/yyyy", required = true)
	private String fimVigencia;

	@ApiModelProperty(hidden = true)
	private String dataAtualizacao;

	public CampanhaMessage() {
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTimeId() {
		return timeId;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public String getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(String inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public String getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(String fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	@Override
	public String toString() {
		return "CampanhaMessage [nome=" + nome + ", timeId=" + timeId + "]";
	}

}
