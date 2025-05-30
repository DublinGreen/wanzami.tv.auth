package tv.wanzami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application entry class
 */

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"tv.wanzami.model"})
@ComponentScan(basePackages = "tv.wanzami")
@SpringBootApplication
@EnableCaching
public class ApplicationEntry {

	/**
	 * Application main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEntry.class, args);
	}

	/**
	 * extend the long method to be used in graphql
	 * @return
	 */
	@Bean
	public graphql.schema.GraphQLScalarType extendedScalarLong() {
	    return graphql.scalars.ExtendedScalars.GraphQLLong;
	}
	
}
