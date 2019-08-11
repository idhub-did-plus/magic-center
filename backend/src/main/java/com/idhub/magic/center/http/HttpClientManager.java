package com.idhub.magic.center.http;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.DefaultCookieSpec;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HttpClientManager {

    static Logger logger = Logger.getLogger(HttpClientManager.class);

    static RequestConfig config;
    static PoolingHttpClientConnectionManager connectManager;
    static SSLConnectionSocketFactory fac;
    static Registry<CookieSpecProvider> r;

    static   {
        try {
            TrustStrategy trustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String authType) throws CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts
                    .custom()
                    .useSSL()
                    .loadTrustMaterial(null, trustStrategy)
                    .setSecureRandom(new SecureRandom())
                    .build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());
            Registry<ConnectionSocketFactory> registry =  RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslConnectionSocketFactory)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();

            X509HostnameVerifier x509HostnameVerifier = new X509HostnameVerifier() {
                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                    //do nothing
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                    //do nothing                                                            //do nothing
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                    //do nothing
                }

                @Override
                public boolean verify(String string, SSLSession ssls) {
                    return true;
                }
            };
            connectManager = new PoolingHttpClientConnectionManager(registry);
            connectManager.setMaxTotal(400);
            connectManager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(50000).build());
            connectManager.setDefaultMaxPerRoute(20);
            connectManager.setDefaultMaxPerRoute(connectManager.getMaxTotal());
            SSLContext ctx = SSLContexts.createSystemDefault();

            r = RegistryBuilder.<CookieSpecProvider>create()
                    .register("easy", new EasySpecProvider())
                    .build();

            fac = new SSLConnectionSocketFactory(ctx, new String[] { "TLSv1" }, null, x509HostnameVerifier);
            config = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.DEFAULT).setConnectionRequestTimeout(60000)
                    .setSocketTimeout(60000)
                    .setConnectTimeout(60000).setStaleConnectionCheckEnabled(true)
                    .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                    .setCookieSpec("easy")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static ThreadLocal<CloseableHttpClient> local = new ThreadLocal<CloseableHttpClient>();

    static public CloseableHttpClient getMyHttpClient() {
        CloseableHttpClient myc = local.get();
        if(myc == null) {
            myc = getHttpClient();
            local.set(myc);
        }
        return myc;
    }
    static public CloseableHttpClient getHttpClient(HttpHost proxy) {
        RequestConfig myconfig = RequestConfig.custom()
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000)
                .setConnectTimeout(60000).setStaleConnectionCheckEnabled(true)
                .setCookieSpec("easy")
                .build();


        try {
            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setDefaultCookieStore(cookieStore)
                    .disableAutomaticRetries()
                    .setDefaultRequestConfig(myconfig)
                    .setDefaultCookieSpecRegistry(r)
                    .setConnectionManager(connectManager).setSSLSocketFactory(fac)
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .build();
            return httpClient;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public CloseableHttpClient getHttpClient() {
     
        try {
            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setDefaultCookieStore(cookieStore)
                    .disableAutomaticRetries()
                   
                    .setDefaultCookieSpecRegistry(r)
                    .setDefaultRequestConfig(config)
                    .setConnectionManager(connectManager).setSSLSocketFactory(fac)
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .build();
            return httpClient;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class EasyCookieSpec extends DefaultCookieSpec {
    @Override
    public void validate(Cookie c, CookieOrigin co) throws MalformedCookieException {
        //allow all cookies
    }
}

class EasySpecProvider implements CookieSpecProvider {
    @Override
    public CookieSpec create(HttpContext context) {
        return new EasyCookieSpec();
    }
}