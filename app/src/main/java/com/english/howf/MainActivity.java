package com.english.howf;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name;
        String email;
        Bundle bundle=getIntent().getBundleExtra("bundle");
        name=bundle.getString("name");
        email=bundle.getString("email");
        NavigationDrawer navigationDrawer=(NavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.navigationdrawer);
        navigationDrawer.init(name,email);
        navigationDrawer.setup(R.id.navigationdrawer,(DrawerLayout)findViewById(R.id.drawerlayout),toolbar);




    }
}
