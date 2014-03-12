package pad.practica1.restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {
	public enum IntentExtras {
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		foodTypeSpinner = (Spinner) findViewById(R.id.foodTypeSpinner);
		roadTypeSpinner = (Spinner) findViewById(R.id.typeRoadSpinner);
		coussineSpinner = (Spinner) findViewById(R.id.coussineSpinner);
		countryAutoText = (AutoCompleteTextView) findViewById(R.id.countryAutoText);
		nameText = (EditText) findViewById(R.id.nameText);
		numberText = (EditText) findViewById(R.id.numberText);
		addressText = (EditText) findViewById(R.id.addressText);
		priceText = (EditText) findViewById(R.id.priceText);
		townText = (EditText) findViewById(R.id.townText);
		searchButton = (ImageButton) findViewById(R.id.searchButton);
		
		String[] countries = getResources().getStringArray(R.array.array_country);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		countryAutoText.setAdapter(adapter);
		
		searchButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ResultsActivity.class);
		
		int price = priceText.getText().toString().equals("") ? -1 : Integer.valueOf(priceText.getText().toString()),
				number = numberText.getText().toString().equals("") ? -1 : Integer.valueOf(numberText.getText().toString());
				
		intent.putExtra(IntentExtras.FOOD_TYPE.toString(), foodTypeSpinner.getSelectedItem().toString());
		intent.putExtra(IntentExtras.ROAD_TYPE.toString(), roadTypeSpinner.getSelectedItem().toString());
		intent.putExtra(IntentExtras.COUSSINE.toString(), coussineSpinner.getSelectedItem().toString());
		intent.putExtra(IntentExtras.COUNTRY.toString(), countryAutoText.getText());
		intent.putExtra(IntentExtras.NAME.toString(), nameText.getText());
		intent.putExtra(IntentExtras.ADDRESS_NUM.toString(), number);
		intent.putExtra(IntentExtras.ADDRESS.toString(), addressText.getText());
		intent.putExtra(IntentExtras.PRICE.toString(), price);
		intent.putExtra(IntentExtras.TOWN.toString(), townText.getText());
		
		startActivity(intent);
	}
}