package br.com.roicamp.icomum;

import org.springframework.context.ApplicationEvent;

public interface INewEvent {

	public ApplicationEvent getEventNewItem(Object source);

}
