package com.krp.android.demogooglegson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvJsonString, tvJsonObj;

    private String jsonString = "{ \"username\":\"test\",\"password\":\"123\" }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJsonString = (TextView) findViewById(R.id.tv_json_string);
        tvJsonObj = (TextView) findViewById(R.id.tv_json_obj);

        tvJsonString.setText(jsonString);
    }

    public void onParse(View v) {
        Gson gson = new Gson();
        User userObj = gson.fromJson(jsonString, User.class);

        tvJsonObj.setText(userObj.toString());
    }
}
