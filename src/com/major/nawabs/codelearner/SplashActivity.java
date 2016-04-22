package com.major.nawabs.codelearner;



import java.io.IOException;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.ActionBarView;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class SplashActivity extends ActionBarActivity 
{
	int i=0;
	ProgressBar pb;
	ActionBar ab;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ab=this.getActionBar();
		ab.hide();
		pb=(ProgressBar)findViewById(R.id.progressBar1);
	

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

		Thread t=new Thread()
		{
			public void run()
			{
				try
				{
					while(i<2)
					{
						pb.setProgress(i*5);
					Thread.sleep(1000);
					i++;}
					Intent i= new Intent(SplashActivity.this,TActivity.class);
			finish();
					startActivity(i);
				}
				catch(InterruptedException e)
				{
					
				}
			}
		};
		t.start();
		
		}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
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
