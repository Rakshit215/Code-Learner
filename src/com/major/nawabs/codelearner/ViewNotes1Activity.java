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
import android.widget.TextView;
import android.widget.Toast;

public class ViewNotes1Activity extends ActionBarActivity 
{
	TextView tv1,tv2;
	String q,titlename,Mat;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_notes1);

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
		try 
		{
			mh.openDataBase();
		}
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}
		
		SQLiteDatabase db = mh.getReadableDatabase();
		
		
		
		Bundle bundle = getIntent().getExtras();
		titlename = bundle.getString("name");
			q = "select * from Sheet2 where NAME like '"+titlename+"' ";
			tv1=(TextView) findViewById(R.id.textView1);
			tv2=(TextView) findViewById(R.id.textView2);
		try
		{
			
			Cursor c= db.rawQuery(q, null);
			while(c.moveToNext())
			{
				Mat= c.getString(1);			
				
			}
			
			tv1.setText(titlename);
			tv2.setText(Mat);
			c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
			Toast.makeText(this, "error occured", 3000).show();
		}
				Toast.makeText(this, titlename, 3000).show();
				
				db.close();
				mh.close();



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_notes1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(item.getItemId())
		{
		case R.id.addnew1:
			
			Intent i = new Intent(this,EditContentActivity.class);
			i.putExtra("name", titlename);
			i.putExtra("ss","Sheet2");
			finish();
			startActivity(i);
		break;
		
		case R.id.compile:
			 i = new Intent(this,NetActivity.class);		
			finish();
						startActivity(i);
		break;
					
			case R.id.run:
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
