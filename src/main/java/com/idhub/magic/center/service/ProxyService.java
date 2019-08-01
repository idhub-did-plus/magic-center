package com.idhub.magic.center.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import io.reactivex.disposables.Disposable;
@Service
public class ProxyService {
	@Autowired
    private Web3j web3j;
	@Autowired 
	Admin admin;
    @PostConstruct
    void init(){
    	System.out.println(web3j.toString());
    	
    	 try {
    		NetVersion nv = web3j.netVersion().send();
    		System.out.println(nv.getNetVersion());
    		 String v = getClientVersion();
    		 System.out.println(v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getClientVersion() throws IOException {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        return web3ClientVersion.getWeb3ClientVersion();
    }
    private static final int COUNT = 10;

    private static Logger log = LoggerFactory.getLogger(ProxyService.class);


    static public void main(String[] ss) {
    	Web3j web3j = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
    	
    }

    private void run() throws Exception {
        simpleFilterExample();
     //   blockInfoExample();
     //   countingEtherExample();
      //  clientVersionExample();
        System.exit(0);  // we explicitly call the exit to clean up our ScheduledThreadPoolExecutor used by web3j
    }


    void simpleFilterExample() throws Exception {

        Disposable subscription = web3j.blockFlowable(false).subscribe(block -> {
            log.info("Sweet, block number " + block.getBlock().getNumber()
                    + " has just been created");
        }, Throwable::printStackTrace);

        TimeUnit.MINUTES.sleep(2);
        subscription.dispose();
    }

   /* void blockInfoExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

        log.info("Waiting for " + COUNT + " transactions...");
        Subscription subscription = web3j.blockObservable(true)
                .take(COUNT)
                .subscribe(ethBlock -> {
                    EthBlock.Block block = ethBlock.getBlock();
                    LocalDateTime timestamp = Instant.ofEpochSecond(
                            block.getTimestamp()
                                    .longValueExact()).atZone(ZoneId.of("UTC")).toLocalDateTime();
                    int transactionCount = block.getTransactions().size();
                    String hash = block.getHash();
                    String parentHash = block.getParentHash();

                    log.info(
                            timestamp + " " +
                                    "Tx count: " + transactionCount + ", " +
                                    "Hash: " + hash + ", " +
                                    "Parent hash: " + parentHash
                    );
                    countDownLatch.countDown();
                }, Throwable::printStackTrace);

        countDownLatch.await(10, TimeUnit.MINUTES);
        subscription.unsubscribe();
    }

    void countingEtherExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        log.info("Waiting for " + COUNT + " transactions...");
        Observable<BigInteger> transactionValue = web3j.transactionObservable()
                .take(COUNT)
                .map(Transaction::getValue)
                .reduce(BigInteger.ZERO, BigInteger::add);

        Subscription subscription = transactionValue.subscribe(total -> {
            BigDecimal value = new BigDecimal(total);
            log.info("Transaction value: " +
                    Convert.fromWei(value, Convert.Unit.ETHER) + " Ether (" +  value + " Wei)");
            countDownLatch.countDown();
        }, Throwable::printStackTrace);

        countDownLatch.await(10, TimeUnit.MINUTES);
        subscription.unsubscribe();
    }

    void clientVersionExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Subscription subscription = web3j.web3ClientVersion().observable().subscribe(x -> {
            log.info("Client is running version: {}", x.getWeb3ClientVersion());
            countDownLatch.countDown();
        });

        countDownLatch.await();
        subscription.unsubscribe();
    }*/
}