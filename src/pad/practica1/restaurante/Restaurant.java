package pad.practica1.restaurante;

import java.util.Locale;
import android.annotation.SuppressLint;
import android.content.Context;


public class Restaurant {
	public enum RestaurantAttr {
		NAME, ADDRESS, ROAD_TYPE, ADDRESS_NUM, COUSSINE, FOOD_TYPE, 
		COUNTRY, TOWN, PRICE
	}
	
	private static final int NUMBER_ERROR_LIMIT = 10;
	private static final int PRICE_ERROR_LIMIT = 10;
	
	private String address;
	private String country;
	private String coussine;
	private String foodType;
	private String name;
	private int number;
	private double price;
	private String roadType;
	private String town;
	
	private String default_value;
	public Restaurant(){
		super();
		this.address = "";
		this.country = "";
		this.coussine = "";
		this.foodType = "";
		this.name = "";
		this.number = -1;
		this.price = -1;
		this.roadType = "";
		this.town = "";
		this.default_value = "";
		
	}
	
	public Restaurant(Context context, String name, String foodType, String coussine, String roadType,
			String address, int number, String town, String country, double price) {
		
		super();
		this.address = address;
		this.country = country;
		this.coussine = coussine;
		this.foodType = foodType;
		this.name = name;
		this.number = number;
		this.price = price;
		this.roadType = roadType;
		this.town = town;
		
		this.default_value = context.getResources().getString(R.string.default_value);
	}
	
	public boolean match(String address, String country, String coussine,
			String foodType, String name, int number, double price,
			String roadType, String town) {
		if ((!address.equals("")) && (!address.equalsIgnoreCase(this.address)))
			return false;
		
		if ((!country.equals("")) && (!country.equalsIgnoreCase(this.country)))
			return false;
		
		if ((!coussine.equals(default_value)) && (!coussine.equalsIgnoreCase(this.coussine)))
			return false;
		
		if ((!foodType.equals(default_value)) && (!foodType.equalsIgnoreCase(this.foodType)))
			return false;
		
		if ((!name.equals("")) && (!name.equalsIgnoreCase(this.name)))
			return false;
		
		if ((!town.equals("")) && (!town.equalsIgnoreCase(this.town)))
			return false;
		
		if ((!roadType.equals(default_value)) && (!roadType.equalsIgnoreCase(this.roadType)))
			return false;
		
		if ((number > 0) && (Math.abs(number - this.number) > NUMBER_ERROR_LIMIT))
			return false;
		
		if ((price > 0) && (Math.abs(price - this.price) > PRICE_ERROR_LIMIT))
			return false;
		
		return true;
	}
	
	public boolean match(Restaurant r) {
		if ((!r.address.equals("")) && (!r.address.equalsIgnoreCase(this.address)))
			return false;
		
		if ((!r.country.equals("")) && (!r.country.equalsIgnoreCase(this.country)))
			return false;
		
		if ((!r.coussine.equals(default_value)) && (!r.coussine.equalsIgnoreCase(this.coussine)))
			return false;
		
		if ((!r.foodType.equals(default_value)) && (!r.foodType.equalsIgnoreCase(this.foodType)))
			return false;
		
		if ((!r.name.equals("")) && (!r.name.equalsIgnoreCase(this.name)))
			return false;
		
		if ((!r.town.equals("")) && (!r.town.equalsIgnoreCase(this.town)))
			return false;
		
		if ((!r.roadType.equals(default_value)) && (!r.roadType.equalsIgnoreCase(this.roadType)))
			return false;
		
		if ((r.number > 0) && (Math.abs(r.number - this.number) > NUMBER_ERROR_LIMIT))
			return false;
		
		if ((r.price > 0) && (Math.abs(r.price - this.price) > PRICE_ERROR_LIMIT))
			return false;
		
		return true;
	}
	
	@SuppressLint("DefaultLocale")
	public void setAttribute(String attr, String value){
		switch (RestaurantAttr.valueOf(attr.toUpperCase(Locale.ENGLISH))) {
		case ADDRESS:
			this.setAddress(value);
			break;
		case ADDRESS_NUM:
			this.setNumber(Integer.valueOf(value));
			break;
		case COUNTRY:
			this.setCountry(value);
			break;
		case COUSSINE:
			this.setCoussine(value);
			break;
		case FOOD_TYPE:
			this.setFoodType(value);
			break;
		case NAME:
			this.setName(value);
			break;
		case PRICE:
			this.setPrice(Double.valueOf(value));
			break;
		case ROAD_TYPE:
			this.setRoadType(value);
			break;
		case TOWN:
			this.setTown(value);
			break;
		}  
		
	}

	public String getAddress() {
		return address;
	}
	
	public String getFullAddress() {
		return roadType+" "+address+" "+number+", "+town;
	}

	public String getCountry() {
		return country;
	}

	public String getCoussine() {
		return coussine;
	}

	public String getFoodType() {
		return foodType;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public double getPrice() {
		return price;
	}

	public String getRoadType() {
		return roadType;
	}

	public String getTown() {
		return town;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCoussine(String coussine) {
		this.coussine = coussine;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public void setTown(String town) {
		this.town = town;
	}
}