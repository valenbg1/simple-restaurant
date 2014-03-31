package pad.practica1.restaurante;

import android.app.Application;

public class RestaurantApplication extends Application {
	private Restaurant stuff;

	public Restaurant getStuff() {
		return stuff;
	}

	public void setStuff(Restaurant stuff) {
		this.stuff = stuff;
	}
	

}
