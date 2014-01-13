package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

	/*
	 * Esta Activity es el Login.
	 */

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// Localizar los controles
		Button buttondc = (Button) findViewById(R.id.buttondc); // cancelar
		Button buttondl = (Button) findViewById(R.id.buttondl); // login
		
		buttondc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); // el boton cancelar cierra la app
			}
		});

		buttondl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// aqui debe conectarse a dropbox. Por ahora saltamos ese paso y
				// vamos directo a la lista de libros.
			    EditText edtLogin = (EditText)findViewById(R.id.editText1);
			    String login = edtLogin.getText().toString();
			    
			    EditText edtPass = (EditText)findViewById(R.id.editText2);
			    String pass = edtPass.getText().toString();
			    
			    // tenemos el login y el pass, se manda a la api de dropbox...
			    // fin dropbox
			    // Ahora abrimos la Activity lista
			    // falta comprobar si está activado el chekbox y usando sharedpreferences...
			  //Creamos el Intent
                Intent intent =
                        new Intent(Login.this, Libros.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                finish(); // para matar esta pantalla, y no se pueda volver a ella
			}
		});

	}

	/*
	 * El siguiente apartado lo podíamos borrar, puesto que no habrá menús.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
