package com.jameswasher.bookpublisher.models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Author")//
public class Author { //this model stores every author so that their books can be displayed
	@Id
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "name")
	private String authorName;
	
	private List<Book> authorBooks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<Book> getAuthorBooks() {
		return authorBooks;
	}
	public void setAuthorBooks(List<Book> authorBooks) {
		this.authorBooks = authorBooks;
	}	
}
