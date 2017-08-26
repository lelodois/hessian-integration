package br.com.roicamp.event.impl;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import br.com.roicamp.icomum.INewEvent;
import br.com.roicamp.icomum.ITeamEvent;
import br.com.roicamp.icomum.IUpdateEvent;

@Component
public class PostHibernateEventListener
		implements PostInsertEventListener, PostUpdateEventListener, ApplicationEventPublisherAware {

	private static final long serialVersionUID = 7657925593748335153L;

	private ApplicationEventPublisher publisher;

	@Override
	public void onPostInsert(PostInsertEvent event) {
		if (event.getEntity() != null && event.getEntity() instanceof INewEvent) {
			publisher.publishEvent(((INewEvent) event.getEntity()).getEventNewItem(this));
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		Object entity = event.getEntity();

		if (entity != null && entity instanceof ITeamEvent) {
			for (int index : event.getDirtyProperties()) {
				if (index == getPropertyIndexFieldTime(event.getPersister().getPropertyNames())) {
					publisher.publishEvent(((ITeamEvent) entity).getEventExchangeTeam(this));
				}
			}
		}

		if (entity != null && entity instanceof IUpdateEvent) {
			publisher.publishEvent(((IUpdateEvent) entity).getEventUpdateItem(this));
		}
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	private int getPropertyIndexFieldTime(String[] propertyNames) {
		for (int index = 0; index < propertyNames.length; index++) {
			if (ITeamEvent.FIELD_TIME.equals(propertyNames[index])) {
				return index;
			}
		}
		return -1;
	}

}
