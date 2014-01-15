package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.android.AuthActivity;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;

public class Login extends Activity {

	// Claves para usar y autenticar nuestra App con Dropbox
	final static private String APP_KEY = "e84tnbc1q4tebbn";
	final static private String APP_SECRET = "amd4zqipk7wb70e";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	final static private String ACCOUNT_PREFS_NAME = "prefs";
	final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
	final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";
	
	boolean mLoggedIn = false;

	private DropboxAPI<AndroidAuthSession> mDBApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		AndroidAuthSession session = buildSession();
		inicializar();
		setContentView(R.layout.login);

		checkAppKeySetup();

		// Localizar los controles
		Button buttondl = (Button) findViewById(R.id.buttondl); // login

		buttondl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDBApi.getSession().startAuthentication(Login.this);
				//finish();
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();
		AndroidAuthSession session = mDBApi.getSession();
		if (session.authenticationSuccessful()) {
			try {
				// Llamada obligatoria para completar la autenticación
				session.finishAuthentication();

				// Guardar las keys para después
				TokenPair tokens = session.getAccessTokenPair();
				storeKeys(tokens.key, tokens.secret);
				setLoggedIn(true); 
				// aqui llegamos después de pulsar el botón permitir
			    Intent i = null;
			    i = new Intent (Login.this, Libros.class);
			    startActivity(i);
			
			} catch (IllegalStateException e) {
				Toast.makeText(
						this,
						"No se ha podido autenticar con Dropbox:"
								+ e.getLocalizedMessage(), Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private void inicializar() {
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys,
				ACCESS_TYPE);
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
	}

	private AndroidAuthSession buildSession() {
		AppKeyPair appKeyPair = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session;

		String[] stored = getKeys();
		if (stored != null) {
			AccessTokenPair accessToken = new AccessTokenPair(stored[0],
					stored[1]);
			session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE,
					accessToken);
		} else {
			session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE);
		}

		return session;

	}

	/**
	 * Para no re-autenticarse cada vez, recupera las credenciales si ya estaba
	 * autenticado.
	 * 
	 * @return Array de [access_key, access_secret], o null si no hay nada
	 */
	private String[] getKeys() {
		SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
		String key = prefs.getString(ACCESS_KEY_NAME, null);
		String secret = prefs.getString(ACCESS_SECRET_NAME, null);
		if (key != null && secret != null) {
			String[] ret = new String[2];
			ret[0] = key;
			ret[1] = secret;
			return ret;
		} else {
			return null;
		}
	}

	private void checkAppKeySetup() {
		// Check if the app has set up its manifest properly.
		Intent testIntent = new Intent(Intent.ACTION_VIEW);
		String scheme = "db-" + APP_KEY;
		String uri = scheme + "://" + AuthActivity.AUTH_VERSION + "/test";
		testIntent.setData(Uri.parse(uri));
		PackageManager pm = getPackageManager();
		if (0 == pm.queryIntentActivities(testIntent, 0).size()) {
			Toast.makeText(
					this,
					"Comprueba el URL del "
							+ "manifest. Debería tener "
							+ "com.dropbox.client2.android.AuthActivity con el esquema"
							+ scheme, Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	/**
	 * Guarda las keys devueltas autenticadas localmente
	 * 
	 */
	private void storeKeys(String key, String secret) {
		// Save the access key for later
		SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
		Editor edit = prefs.edit();
		edit.putString(ACCESS_KEY_NAME, key);
		edit.putString(ACCESS_SECRET_NAME, secret);
		edit.commit();
	}
	
	private void setLoggedIn(boolean loggedIn) {
        mLoggedIn = loggedIn;
}


}
