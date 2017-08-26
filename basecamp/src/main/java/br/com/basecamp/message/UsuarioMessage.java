package br.com.basecamp.message;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo que representa informa��es do usu�rio")
public class UsuarioMessage implements Serializable {

	private static final long serialVersionUID = -884399160367512191L;

	@ApiModelProperty(required = false, notes = "Desconsiderado na inclus�o")
	private Long id;

	@ApiModelProperty(example = "L�o Costa", required = true)
	private String nome;

	@ApiModelProperty(example = "1000", required = true, dataType = "Long")
	private Long timeId;

	@ApiModelProperty(example = "leoeduar@gmail.com", required = true)
	private String email;

	@ApiModelProperty(example = "09/03/1987", allowableValues = "Data string no formato dd/MM/yyyy", required = true)
	private String dataNascimento;

	@ApiModelProperty(hidden = true)
	private String dataAtualizacao;

	@ApiModelProperty(hidden = true)
	private List<CampanhaMessage> campanhasParticipantes = Lists.newArrayList();

	public UsuarioMessage() {
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

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public void setCampanhasParticipantes(List<CampanhaMessage> campanhasParticipantes) {
		this.campanhasParticipantes = campanhasParticipantes;
	}

	public List<CampanhaMessage> getCampanhasParticipantes() {
		return campanhasParticipantes;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UsuarioMessage [nome=" + nome + ", email=" + email + "]";
	}

}
