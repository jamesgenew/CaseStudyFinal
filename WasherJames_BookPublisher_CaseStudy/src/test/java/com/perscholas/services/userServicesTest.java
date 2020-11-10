package com.perscholas.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.jameswasher.bookpublisher.models.User;
import com.jameswasher.bookpublisher.services.userServices;

public class userServicesTest {
	@Test //unit test to test whether findUser method of userServices class works, if it works then the zip code found should be 01234
	void testFindUser() { 
		String emailExample = "james@email.com"; 
		userServices us = new userServices();
		User foundUser = us.findUser(emailExample);
		assertEquals("01234", foundUser.getZipCode());
	}
	@Test //unit test for findUser method of userServices class, if it works then the address of found user should be "5 main st"
	void testFindUser2() { 
		String emailExample = "steve@email.com"; 
		userServices us = new userServices();
		User foundUser = us.findUser(emailExample);
		assertEquals("5 main st", foundUser.getUserAddress());
	}
}
