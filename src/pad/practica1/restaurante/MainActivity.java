package pad.practica1.restaurante;

import java.util.ArrayList;
import java.util.Locale;

import pad.practica1.restaurante.Restaurant.RestaurantAttr;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {
	
	private Spinner foodTypeSpinner;
	private Spinner roadTypeSpinner;
	private Spinner coussineSpinner;
	private AutoCompleteTextView countryAutoText;
	private EditText nameText;
	private EditText numberText;
	private EditText addressText;
	private EditText priceText;
	private EditText townText;
	private ImageButton searchButton;
	
	private ArrayAdapter<String> countries_adapter;
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ResultsActivity.class);
		
		int number;
		double price;
		
		try {
			price = Double.valueOf(priceText.getText().toString());
		} catch (NumberFormatException ex) {
			price = -1.0;
		}
		try{
			number = Integer.valueOf(numberText.getText().toString());
		} catch (NumberFormatException ex) {
			number = -1;
		}
		
		RestaurantApplication app = (RestaurantApplication) getApplication();
		Restaurant stuff = new Restaurant(this);
		
		// Vease flush restaurants (Fdo. Valen)
		app.setRestaurants(new ArrayList<Restaurant>());		
		
		stuff.setFoodType(foodTypeSpinner.getSelectedItem().toString());
		stuff.setRoadType(roadTypeSpinner.getSelectedItem().toString());
		stuff.setCoussine(coussineSpinner.getSelectedItem().toString());
		stuff.setCountry(countryAutoText.getText().toString());
		stuff.setName(nameText.getText().toString());
		stuff.setNumber(number);
		stuff.setAddress(addressText.getText().toString());
		stuff.setPrice(price);
		stuff.setTown(townText.getText().toString());
		
		app.setCurrentRestaurant(stuff);
		
		startActivity(intent);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		saveState();
		setOrientationView(newConfig.orientation);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setOrientationView(this.getResources().getConfiguration().orientation);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		saveState();
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		restoreState();
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		
		restoreState();
	};
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		
		saveState();
	}
	
	private void restoreState() {
		SharedPreferences savedState = getPreferences(MODE_PRIVATE);
		
		if (savedState != null) {
			int number;
			double price;
			
			foodTypeSpinner.setSelection(savedState.getInt(RestaurantAttr.FOOD_TYPE.toString(), 0));
			roadTypeSpinner.setSelection(savedState.getInt(RestaurantAttr.ROAD_TYPE.toString(), 0));
			coussineSpinner.setSelection(savedState.getInt(RestaurantAttr.COUSSINE.toString(), 0));
			countryAutoText.setText(savedState.getString(RestaurantAttr.COUNTRY.toString(), ""));
			addressText.setText(savedState.getString(RestaurantAttr.ADDRESS.toString(), ""));
			number = savedState.getInt(RestaurantAttr.ADDRESS_NUM.toString(), -1);
			nameText.setText(savedState.getString(RestaurantAttr.NAME.toString(), ""));
			price = savedState.getFloat(RestaurantAttr.PRICE.toString(), -1.0f);
			townText.setText(savedState.getString(RestaurantAttr.TOWN.toString(), ""));
			
			if (number != -1)
				numberText.setText(Integer.toString(number));
			
			if (price != -1.0)
				priceText.setText(String.format(Locale.US, "%.2f", price));
		}
	}
	
	private void saveState() {
		SharedPreferences.Editor savedStateEdit = getPreferences(MODE_PRIVATE).edit();
		
		int number;
		double price;
		
		try {
			price = Double.valueOf(priceText.getText().toString());
		} catch (NumberFormatException ex) {
			price = -1.0;
		}
		
		try {
			number = Integer.valueOf(numberText.getText().toString());
		} catch (NumberFormatException ex) {
			number = -1;
		}
		
		Log.i("saveState","Valor de price , num: "+price+", "+number);
		
		savedStateEdit.putInt(RestaurantAttr.FOOD_TYPE.toString(), foodTypeSpinner.getSelectedItemPosition());
		savedStateEdit.putInt(RestaurantAttr.ROAD_TYPE.toString(), roadTypeSpinner.getSelectedItemPosition());
		savedStateEdit.putInt(RestaurantAttr.COUSSINE.toString(), coussineSpinner.getSelectedItemPosition());
		savedStateEdit.putString(RestaurantAttr.COUNTRY.toString(), countryAutoText.getText().toString());
		savedStateEdit.putString(RestaurantAttr.NAME.toString(), nameText.getText().toString());
		savedStateEdit.putInt(RestaurantAttr.ADDRESS_NUM.toString(), number);
		savedStateEdit.putString(RestaurantAttr.ADDRESS.toString(), addressText.getText().toString());
		savedStateEdit.putFloat(RestaurantAttr.PRICE.toString(), (float) price);
		savedStateEdit.putString(RestaurantAttr.TOWN.toString(), townText.getText().toString());
		
		savedStateEdit.apply();
	}

	private void setOrientationView(int orientation) {
		setContentView(R.layout.activity_main);
		
		setViews();
	}

	private void setViews() {
		foodTypeSpinner = (Spinner) findViewById(R.id.foodTypeSpinner);
		roadTypeSpinner = (Spinner) findViewById(R.id.typeRoadSpinner);
		coussineSpinner = (Spinner) findViewById(R.id.coussineSpinner);
		nameText = (EditText) findViewById(R.id.nameText);
		numberText = (EditText) findViewById(R.id.numberText);
		addressText = (EditText) findViewById(R.id.addressText);
		priceText = (EditText) findViewById(R.id.priceText);
		townText = (EditText) findViewById(R.id.townText);
		searchButton = (ImageButton) findViewById(R.id.searchButton);
		countryAutoText = (AutoCompleteTextView) findViewById(R.id.countryAutoText);
		
		if (countries_adapter == null) {
			String[] countries = Locale.getISOCountries();
			 
			for (int i = 0; i < countries.length; ++i)
				countries[i] = new Locale("", countries[i]).getDisplayCountry();
	
			countries_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		}
		
		countryAutoText.setAdapter(countries_adapter);
		
		searchButton.setOnClickListener(this);
		
		restoreState();
	}
}