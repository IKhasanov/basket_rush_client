package ru.twoida.basketrush.activities;

import ru.twoida.basket_rush_client.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InviteScreen extends BaseActivity implements OnClickListener {
	Button btnSelectPartner; 
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite_screen);
		
		btnSelectPartner = (Button) findViewById(R.id.btnSelectPartner);
		btnSelectPartner.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		    case R.id.btnSelectPartner:
		    	Intent intent = new Intent(this, ListActivity.class);
		        startActivity(intent);
		      break;
		    default:
		      break;
		    }
	}
}
