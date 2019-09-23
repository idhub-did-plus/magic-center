package com.idhub.magic.provider.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.provider.agent.OrderBookFactory;
import com.idhub.magic.provider.model.IdentityEntity;

@Service
public class DirectedOrderFecher {
	@Autowired OrderBookFactory fac;
	@Autowired OrderRepository rep;
	@Autowired Datastore ds;
	ScheduledExecutorService pool;
	Logger logger = Logger.getLogger(DirectedOrderFecher.class.getSimpleName());
	@PostConstruct
	void start() {
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {
			try {
				String identity = AccountManager.getMyAccount().getAddress();
				List<Order> my = fac.getOrderBook().directed(identity).execute().body();
				if(my == null)
					return;
				//List<Order> suc = new ArrayList<Order>();
				/*
				 * for(Order o : my) { boolean b = fac.getOrderBook().receive(identity,
				 * o.id).execute().body(); if(b) { suc.add(o); } }
				 */
				rep.store(my);
				/*
				 * List<IdentityEntity> es = new LinkedList<IdentityEntity>(); for(Order o :
				 * suc) { IdentityData ia = fac.getOrderBook().getIdentityInformation(identity,
				 * o.identity).execute().body(); IdentityEntity id = new IdentityEntity(ia);
				 * es.add(id); } ds.save(es);
				 */
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.log(Level.SEVERE, e.getMessage());
			}
			

		}, 0, 15, TimeUnit.SECONDS);
	}

}
