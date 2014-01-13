package com.example.prueba;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Libros extends ListActivity {
	private TextView seleccionado;
	private static final String [] items = { "Harry Potter","El Psicoanalista","Vida de Pi","El señor de los anillos","El Hobbit","Rebelión en la granja"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.libros);
		setListAdapter(new ArrayAdapter<String>(this,R.layout.filalibro,R.id.label,items));
		
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
	//...aqui mostrar la portada de cada libro clicado
		Intent intent =
                new Intent(Libros.this, MuestraPortada.class);
        //Iniciamos la Portada
        startActivity(intent);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Aqui tenemos el menu desplegable
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected (MenuItem item){
		switch (item.getItemId()){
		case R.id.item1: break;  // métodos de ordenación
		case R.id.item2: break;
		}
		return true;
	}

}
