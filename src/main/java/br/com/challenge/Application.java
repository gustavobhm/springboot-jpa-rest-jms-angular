package br.com.challenge;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

/**
 * This class is responsible for start the springboot application
 * 
 * @author Gustavo Luis dos Santos
 */
@EnableJms
@SpringBootApplication
public class Application {

	/**
	 * Method that starts the springboot
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
		
	/**
	 * Method to create your JMS ConnectionFactory which will also automatically create an embedded broker
	 */
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		factory.setTrustAllPackages(true);
		return factory;
	}
	
}
