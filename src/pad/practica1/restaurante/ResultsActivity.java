package pad.practica1.restaurante;

import java.util.LinkedList;
import java.util.List;

import pad.practica1.restaurante.MainActivity.MainActivityElements;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultsActivity extends Activity {
	public static final Restaurant[] restaurants = {
		new Restaurant("Restaurante1", "tradicional" , "española", "calle", "Falsa", 123, "Madrid", "Madrid", 20.0),
		new Restaurant("Restaurante12", "tradicional" , "española", "calle", "Falsa", 124, "Madrid", "Madrid", 10.0),
		new Restaurant("Restaurante13", "creativa" , "india", "avenida", "La Falsa", 23, "Madrid", "Madrid", 25.0),
		new Restaurant("Restaurante14", "tradicional" , "española", "calle", "Falsa", 125, "Madrid", "Madrid", 27.0),
		new Restaurant("Restaurante15", "creativa" , "china", "calle", "Falsa", 122, "Madrid", "Madrid", 15.0),
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

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
		List<String> list = new LinkedList<String>();
		
		for (int i = 0; i < restaurants.length; ++i) {
			if (restaurants[i].match(address, country, coussine, foodType, name, number, price, roadType, town))
				list.add(restaurants[i].getName());
		}
		
		listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}
}