package com.major.nawabs.codelearner;

import java.io.IOException;

import com.major.nawabs.codelearner.R.menu;


import android.support.v7.app.ActionBarActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditContentActivity extends ActionBarActivity 
{

	public EditText et1;
	public TextView tv;
	Button btn;
	public String titlename;
	public String Matter;
	public String newtitle ;
	 public String newmatter ;
public String q;
public String ss;
@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_content);
		et1=(EditText) findViewById(R.id.editText1);
		tv=(TextView) findViewById(R.id.textView7);
		btn=(Button) findViewById(R.id.button1);
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
 ss=bundle.getString("ss");
		
		try
		{
		if(ss.equalsIgnoreCase("Sheet1"))
		{
			 q = "select * from Sheet1 where NAME like '"+titlename+"' ";
		}	
		else
		{ 
			q = "select * from Sheet2 where NAME like '"+titlename+"' ";
		}	
			Cursor c = db.rawQuery(q, null);
		
			while(c.moveToNext())
			{
				Matter = c.getString(1);
				
			}
			tv.setText(titlename);
			et1.setText(Matter);
			c.close();

		}//end of try
		
		catch(Exception e)
		{
			e.printStackTrace();
			Toast.makeText(this, "error occured", 3000).show();
		}//end of catch
		db.close();
		mh.close();
		btn.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				DatabaseRetrieval mh= new DatabaseRetrieval(EditContentActivity.this.getApplicationContext());
				mh= new DatabaseRetrieval(EditContentActivity.this);
				
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
				
				
				 newmatter = et1.getText().toString();
					try
					{
					if(ss.equalsIgnoreCase("Sheet1"))
					{
						 q = "update Sheet1 set CODE = '"+newmatter+"'  where NAME like '"+titlename+"' ";
					}	
					else
					{ 
						
						q ="update Sheet2 set PATTERN = '"+newmatter+"'  where NAME like '"+titlename+"' ";
						
					}	

					 db.execSQL(q);
					Toast.makeText(EditContentActivity.this, "updated", 3000).show();
					}

					catch(Exception e)
					{
						e.printStackTrace();
						
					}//end of catch
					
			
				db.close();
				mh.close();

				finish();
				
				
			}

			private Context getApplicationContext() {
				// TODO Auto-generated method stub
				return null;
			}
		});//end of handler anonymous method

		
	}





@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_content, menu);
		
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
