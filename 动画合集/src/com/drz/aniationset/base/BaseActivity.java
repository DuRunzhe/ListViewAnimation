package com.drz.aniationset.base;

import com.drz.aniationset.R;
import com.drz.aniationset.R.layout;
import com.drz.aniationset.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}

}
