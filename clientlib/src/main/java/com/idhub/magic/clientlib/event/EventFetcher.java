package com.idhub.magic.clientlib.event;

import java.util.ArrayList;
import java.util.List;

import com.idhub.magic.center.event.Event;

public class EventFetcher {
	static List<EventListener> listeners = new ArrayList<EventListener>();
	static void processEvent(Event e) {
		List<EventListener> temp =  new ArrayList<EventListener>(listeners);
		for(EventListener l : temp) {
			if(l.filt(e)) {
				l.onEvent(e);
				listeners.remove(l);
			}
				
		}
	}
	static public void listen(EventListener l) {
		listeners.add(l);
	}
	

}
