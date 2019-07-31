package com.idhub.magic.center;
import java.io.IOException;

import org.apache.http.conn.HttpHostConnectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3jService;

import com.idhub.magic.center.service.ProxyService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EtherTest {

    @Autowired
    private ProxyService proxy;

    // This test will only run if you provide a real Ethereum client for web3j to connect to
    @Test(expected = HttpHostConnectException.class)
    public void testGetClientVersion() throws IOException {
        assertThat(proxy.getClientVersion()).startsWith("Geth/");
    }
}