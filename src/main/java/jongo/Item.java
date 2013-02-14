package jongo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	 private int quantity;

	 private double price;
	 
	 @JsonProperty("desc") private String description;

	 public Item() {
	 }
	 
	public Item(int quantity, double price, String description) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
