package com.example.TLBkadai;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mydata")
public class MyData {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private long id;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 500, nullable = true)
	private String description;

	@Min(0)
	private long price;

	@Column(nullable = true)
	private String image;

	public long getId() {return id;}
	public void setId(long id) {this.id = id;}

	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public long getPrice() {return price;}
	public void setPrice(long price) {this.price = price;}

	public String getImage() {return image;}
	public void setImage(String image) {this.image = image;}

}