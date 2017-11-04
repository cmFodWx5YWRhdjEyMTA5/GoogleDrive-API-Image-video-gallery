package com.expose.drive;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.expose.drive.model.Model;
import com.expose.drive.rest.ApiClient;
import com.expose.drive.rest.ApiInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView video_recyclerview;
    DriveAdapter  driveAdapter;
    List<Model> VIDEOS;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        VIDEOS = new ArrayList<>();

        video_recyclerview =(RecyclerView)findViewById(R.id.video_recyclerview);




        prepareData();


    }

    private void prepareData() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Model> call = apiService.getDrivelist();
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if (response.isSuccessful())
                {

                    VIDEOS =response.body().getFILES();
                    adapter();
                }

                else
                {
                    Toast.makeText(MainActivity.this, "response getting error !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {


            }
        });

    }

    private void adapter() {

        driveAdapter=new DriveAdapter(getApplicationContext(),VIDEOS);
        layoutManager =new GridLayoutManager(getApplicationContext(),2);
        video_recyclerview.setHasFixedSize(true);
        video_recyclerview.setLayoutManager(layoutManager);
        video_recyclerview.setAdapter(driveAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DriveAdapter extends RecyclerView.Adapter<MyviewHolder> {
        Context context;
        List<Model> videolist;
        public DriveAdapter(Context context,List<Model> videolist) {
            this.context=context;
            this.videolist=videolist;
        }

        @Override
        public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View layoutview = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);

            return  new MyviewHolder(layoutview);
        }

        @Override
        public void onBindViewHolder(MyviewHolder holder, final int position) {
            final Model m=videolist.get(position);
          holder.name.setText(m.getNAME());


            Glide.with(getApplicationContext()).load("https://drive.google.com/uc?export=view&id="+m.getID())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);

        }



        @Override
        public int getItemCount() {
            return videolist.size();
        }
    }

    private class MyviewHolder extends RecyclerView.ViewHolder {
ImageView image;
        TextView name;
        public MyviewHolder(View itemView) {

            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.image);
            name=(TextView)itemView.findViewById(R.id.name);



        }
    }
}
