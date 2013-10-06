package ru.twoida.basketrush.activities;

import java.util.ArrayList;
import java.util.List;

import ru.twoida.basket_rush.models.User;
import ru.twoida.basket_rush_client.R;
import ru.twoida.basketrush.utils.net.BasketRushAPISession;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class AddTaskActivity extends BaseActivity {
	
	private TaskListAdapter adapter;
	private List<String> taskModelList = new ArrayList<String>();

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
						if (actionId == 6) { //Нажали Enter
							BasketRushAPISession apiSession = new BasketRushAPISession();
							
							apiSession.requestAddListItem(settings.getString(User.LOGIN, ""), settings.getString(User.SECRET_KEY, ""), v.getText(), null, null);
						}
						return false;
					}
				});
			}
		});
		
		//--------------------------------------------МЕТОДЫ СПИСКА----------------------------
		ListView lvMain = (ListView) findViewById(R.id.lvShoppingList);
		
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
	
	// Тут сплошная копипаста из ListActivity. Если когда-нибудь это дело будет дальше развиваться, то перепишу по нормальному
	public final class GetTaskListTask extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected List<String> doInBackground(final Void... params) {
			List<String> taskList = new ArrayList<String>();
			
			for (int i = 0; i < 10; i++) {
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
