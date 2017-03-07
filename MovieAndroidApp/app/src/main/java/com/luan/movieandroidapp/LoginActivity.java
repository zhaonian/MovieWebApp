package com.luan.movieandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogInClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                login();
            }
        }).start();
    }

    protected void login() {
        ClientServerConnector clientServerConnector = new ClientServerConnector();

        Map<String, String> map = new HashMap<>();
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);

        map.put("email", email.getText().toString());
        map.put("password", password.getText().toString());

        JSONObject jsonObject = clientServerConnector.getJsonObject("MobileLogin", map);
        try {
            if ((boolean) jsonObject.get("loggedIn") == true) {
                Intent goToIntent = new Intent(this, SearchActivity.class);
                startActivity(goToIntent);
            } else {
                this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Invalid email/password", Toast.LENGTH_LONG).show();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
