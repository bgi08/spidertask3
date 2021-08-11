package com.example.superheroes;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HeroInfo extends AppCompatActivity {
    private  TextView textView1;
    private ImageView imageView1;
    private String info;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_details);
        info=getIntent().getStringExtra("herodetail");
        textView1=findViewById(R.id.herodetails);
        imageView1=findViewById(R.id.heroimage);
        textView1.setText(info);
        url=getIntent().getStringExtra("url");
        Picasso.get().load(url).fit().centerInside().into(imageView1, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
            textView1.setText("Sorry error has occured");
            }
        });

    }
}
