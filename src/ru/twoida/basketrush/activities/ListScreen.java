package ru.twoida.basketrush.activities;

import ru.twoida.basket_rush_client.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListScreen extends Activity {
	
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_screen);
		
		//String[] names = new String[] {"Кефир как на картинке", "Помидоры местные", "Сухарики Ядрен батон"};
		
		//--------------------------------------------МЕТОДЫ СПИСКА----------------------------
		ListView lvMain = (ListView) findViewById(R.id.lvMain);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_1);
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
}
