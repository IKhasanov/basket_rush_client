package ru.twoida.basketrush.activities;

import com.google.android.gcm.GCMRegistrar;

import ru.twoida.basket_rush.models.User;
import ru.twoida.basket_rush_client.R;
import ru.twoida.basketrush.utils.net.BasketRushAPISession;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InviteScreen extends BaseActivity implements OnClickListener {
	public final static int REQUEST_PICK_CONTACT_PARTNER = 1001;

	public static TextView textView3; 
	public static TextView femaleText;
	
	Button btnSelectPartner; 
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite_screen);
		textView3 = (TextView) findViewById(R.id.textView3);
		femaleText = (TextView) findViewById(R.id.textView2);
		btnSelectPartner = (Button) findViewById(R.id.btnSelectPartner);
		btnSelectPartner.setOnClickListener(this);
	}
	
	@Override
	public void onActivityResult(final int reqCode, final int resultCode, final Intent data) {
		super.onActivityResult(reqCode, resultCode, data);
		
		
		switch (reqCode) {
			case (REQUEST_PICK_CONTACT_PARTNER): {
				if (resultCode == Activity.RESULT_OK) {
					final Uri contactData = data.getData();
					final Cursor c = managedQuery(contactData, null, null, null, null);
					if (c.moveToFirst()) {
						final String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
						final String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
						String phoneNumber = "";
						final Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
								null, null);
						while (phones.moveToNext()) {
							phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
						}
						phones.close();
						textView3.setText(phoneNumber);
						BasketRushAPISession apiSession = new BasketRushAPISession();
						User user = apiSession.requestAccountCreation(settings.getString(User.LOGIN, ""), settings.getString(User.GENDER, ""), phoneNumber);
						
						Editor editor = settings.edit();
						editor.putString(User.GENDER, user.getGender());
						editor.putString(User.ID, user.getId());
						editor.putString(User.LOGIN, user.getLogin());
						editor.putString(User.SECRET_KEY, user.getSecretKey());
						
					    GCMRegistrar.checkDevice(this);
					    GCMRegistrar.checkManifest(this);
					    
					    // Достаем идентификатор регистрации
					    String regId = GCMRegistrar.getRegistrationId(this);
					    
					    if (regId.equals("")) { // Если отсутствует, то регистрируемся
					    	
					    	GCMRegistrar.register(this, sender_id);
					      Log.d("regID", regId);
					   
					    } else {
					      Log.v("GCM", "Already registered: " + regId);
					     
					    }
					    
					    apiSession.requestRegId(user.getLogin(), user.getSecretKey(), regId);

						editor.commit();
						
						if (user.getGender().equals("male")) {
							Intent intent = new Intent(this, ListActivity.class);
					        startActivity(intent);
						} else {
							if (user.getGender().equals("female")) {
								Intent intent = new Intent(this, AddTaskActivity.class);
								startActivity(intent);
								}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		    case R.id.btnSelectPartner:
				final Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, InviteScreen.REQUEST_PICK_CONTACT_PARTNER);
		    	
		      break;
		    default:
		      break;
		    }
	}
}
