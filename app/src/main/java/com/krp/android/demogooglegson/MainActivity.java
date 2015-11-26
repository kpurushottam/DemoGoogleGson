package com.krp.android.demogooglegson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    private TextView tvJsonString, tvJsonObj;
    private TextView tvLoadClass, tvClassModifiers, tvClassDef;

    private String jsonString = "{ \"username\":\"test\",\"password\":\"123\", \"type\":\"Student\" }";

    private Class testClass;
    private StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJsonString = (TextView) findViewById(R.id.tv_json_string);
        tvJsonObj = (TextView) findViewById(R.id.tv_json_obj);
        tvLoadClass = (TextView) findViewById(R.id.tv_load_class);
        tvClassModifiers = (TextView) findViewById(R.id.tv_load_class_modifiers);
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

    public void onLoadClass(View v) {
        builder = new StringBuilder();
        // testClass = TestClassReflection.class;
        // or
        try {
            testClass = Class.forName("com.krp.android.demogooglegson.TestClassReflection");
            builder.append("Loaded successfully\n");
            builder.append("Class Name: \n").append(testClass.getSimpleName()).append("\n");
            builder.append("Class Name: \n").append(testClass.getName());
            tvLoadClass.setText(builder.toString());
            tvLoadClass.setTextColor(getResources().getColor(android.R.color.black));
        } catch (ClassNotFoundException e) {
            tvLoadClass.setText(e.getClass().getSimpleName() + "\nexception occured");
            tvLoadClass.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    public void onLoadClassModifiers(View v) {
        if(testClass == null) {
            tvLoadClass.setText("Load class first");
            tvLoadClass.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            return;
        }

        int modifiers = testClass.getModifiers();
        tvClassModifiers.setText(Modifier.toString(modifiers));
    }

    public void onReflectClassUser(View v) {
        Method[] methods = TestClassReflection.class.getMethods();
        builder = new StringBuilder();

        for(Method method : methods) {
            builder.append(method.getName()).append("\n");
        }

        tvClassDef.setText(builder.toString());
    }
}
