package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.belosoft.mejorarencasa.R;
import org.belosoft.mejorarencasa.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextUser;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        // Shared Preferences
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        setCredentialsIfExist();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();
                // si no hay ningun error en user o password, se vuelve al MainActivity
                if (login(user, password)) {
                    // vamos al MainActivity
                    goToMain();
                    // guardamos las Preferences
                    saveOnPreferences(user, password);
                }
            }
        });
    }

    private void bindUI() {
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void    setCredentialsIfExist(){
        String user = Util.getUserPreferences(prefs);
        String password = Util.getPassPreferences(prefs);
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            editTextUser.setText(user);
            editTextPassword.setText(password);
        }
    }

    private boolean login(String user, String password){
        if (!isValidUser(user)){
            Toast.makeText(this, "User is empty, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this, "Password too short, please try again", Toast.LENGTH_LONG).show();
            return false;
        }  else {
            return  true;
        }
    }

    private void saveOnPreferences(String user, String password){
        if (switchRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user",user);
            editor.putString("pass",password);
            editor.commit();
            editor.apply();
        }
    }

    private boolean isValidUser(String user){
        return !TextUtils.isEmpty(user);
    }

    private boolean isValidPassword(String password){
        return password.length() >= 4;
    }

    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        // para que no se pueda volver al Login desde el MainActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
