package pad.practica1.restaurante;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantDetailActivity extends Activity {
	private TextView foodType;
	private TextView address;
	private TextView coussine;
	private TextView country;
	private TextView name;
	private TextView price;
	private TextView town;
	private ImageView image;
	
	private Restaurant fetchCurrentRestaurant() {
		return new Restaurant(this, "", "", "", "", "", 0, "", "", 0.0);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.restaurant_detail);
		
		foodType = (TextView) findViewById(R.id.detailFoodType);
		address = (TextView) findViewById(R.id.detailAddr);
		coussine = (TextView) findViewById(R.id.detailCoussine);
		country = (TextView) findViewById(R.id.detailCountry);
		name = (TextView) findViewById(R.id.detailName);
		price = (TextView) findViewById(R.id.detailPrice);
		town = (TextView) findViewById(R.id.detailTown);
		image = (ImageView) findViewById(R.id.detailImage);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		setCurrentRestaurant();
	}

	private void setCurrentRestaurant() {
		Restaurant current = fetchCurrentRestaurant();
		
		foodType.setText(current.getFoodType());
		address.setText(current.getFullAddress());
		coussine.setText(current.getCoussine());
		country.setText(current.getCountry());
		name.setText(current.getName());
		price.setText(Double.toString(current.getPrice()));
		town.setText(current.getTown());
	};
}