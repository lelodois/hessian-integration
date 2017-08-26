package br.com.roicamp;

import javax.annotation.PostConstruct;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.roicamp.event.impl.PostHibernateEventListener;

@Component
public class HibernateListenerComponent {

	@Autowired
	private HibernateEntityManagerFactory entityFactory;

	@Autowired
	private PostHibernateEventListener listener;

	@PostConstruct
	public void registerEventListener() {
		EventListenerRegistry listenerRegistry = ((SessionFactoryImpl) entityFactory.getSessionFactory())
				.getServiceRegistry().getService(EventListenerRegistry.class);
		listenerRegistry.setListeners(EventType.POST_INSERT, listener);
		listenerRegistry.setListeners(EventType.POST_UPDATE, listener);
	}
}
