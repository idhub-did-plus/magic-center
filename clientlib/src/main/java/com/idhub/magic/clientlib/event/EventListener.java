package com.idhub.magic.clientlib.event;

import com.idhub.magic.center.event.Event;

public interface EventListener {
	boolean filt(Event e);
	void onEvent(Event e);
	

}
