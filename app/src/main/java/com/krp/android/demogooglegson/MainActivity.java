package com.krp.android.demogooglegson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private TextView tvJsonString, tvJsonObj, tvClassDef;

    private String jsonString = "{ \"username\":\"test\",\"password\":\"123\", \"type\":\"Student\" }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJsonString = (TextView) findViewById(R.id.tv_json_string);
        tvJsonObj = (TextView) findViewById(R.id.tv_json_obj);
        tvClassDef = (TextView) findViewById(R.id.tv_class_def);

        tvJsonString.setText(jsonString);
    }

    public void onParse(View v) {
        Gson gson = new Gson();
        User userObj = gson.fromJson(jsonString, User.class);

        tvJsonObj.setText("username: "+userObj.getUsername()+"\n" +
                "password: "+userObj.getPassword()+"\n" +
                "user-type: "+userObj.getType());
    }

    public void onReflectClassUser(View v) {
        Method[] methods = TestClassReflection.class.getMethods();
        StringBuilder builder = new StringBuilder();

        for(Method method : methods) {
            builder.append(method.getName()+"\n");
        }

        tvClassDef.setText(builder.toString());
    }
}
