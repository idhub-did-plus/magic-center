package com.idhub.magic.provider.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.provider.agent.OrderBookFactory;

import retrofit2.Call;

@Service
public class DirectedOrderFecher {
	@Autowired OrderBookFactory fac;
	@Autowired OrderRepository rep;
	ScheduledExecutorService pool;
	@PostConstruct
	void start() {
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {
			try {
				String identity = AccountManager.getMyAccount().getAddress();
				List<Order> my = fac.getOrderBook().directed(identity).execute().body();
				if(my == null)
					return;
				List<Order> suc = new ArrayList<Order>();
				for(Order o : my) {
					boolean b = fac.getOrderBook().receive(identity, o.id).execute().body();
					if(b)
						suc.add(o);
				}
				
				rep.store(suc);
				
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}, 0, 15, TimeUnit.SECONDS);
	}

}
