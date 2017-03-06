package com.luan.movieandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
                System.out.println("in");
            } else {
                System.out.println("out");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connecction = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/moviedb?user=root&password=password");
//
//            String select = "SELECT id FROM customer WHERE email = ? AND password = ?;";
//            PreparedStatement preparedStatement = connecction.prepareStatement(select);
//
//            EditText email   = (EditText)findViewById(R.id.email);
//            EditText password   = (EditText)findViewById(R.id.password);
//
//            preparedStatement.setString(1, email.toString());
//            preparedStatement.setString(2, password.toString());
//
//            int logInStatus = preparedStatement.executeUpdate();
//
//            if (logInStatus == 0) {
//                System.out.println("fail LoginActivity");
//            } else {
//                System.out.println("success LoginActivity");
//
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }
}
