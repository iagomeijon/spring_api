package com.iagomeijon.spring_api.domain.enums;

public enum PaymentStatus {
	
	PENDING(1, "pending"),
	CLOSED(2, "closed"),
	CANCELED(3, "canceled");
	
	private int id;
	private String description;
	
	private PaymentStatus(int id, String desc) {
		this.id = id;
		this.description = desc;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer id) {
		if(id == null ) {
			return null;
		}
		//Check all payments status and return the PaymentStatus that has same id passed
		for(PaymentStatus type : PaymentStatus.values()) {	
			if(id.equals(type.getId())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Payment status id invlaid: " + id);
	}
}
