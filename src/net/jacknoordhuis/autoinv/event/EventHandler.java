package net.jacknoordhuis.autoinv.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.util.HashMap;

public abstract class EventHandler implements Listener {

	private EventManager manager;

	public EventHandler(EventManager manager) {
		this.manager = manager;
	}

	public EventManager getManager() {
		return this.manager;
	}

	/**
	 * Returns the priority of the event handler
	 */
	public EventPriority getEventPriority() {
		return EventPriority.NORMAL;
	}

	/**
	 * Returns whether the handler should be called if the event is cancelled
	 */
	public boolean ignoreCancelled() {
		return true;
	}

}
