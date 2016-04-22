package com.major.nawabs.codelearner;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings({ "unused", "deprecation" })
public class TActivity extends TabActivity
{
	TabHost mTabHost;
	Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t);
		
mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		
		    setupTab(new TextView(this), "PROGRAM");
		    setupTab(new TextView(this), "PATTERN");
	}

	private void setupTab(final View View, final String tag)
	{
		// TODO Auto-generated method stub
		View tabview = createTabView(mTabHost.getContext(), tag);
		if("PROGRAM".equals(tag))
			i=new Intent(TActivity.this,ProgActivity.class);
		else
			i=new Intent(TActivity.this,ImgActivity.class);
		        TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(i);
		    mTabHost.addTab(setContent);
	}

	private android.view.View createTabView(final Context context, final String text) 
	{
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
	    TextView tv = (TextView) view.findViewById(R.id.tabsText);
	    tv.setText(text);
	    return view;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.t, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}
}
