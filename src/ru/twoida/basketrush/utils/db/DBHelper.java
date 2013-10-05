package ru.twoida.basketrush.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "BASKET_RUSH";
	
	private static final String[] DB_CREATE_QUERIES = {};
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		for (final String qry : DB_CREATE_QUERIES) {
			db.execSQL(qry);
		}
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
