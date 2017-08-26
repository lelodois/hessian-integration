package br.com.roicamp.icomum;

import org.springframework.context.ApplicationEvent;

public interface IUpdateEvent {

	public ApplicationEvent getEventUpdateItem(Object source);

}
