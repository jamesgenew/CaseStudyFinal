package com.jameswasher.bookpublisher.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jameswasher.bookpublisher.models.Book;
import com.jameswasher.bookpublisher.models.User;

public class bookServices {
	public Book findBook(int id) { //function used to find Book by its ID 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Book result = (Book)entityManager.createQuery("SELECT u FROM Book u WHERE u.id =:id").setParameter("id",id).getSingleResult();
			entityManager.close();
			entityManagerFactory.close();
			return result;
		}catch(NoResultException e) {
			return null;
		}
	}
	public List<Book> findAllBooks(){ //function used to return list of all books available
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Book> result = (List<Book>)entityManager.createQuery("SELECT u FROM Book u").getResultList();
			entityManager.close();
			entityManagerFactory.close();
			return result;
		}catch(NoResultException e) {
			return null;
		}
	}
}
