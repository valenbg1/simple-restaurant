package pad.practica1.restaurante;

public class Restaurant {
	private String address;
	private String country;
	private String coussine;
	private String foodType;
	private String name;
	private int number;
	private double price;
	private String roadType;
	private String town;
	
	public Restaurant(String address, String country, String coussine,
			String foodType, String name, int number, double price,
			String roadType, String town) {
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
	}
	
	public boolean match(String address, String country, String coussine,
			String foodType, String name, int number, double price,
			String roadType, String town) {
		if ((address != null) && (!address.equals("")) && (!address.equalsIgnoreCase(this.address)))
			return false;
		
		if ((country != null) && (!country.equals("")) && (!country.equalsIgnoreCase(this.country)))
			return false;
		
		if ((coussine != null) && (!coussine.equals("")) && (!coussine.equalsIgnoreCase(this.coussine)))
			return false;
		
		if ((foodType != null) && (!foodType.equals("")) && (!foodType.equalsIgnoreCase(this.foodType)))
			return false;
		
		if ((name != null) && (!name.equals("")) && (!name.equalsIgnoreCase(this.name)))
			return false;
		
		if ((town != null) && (!town.equals("")) && (!town.equalsIgnoreCase(this.town)))
			return false;
		
		if ((roadType != null) && (!roadType.equals("")) && (!roadType.equalsIgnoreCase(this.roadType)))
			return false;
		
		if ((number > 0) && (Math.abs(number - this.number) > 10))
			return false;
		
		if ((price > 0) && (Math.abs(price - this.price) > 20))
			return false;
		
		return true;
	}

	public String getAddress() {
		return address;
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