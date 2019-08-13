package com.idhub.magic.clientlib.event;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idhub.magic.center.event.ChainEvent;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.http.HttpAccessor;

public class EventFetcher {
	static EventFetcher instance = new EventFetcher();

	static public EventFetcher getInstance() {
		return instance;
	}

	static String url = "http://localhost:8000/chainevent/getChianEvents";
	Map<String, EventListener> listeners = new HashMap<String, EventListener>();
	ScheduledExecutorService pool;

	private EventFetcher() {
		final String identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {

			String json = HttpAccessor.get(identity, url, null);
			JSONObject data = (JSONObject) JSON.parse(json);
			Boolean suc = (Boolean) data.get("success");
			if (!suc)
				return;
			Object esdata = data.get("data");
			String ess = esdata.toString();
			List<ChainEvent> rst = JSON.parseArray(ess, ChainEvent.class);
			for (ChainEvent e : rst)
				processEvent(e);

		}, 0, 15, TimeUnit.SECONDS);

	}

	void processEvent(ChainEvent e) {

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

	static public void main(String[] ss) throws Exception {
		JSONObject data = (JSONObject) JSON.parse(IOUtils.toString(new FileInputStream("new-filter.json")));
		Boolean suc = (Boolean) data.get("success");
		if (!suc)
			System.out.println(data);
	}

}
