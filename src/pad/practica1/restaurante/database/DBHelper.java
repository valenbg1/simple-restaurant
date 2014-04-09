package pad.practica1.restaurante.database;

import pad.practica1.restaurante.restaurant.Restaurant;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper {
	public static final String DB_TABLE = "Restaurant";
	public static final String DB_NAME = "Restaurants";
	public static final int DB_VERSION = 1;
	public static final String CLASSNAME = DBHelper.class.getName();
	private static final String[] COLS = new String[] { "_id", "name",
			"address", "country", "coussine", "food_type", "address_num",
			"price", "road_type", "town" };
	private SQLiteDatabase db;
	private final DBOpenHelper dbOpenHelper;

	public DBHelper(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context, "WR_DATA", 1);
		this.establishDb();
	}

	private void establishDb() {
		if (this.db == null) {
			this.db = this.dbOpenHelper.getWritableDatabase();
		}
	}

	public void cleanup() {
		if (this.db != null) {
			this.db.close();
			this.db = null;
		}
	}
	
	public void insert(Restaurant restaurant) {
		ContentValues values = new ContentValues();
		int i = 1;
		
		values.put(COLS[i++], restaurant.getName());
		values.put(COLS[i++], restaurant.getAddress());
		values.put(COLS[i++], restaurant.getCountry());
		values.put(COLS[i++], restaurant.getCoussine());
		values.put(COLS[i++], restaurant.getFoodType());
		values.put(COLS[i++], restaurant.getNumber());
		values.put(COLS[i++], restaurant.getPrice());
		values.put(COLS[i++], restaurant.getRoadType());
		values.put(COLS[i++], restaurant.getTown());
		this.db.insert(DBHelper.DB_TABLE, null, values);
	}

	public void update(Restaurant restaurant) {
//		TODO
//		ContentValues values = new ContentValues();
//		//values.put("zip", restaurant.zip);
//		this.db.update(DBHelper.DB_TABLE, values, "_id=" + restaurant.getId(), null);
	}

	public void delete(long id) {
		this.db.delete(DBHelper.DB_TABLE, "_id=" + id, null);
	}

	private static class DBOpenHelper extends SQLiteOpenHelper {
		private static final String DB_CREATE =
				"CREATE TABLE "+ DBHelper.DB_TABLE
				+ " (_id INTEGER PRIMARY KEY,"
				+ "name TEXT NOT NULL,"
				+ "address TEXT NOT NULL,"
				+ "country TEXT NOT NULL,"
				+ "coussine TEXT NOT NULL, "
				+ "food_type TEXT NOT NULL, "
				+ "address_num INTEGER, "
				+ "price REAL, "
				+ "road_type TEXT NOT NULL, "
				+ "town TEXT NOT NULL);";

		public DBOpenHelper(Context context, String dbName, int version) {
			super(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DBOpenHelper.DB_CREATE);
			} catch (SQLException e) {
				Log.e("Database", DBHelper.CLASSNAME, e);
			}
		}

		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DBHelper.DB_TABLE);
			this.onCreate(db);
		}
	}

}