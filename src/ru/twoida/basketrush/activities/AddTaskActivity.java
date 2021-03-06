package ru.twoida.basketrush.activities;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.twoida.basket_rush.models.Task;
import ru.twoida.basket_rush.models.User;
import ru.twoida.basket_rush_client.R;
import ru.twoida.basketrush.utils.net.BasketRushAPISession;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class AddTaskActivity extends BaseActivity {
	
	public final static int REQUEST_TAKE_PHOTO = 1011;
	
	public final static int[] color_Stubs = {R.drawable.stub_blue, R.drawable.stub_green, R.drawable.stub_orange, R.drawable.stub_red, R.drawable.stub_violet};
	
	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	
	private TaskListAdapter adapter;
	private List<Task> taskModelList = new ArrayList<Task>();
	
	private Uri imageUri;

	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_task_activity);
		
		new GetTaskListTask().execute();
		
		final Button btnAddTask = (Button) this.findViewById(R.id.btnAddTask);
		final EditText etAddTask = (EditText) this.findViewById(R.id.etAddTask);
		final RelativeLayout rlEditText = (RelativeLayout) this.findViewById(R.id.rlEditText);
		
		btnAddTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnAddTask.setVisibility(View.GONE);
				rlEditText.setVisibility(View.VISIBLE);
				if(etAddTask.requestFocus()) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(etAddTask, InputMethodManager.SHOW_IMPLICIT);
				}
				etAddTask.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					
					@Override
					public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						System.out.println("actionId = " + actionId);
						if (actionId == 6) { //������ Enter
							BasketRushAPISession apiSession = new BasketRushAPISession();
							
							apiSession.requestAddListItem(settings.getString(User.LOGIN, ""), settings.getString(User.SECRET_KEY, ""), v.getText().toString(), null, null);
							Task task = new Task();
							task.setTitle(v.getText().toString());
							adapter.add(task);
							taskModelList.add(task);
							v.setText("");
						}
						return false;
					}
				});
				
				Button btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
				btnTakePhoto.setClickable(true);
				btnTakePhoto.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						System.out.println("btnTakePhoto");
						final Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						final File storageDir = getExternalFilesDir(null);
						final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
						final String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
						final File imageF = new File(storageDir, imageFileName + JPEG_FILE_SUFFIX);
						try {
							imageF.createNewFile();
						} catch (final IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						imageUri = Uri.fromFile(imageF);
						System.out.println("imageURI = " + imageUri);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

						startActivityForResult(intent, REQUEST_TAKE_PHOTO);
						System.out.println("imageURI = " + imageUri);					}
				});
			}
		});
		
		//--------------------------------------------������ ������----------------------------
		ListView lvMain = (ListView) findViewById(R.id.lvShoppingList);
		
		adapter = new TaskListAdapter();
		lvMain.setAdapter(adapter);
		
		//---�������� �����
		lvMain.setOnItemClickListener(new OnItemClickListener(){
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
			}
			
		});
		
		//---------------�������� ���������
		lvMain.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//-----------------------------����� ������� ������
	}
	
	public String getRealPathFromURI(final Uri contentUri) {
		final String[] proj = { MediaStore.Images.Media.DATA };
		final Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		if (cursor == null) {
			return contentUri.getPath();
		}
		final int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case REQUEST_TAKE_PHOTO: {
				if (resultCode == Activity.RESULT_OK) {
					try {
						System.out.println("imageURI " + imageUri);
						final File file = new File(getRealPathFromURI(imageUri));

						Toast.makeText(this, imageUri.toString(), Toast.LENGTH_LONG).show();
					} catch (final Exception e) {
						Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
						Log.e("Camera", e.toString());
						e.printStackTrace();
					}
				}
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	// ��� �������� ��������� �� ListActivity. ���� �����-������ ��� ���� ����� ������ �����������, �� �������� �� �����������
	public final class GetTaskListTask extends AsyncTask<Void, Void, List<Task>> {

		@Override
		protected List<Task> doInBackground(final Void... params) {
			List<Task> taskList = new ArrayList<Task>();
			
			BasketRushAPISession apiSession = new BasketRushAPISession();
			taskList = apiSession.requestList(settings.getString(User.LOGIN, ""), settings.getString(User.SECRET_KEY, ""));
			
//			for (int i = 0; i < 10; i++) {
//				taskList.add("111");
//			}
			
			return taskList;
		}

		protected void onPostExecute(final List<Task> taskList) {
			super.onPostExecute(taskList);

			taskModelList = taskList;
			adapter.clear();
			if (taskList != null) {
				for (final Task task : taskModelList) {
					adapter.add(task);
				}
			}
			adapter.notifyDataSetChanged();
			System.out.println("Adapter notified");
		}
	}

	
	public class TaskListAdapter extends ArrayAdapter<Task> {
		private final LayoutInflater mInflater;
		
		public TaskListAdapter() {
			super(getApplication(), R.layout.list_item);
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.list_item, null);
				
				holder.tvTaskText = (TextView) convertView.findViewById(R.id.tvTaskText);
				holder.btnTaskPhoto = (RelativeLayout) convertView.findViewById(R.id.btnTaskPhoto);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.tvTaskText.setText(taskModelList.get(position).getTitle());
			holder.btnTaskPhoto.setBackgroundResource(color_Stubs[position % 5]);
			holder.btnTaskPhoto.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					BasketRushAPISession apiSession = new BasketRushAPISession();
					Task task = taskModelList.get(position);
					apiSession.requestDeleteListItem(settings.getString(User.LOGIN, ""), settings.getString(User.SECRET_KEY, ""), task.getId());
					taskModelList.remove(position);
					adapter.remove(task);
					adapter.notifyDataSetChanged();
				}
			});
			
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		TextView tvTaskText;
		RelativeLayout btnTaskPhoto;
	}

}
