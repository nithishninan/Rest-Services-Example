package com.stark.coursews.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Course")
public class Course {
	private long id, price, rating;
	private String name, taughtby;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaughtby() {
		return taughtby;
	}
	public void setTaughtby(String taughtby) {
		this.taughtby = taughtby;
	}
}
