
package ru.twoida.basketrush.activities;

import java.util.ArrayList;
import java.util.List;

import ru.twoida.basket_rush.models.Task;
import ru.twoida.basket_rush.models.User;
import ru.twoida.basket_rush_client.R;
import ru.twoida.basketrush.utils.net.BasketRushAPISession;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListActivity extends BaseActivity {
	
	public final static int[] color_Stubs = {R.drawable.stub_blue, R.drawable.stub_green, R.drawable.stub_orange, R.drawable.stub_red, R.drawable.stub_violet};
	
	private TaskListAdapter adapter;
	private List<Task> taskModelList = new ArrayList<Task>();
	
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_screen);
		
		new GetTaskListTask().execute();
		
		//--------------------------------------------������ ������----------------------------
		ListView lvMain = (ListView) findViewById(R.id.lvShoppingList);
		
		//ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, R.layout.list_item);
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

		@Override
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
