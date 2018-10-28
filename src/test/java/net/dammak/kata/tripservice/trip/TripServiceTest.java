package net.dammak.kata.tripservice.trip;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import net.dammak.kata.tripservice.exception.*;
import net.dammak.kata.tripservice.trip.TripService;
import net.dammak.kata.tripservice.user.User;

public class TripServiceTest {

	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTRED_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRASIL = new Trip();
	private static final Trip TO_LONDON = new Trip();
	private User loggedInUser;
	private TripService tripService;

	@Before
	public void initialize() {
		tripService = new TestableTripService();
		
	}
	
	@Test(expected = UserNotLoggedInException.class) 
	public void should_throw_an_exception_when_user_is_not_logged_in(){
		
		loggedInUser = GUEST;
		tripService.getTripsByUser(UNUSED_USER); 
	}

	@Test public void 
	should_not_return_any_tips_when_users_are_not_friends() {
		
		loggedInUser = REGISTRED_USER;

		
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		
		friend.addTrip(TO_BRASIL);
        

		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		assertThat(friendTrips.size(),is(0));
	}
	
	@Test public void 
	should_return_friend_trips_when_users_are_friends() {
		loggedInUser = REGISTRED_USER;
		
		User friend = UserBuilder.aUser()
		              .friendsWith(loggedInUser, ANOTHER_USER)
		              .withTrips(TO_BRASIL, TO_LONDON)
		              .Build();
		//User friend = new User();
		
		//friend.addFriend(loggedInUser);
		//friend.addFriend(ANOTHER_USER);
		//friend.addTrip(TO_BRASIL);
		//friend.addTrip(TO_LONDON);
        

		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		assertThat(friendTrips.size(),is(2));

	}

	private class TestableTripService extends TripService {

		@Override
		protected User getLoggedInUser() {
			// TODO Auto-generated method stub
			return loggedInUser;
		}

		@Override
		protected List<Trip> tripsByUser(User user) {
			return user.trips();
		}

		
		
	}

}
