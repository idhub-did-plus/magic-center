package com.idhub.magic.clientlib.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.idhub.magic.center.event.EventWrapper;

public class EventFetcher {
	static EventFetcher instance = new EventFetcher();
	static public EventFetcher getInstance() {
		return instance;
	}
	 Map<String, EventListener> listeners = new HashMap<String, EventListener>();
	 ScheduledExecutorService pool;
	 private EventFetcher()
	 {
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {
			
			
			
			
		}, 0, 15, TimeUnit.SECONDS);

	}

	 void processEvent(EventWrapper e) {

		Map<String, EventListener> temp = new HashMap<String, EventListener>(listeners);
		for (String t : temp.keySet()) {
			if (temp.equals(e.threadId)) {
				temp.get(t).onEvent(e);
				listeners.remove(t);
			}

		}
	}

	 public void listen(EventListener l) {
		String threadId = UUID.randomUUID().toString();
		listeners.put(threadId, l);
	}

}
