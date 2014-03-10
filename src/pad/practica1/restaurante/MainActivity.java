package pad.practica1.restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {
	
	
	
	private Spinner foodTypeSpinner;
	private Spinner typeRoadSpinner;
	private Spinner coussineSpinner;
	private AutoCompleteTextView countryAutoText;
	private EditText nameText;
	private EditText numberText;
	private EditText addressText;
	private EditText priceText;
	private ImageButton searchButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		foodTypeSpinner = (Spinner) findViewById(R.id.foodTypeSpinner);
		typeRoadSpinner = (Spinner) findViewById(R.id.typeRoadSpinner);
		coussineSpinner = (Spinner) findViewById(R.id.coussineSpinner);
		countryAutoText = (AutoCompleteTextView) findViewById(R.id.countryAutoText);
		nameText = (EditText) findViewById(R.id.nameText);
		numberText = (EditText) findViewById(R.id.numberText);
		addressText = (EditText) findViewById(R.id.addressText);
		priceText = (EditText) findViewById(R.id.priceText);
		searchButton = (ImageButton) findViewById(R.id.searchButton);
		
		String[] countries = getResources().getStringArray(R.array.array_country);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		countryAutoText.setAdapter(adapter);
		
		searchButton.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ResultsActivity.class);
		
		
		intent.putExtra(IntentExtras.FOOD_TYPE.toString(), foodTypeSpinner.getSelectedItem().toString());
		
		startActivity(intent);
		
		
	}

}
