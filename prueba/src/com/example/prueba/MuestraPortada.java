package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MuestraPortada extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.muestra_portada);
		
		ImageView imgportada = (ImageView)findViewById(R.id.imgportada);
		//imgportada.setImageBitmap(R.drawable.label);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.muestra_portada, menu);
		return true;
	}

}
