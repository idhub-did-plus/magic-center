package com.idhub.magic.provider;

import javax.servlet.MultipartConfigElement;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.mongodb.MongoClient;

@Configuration
public class MorphiaAutoConfiguration {

	@Autowired
	private MongoClient mongoClient; // created from MongoAutoConfiguration

	@Bean
	public Datastore datastore() {
		Morphia morphia = new Morphia();

		// map entities, there is maybe a better way to find and map all entities
		ClassPathScanningCandidateComponentProvider entityScanner = new ClassPathScanningCandidateComponentProvider(
				true);
		entityScanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		for (BeanDefinition candidate : entityScanner.findCandidateComponents("com.idhub.magic.center.entity")) { // from
																													// properties?
			try {
				morphia.map(Class.forName(candidate.getBeanClassName()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return morphia.createDatastore(mongoClient, "dataStoreInstanceId"); // "dataStoreInstanceId" may come from
																			// properties?
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("20480KB");
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}