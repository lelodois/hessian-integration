package br.com.roicamp.event;

import org.springframework.context.ApplicationEvent;

import br.com.roicamp.model.Campanha;

public class CampanhaNewEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3272173486450125006L;

	private Campanha campanha;

	public CampanhaNewEvent(Object myInstance, Campanha campanha) {
		super(myInstance);
		this.campanha = campanha;
	}

	public Campanha getCampanha() {
		return campanha;
	}

}
