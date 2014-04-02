package pad.practica1.restaurante;

import java.util.ArrayList;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;

import android.app.ActionBar.LayoutParams;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultsActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final ArrayList<Restaurant> restaurants = getRestaurants();

		// Create a progress bar to display while the list loads
		ProgressBar progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, Gravity.CENTER));
		progressBar.setIndeterminate(true);
		getListView().setEmptyView(progressBar);

		// Must add the progress bar to the root of the layout
		ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
		root.addView(progressBar);

		RestaurantApplication app = (RestaurantApplication) getApplication();
		Restaurant stuff = app.getCurrentRestaurant();

		ListView listview = getListView();
		ArrayList<Restaurant> list = app.getRestaurants();
		
		if (list.isEmpty()) {
			Iterator<Restaurant> it = restaurants.iterator();
			while (it.hasNext()){
				if(!it.next().match(stuff))
					it.remove();
			}
			
			list = restaurants;
		}

		listview.setAdapter(new RestaurantAdapter(this,
				android.R.layout.simple_list_item_1, list));
	}

	private ArrayList<Restaurant> getRestaurants() {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		XmlPullParser parser = this.getResources().getXml(R.xml.restaurants_db);
		try {
			while (parser.next() != XmlPullParser.END_DOCUMENT) {
				String name = parser.getName();
				if ((name != null) && name.equals("restaurant") && parser.getEventType() != XmlPullParser.END_TAG) {
					int size = parser.getAttributeCount();
					Restaurant restaurant = new Restaurant();
					for (int i = 0; i < size; i++) {
						String attrName = parser.getAttributeName(i);
						String attrValue = parser.getAttributeValue(i);
						
						restaurant.setAttribute(attrName, attrValue);
					}
										
					restaurants.add(restaurant);
				}
			}
		} catch (Exception e) {
			Log.e("ReadXMLResourceFile", e.getMessage(), e);
		}

		return restaurants;
		// return new Restaurant[]{
		// new Restaurant(this, "Restaurante1", "tradicional" , "española",
		// "calle", "Falsa", 123, "Madrid", "Madrid", 20.0),
		// new Restaurant(this, "Restaurante12", "tradicional" , "española",
		// "calle", "Falsa", 124, "Madrid", "Madrid", 10.0),
		// new Restaurant(this, "Restaurante13", "creativa" , "india",
		// "avenida", "La Falsa", 23, "Madrid", "Madrid", 25.0),
		// new Restaurant(this, "Restaurante14", "tradicional" , "española",
		// "calle", "Falsa", 125, "Madrid", "Madrid", 27.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// new Restaurant(this, "Restaurante15", "creativa" , "china", "calle",
		// "Falsa", 122, "Madrid", "Madrid", 15.0),
		// };
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		RestaurantApplication app = (RestaurantApplication) getApplication();
		RestaurantAdapter adapter = (RestaurantAdapter) l.getAdapter();

		app.setCurrentRestaurant(adapter.getRestaurant(position));

		Intent i = new Intent(this, RestaurantDetailActivity.class);
		startActivity(i);
	}

	private class RestaurantAdapter extends ArrayAdapter<Restaurant> {
		private ArrayList<Restaurant> items;
		private LayoutInflater inflater;

		public RestaurantAdapter(Context context, int resource,
				ArrayList<Restaurant> items) {
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
				price.setText(item.getPrice() + "€");
			}

			return vi;
		}

		public Restaurant getRestaurant(int i) {
			return items.get(i);
		}
	}
}