package ru.twoida.basketrush.activities;

import java.util.ArrayList;
import java.util.List;

import ru.twoida.basket_rush_client.R;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends BaseActivity {
	
	private TaskListAdapter adapter;
	private List<String> taskModelList = new ArrayList<String>();
	
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_screen);
		
		new GetTaskListTask().execute();
		
		//--------------------------------------------МЕТОДЫ СПИСКА----------------------------
		ListView lvMain = (ListView) findViewById(R.id.lvShoppingList);
		
		//ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, R.layout.list_item);
		adapter = new TaskListAdapter();
		lvMain.setAdapter(adapter);
		
		//---листенер клика
		lvMain.setOnItemClickListener(new OnItemClickListener(){
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//---------------листенер прокрутки
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
		
		//-----------------------------КОНЕЦ МЕТОДОВ СПИСКА
	}
	
	public final class GetTaskListTask extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected List<String> doInBackground(final Void... params) {
			List<String> taskList = new ArrayList<String>();
			
			for (int i = 0; i < 4; i++) {
				taskList.add("111");
			}
			
			return taskList;
		}

		@Override
		protected void onPostExecute(final List<String> taskList) {
			super.onPostExecute(taskList);

			taskModelList = taskList;
			adapter.clear();
			if (taskList != null) {
				for (final String task : taskModelList) {
					adapter.add(task);
				}
			}
			adapter.notifyDataSetChanged();
			System.out.println("Adapter notified");
		}
	}

	
	public class TaskListAdapter extends ArrayAdapter<String> {
		private final LayoutInflater mInflater;
		
		public TaskListAdapter() {
			super(getApplication(), R.layout.list_item);
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.list_item, null);
				
				holder.tvTaskText = (TextView) convertView.findViewById(R.id.tvTaskText);
				holder.btnTaskPhoto = (ImageButton) convertView.findViewById(R.id.btnTaskPhoto);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		TextView tvTaskText;
		ImageButton btnTaskPhoto;
	}
}
