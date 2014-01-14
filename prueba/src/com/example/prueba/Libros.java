package com.example.prueba;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Libros extends ListActivity {

	// Mientras no se haga la consulta de datos de DropBox, usamos estos libros
	// estáticos:
	private static String[] libros = { "Harry Potter", "El Psicoanalista",
			"Vida de Pi", "El señor de los anillos", "El Hobbit",
			"Rebelión en la granja" };
	private static ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.libros);
		// setListAdapter(new
		// ArrayAdapter<String>(this,R.layout.filalibro,R.id.label,libros));
		adapter = new ArrayAdapter<String>(this, R.layout.filalibro,
				R.id.label, libros);
		setListAdapter(adapter);

	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		// ...aqui mostrar la portada de cada libro clicado
		Intent intent = new Intent(Libros.this, MuestraPortada.class);
		// Iniciamos la Portada
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			ordenarAlfab();
			adapter.notifyDataSetChanged();
			break; // métodos de ordenación
		case R.id.item2:
			ordenarFecha();
			break;
		}
		return true;
	}

	private void ordenarAlfab() {
		List<String> LibrosOrdAlfab = Arrays.asList(libros);
		Collections.sort(LibrosOrdAlfab);
		libros = (String[]) LibrosOrdAlfab.toArray();
	}

	private void ordenarFecha() {

	}
}
