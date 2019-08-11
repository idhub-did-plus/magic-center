package com.idhub.magic.center.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.idhub.magic.center.contracts.IdentityRegistryInterface;
import com.idhub.magic.center.controller.parameter.CreateIdentityDelegatedParam;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
@Service
public class DelegationService {
	@Autowired
    private Web3j web3j;
	@Autowired 
	Admin admin;
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
			TransactionReceipt tr = c1484.createIdentityDelegated(param.recoveryAddress, param.associatedAddress, param.providers, param.resolvers, V, r, s, ts).send();
			BigInteger b = c1484.getEIN(param.associatedAddress).send();
			
			System.out.println(tr.getStatus());
			System.out.println(b);
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