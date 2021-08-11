package com.example.superheroes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private String heroinfo ;
    RecyclerView_Adapter recyclerView_adapter;
    ArrayList<BasicInfo> basicInfoArrayList;
    RecyclerView Herolist;
    private String url;
    LinearLayoutManager manager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Herolist = findViewById(R.id.recyclerView);
        getRetrofit();
        jsonPlaceHolderApi = getRetrofit();
        getAllNames();

   /*     ShowAppearance();
        ShowBiography();
        ShowWork();
        ShowConnections();
        //Showimages();
*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
        return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAllNames() {
        Call<List<BasicInfo>> call=jsonPlaceHolderApi.getBasicInfo();

     call.enqueue(new Callback<List<BasicInfo>>() {
         @Override
         public void onResponse(Call<List<BasicInfo>> call, Response<List<BasicInfo>> response) {
             List<BasicInfo> list = response.body();
             basicInfoArrayList = new ArrayList<>(list);
             manager = new LinearLayoutManager(MainActivity.this);
             recyclerView_adapter = new RecyclerView_Adapter(MainActivity.this,basicInfoArrayList);
             Herolist.setLayoutManager(manager);
             Herolist.setAdapter(recyclerView_adapter);
             recyclerView_adapter.setOnitemclicklistenerList(MainActivity.this);
         }

         @Override
         public void onFailure(Call<List<BasicInfo>> call, Throwable t) {

         }
     });


    }
    private JsonPlaceHolderApi getRetrofit() {

        if(retrofit==null)
        {
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
        heroinfo = "Name :" + superhero.getName() +"\n"+"Slug :"+superhero.getSlug()+"\n\n"+"PowerStats"+"\n"+"Intelligence :"+superhero.getPowerStats().getIntelligence()
                +"\n" + "Strength :"+superhero.getPowerStats().getStrength()+"\n"+"Speed :" +superhero.getPowerStats().getSpeed()+"\n"+"Durability :"+superhero.getPowerStats().getDurability()
                +"\n"+ "Power :"+superhero.getPowerStats().getPower()+"\n"+"Combat :"+superhero.getPowerStats().getCombat()+"\n\n"+"Appearance"+"\n"+"Gender :"+superhero.getAppearance().getGender()
                +"\n" + "Race :"+ superhero.getAppearance().getRace()+"\n"+"Height :"+ Arrays.toString(superhero.getAppearance().getHeight()) +"\n" +"Weight :"+ Arrays.toString(superhero.getAppearance().getWeight())+"\n"+
                "Eyecolor :"+superhero.getAppearance().getEyecolor()+"\n"+"Haircolor :"+superhero.getAppearance().getHaircolor()+"\n\n"+"Biography"+"\n"+"Fullname :"+superhero.getBiography().getFullname()+"\n"+"AlterEgos :" + superhero.getBiography().getAlteregos()+"\n"
                +"Aliases :"+ Arrays.toString(superhero.getBiography().getAliases()) +"\n"+"Place of Birth :"+ superhero.getBiography().getPlaceofbirth()+"\n"+"First Appearance :"+superhero.getBiography().getFirstappearance()+"\n"+"Publisher :"+superhero.getBiography().getPublisher()+"\n"
                +"Alignment :"+superhero.getBiography().getAlignment()+"\n\n"+"Work"+"\n"+"Occupation :"+superhero.getWork().getOccupation()+"\n"+"Base :"+superhero.getWork().getBase()+"\n\n"+"Connections"+"\n"+"GroupAffiliation :"+superhero.getConnections().getGroupAffiliation()+"\n"
                +"Relatives :"+superhero.getConnections().getRelatives();
        url =superhero.getImages().getSize();
        Intent intent = new Intent(this,HeroInfo.class);
        intent.putExtra("herodetail",heroinfo);
        intent.putExtra("url",url);
        startActivity(intent);
    }

}

