package com.major.nawabs.codelearner;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ImgActivity extends ActionBarActivity
{
	ListView listm;
	int row;
	String[] ListTitle = new String[200] ;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_img);

			DatabaseRetrieval mh= new DatabaseRetrieval(this.getApplicationContext());
			mh= new DatabaseRetrieval(this);
			try 
			{
				mh.createDataBase();
				}
			catch (IOException ioe) 
			{
				throw new Error("Unable to create database");
			}
			try {

				mh.openDataBase();
			}
			catch (SQLException sqle) 
			{
				sqle.printStackTrace();
			}
			
			SQLiteDatabase db = mh.getReadableDatabase();
			Cursor c ;
			String q="select * from Sheet2";
			c = db.rawQuery(q, null);
			row=c.getCount();
			final String[] ListTitle = new String[row] ;
  
			int i = 0;		
			String Name;
			while(c.moveToNext())
			{
				Name = c.getString(0);			
				ListTitle[i]= Name;
				i++;
			}



		CustomList adapter = new CustomList(ImgActivity.this, ListTitle,(Integer) null);
		listm=(ListView)findViewById(R.id.listView2);
		listm.setAdapter(adapter);
		listm.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(ImgActivity.this,ViewNotes1Activity.class);
				intent.putExtra("name",ListTitle[position]);				
				startActivity(intent);
							
			}
		}); 
		
		
		registerForContextMenu(listm);
		
		c.close();
		db.close();
		mh.close();

}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.img, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
