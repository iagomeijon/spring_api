package com.iagomeijon.spring_api.resources.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private Integer Status;
		private String message;
		private Long timeStamp;
		
		public Integer getStatus() {
			return Status;
		}

		public void setStatus(Integer status) {
			Status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Long getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(Long timeStamp) {
			this.timeStamp = timeStamp;
		}

		public StandartError(Integer status, String message, Long timeStamp) {
			super();
			Status = status;
			this.message = message;
			this.timeStamp = timeStamp;
		}
}
