package com.jameswasher.bookpublisher.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.jameswasher.bookpublisher.currentUser.CurrentUser;
import com.jameswasher.bookpublisher.models.Book;
import com.jameswasher.bookpublisher.models.Cart;
import com.jameswasher.bookpublisher.models.User;


public class cartServices {
	public void addBooktoCart(Book b, String userId){ //(userId is the email address) adds Book b to cart owned by User with userId
		Cart result = findCartByUserId(userId);
		int cartId = result.getCartId();
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cart resultc = entityManager.find(Cart.class, cartId);
		Book resultb = entityManager.find(Book.class, b.getId());
		boolean bookFound = false;
		for(int i = 0; i < resultc.getCartBooks().size(); i++) { //check if book already exists in cart
			if(resultc.getCartBooks().get(i).getId() == b.getId()) {
				bookFound = true;
			}
		}
		if(!bookFound) { //only add book to cart if cart does not have book yet
			updateSubtotal(b.getBookPrice(), resultc.getCartId());
			entityManager.getTransaction().begin();
			resultc.getCartBooks().add(b);
			entityManager.getTransaction().commit();
		}
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public Cart findCartByUserId(String i) { //This function returns Cart belonging to user with email ID i
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cart result = (Cart)entityManager.createQuery("SELECT s FROM Cart s WHERE s.userId =:id").setParameter("id", i).getSingleResult();
		entityManager.close();
		entityManagerFactory.close();
		return result;
	}

	
	public void createCart(Cart newCart) { //this function is used to create a new cart
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(newCart);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public void updateSubtotal(double price, int cartId) { //this function is used to update the subtotal of the cart with id  
		try {                                              //cartId, a negative number can be passed when deleting a book or clearing cart
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cart result = entityManager.find(Cart.class, cartId);
		entityManager.getTransaction().begin();
		result.setSubtotal(price+result.getSubtotal());
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	public void deleteFromCart(int cartId, int bookId) { //function to be used when removing a book from a cart
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cart result = entityManager.find(Cart.class, cartId);
		List<Book> cBooks = result.getCartBooks();
		int bIndex = -1;
		bookServices bs = new bookServices();
		for(int i = 0; i < cBooks.size(); i++) {
			if(cBooks.get(i).getId() == bookId) {
				bIndex = i;
				break;
			}
		}
		if(bIndex != -1) {
			cBooks.remove(bIndex);
			entityManager.getTransaction().begin();
			result.setCartBooks(cBooks);
			entityManager.getTransaction().commit();
			
			updateSubtotal((bs.findBook(bookId).getBookPrice()*-1),cartId); //remove price from subtotal
			
			entityManager.close();
			entityManagerFactory.close();
		}else {
			entityManager.close();
			entityManagerFactory.close();
		}
	}
	public void deleteAllFromCart(int cartId) { //use this function when transferring books from cart to user's owned books,
		try {                                   //deletes all books from the cart with id cartId
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cart resultc = entityManager.find(Cart.class, cartId);
		double currSubtotal = resultc.getSubtotal();
		updateSubtotal((currSubtotal * -1), cartId);
		
		entityManager.getTransaction().begin();
		resultc.getCartBooks().clear();
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public void transferBooks(int cartId) { //use this function when transferring books from cart with id cartId to current user's owned books
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("casestudydraft1");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cart resultc = entityManager.find(Cart.class, cartId);
		User currUser = entityManager.find(User.class, CurrentUser.getCurrentUser().getEmail());
		List<Book> userBooks = currUser.getUserBooks();
		List<Book> result = new ArrayList<Book>();
		boolean found = false;
		for(int i = 0; i < resultc.getCartBooks().size(); i++){ //check if each book in cart is already owned, if a book is not owned 
			found = false;                                      //then it may be added to user's book collection
			for(int j = 0; j < userBooks.size(); j++) {
				if(userBooks.get(j).getId() == resultc.getCartBooks().get(i).getId()) {
					found = true;
				}
			}
			if(!found) {
				result.add( resultc.getCartBooks().get(i));
			}
		}
		
		entityManager.getTransaction().begin();
		currUser.getUserBooks().addAll(result);
		entityManager.getTransaction().commit();
				
		entityManager.close();
		entityManagerFactory.close();
	}
	
}
