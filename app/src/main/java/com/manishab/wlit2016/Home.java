package com.manishab.wlit2016;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.NameList;


public class Home extends AppCompatActivity {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerlayout= (DrawerLayout)findViewById(R.id.d_drawer);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState==null){
            Fragment wlitFragment=new WLITClass();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,wlitFragment,null);
            fragmentTransaction.commit();
        }
        navigationView=(NavigationView)findViewById(R.id.t_navigation);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle=new ActionBarDrawerToggle(this,drawerlayout,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("List");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Wlit 2016");
            }
        };
        toggle.syncState();

        drawerlayout.setDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerlayout.closeDrawers();

                switch (item.getItemId()){
                    case R.id.wlit_list:
                        Fragment wlitFragment=new WLITClass();
                        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content,wlitFragment,null);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.namelist:
                        Intent intent=new Intent(Home.this,Namelist.class);
                        startActivity(intent);
                        Toast.makeText(Home.this,"NameList activity",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.about:
                        Toast.makeText(Home.this,"About activity",Toast.LENGTH_LONG).show();
                    case R.id.contact:
                        Toast.makeText(Home.this,"Contact activity",Toast.LENGTH_LONG).show();
                    case R.id.link:
                        Toast.makeText(Home.this,"Link activity",Toast.LENGTH_LONG).show();
                        return true;
                    default:
                        Toast.makeText(Home.this,"NameList activity",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.navigationmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
}
