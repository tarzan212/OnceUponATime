package com.example.user.onceuponatime.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.onceuponatime.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;

    private String mUid;

    private View navHeader;


    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private DrawerLayout drawerLayout;
    private TextView nameField,storiesCountField;
    private Toolbar mToolbar;
    private RecyclerView mRecycler;

    private String[] activityTitles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if(mUser == null) {
            Intent intent = new Intent(this,AuthentificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            mUid = mUser.getUid();
        }



        mRecycler =(RecyclerView) findViewById(R.id.main_recycler);
        navigationView = (NavigationView) findViewById(R.id.nav_main);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingbutton);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);


        navHeader = navigationView.getHeaderView(0);
        nameField = (TextView) navHeader.findViewById(R.id.username_header);
        storiesCountField = (TextView) navHeader.findViewById(R.id.count_stories_involved_header);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,mToolbar,R.string.drawer_opened,R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


    actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.user_logout) {
            mAuth.signOut();
            Intent intentSignedOut = new Intent(MainActivity.this,AuthentificationActivity.class);

            Toast.makeText(this,R.string.logout_success,Toast.LENGTH_LONG).show();

            startActivity(intentSignedOut);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }
    //TODO 1 Regler le probleme des fragments -- ALMOST COMPLETE MAINLY CAUSED BY DATABINDING
    //TODO 2 Recup infos de firebase pr articles
    //TODO 3 Utiliser un recyclerview et un cardview pour afficher les histoires
    //TODO 4 possibilité de changer les histoires
    //TODO 5 espace membre
    //TODO 6 Preferences
    //TODO 7 Ameliorer le design
}
