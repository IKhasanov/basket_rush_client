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
import ru.twoida.basketrush.activities.ListScreen;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity implements OnClickListener {
	
	Button btnFrstScr;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnFrstScr = (Button) findViewById(R.id.btnFrstScr);
    btnFrstScr.setOnClickListener(this);
    
    GCMRegistrar.checkDevice(this);
    GCMRegistrar.checkManifest(this);
    
    // ������� ������������� �����������
    final String regId = GCMRegistrar.getRegistrationId(this);
    
    if (regId.equals("")) { // ���� �����������, �� ��������������
      GCMRegistrar.register(this);
      Toast.makeText(this, "����������������", 10);
    } else {
      Log.v("GCM", "Already registered: " + regId);
      
      Toast.makeText(this, "����������������� ���", 10);
    }
        
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
		    	Intent intent = new Intent(this, ListScreen.class);
		        startActivity(intent);
		      break;
		    default:
		      break;
		    }
	}
    
}
