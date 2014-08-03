package com.smbc.android;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class SMBCActivity extends Activity {

	private ImageView comic;
	private int comicCount;
	private int currentCount;
	private Bitmap currentComic;
	private Bitmap currentAfterComic;
	private String comicPath;
	private String afterComicPath;
	private SharedPreferences comicState;
	private List<Integer> backStack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smbc);
		comic = (ImageView) this.findViewById(R.id.comic_image);
		new FetchComicContentTask().execute(SMBCConstants.SMBC_URL, getApplicationContext().getFilesDir().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smbc, menu);
		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
	};
}
