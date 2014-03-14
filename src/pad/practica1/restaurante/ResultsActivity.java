package pad.practica1.restaurante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pad.practica1.restaurante.MainActivity.MainActivityElements;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
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
		double price = intent.getIntExtra(MainActivityElements.PRICE.toString(), -1);

		final ListView listview = (ListView) findViewById(R.id.resultsListViews);

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < restaurants.length; ++i) {
			if (restaurants[i].match(address, country, coussine, foodType, name, number, price, roadType, town))
				list.add(restaurants[i].getName());
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}
}