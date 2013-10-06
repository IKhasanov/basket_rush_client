package ru.twoida.basket_rush_client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import ru.twoida.basket_rush_client.R;
import ru.twoida.basketrush.activities.ListActivity;
import ru.twoida.basketrush.utils.net.BasketRushAPISession;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	/*
	Button btnFrstScr;
	String sender_id = "127652006695";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnFrstScr = (Button) findViewById(R.id.btnFrstScr);
    btnFrstScr.setOnClickListener(this);
    BasketRushAPISession apiSession = new BasketRushAPISession();
    apiSession.requestAccountCreation("+79179140000", "male", "+79033060000");
   
    GCMRegistrar.checkDevice(this);
    GCMRegistrar.checkManifest(this);
    
    // Достаем идентификатор регистрации
    String regId = GCMRegistrar.getRegistrationId(this);
    
    if (regId.equals("")) { // Если отсутствует, то регистрируемся
    	
    	GCMRegistrar.register(this,sender_id);
      Log.d("regID", regId);
   
    } else {
      Log.v("GCM", "Already registered: " + regId);
     
    }
     	
    //BasketRushAPISession apiSession2 = new BasketRushAPISession();
    //apiSession.requestRegId(regId);
    //Log.d("ListView","ID= "+apiSession.requestAddListItem("NEW PRODUCT","1200", "no_photo"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		    case R.id.btnFrstScr:
		    	Intent intent = new Intent(this, ListActivity.class);
		        startActivity(intent);
		      break;
		    default:
		      break;
		    }
	}*/
    
}
