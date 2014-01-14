package com.example.prueba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.android.AuthActivity;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

public class Login extends Activity {

	// Claves para usar y autenticar nuestra App con Dropbox
	final static private String APP_KEY = "aqlpzm4pt4juvm4";
	final static private String APP_SECRET = "k2lb0m80cywmkso";
	final static private AccessType ACCESS_TYPE = AccessType.DROPBOX;

	private DropboxAPI<AndroidAuthSession> mDBApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		inicializar();

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
				EditText edtLogin = (EditText) findViewById(R.id.editText1);
				String login = edtLogin.getText().toString();
				EditText edtPass = (EditText) findViewById(R.id.editText2);
				String pass = edtPass.getText().toString();

				/*
				 * Para recordar el inicio de sesi�n, usamos sharedpreferences
				 * 
				 * Lo dejamos comentado para futuras mejoras...
				 * 
				 * SharedPreferences recuerdame = getSharedPreferences("rec",
				 * Context.MODE_PRIVATE); SharedPreferences.Editor editor =
				 * recuerdame.edit(); // if (){ el checkbox esta activado
				 * editor.putBoolean(login, true); editor.commit();
				 */
				mDBApi.getSession().startAuthentication(Login.this);
				/* Creamos el Intent
				Intent intent = new Intent(Login.this, Libros.class);
				// Iniciamos la nueva actividad
				startActivity(intent);
				finish();
				*/
			}
		});

	}

	private void inicializar() {
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys,
				ACCESS_TYPE);
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
	}
}
