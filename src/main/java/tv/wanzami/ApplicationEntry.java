package tv.wanzami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tv.wanzami.config.AppProperties;
import tv.wanzami.config.CorsProperties;

/**
 * Application entry class
 */

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({AppProperties.class, CorsProperties.class})
@EntityScan(basePackages = {"tv.wanzami.model"})
@ComponentScan(basePackages = "tv.wanzami")
@SpringBootApplication
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
