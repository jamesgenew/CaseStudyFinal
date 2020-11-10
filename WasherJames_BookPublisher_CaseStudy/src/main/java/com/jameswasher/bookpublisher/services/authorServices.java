package com.jameswasher.bookpublisher.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jameswasher.bookpublisher.models.Author;
import com.jameswasher.bookpublisher.models.Book;
import com.jameswasher.bookpublisher.models.Cart;

public class authorServices {
	public Author findAuthor(int id) { //function used to find an author with id "id"
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Author result = (Author)entityManager.createQuery("SELECT u FROM Author u WHERE u.id =:id").setParameter("id",id).getSingleResult();
			entityManager.close();
			entityManagerFactory.close();
			return result;
		}catch(NoResultException e) {
			System.out.println("No result!");
			return null;
		}
	}
	
	public Author findAuthorByName(String name) { //function to find an author by name
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Author result = (Author)entityManager.createQuery("SELECT u FROM Author u WHERE u.authorName =:name").setParameter("name",name).getSingleResult();
			entityManager.close();
			entityManagerFactory.close();
			return result;
		}catch(NoResultException e) {
			System.out.println("No result!");
			return null;
		}
	}
}

//	public void addBooks() { //populate authors' book lists
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		Author resulta = entityManager.find(Author.class, 1);
//		Book resultb = entityManager.find(Book.class, 1);
//		Book resultb2 = entityManager.find(Book.class, 4);
//		List<Book> books = new ArrayList<Book>();
//		books.add(resultb);
//		books.add(resultb2);
//		entityManager.getTransaction().begin();
//		resulta.setAuthorBooks(books);
//		entityManager.getTransaction().commit();
//		resulta = entityManager.find(Author.class, 2);
//		resultb = entityManager.find(Book.class, 2);
//		books.clear();
//		books.add(resultb);
//		entityManager.getTransaction().begin();
//		resulta.setAuthorBooks(books);
//		entityManager.getTransaction().commit();
//		resulta = entityManager.find(Author.class, 3);
//		resultb = entityManager.find(Book.class, 3);
//		books.clear();
//		books.add(resultb);
//		entityManager.getTransaction().begin();
//		resulta.setAuthorBooks(books);
//		entityManager.getTransaction().commit();
//		resulta = entityManager.find(Author.class, 4);
//		resultb = entityManager.find(Book.class, 5);
//		books.clear();
//		books.add(resultb);
//		entityManager.getTransaction().begin();
//		resulta.setAuthorBooks(books);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();
//	}

