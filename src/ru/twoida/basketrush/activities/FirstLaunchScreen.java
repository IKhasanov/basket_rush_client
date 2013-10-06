package ru.twoida.basketrush.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ru.twoida.basket_rush_client.R;

public class FirstLaunchScreen extends BaseActivity implements OnClickListener {
	
	Button btnSelectYourSelf;
	
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_launch_screen);
		btnSelectYourSelf = (Button) findViewById(R.id.btnMale);
		btnSelectYourSelf.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		    case R.id.btnMale:
		    	Intent intent = new Intent(this, InviteScreen.class);
		        startActivity(intent);
		      break;
		    default:
		      break;
		    }
	}
	
	
	
}
