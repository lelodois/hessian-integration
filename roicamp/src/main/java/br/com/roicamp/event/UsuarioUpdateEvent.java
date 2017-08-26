package br.com.roicamp.event;

import org.springframework.context.ApplicationEvent;

import br.com.roicamp.model.Usuario;

public class UsuarioUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1383402639540001020L;
	private Usuario usuario;

	public UsuarioUpdateEvent(Object myInstance, Usuario usuario) {

		super(myInstance);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
