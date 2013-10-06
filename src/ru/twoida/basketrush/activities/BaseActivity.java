package ru.twoida.basketrush.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

public abstract class BaseActivity extends Activity {
	
	protected final static String PREFERENCES_NAME = "BasketRush";
	
	protected final static String PHONE_NUMBER = "phone_number";
	protected final static String GENDER = "gender";
	protected final static String PARTNER_ID = "partner_id";
	
	protected SharedPreferences settings = null;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
	}
}
