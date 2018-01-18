package net.jacknoordhuis.autoinv.event;

import net.jacknoordhuis.autoinv.AutoInv;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class EventManager {

	private AutoInv plugin;

	private ArrayList<EventHandler> eventHandlers;

	public EventManager(AutoInv plugin) {
		this.plugin = plugin;
		this.eventHandlers = new ArrayList<>();
	}

	public AutoInv getPlugin() {
		return this.plugin;
	}

	public void registerHandler(EventHandler handler) {
		this.eventHandlers.add(handler);

		Arrays.stream(handler.getClass().getDeclaredMethods()).forEach(method -> {
			Class checkClass;
			if(!this.isMethodBad(method) && method.getParameters().length == 1 && Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
				final Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
				method.setAccessible(true);
				EventExecutor executor = new EventExecutor() {
					public void execute(Listener listener, Event event) throws EventException {
						try {
							if(eventClass.isAssignableFrom(event.getClass())) {
								method.invoke(listener, event);
							}
						} catch(InvocationTargetException var4) {
							throw new EventException(var4.getCause());
						} catch(Throwable var5) {
							throw new EventException(var5);
						}
					}
				};
				this.plugin.getServer().getPluginManager().registerEvent(eventClass, handler, handler.getEventPriority(), executor, this.plugin, handler.ignoreCancelled());
			}
		});
	}

	private boolean isMethodBad(Method method) {
		return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(org.bukkit.event.EventHandler.class);
	}

}
