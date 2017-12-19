package com.apisec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageCollection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> messages;
	private List<Object> data;
	private boolean success;

	public MessageCollection() {
		this.messages = new ArrayList<String>();
		this.data = new ArrayList<Object>();
		this.success = false;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addMessages(String messages) {
		this.messages.add(messages);
	}

	public List<Object> getData() {
		return data;
	}

	public void addData(Object data) {
		this.data.add(data);
	}

	public boolean isSuccess() {
		return success;
	}

	public void isSuccess(boolean success) {
		this.success = success;
	}

}