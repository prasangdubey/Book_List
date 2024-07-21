package com.assignment.Book_List.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@NotBlank(message="Title cannot be blank")
	@Column(name="title")
	private String title;
	
	@NotBlank(message="Author cannot be blank")
	@Column(name="author")
	private String author;
	
	@NotNull(message="Published Date cannot be blank")
	@Column(name="publishedDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date publishedDate;
	
	@Column(name="isbn")
	@Pattern(regexp="^(978\\d{10}|\\d{13})$",message="Invalid ISN Format")
	private String isbn;
	
	
	@Column(name="price")
	private double price;
	
	
	public Book() {
		
	}
	

	public Book(Long id, @NotBlank(message = "Title cannot be blank") String title,
			@NotBlank(message = "Author cannot be blank") String author,
			@NotNull(message = "Published Date cannot be blank") Date publishedDate,
			@Pattern(regexp = "^(978\\d{10}|\\d{13})$", message = "Invalid ISN Format") String isbn, double price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
		this.isbn = isbn;
		this.price = price;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
