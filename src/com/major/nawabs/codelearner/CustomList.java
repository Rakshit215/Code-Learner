package com.major.nawabs.codelearner;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter 
{
	private final Activity context;
	private final String[] listTitle;



	public CustomList(Activity context, String[] listTitle,Integer integer) 
	{
		// TODO Auto-generated constructor stub
		super(context,R.layout.list_single,listTitle);
		this.context = context;
		this.listTitle =listTitle;
		
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{if(listTitle[position]!=null)
	{
LayoutInflater inflater = context.getLayoutInflater();
View rowView= inflater.inflate(R.layout.list_single, null, true);
TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

txtTitle.setText(listTitle[position]);


return rowView;
	}
	else
	{

LayoutInflater inflater = context.getLayoutInflater();
View rowView= inflater.inflate(R.layout.whitespace, null, true);
return rowView;
	}}

	
}
