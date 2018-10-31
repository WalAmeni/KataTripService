package net.dammak.kata.tripservice.trip;

import net.dammak.kata.tripservice.user.User;

public class UserBuilder {
	
	private User[] friends =new User[]{};
	private Trip[] trips = new Trip[] {};

	public static UserBuilder aUser() {
		// TODO Auto-generated method stub
		return new UserBuilder();
	}

	public  UserBuilder friendsWith(User... friends) {
		
		this.friends =friends;
		return this;
	}

	public UserBuilder withTrips(Trip... trips) {
		this.trips= trips;
		return this;
	}

	public User build() {
		User user = new User();
		for(User friend :friends) {	
			user.addFriend(friend);
		}
		for(Trip trip :trips) {	
			user.addTrip(trip);
		}
		return user;
	}

}
