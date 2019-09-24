package com.idhub.magic.center.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.center.entity.EventWrapper;
import com.idhub.magic.common.contracts.IdentityRegistryInterface;
import com.idhub.magic.common.contracts.IdentityRegistryInterface.IdentityCreatedEventResponse;
import com.idhub.magic.common.event.MagicEvent;
import com.idhub.magic.common.event.MagicEventType;
import com.idhub.magic.common.parameter.CreateIdentityDelegatedParam;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
@Service
public class DelegationService {
	@Autowired Datastore store;
	@Autowired
	ObjectMapper mapper = new ObjectMapper();
	@Autowired MagicEventStore eventStore;
    private Web3j web3j;
	/*
	 * @Autowired Admin admin;
	 */
	@Autowired 
	ContractManager contractManager;
    @PostConstruct
    void init(){
    	
    }
    public void createEntityDelegated(CreateIdentityDelegatedParam param) {
    	IdentityRegistryInterface c1484 = contractManager.getRegistry1484();
    	byte[] r = Numeric.hexStringToByteArray(param.r);
    	byte[] s = Numeric.hexStringToByteArray(param.s);
    	BigInteger ts = Numeric.toBigInt(param.timestamp);
    	BigInteger V = Numeric.toBigInt(param.v);
    	try {
			CompletableFuture<TransactionReceipt> tr = c1484.createIdentityDelegated(param.recoveryAddress, param.associatedAddress, param.providers, param.resolvers, V, r, s, ts).sendAsync();
			
			tr.thenAccept(ein -> {
				List<IdentityCreatedEventResponse> evs = contractManager.registry1484.getIdentityCreatedEvents(ein);
				IdentityCreatedEventResponse resp = evs.get(0);
				eventStore.store(MagicEventType.chain_event, param.associatedAddress, resp);
			}).exceptionally(transactionReceipt -> {
				
				
				return null;
			});
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private static Logger log = LoggerFactory.getLogger(DelegationService.class);



    void simpleFilterExample() throws Exception {

        Disposable subscription = web3j.blockFlowable(false).subscribe(block -> {
            log.info("Sweet, block number " + block.getBlock().getNumber()
                    + " has just been created");
        }, Throwable::printStackTrace);

        TimeUnit.MINUTES.sleep(2);
        subscription.dispose();
    }

    void blockInfoExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        log.info("Waiting for " + 100 + " transactions...");
        Disposable subscription = web3j.blockFlowable(true)
                .take(100)
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
        subscription.dispose();
    }

    void countingEtherExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        log.info("Waiting for " + 100 + " transactions...");
        Single<BigInteger> transactionValue = web3j.transactionFlowable()
                .take(100)
                .map(Transaction::getValue)
                .reduce(BigInteger.ZERO, BigInteger::add);

        Disposable subscription = transactionValue.subscribe(total -> {
            BigDecimal value = new BigDecimal(total);
            log.info("Transaction value: " +
                    Convert.fromWei(value, Convert.Unit.ETHER) + " Ether (" +  value + " Wei)");
            countDownLatch.countDown();
        }, Throwable::printStackTrace);

        countDownLatch.await(10, TimeUnit.MINUTES);
        subscription.dispose();
    }

    void clientVersionExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Disposable subscription = web3j.web3ClientVersion().flowable().subscribe(x -> {
            log.info("Client is running version: {}", x.getWeb3ClientVersion());
            countDownLatch.countDown();
        });

        countDownLatch.await();
        subscription.dispose();
    }
}