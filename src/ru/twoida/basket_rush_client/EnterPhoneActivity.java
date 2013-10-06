package ru.twoida.basket_rush_client;

import ru.twoida.basket_rush.models.User;
import ru.twoida.basketrush.activities.BaseActivity;
import ru.twoida.basketrush.activities.FirstLaunchScreen;
import android.os.Bundle;
import android.os.Handler;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class EnterPhoneActivity extends BaseActivity {
	private Handler mHandler = new Handler();
	EditText etPhoneNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_phone);
		etPhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
		showInputMethod();
		etPhoneNumber.setSelection(etPhoneNumber.getText().length());
		etPhoneNumber.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				if ((arg2.getAction() == KeyEvent.ACTION_DOWN) &&
			            (arg1 == KeyEvent.KEYCODE_ENTER)) {
			          // Perform action on key press
					SharedPreferences.Editor editor =  EnterPhoneActivity.this.settings.edit();
					editor.putString(User.LOGIN, etPhoneNumber.getText().toString());
					editor.commit();
					
			    	Intent intent = new Intent(EnterPhoneActivity.this, FirstLaunchScreen.class);
			        startActivity(intent);
			        hideInputMethod();
			          return true;
			        }
				
				hideInputMethod();
				return false;
				
			}
			
		});
		//hideInputMethod();
		//etPhoneNumber.requestFocus();
		//InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		//imm.toggleSoftInput(etPhoneNumber, InputMethodManager.);
		
	}
	
	protected void hideInputMethod() {
	    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    if (imm != null) {
	        imm.hideSoftInputFromWindow(etPhoneNumber.getWindowToken(), 0);
	    }
	}

	/**
	 * показываем программную клавиатуру
	 */
	protected void showInputMethod() {
	    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    if (imm != null) {
	    	imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	    }
	}

	private Runnable mShowInputMethodTask = new Runnable() {
	    public void run() {
	        showInputMethod();
	    }
	};

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    super.onWindowFocusChanged(hasFocus);
	    if (hasFocus) {
	        // если окно в фокусе, то ждем еще немного и показываем клавиатуру
	        mHandler.postDelayed(mShowInputMethodTask, 0);
	    }
	    else 
	    {
	    	hideInputMethod();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_phone, menu);
		return true;
	}

}
