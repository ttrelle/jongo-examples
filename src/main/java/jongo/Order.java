package jongo;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

	private ObjectId id;
	
	private Date date;
	
	@JsonProperty("custInfo") private String customerInfo;
	
	List<Item> items;
	
	public Order() {
		this(null);
	}

	public Order(String customerInfo) {
		super();
		this.customerInfo = customerInfo;
		this.date = new Date();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}	

	
}
