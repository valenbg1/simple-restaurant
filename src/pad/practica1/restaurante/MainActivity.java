package pad.practica1.restaurante;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {
	public enum MainActivityElements {
		NAME, ADDRESS, ROAD_TYPE, ADDRESS_NUM, COUSSINE, FOOD_TYPE, 
		COUNTRY, TOWN, PRICE
	}
	
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
			price = priceText.getText().toString().equals("") ? -1.0 : Double.valueOf(priceText.getText().toString());
			number = numberText.getText().toString().equals("") ? -1 : Integer.valueOf(numberText.getText().toString());
		} catch (NumberFormatException ex) {
			price = -1.0;
			number = -1;
		}
			
		intent.putExtra(MainActivityElements.FOOD_TYPE.toString(), foodTypeSpinner.getSelectedItem().toString());
		intent.putExtra(MainActivityElements.ROAD_TYPE.toString(), roadTypeSpinner.getSelectedItem().toString());
		intent.putExtra(MainActivityElements.COUSSINE.toString(), coussineSpinner.getSelectedItem().toString());
		intent.putExtra(MainActivityElements.COUNTRY.toString(), countryAutoText.getText().toString());
		intent.putExtra(MainActivityElements.NAME.toString(), nameText.getText().toString());
		intent.putExtra(MainActivityElements.ADDRESS_NUM.toString(), number);
		intent.putExtra(MainActivityElements.ADDRESS.toString(), addressText.getText().toString());
		intent.putExtra(MainActivityElements.PRICE.toString(), price);
		intent.putExtra(MainActivityElements.TOWN.toString(), townText.getText().toString());
		
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
			
			foodTypeSpinner.setSelection(savedState.getInt(MainActivityElements.FOOD_TYPE.toString(), 0));
			roadTypeSpinner.setSelection(savedState.getInt(MainActivityElements.ROAD_TYPE.toString(), 0));
			coussineSpinner.setSelection(savedState.getInt(MainActivityElements.COUSSINE.toString(), 0));
			countryAutoText.setText(savedState.getString(MainActivityElements.COUNTRY.toString(), ""));
			addressText.setText(savedState.getString(MainActivityElements.ADDRESS.toString(), ""));
			number = savedState.getInt(MainActivityElements.ADDRESS_NUM.toString(), -1);
			nameText.setText(savedState.getString(MainActivityElements.NAME.toString(), ""));
			price = savedState.getFloat(MainActivityElements.PRICE.toString(), -1.0f);
			townText.setText(savedState.getString(MainActivityElements.TOWN.toString(), ""));
			
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
			price = priceText.getText().toString().equals("") ? -1.0 : Double.valueOf(priceText.getText().toString());
			number = numberText.getText().toString().equals("") ? -1 : Integer.valueOf(numberText.getText().toString());
		} catch (NumberFormatException ex) {
			price = -1.0;
			number = -1;
		}
		
		savedStateEdit.putInt(MainActivityElements.FOOD_TYPE.toString(), foodTypeSpinner.getSelectedItemPosition());
		savedStateEdit.putInt(MainActivityElements.ROAD_TYPE.toString(), roadTypeSpinner.getSelectedItemPosition());
		savedStateEdit.putInt(MainActivityElements.COUSSINE.toString(), coussineSpinner.getSelectedItemPosition());
		savedStateEdit.putString(MainActivityElements.COUNTRY.toString(), countryAutoText.getText().toString());
		savedStateEdit.putString(MainActivityElements.NAME.toString(), nameText.getText().toString());
		savedStateEdit.putInt(MainActivityElements.ADDRESS_NUM.toString(), number);
		savedStateEdit.putString(MainActivityElements.ADDRESS.toString(), addressText.getText().toString());
		savedStateEdit.putFloat(MainActivityElements.PRICE.toString(), (float) price);
		savedStateEdit.putString(MainActivityElements.TOWN.toString(), townText.getText().toString());
		
		savedStateEdit.apply();
	}

	private void setOrientationView(int orientation) {
		if (orientation == Configuration.ORIENTATION_LANDSCAPE)
			setContentView(R.layout.activity_main_hor);
		else
			setContentView(R.layout.activity_main_vert);
		
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