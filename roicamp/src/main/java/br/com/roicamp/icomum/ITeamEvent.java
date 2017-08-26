package br.com.roicamp.icomum;

import org.springframework.context.ApplicationEvent;

public interface ITeamEvent {

	public Long getTimeId();

	public ApplicationEvent getEventExchangeTeam(Object source);

	public final String FIELD_TIME = "timeId";
}
