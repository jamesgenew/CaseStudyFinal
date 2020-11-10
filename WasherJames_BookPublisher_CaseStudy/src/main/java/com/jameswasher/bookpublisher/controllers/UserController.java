package com.jameswasher.bookpublisher.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jameswasher.bookpublisher.currentUser.CurrentUser;
import com.jameswasher.bookpublisher.models.Author;
import com.jameswasher.bookpublisher.models.Book;
import com.jameswasher.bookpublisher.models.Cart;
import com.jameswasher.bookpublisher.models.User;
import com.jameswasher.bookpublisher.services.authorServices;
import com.jameswasher.bookpublisher.services.bookServices;
import com.jameswasher.bookpublisher.services.cartServices;
import com.jameswasher.bookpublisher.services.userServices;

@Controller
public class UserController {
	@GetMapping("/")
	public ModelAndView index() { //index page
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST) //post used when submitting account info for registration
	public ModelAndView register(@RequestParam("email") String email,
			@RequestParam("fname") String fname,
			@RequestParam("lname") String lname,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			@RequestParam("zip") String zip) { 
		userServices us = new userServices();
		User newUser = new User(email,fname,lname,password,address,zip);
		us.createUser(newUser);
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	@GetMapping("/registration") 
	public ModelAndView registerpage() { //used when registering a new account
		ModelAndView mav = new ModelAndView("registration");
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST) //postmapping used when attempting to log in
	public ModelAndView loginpage(
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		userServices us = new userServices();
		User findResult = us.findUser(email);
		if(findResult != null) {		
			if(findResult.getPassword().equals(password)) { 
				//user corresponding to the given email must be found & given password must match user's password 
				ModelAndView mav = new ModelAndView("login_success");
				mav.addObject("email", email);
				userServices us2 = new userServices();
				User u = us2.findUser(email);
				CurrentUser.setCurrentUser(u);
				return mav;
			}else { 
				ModelAndView mav = new ModelAndView("login_form");
				mav.addObject("error", "wrong username and/or password");
				return mav;
			}
		}else {
			ModelAndView mav = new ModelAndView("login_form");
			mav.addObject("error", "wrong username and/or password");
			return mav;
		}
	}
	
	@GetMapping("/login") //this function will display the form to log in to an account
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login_form");
		return mav;
	}
	
	@GetMapping("/books") //this function will display a list of the publisher's books in a table
	public ModelAndView userBooks() {
		if(CurrentUser.getCurrentUser() == null) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}else {
		ModelAndView mav = new ModelAndView("books");
		bookServices bs = new bookServices();
		List<Book> theBooks = bs.findAllBooks();
		mav.addObject("theBooks",theBooks);
		return mav;
		}
	}
	
	@GetMapping("/cart") 
	public ModelAndView userCart() { //used to display the current user's cart
		if(CurrentUser.getCurrentUser() == null) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}
		ModelAndView model = new ModelAndView("cart");
		User currUser = CurrentUser.getCurrentUser();
		String uId = currUser.getEmail();
		userServices us = new userServices();
		cartServices cs = new cartServices();
		Cart c = cs.findCartByUserId(currUser.getEmail());
		List<Book> theBooks = c.getCartBooks(); //find current user's cart and store its books in theBooks
		double subtotal = c.getSubtotal();
		model.addObject("theBooks",theBooks);
		model.addObject("subtotal", subtotal);
		return model;
	}
	
	@PostMapping("/addtocart/{id}") //"id" is the id of the Book which will be added to the user's cart
	public ModelAndView addToCart(@PathVariable("id") String id) { //post request used when adding a book to the current user's cart
		bookServices bs = new bookServices();
		cartServices cs = new cartServices();
		Book b = bs.findBook(Integer.parseInt(id));
		cs.addBooktoCart(b, CurrentUser.getCurrentUser().getEmail());
		ModelAndView mav = new ModelAndView("redirect:/cart");
		return mav;
	}
	
	@PostMapping("/deletefromcart/{id}") //"id" is the id of the Book that will be deleted from the cart
	public ModelAndView deleteFromCart(@PathVariable("id") String id) { //postmapping used when deleting an item from the current user's cart
		bookServices bs = new bookServices();
		cartServices cs = new cartServices();
		Cart currentCart = cs.findCartByUserId(CurrentUser.getCurrentUser().getEmail());
		cs.deleteFromCart(currentCart.getCartId(), Integer.parseInt(id));
		ModelAndView mav = new ModelAndView("redirect:/cart");
		return mav;
	}
	
	@GetMapping("/displayauthor/{id}") //"id" is the id of the book whose author will be displayed
	public ModelAndView displayAuthor(@PathVariable("id") String id){ //getmapping used when displaying an author's books
		if(CurrentUser.getCurrentUser() == null) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}else {
			
			authorServices as = new authorServices();
			bookServices bs = new bookServices();
			//as.addBooks(); //run only once to populate authors' authorBooks field, the addBooks function must also be uncommented
			Book authorBook = bs.findBook(Integer.parseInt(id));
			String aName = authorBook.getAuthor();
			Author a = as.findAuthorByName(aName);
			List<Book> aBooks = a.getAuthorBooks();
			ModelAndView mav = new ModelAndView("author");
			mav.addObject("aBooks", aBooks);
			mav.addObject("aName", aName);
			return mav;
		}
	}
	
	@PostMapping("/checkout") 
	public ModelAndView checkout() { //function to be used when user checks out at cart
		cartServices cs = new cartServices();
		Cart currentCart = cs.findCartByUserId(CurrentUser.getCurrentUser().getEmail());
		cs.transferBooks(currentCart.getCartId());
		cs.deleteAllFromCart(currentCart.getCartId());
		ModelAndView mav = new ModelAndView("redirect:/ownedbooks");
		return mav;
	}
	
	@GetMapping("/ownedbooks")
	public ModelAndView ownedBooks(){ //used to display current user's owned books
		if(CurrentUser.getCurrentUser() == null) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}else {			
			List<Book> aBooks = CurrentUser.getCurrentUser().getUserBooks();
			String uName = CurrentUser.getCurrentUser().getFirstName();
			ModelAndView mav = new ModelAndView("ownedbooks");
			mav.addObject("aBooks", aBooks);
			mav.addObject("uName", uName);
			return mav;
		}
	}
	
	@GetMapping("/changepassword")
	public ModelAndView changePassword(){ //displays the page to be used when current user wishes to change password
		if(CurrentUser.getCurrentUser() == null) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}else {			
			ModelAndView mav = new ModelAndView("changepassword");
			return mav;
		}
	}
	
	@RequestMapping(value="/newpassword", method=RequestMethod.POST)
	public ModelAndView newPassword(@RequestParam("oldpassword") String oldpassword,
			@RequestParam("password") String password) { //postmapping which is used when changing the current user's password
		if(!oldpassword.equals(CurrentUser.getCurrentUser().getPassword())) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}else {
			System.out.println(CurrentUser.getCurrentUser().getEmail());
			System.out.println("oldpassword entered: " + oldpassword + " curruser password: " + CurrentUser.getCurrentUser().getPassword() + " new password: " + password);
			userServices us = new userServices();
			us.changeUserPassword(password);
			CurrentUser.setCurrentUser(null);
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(){ //logs out current user & redirects them to index page
		if(CurrentUser.getCurrentUser() == null) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}else {
			CurrentUser.setCurrentUser(null);
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}
	}
}
