package com.iagomeijon.spring_api.domain.enums;

public enum ClientType {

	PHYSICALPERSON(1, "Physical person"),
	LEGALPERSON(2, "Legal person");
	
	private int id;
	private String description;
	
	private ClientType(int id, String desc) {
		this.id = id;
		this.description = desc;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer id) {
		if(id == null ) {
			return null;
		}
		//Check all clients types and return the ClientType that has same id passed
		for(ClientType type : ClientType.values()) {	
			if(id.equals(type.getId())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Client type id invlaid: " + id);
	}
	
}
