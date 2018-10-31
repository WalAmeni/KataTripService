package net.dammak.kata.tripservice.trip;

import net.dammak.kata.tripservice.exception.UserNotLoggedInException;
import net.dammak.kata.tripservice.user.User;
import net.dammak.kata.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;


public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		//User loggedUser = getLoggedInUser();
		
		if (getLoggedInUser() == null) {
			throw new UserNotLoggedInException();
		}
		//	List<Trip> tripList = new ArrayList<Trip>();

		//if (loggedUser != null) {
		//boolean isFriend = false;
		//for (User friend : user.getFriends()) {
		//	if (friend.equals(loggedUser)) {
		//		isFriend = true;
		//		break;
		//	}
		//}
		//
		//if (user.isFriendWith(loggedUser)) {
		//	return tripsByUser(user);
		//}else {
		//return new ArrayList<Trip>() ;

		return user.isFriendWith(getLoggedInUser())
				? tripsByUser(user)
				: noTrips() ;
	
}


	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}


protected List<Trip> tripsByUser(User user) {
	return TripDAO.findTripsByUser(user);
}

protected User getLoggedInUser() {
	return UserSession.getInstance().getLoggedUser();
}

}
