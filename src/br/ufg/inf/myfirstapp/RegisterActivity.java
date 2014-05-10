package br.ufg.inf.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	Button botaoGcm;
	Button botaoAplicacao;
	GoogleCloudMessaging gcm;
	Context context;
	String registroId;
	
	public static final String REG_ID = "regId";
	private static final String VERSAO_APP = "appVersion";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		context = getApplicationContent();
		
		botaoGcm = (Button) findViewById(R.id.botaoGcm);
		botaoGcm.setOnClickListener(new View.OnClickListener()) {
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(registroId)) {
					registroId = 
				} else {
					Toast.makeText(getApplicationContext(), "Já registrado no Servidor!",
					Toast.LENGTH_LONG.show();
				}
			}
		}
	}
	
}
