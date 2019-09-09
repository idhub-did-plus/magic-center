package com.idhub.magic.provider.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.event.MagicEvent;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.provider.agent.OrderBookFactory;

import retrofit2.Call;

@Service
public class DirectedOrderFecher {
	@Autowired OrderBookFactory fac;
	@Autowired OrderRepository rep;
	ScheduledExecutorService pool;
	void init() {
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {
			try {
				List<Order> my = fac.getOrderBook().directed(AccountManager.getMyAccount().getAddress()).execute().body();
				rep.store(my);
				
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}, 0, 15, TimeUnit.SECONDS);
	}

}
