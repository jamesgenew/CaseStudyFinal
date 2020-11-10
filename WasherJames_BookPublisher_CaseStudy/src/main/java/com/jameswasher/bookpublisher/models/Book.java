package com.jameswasher.bookpublisher.models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book { //this model stores every book and its information
	@Id
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name="title")
	private String bookTitle;
	@Basic
	@Column(name="price")
	private double bookPrice;
	@Basic
	@Column(name="summary")
	private String bookSummary;
	@Basic
	@Column(name="genre")
	private String genre;
	@Basic
	@Column(name="coverart")
	private String coverArt; //address of .jpg for cover art
	@Basic
	@Column(name="author")
	private String author;
	
	@ManyToMany(mappedBy = "cartBooks") //every book can belong to many users' carts
	private List<Cart> carts;
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookSummary() {
		return bookSummary;
	}
	public void setBookSummary(String bookSummary) {
		this.bookSummary = bookSummary;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoverArt() {
		return coverArt;
	}
	public void setCoverArt(String coverart) {
		this.coverArt = coverart;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
}
