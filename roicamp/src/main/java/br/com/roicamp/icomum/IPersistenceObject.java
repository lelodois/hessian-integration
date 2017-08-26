package br.com.roicamp.icomum;

import java.io.Serializable;

import br.com.roicamp.model.BaseModel;

public interface IPersistenceObject extends Serializable {

	public BaseModel getBaseModel();

	public void setBaseModel(BaseModel baseCrud);

	public Long getId();

	public void setId(Long id);
	
}
