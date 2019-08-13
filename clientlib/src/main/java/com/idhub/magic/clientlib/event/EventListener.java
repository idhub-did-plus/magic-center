package com.idhub.magic.clientlib.event;

import com.idhub.magic.center.event.ChainEventWrapper;

public interface EventListener {

	void onEvent(ChainEventWrapper e);
	

}
