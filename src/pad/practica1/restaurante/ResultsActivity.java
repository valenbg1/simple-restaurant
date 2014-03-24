package pad.practica1.restaurante;

import java.util.ArrayList;

import pad.practica1.restaurante.MainActivity.MainActivityElements;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		final Restaurant[] restaurants = {
				new Restaurant(this, "Restaurante1", "tradicional" , "española", "calle", "Falsa", 123, "Madrid", "Madrid", 20.0),
				new Restaurant(this, "Restaurante12", "tradicional" , "española", "calle", "Falsa", 124, "Madrid", "Madrid", 10.0),
				new Restaurant(this, "Restaurante13", "creativa" , "india", "avenida", "La Falsa", 23, "Madrid", "Madrid", 25.0),
				new Restaurant(this, "Restaurante14", "tradicional" , "española", "calle", "Falsa", 125, "Madrid", "Madrid", 27.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
				new Restaurant(this, "Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
		};

		Intent intent = getIntent();
		
		String address = intent.getStringExtra(MainActivityElements.ADDRESS.toString()),
				country = intent.getStringExtra(MainActivityElements.COUNTRY.toString()),
				coussine = intent.getStringExtra(MainActivityElements.COUSSINE.toString()),
				foodType = intent.getStringExtra(MainActivityElements.FOOD_TYPE.toString()),
				name = intent.getStringExtra(MainActivityElements.NAME.toString()),
				roadType = intent.getStringExtra(MainActivityElements.ROAD_TYPE.toString()),
				town = intent.getStringExtra(MainActivityElements.TOWN.toString());
		int number = intent.getIntExtra(MainActivityElements.ADDRESS_NUM.toString(), -1);
		double price = intent.getDoubleExtra(MainActivityElements.PRICE.toString(), -1);

		ListView listview = (ListView) findViewById(R.id.resultsListViews);
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		for (int i = 0; i < restaurants.length; ++i) {
			if (restaurants[i].match(address, country, coussine, foodType, name, number, price, roadType, town))
				list.add(restaurants[i]);
		}
		
		listview.setAdapter(new RestaurantAdapter(this, android.R.layout.simple_list_item_1, list));
	}
	
	public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
		private ArrayList<Restaurant> items;
		private LayoutInflater inflater;
		
		public RestaurantAdapter(Context context, int resource, ArrayList<Restaurant> items) {
			super(context, resource, items);
			this.items = items;
			this.inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			if (convertView == null)
				vi = inflater.inflate(R.layout.list_restaurant_item, null);
			
			TextView name = (TextView) vi.findViewById(R.id.nameResult);
			TextView address = (TextView) vi.findViewById(R.id.addressResult);
			TextView coussine = (TextView) vi.findViewById(R.id.coussineResult);
			TextView price = (TextView) vi.findViewById(R.id.priceResult);
			
			Restaurant item = items.get(position);
			
			if (item != null) {
				name.setText(item.getName());
				address.setText(item.getFullAddress());
				coussine.setText(item.getCoussine());
				price.setText(item.getPrice()+"€");
			}
			
			return vi;
		}
	}
}