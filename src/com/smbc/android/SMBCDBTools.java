package com.smbc.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SMBCDBTools extends SQLiteOpenHelper {

	// Context : provides access to application specific resources and classes

	public SMBCDBTools(Context applicationcontext) {

		// Call use the database or to create it

		super(applicationcontext, SMBCConstants.DB_NAME, null, 1);

	}

	public void onCreate(SQLiteDatabase database) {

		String query = "CREATE TABLE comics ( " + SMBCConstants.COLUMN_ID
				+ " INTEGER, " + SMBCConstants.COLUMN_COMIC_PATH + " TEXT, "
				+ SMBCConstants.COLUMN_AFTER_COMIC_PATH + " TEXT )";

		database.execSQL(query);

	}

	public void onUpgrade(SQLiteDatabase database, int version_old,
			int current_version) {
		
		String query = "DROP TABLE IF EXISTS comics";

		database.execSQL(query);
		onCreate(database);
	}

	public void insertComic(SMBCComic comic) {

		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = comic.toContentValues();

		database.insert(SMBCConstants.DB_NAME, null, values);

		database.close();
	}

	public int updateComic(SMBCComic comic) {

		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = comic.toContentValues();

		return database.update(SMBCConstants.DB_NAME, values, SMBCConstants.COLUMN_ID + " = ?",
				new String[] { Long.toString(comic.getId()) });
	}

	public void deleteContact(Long id) {

		SQLiteDatabase database = this.getWritableDatabase();

		String deleteQuery = "DELETE FROM  contacts where " + SMBCConstants.COLUMN_ID + " ='" + id
				+ "'";

		database.execSQL(deleteQuery);
	}

	public List<SMBCComic> getAllContacts() {

		// ArrayList that contains every row in the database
		// and each row key / value stored in a HashMap

		ArrayList<HashMap<String, String>> contactArrayList;

		contactArrayList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT  * FROM contacts";

		// Open a database for reading and writing

		SQLiteDatabase database = this.getWritableDatabase();

		// Cursor provides read and write access for the
		// data returned from a database query

		// rawQuery executes the query and returns the result as a Cursor

		Cursor cursor = database.rawQuery(selectQuery, null);

		// Move to the first row

		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> contactMap = new HashMap<String, String>();

				// Store the key / value pairs in a HashMap
				// Access the Cursor data by index that is in the same order
				// as used when creating the table

				contactArrayList.add(contactMap);
			} while (cursor.moveToNext()); // Move Cursor to the next row
		}

		// return contact list
		return null;
	}

	public HashMap<String, String> getContactInfo(String id) {
		HashMap<String, String> contactMap = new HashMap<String, String>();

		// Open a database for reading only

		SQLiteDatabase database = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM contacts where contactId='" + id
				+ "'";

		// rawQuery executes the query and returns the result as a Cursor

		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {

				contactMap.put("firstName", cursor.getString(0));
				contactMap.put("lastName", cursor.getString(0));
				contactMap.put("phoneNumber", cursor.getString(0));
				contactMap.put("emailAddress", cursor.getString(0));
				contactMap.put("homeAddress", cursor.getString(0));

			} while (cursor.moveToNext());
		}
		return contactMap;
	}

}
