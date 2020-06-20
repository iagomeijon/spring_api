package com.iagomeijon.spring_api.domain;



import java.util.Date;

import javax.persistence.Entity;

import com.iagomeijon.spring_api.domain.enums.PaymentStatus;

@Entity
public class BillPayment extends Payment {
	private static final long serialVersionUID = 1L;

	private Date endDate;
	private Date paymentDate;

	public BillPayment() {
	}

	public BillPayment(Integer id, PaymentStatus status, Order order, Date endDate, Date dataPagamento) {
		super(id, status, order);
		this.paymentDate = dataPagamento;
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date dataPagamento) {
		this.paymentDate = dataPagamento;
	}	
	
}