package com.example.superheroes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import com.google.androidgamesdk.gametextinput.Listener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements OnItemClickListener,NavigationView.OnNavigationItemSelectedListener {
    private static MainActivity instance;
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private String heroinfo;
    RecyclerView_Adapter recyclerView_adapter,gender_adapter;
    ArrayList<BasicInfo> basicInfoArrayList, favHerolist,herogenderlist;
    RecyclerView Herolist,Genderlist;
    private String url;
    LinearLayoutManager manager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageButton favbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        instance = this;
        Herolist = findViewById(R.id.recyclerView);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getRetrofit();
        jsonPlaceHolderApi = getRetrofit();
        getAllNames();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private JsonPlaceHolderApi getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl("https://akabab.github.io/superhero-api/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return (retrofit.create(JsonPlaceHolderApi.class));
    }

    @Override
    public void onitemclick(int position) {
        BasicInfo superhero = basicInfoArrayList.get(position);
        String powerstats = "Intelligence :" + superhero.getPowerStats().getIntelligence()
                + "\n" + "Strength :" + superhero.getPowerStats().getStrength() + "\n" + "Speed :" + superhero.getPowerStats().getSpeed() + "\n" + "Durability :" + superhero.getPowerStats().getDurability();


        String Appearance = "Gender :" + superhero.getAppearance().getGender()
                + "\n" + "Race :" + superhero.getAppearance().getRace() + "\n" + "Height :" + Arrays.toString(superhero.getAppearance().getHeight()) + "\n" + "Weight :" + Arrays.toString(superhero.getAppearance().getWeight()) + "\n" +
                "Eyecolor :" + superhero.getAppearance().getEyecolor() + "\n" + "Haircolor :" + superhero.getAppearance().getHaircolor();


        String Biography = "Fullname :" + superhero.getBiography().getFullname() + "\n" + "AlterEgos :" + superhero.getBiography().getAlteregos() + "\n"
                + "Aliases :" + Arrays.toString(superhero.getBiography().getAliases()) + "\n" + "Place of Birth :" + superhero.getBiography().getPlaceofbirth() + "\n" + "First Appearance :" + superhero.getBiography().getFirstappearance() + "\n" + "Publisher :" + superhero.getBiography().getPublisher() + "\n"
                + "Alignment :" + superhero.getBiography().getAlignment();


        String Work = "Occupation :" + superhero.getWork().getOccupation() + "\n" + "Base :" + superhero.getWork().getBase();


        String Connections = "GroupAffiliation :" + superhero.getConnections().getGroupAffiliation() + "\n"
                + "Relatives :" + superhero.getConnections().getRelatives();
        String shareinfo = "Fullname :" + superhero.getBiography().getFullname() + "Gender :" + superhero.getAppearance().getGender() + "Place of Birth :" + superhero.getBiography().getPlaceofbirth() + "\n" + "First Appearance :" + superhero.getBiography().getFirstappearance()
                + "\n" + "Race :" + superhero.getAppearance().getRace() + "\n" + "Height :" + Arrays.toString(superhero.getAppearance().getHeight()) + "\n" + "Weight :" + Arrays.toString(superhero.getAppearance().getWeight()) + "\n" +
                "Eyecolor :" + superhero.getAppearance().getEyecolor() + "\n" + "Haircolor :" + superhero.getAppearance().getHaircolor();


        url = superhero.getImages().getSize();
        Intent intent = new Intent(this, HeroInfo.class);
        intent.putExtra("name", String.valueOf(superhero.getName()));
        intent.putExtra("powerstats", powerstats);
        intent.putExtra("appearance", Appearance);
        intent.putExtra("biography", Biography);
        intent.putExtra("work", Work);
        intent.putExtra("connections", Connections);
        intent.putExtra("url", url);
        intent.putExtra("shareinfo", shareinfo);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.males) {

            recyclerView_adapter.notifyDataSetChanged();
            searchHeroes("Male");

        }
        if (id == R.id.females) {

            recyclerView_adapter.notifyDataSetChanged();
            searchHeroes("Female");

        }
        if (id == R.id.favorite) {
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    public void searchHeroes(String text) {
     herogenderlist = new ArrayList<>();

        for (BasicInfo hero : basicInfoArrayList) {

            if (hero.getAppearance().getGender().equals(text))
            {
                herogenderlist.add(hero);
            }

        }

        if (herogenderlist.isEmpty()) {
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
        }
        else {
            recyclerView_adapter.genderlist(herogenderlist);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });

        return true;
    }

    public void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<BasicInfo> superherofilterlist = new ArrayList<>();

        for (BasicInfo hero : basicInfoArrayList) {

            if (hero.getName().toLowerCase().contains(text.toLowerCase())) {
                superherofilterlist.add(hero);
            }
        }
        if (superherofilterlist.isEmpty()) {
            closeKeyboard();
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();

        } else {
            recyclerView_adapter.filterList(superherofilterlist);

        }


    }

    public void getAllNames() {
        Call<List<BasicInfo>> call = jsonPlaceHolderApi.getBasicInfo();

        call.enqueue(new Callback<List<BasicInfo>>() {
            @Override
            public void onResponse(Call<List<BasicInfo>> call, Response<List<BasicInfo>> response) {
                List<BasicInfo> list = response.body();
                basicInfoArrayList = new ArrayList<>(list);
                herogenderlist=new ArrayList<>(list);
                manager = new LinearLayoutManager(MainActivity.this);
                recyclerView_adapter = new RecyclerView_Adapter(MainActivity.this, basicInfoArrayList);
                Herolist.setLayoutManager(manager);
                Herolist.setAdapter(recyclerView_adapter);
                recyclerView_adapter.setOnitemclicklistenerList(MainActivity.this);

            }

            @Override
            public void onFailure(Call<List<BasicInfo>> call, Throwable t) {

            }
        });


    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}




