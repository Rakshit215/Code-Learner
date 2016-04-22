package com.major.nawabs.codelearner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseRetrieval extends SQLiteOpenHelper 
{

	private static String DB_PATH = "/data/data/com.major.nawabs.codelearner/databases/";
    private static String DB_NAME = "test.db";
    private SQLiteDatabase myDataBase; 
    private final Context myContext;
 
    public DatabaseRetrieval(Context con)
    {
    	super(con, DB_NAME, null, 1);
        this.myContext = con;
    }	

    public void createDataBase() throws IOException
    {
      	boolean dbExist = checkDataBase();
    	if(dbExist){
    	}
    	else
    	{
        	this.getReadableDatabase();
         	try {
    			copyDataBase();
    		} 
         	catch (IOException e)
         	{
         		throw new Error("Error copying database");
         	}
    	}
     }

    private boolean checkDataBase()
    {
       	SQLiteDatabase checkDB = null;
     	try
     	{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}
    	catch(SQLiteException e)
    	{
 
    	}
 
    	if(checkDB != null)
    	{
     		checkDB.close();
     	}
 
    	return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException
    {
 
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
     	String outFileName = DB_PATH + DB_NAME;
     	OutputStream myOutput = new FileOutputStream(outFileName);
     	byte[] buffer = new byte[4024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
 

    public void openDataBase() throws SQLException
    {
     	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY|SQLiteDatabase.NO_LOCALIZED_COLLATORS);
     }
 
    @Override
	public synchronized void close() 
    {
     	    if(myDataBase != null)
    		    myDataBase.close();
     	    super.close();
 
	}

    
    @Override
	public synchronized SQLiteDatabase getReadableDatabase() 
    {
		// TODO Auto-generated method stub
		return super.getReadableDatabase();
	}



	@Override
	public void onOpen(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		super.onOpen(db);
	}



	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		
	}
}
