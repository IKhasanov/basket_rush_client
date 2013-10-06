package ru.twoida.basketrush.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ru.twoida.basket_rush.models.User;
import ru.twoida.basket_rush_client.EnterPhoneActivity;
import ru.twoida.basket_rush_client.R;

public class FirstLaunchScreen extends BaseActivity implements OnClickListener {
	
	Button btnSelectMale;
	Button btnSelectFemale;
	
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_launch_screen);
		btnSelectMale = (Button) findViewById(R.id.btnMale);
		btnSelectMale.setOnClickListener(this);
		btnSelectFemale = (Button) findViewById(R.id.btnFemale);
		btnSelectFemale.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		SharedPreferences.Editor editor =  FirstLaunchScreen.this.settings.edit();
		 switch (v.getId()) {
		    case R.id.btnMale:
				editor.putString(User.GENDER, "male");
				editor.commit();
		    	
		    	Intent intentMale = new Intent(this, InviteScreen.class);
		        startActivity(intentMale);
		      break;
		    case R.id.btnFemale:
				editor.putString(User.GENDER, "female");
				editor.commit();

		    	Intent intentFemale = new Intent(this, InviteScreen.class);
		    	startActivity(intentFemale);
		    default:
		      break;
		    }
	}
	
	
	
}
