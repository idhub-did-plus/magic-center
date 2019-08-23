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

import javax.xml.transform.ErrorListener;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idhub.magic.center.event.ChainEvent;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.http.HttpAccessor;
import com.idhub.magic.clientlib.http.RetrofitAccessor;
import com.idhub.magic.clientlib.interfaces.EventService;
import com.idhub.magic.clientlib.interfaces.ExceptionListener;

import retrofit2.Call;

public class EventFetcher {
	static EventFetcher instance = new EventFetcher();

	static public EventFetcher getInstance() {
		return instance;
	}

	static String url = "http://localhost:8080/chainevent/getChainEvent";
	ScheduledExecutorService pool;
	EventListener listener;
	private EventFetcher() {
		final String identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
		EventService eventService = RetrofitAccessor.getInstance().getEventService();
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {

			MagicResponse<List<ChainEvent>> evs;
			try {
				evs = eventService.queryEvents(identity).execute().body();
				for (ChainEvent e : evs.getData()) {
					if(listener != null)
						listener.onEvent(e);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			

		}, 0, 15, TimeUnit.SECONDS);

	}

	

	public void listen(EventListener l) {
		
	}

	static public void main(String[] ss) throws Exception {
	/*	JSONObject data = (JSONObject) JSON.parse(IOUtils.toString(new FileInputStream("new-filter.json")));
		Boolean suc = (Boolean) data.get("success");
		if (!suc)
			System.out.println(data);*/
	}

}
