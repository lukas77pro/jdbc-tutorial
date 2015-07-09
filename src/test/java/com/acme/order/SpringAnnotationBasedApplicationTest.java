package com.acme.order;

import static org.junit.Assert.assertEquals;
import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.SpringockitoAnnotatedContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acme.order.application.SpringAnnotationBasedApplication;
import com.acme.order.delivery.strategy.DeliveryTimeStrategy;
import com.acme.order.delivery.strategy.PizzaTypeDeliveryTimeStrategy;
import com.acme.order.pizza.PizzaOrderService;
import com.acme.order.pizza.PizzaType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoAnnotatedContextLoader.class, classes = SpringAnnotationBasedApplication.class)
@ActiveProfiles("pizzaType")
@Slf4j
public class SpringAnnotationBasedApplicationTest {

	@Autowired
	private JdbcOrderRepository repository;

	@Autowired
	private DeliveryTimeStrategy deliveryTimeStrategy;

	@Before
	public void before() {
		assertEquals(PizzaTypeDeliveryTimeStrategy.class, deliveryTimeStrategy.getClass());
	}

	@Test
	public void testApplication() {
		repository.findAll();
		
		
		
		
		

	}

}
