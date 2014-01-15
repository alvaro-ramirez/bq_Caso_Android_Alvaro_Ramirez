package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

public class Login extends Activity {

	// Claves para usar y autenticar nuestra App con Dropbox
	final static private String APP_KEY = "e84tnbc1q4tebbn";
	final static private String APP_SECRET = "amd4zqipk7wb70e";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;

	private DropboxAPI<AndroidAuthSession> mDBApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		inicializar();

		// Localizar los controles
		//Button buttondc = (Button) findViewById(R.id.buttondc); // cancelar
		Button buttondl = (Button) findViewById(R.id.buttondl); // login
		
		/*buttondc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); // el boton cancelar cierra la app
			}
		});
*/
		buttondl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				/*
				 * Para recordar el inicio de sesión, usamos sharedpreferences
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
	
	protected void onResume() {
	    super.onResume();

	    if (mDBApi.getSession().authenticationSuccessful()) {
	        try {
	            // Required to complete auth, sets the access token on the session
	            mDBApi.getSession().finishAuthentication();

	            AccessTokenPair tokens = mDBApi.getSession().getAccessTokenPair();
	        } catch (IllegalStateException e) {
	            Log.i("DbAuthLog", "Error de autenticación", e);
	        }
	    }
	}

	private void inicializar() {
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys,
				ACCESS_TYPE);
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
	}
}
