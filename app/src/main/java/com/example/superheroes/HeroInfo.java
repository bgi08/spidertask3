package com.example.superheroes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class HeroInfo extends AppCompatActivity {

    private ImageView imageView1;
    private String powerstats,appearance,biography,work,connections,name,sharedinfo;
    private String url;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    ImageButton favdeatailbutton,sharebtn;
    BasicInfo basicInfo;
    boolean buttonpress=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_details);
        t1=findViewById(R.id.NameDetail);
        t2=findViewById(R.id.PowerStats);
        t3=findViewById(R.id.PowerStatsContent);
        t4=findViewById(R.id.Appearance);
        t5=findViewById(R.id.ApperanceContent);
        t6=findViewById(R.id.Biography);
        t7=findViewById(R.id.BiographyContent);
        t8=findViewById(R.id.Work);
        t9=findViewById(R.id.WorkContent);
        t10=findViewById(R.id.Connections);
        t11=findViewById(R.id.ConnectionsContent);
        sharebtn=findViewById(R.id.imageButton);
        sharedinfo=getIntent().getStringExtra("shareinfo");
        name=getIntent().getStringExtra("name");
        t1.setText(name);
        t2.setText("Powerstats:");
        powerstats=getIntent().getStringExtra("powerstats");
        t3.setText(powerstats);
        t4.setText("Appearance:");
        appearance=getIntent().getStringExtra("appearance");
        t5.setText(appearance);
        t6.setText("Biography:");
        biography=getIntent().getStringExtra("biography");
        t7.setText(biography);
        t8.setText("Work:");
        work=getIntent().getStringExtra("work");
        t9.setText(work);
        t10.setText("Connections:");
        connections=getIntent().getStringExtra("connections");
        t11.setText(connections);


        imageView1=findViewById(R.id.heroimage);

        favdeatailbutton=findViewById(R.id.imageButton3);
        url=getIntent().getStringExtra("url");
        Picasso.get().load(url).fit().centerInside().into(imageView1, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
            t1.setText("Sorry error has occured");
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable= (BitmapDrawable) imageView1.getDrawable();
                Bitmap bitmap= drawable.getBitmap();
                String bitmappath = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Hero","Image");
                Uri uri = Uri.parse(bitmappath);
                Intent intent =new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM,uri);

                intent.putExtra(Intent.EXTRA_TEXT,sharedinfo);
                startActivity(Intent.createChooser(intent,"Share"));
            }
        });
        favdeatailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonpress==false) {
                    favdeatailbutton.setBackground(getResources().getDrawable(R.drawable.starfill));
                buttonpress=true;
                }
                else
                {
                    favdeatailbutton.setBackground(getResources().getDrawable(R.drawable.star));
                    buttonpress=false;
                }

            }
        });

    }



}

