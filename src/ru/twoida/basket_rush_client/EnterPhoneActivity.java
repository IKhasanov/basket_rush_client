package ru.twoida.basket_rush_client;

import ru.twoida.basketrush.activities.BaseActivity;
import ru.twoida.basketrush.activities.FirstLaunchScreen;
import ru.twoida.basketrush.activities.ListActivity;
import ru.twoida.basketrush.utils.net.BasketRushAPISession;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class EnterPhoneActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_phone);
		
		final EditText etPhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
		etPhoneNumber.setSelection(etPhoneNumber.getText().length());
		
		etPhoneNumber.requestFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(etPhoneNumber, InputMethodManager.SHOW_IMPLICIT);
		
		Button btnAddPhoneNumber = (Button) findViewById(R.id.btnAddPhoneNumber);
		
		btnAddPhoneNumber.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor =  EnterPhoneActivity.this.settings.edit();
				editor.putString(PHONE_NUMBER, etPhoneNumber.getText().toString());
				editor.commit();
				
		    	Intent intent = new Intent(EnterPhoneActivity.this, FirstLaunchScreen.class);
		        startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_phone, menu);
		return true;
	}

}
