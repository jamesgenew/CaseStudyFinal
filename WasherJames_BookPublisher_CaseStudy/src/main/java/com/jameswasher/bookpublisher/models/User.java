package com.jameswasher.bookpublisher.models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User { //User model is used to store user information and list of books owned by the user
	@Id
	@Column(name="email")
	private String email;
	@Basic
	@Column(name="fname")
	private String firstName;
	@Basic
	@Column(name="lname")
	private String lastName;
	@Basic
	@Column(name="password")
	private String password;
	@Basic
	@Column(name = "address")
	private String userAddress;
	@Basic
	@Column(name = "zipcode")
	private String zipCode;
	private List<Book> userBooks;

	public User(String email, String firstName, String lastName, String password) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public User(String email, String firstName, String lastName, String password, 
			String userAddress, String zipCode) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userAddress = userAddress;
		this.zipCode = zipCode;
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User() {
		this.email = "default@email.com";
		this.password = "password";
		this.firstName = "fname";
		this.lastName = "lname";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Book> getUserBooks() {
		return userBooks;
	}

	public void setUserBooks(List<Book> userBooks) {
		this.userBooks = userBooks;
	}

}
