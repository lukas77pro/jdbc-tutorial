package com.acme.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.log.Log;
import com.acme.order.pizza.PizzaOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Primary
public class JdbcOrderRepository implements OrderRepository {

	@Autowired
	private BasicDataSource ds;
	private JdbcTemplate jdbcTemplate;

	@Override
	public String save(PizzaOrder order) {
		return this.jdbcTemplate.queryForObject("INSERT INTO order_t values(?, ?, ?, ?, ?", String.class,
				order.getId(),
				order.getState(),
				order.getPizzaType(),
				order.getEstimatedTime(),
				order.getFinishTime()
				);
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public PizzaOrder get(String pizzaOrderId) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM order_t WHERE id = ?", new Object[] { pizzaOrderId },
				PizzaOrder.class);
	}

	@Override
	public List<PizzaOrder> findAll() {
		List<PizzaOrder> lista = this.jdbcTemplate.queryForList("SELECT * FROM order_t", PizzaOrder.class);
		return lista;
	}

	@Override
	public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
		List<PizzaOrder> lista = this.jdbcTemplate.queryForList("SELECT * FROM order_t WHERE status = ?",
				new Object[] { orderStatus }, PizzaOrder.class);
		return lista;
	}

}
