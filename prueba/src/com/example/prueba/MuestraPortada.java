package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MuestraPortada extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.muestra_portada);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.muestra_portada, menu);
		return true;
	}

}
