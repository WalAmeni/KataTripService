package net.dammak.kata.tripservice.user;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import net.dammak.kata.tripservice.trip.UserBuilder;

public class UserTest {

	
	private static final User BOB = new User();
	private static final User PAUL = new User();

	@Test public void 
	should_inform_when_users_are_not_friends() {
		User user =UserBuilder.aUser()
				.friendsWith(BOB).build();
		assertThat(user.isFriendWith(PAUL), is(false));
	}
	
	@Test public void 
	should_inform_when_users_are_friends() {
		User user =UserBuilder.aUser()
				.friendsWith(BOB, PAUL).build();
		assertThat(user.isFriendWith(PAUL), is(true));
	}
}
