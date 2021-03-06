package com.acme.order.application;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.acme.order.Customer;
import com.acme.order.pizza.PizzaOrderService;
import com.acme.order.pizza.PizzaType;

@Slf4j
@Configuration
@ComponentScan("com.acme.order")
public class SpringAnnotationBasedApplication {
	
	private final String url = "jdbc:mysql://localhost:3306/pizza-tutorial";
	private final String user = "dbuser";
	private final String password = "dbpass";

	public static void main(String[] args) {
		log.info("Spring XML based application");

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnnotationBasedApplication.class);
		PizzaOrderService orderService = ctx.getBean(PizzaOrderService.class);

		Customer customer1 = new Customer(null, "John Smith", "john@smith.com", "Lodz, Jaracza 74");
		Customer customer2 = new Customer(null, "Jan Kowalski", "jan@kowalski.pl", "Lodz, Piotrkowska 100");

		String orderId1 = orderService.createOrder(customer1, PizzaType.LARGE);
		String orderId2 = orderService.createOrder(customer2, PizzaType.SMALL);

		log.info("Unprocessed orders:{}", orderService.fetchUnprocessed());
		log.info("Delivered orders:{}", orderService.fetchDelivered());

		orderService.deliverOrder(orderId1);
		log.info("Delivered orders:{}", orderService.fetchDelivered());
		orderService.cancelOrder(orderId2);
		log.info("Delivered orders:{}", orderService.fetchDelivered());
		log.info("Cancelled orders:{}", orderService.fetchCancelled());
		log.info("Unprocessed orders:{}", orderService.fetchUnprocessed());
	}
	
	
	@Bean
	public BasicDataSource createPool(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		
		ds.setInitialSize(5);
		
		return ds;
	}
	

}
