package ru.twoida.basketrush.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ru.twoida.basket_rush_client.EnterPhoneActivity;
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
		SharedPreferences.Editor editor =  FirstLaunchScreen.this.settings.edit();
		 switch (v.getId()) {
		    case R.id.btnMale:
				editor.putString(GENDER, "male");
				editor.commit();
		    	
		    	Intent intentMale = new Intent(this, InviteScreen.class);
		        startActivity(intentMale);
		      break;
		    case R.id.btnFemale:
				editor.putString(GENDER, "female");
				editor.commit();

		    	Intent intentFemale = new Intent(this, InviteScreen.class);
		    	startActivity(intentFemale);
		    default:
		      break;
		    }
	}
	
	
	
}
