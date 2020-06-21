package com.iagomeijon.spring_api.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	//id composite
	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private double discount;
	private Integer amount;
	private double price;
	
	
	public OrderItem() {
	}


	public OrderItem(Order order, Product product, double discount, Integer amount, double price) {
		super();
		this.id.setOrder(order);
		this.id.setProduct(product);
		this.discount = discount;
		this.amount = amount;
		this.price = price;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public OrderItemPK getId() {
		return id;
	}


	public void setId(OrderItemPK id) {
		this.id = id;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
