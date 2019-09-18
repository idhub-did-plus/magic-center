
package com.idhub.magic.provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
//@EnableAspectJAutoProxy
public class ProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ProviderApplication.class, args);

		
	}

}
//mongodb+srv://baiyuqi:<password>@cluster0-4bfkb.gcp.mongodb.net/test?retryWrites=true&w=majority