package com.jameswasher.bookpublisher.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jameswasher.bookpublisher.currentUser.CurrentUser;
import com.jameswasher.bookpublisher.models.Book;
import com.jameswasher.bookpublisher.models.Cart;
import com.jameswasher.bookpublisher.models.User;

public class userServices {
	public String createUser(User newUser) { //function to create a new user when registering
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(newUser);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		cartServices cs = new cartServices();
		
		Cart c = new Cart(newUser.getEmail());    
		
		cs.createCart(c);  
		
		return newUser.getEmail();
	}
	
	public User findUser(String email) { //function to find a user by their unique email address
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			User result = (User)entityManager.createQuery("SELECT u FROM User u WHERE u.email =:email").setParameter("email",email).getSingleResult();
			entityManager.close();
			entityManagerFactory.close();
			return result;
		}catch(NoResultException e) {
			System.out.println("No result!");
			return null;
		}
	}
	public void addBook(User u, Book b) { //function to add a book to a user's owned books list
		List<Book> old = u.getUserBooks();
		old.add(b);
		u.setUserBooks(old);
	}
	
	public void changeUserPassword(String newPass) { //SELECT s FROM Student s WHERE s.sEmail =:sEmail
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.createQuery("UPDATE User u SET u.password = :newPass WHERE u.email = :email").setParameter("newPass",newPass).setParameter("email", CurrentUser.getCurrentUser().getEmail()).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
