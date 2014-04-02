package pad.practica1.restaurante;

import java.util.ArrayList;

import android.app.Application;

public class RestaurantApplication extends Application {
	private Restaurant restaurant = null;
	private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

	public Restaurant getCurrentRestaurant() {
		return restaurant;
	}

	public void setCurrentRestaurant(Restaurant stuff) {
		this.restaurant = stuff;
	}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	

}
