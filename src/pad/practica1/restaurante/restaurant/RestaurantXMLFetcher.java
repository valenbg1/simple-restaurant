package pad.practica1.restaurante.restaurant;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import pad.practica1.restaurante.R;
import pad.practica1.restaurante.R.xml;
import android.content.Context;
import android.util.Log;

public class RestaurantXMLFetcher implements RestaurantFetcher {
	@Override
	public ArrayList<Restaurant> getRestaurants(Context context) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		XmlPullParser parser = context.getResources().getXml(R.xml.restaurants_db);
		try {
			while (parser.next() != XmlPullParser.END_DOCUMENT) {
				String name = parser.getName();
				if ((name != null) && name.equals("restaurant") && parser.getEventType() != XmlPullParser.END_TAG) {
					int size = parser.getAttributeCount();
					Restaurant restaurant = new Restaurant(context);
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
	}
}
