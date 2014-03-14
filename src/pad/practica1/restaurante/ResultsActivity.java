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
		new Restaurant("Verdadera", "España", "española", "tradicional", "El falso", 123, 21.50, "Calle", "Madrid"),
		new Restaurant("Falsa", "España", "española", "tradicional", "El falso 2", 124, 11.50, "Calle", "Madrid"),
		new Restaurant("Verdadera", "España", "china", "tradicional", "El falso 3", 125, 1.50, "Avenida", "Badajoz"),
		new Restaurant("Falsa", "México", "japonesa", "creativa", "El falso 4", 146, 15.50, "Calle", "Sevilla")
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