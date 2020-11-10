package com.jameswasher.bookpublisher.currentUser;

import com.jameswasher.bookpublisher.models.User;

public class CurrentUser { //this class stores the current user after login, when they logout this object will be null again
	private static User curr = null;
	public static User getCurrentUser() {
		return curr;
	}
	public static void setCurrentUser(User u) {
		curr = u;
	}
}
