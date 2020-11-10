package com.jameswasher.bookpublisher.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jameswasher.bookpublisher.services.cartServices;

@Entity
@Table(name = "Cart")
public class Cart { //this model is used to store cart information for every user, including an id, 
	@Id             //user id, subtotal of cart items and list of books in cart
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cartId")
	private int cartId;
	@Basic
	@Column(name = "userId")
	private String userId;
	@Basic
	@Column(name = "subtotal")
	private double subtotal;
	@ManyToMany
	@JoinTable(
			name = "books",
			joinColumns = @JoinColumn(name = "cart_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> cartBooks; //every cart can have many books, every book can belong to many carts
	
	public Cart(String uId){
		this();
		this.userId = uId;
		this.subtotal = 0;
	}
	
	public Cart(){
		this.cartBooks = new ArrayList<Book>();
	}
	
	public int getCartId() {
		return cartId;
	}
	
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public double getSubtotal() {
		return subtotal;
	}

	public List<Book> getCartBooks() {
		return cartBooks;
	}

	public void setCartBooks(List<Book> cartBooks) {
		this.cartBooks = cartBooks;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
