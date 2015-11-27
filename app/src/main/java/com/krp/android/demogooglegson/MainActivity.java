package com.krp.android.demogooglegson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    private TextView tvJsonString, tvJsonObj;
    private TextView tvLoadClass, tvClassModifiers;
    private TextView tvFindSuperClass, tvFindInterfaces;
    private TextView tvFindConstructors, tvFindStringParametrizedConstructor;
    private TextView tvFindMethods, tvFindFields;
    private TextView tvInstanceClass;

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
        tvFindSuperClass = (TextView) findViewById(R.id.tv_find_super_class);
        tvFindInterfaces = (TextView) findViewById(R.id.tv_find_interfaces);
        tvFindConstructors = (TextView) findViewById(R.id.tv_find_constructors);
        tvFindStringParametrizedConstructor = (TextView) findViewById(R.id.tv_find_string_param_constructor);
        tvFindMethods = (TextView) findViewById(R.id.tv_find_methods);
        tvFindFields = (TextView) findViewById(R.id.tv_find_fields);
        tvInstanceClass = (TextView) findViewById(R.id.tv_instantiate_class);

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

    private boolean isClassLoaded() {
        if(testClass == null) {
            tvLoadClass.setText("Load class first");
            tvLoadClass.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            return false;
        }
        return true;
    }

    public void onLoadClassModifiers(View v) {
        if(isClassLoaded()) {
            int modifiers = testClass.getModifiers();
            tvClassModifiers.setText(Modifier.toString(modifiers));
        }
    }

    public void onFindSuperClass(View v) {
        if(isClassLoaded()) {
            Class superClass = testClass.getSuperclass();
            tvFindSuperClass.setText(superClass.getName());
        }
    }

    public void onFindImplementedInterfaces(View v) {
        if(isClassLoaded()) {
            Class[] implementedInterfaces = testClass.getInterfaces();
            builder = new StringBuilder();
            for(Class implementedInterface : implementedInterfaces) {
                builder.append(implementedInterface.getSimpleName()).append("\n");
            }
            tvFindInterfaces.setText(builder.toString());
        }
    }

    public void onFindConstructors(View v) {
        if(isClassLoaded()) {
            Constructor[] constructors = testClass.getConstructors();
            builder = new StringBuilder();
            for(Constructor constructor : constructors) {
                builder.append(constructor.getName()).append("\n");
            }
            tvFindConstructors.setText(builder.toString());
        }
    }

    public void onFindStringParametrizedConstructor(View v) {
        if(isClassLoaded()) {
            Constructor constructor;
            try {
                constructor = testClass.getConstructor(String.class);
                // constructor = testClass.getConstructor(String.class, String.class);
                // constructor = testClass.getConstructor(new Class[] {String.class});
                // constructor = testClass.getConstructor(Integer.class); // Showing an exception as no such constructor found

                tvFindStringParametrizedConstructor.setText(constructor.getName());
            } catch (NoSuchMethodException e) {
                tvFindStringParametrizedConstructor.setText(e.getClass().getSimpleName() + "\nexception occured");
                tvFindStringParametrizedConstructor.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        }
    }

    public void onFindMethods(View v) {
        if(isClassLoaded()) {
            Method[] methods = testClass.getMethods();
            builder = new StringBuilder();
            for(Method method : methods) {
                builder.append(method.getName()).append("\n");
            }
            tvFindMethods.setText(builder.toString());
        }
    }

    public void onFindFields(View v) {
        if(isClassLoaded()) {
            Field[] fields = testClass.getFields();
            builder = new StringBuilder();
            for(Field field : fields) {
                builder.append(Modifier.toString(field.getModifiers())).append(" ")
                        .append(field.getName()).append("\n");
            }
            tvFindFields.setText(builder.toString());
        }
    }

    public void onClassInstantiation(View v) {
        if(isClassLoaded()) {
            builder = new StringBuilder();

            Constructor[] constructors = testClass.getConstructors();
            for(Constructor constructor : constructors) {
                switch (constructor.getParameterTypes().length) {
                    case 0:
                    default:
                        builder.append("No param instance:").append("\n");
                        try {
                            TestClassReflection noParmInstance = (TestClassReflection) constructor.newInstance();
                            builder.append(noParmInstance).append("\n\n");
                        } catch (InstantiationException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        } catch (IllegalAccessException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        } catch (InvocationTargetException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        }
                        break;

                    case 1:
                        builder.append("Single param instance:").append("\n");
                        try {
                            TestClassReflection singleParmaInstance = (TestClassReflection) constructor.newInstance("root");
                            builder.append(singleParmaInstance).append("\n\n");
                        } catch (InstantiationException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        } catch (IllegalAccessException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        } catch (InvocationTargetException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        }
                        break;

                    case 2:
                        builder.append("Two param instance:").append("\n");
                        try {
                            TestClassReflection dualParamInstance = (TestClassReflection) constructor.newInstance("root", "root");
                            builder.append(dualParamInstance);
                        } catch (InstantiationException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        } catch (IllegalAccessException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        } catch (InvocationTargetException e) {
                            builder.append(e.getClass().getSimpleName() + "\nexception occured");
                            builder.append("*****************");
                        }
                        break;
                }
            }
            tvInstanceClass.setText(builder.toString());
        }
    }
}
