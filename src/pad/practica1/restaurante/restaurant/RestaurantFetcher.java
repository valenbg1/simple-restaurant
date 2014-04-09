package pad.practica1.restaurante.restaurant;

import java.util.ArrayList;

import android.content.Context;

public interface RestaurantFetcher {
	public ArrayList<Restaurant> getRestaurants(Context context);
}