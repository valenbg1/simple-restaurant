package pad.practica1.restaurante;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
		intent.putExtra(MainActivityElements.COUNTRY.toString(), countryAutoText.getText());
		intent.putExtra(MainActivityElements.NAME.toString(), nameText.getText());
		intent.putExtra(MainActivityElements.ADDRESS_NUM.toString(), number);
		intent.putExtra(MainActivityElements.ADDRESS.toString(), addressText.getText());
		intent.putExtra(MainActivityElements.PRICE.toString(), price);
		intent.putExtra(MainActivityElements.TOWN.toString(), townText.getText());
		
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
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
		
		String[] countries = Locale.getISOCountries();
		 
		for (int i = 0; i < countries.length; ++i)
			countries[i] = new Locale("", countries[i]).getDisplayCountry();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		countryAutoText.setAdapter(adapter);
		
		restoreState();
		
		searchButton.setOnClickListener(this);
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		
		saveState();
	};
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		restoreState();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		restoreState();
	}
	
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
				priceText.setText(String.format("%.2f", price));
		}
	}

	private void saveState() {
		SharedPreferences.Editor savedState = getPreferences(MODE_PRIVATE).edit();
		
		int number;
		double price;
		
		try {
			price = priceText.getText().toString().equals("") ? -1.0 : Double.valueOf(priceText.getText().toString());
			number = numberText.getText().toString().equals("") ? -1 : Integer.valueOf(numberText.getText().toString());
		} catch (NumberFormatException ex) {
			price = -1.0;
			number = -1;
		}
		
		savedState.putInt(MainActivityElements.FOOD_TYPE.toString(), foodTypeSpinner.getSelectedItemPosition());
		savedState.putInt(MainActivityElements.ROAD_TYPE.toString(), roadTypeSpinner.getSelectedItemPosition());
		savedState.putInt(MainActivityElements.COUSSINE.toString(), coussineSpinner.getSelectedItemPosition());
		savedState.putString(MainActivityElements.COUNTRY.toString(), countryAutoText.getText().toString());
		savedState.putString(MainActivityElements.NAME.toString(), nameText.getText().toString());
		savedState.putInt(MainActivityElements.ADDRESS_NUM.toString(), number);
		savedState.putString(MainActivityElements.ADDRESS.toString(), addressText.getText().toString());
		savedState.putFloat(MainActivityElements.PRICE.toString(), (float) price);
		savedState.putString(MainActivityElements.TOWN.toString(), townText.getText().toString());
		
		savedState.commit();
	}
}