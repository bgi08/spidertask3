package com.example.superheroes;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.View_Holder> {

    private ArrayList<BasicInfo> basicInfoArrayList;
    private  Context context;
    private OnItemClickListener listener;
    ImageButton favdeatailbutton;

    public RecyclerView_Adapter( Context mcontext,ArrayList<BasicInfo> basicInfoArrayList1) {
        this.basicInfoArrayList=basicInfoArrayList1;
        this.context = mcontext;
    }
    public void filterList(ArrayList<BasicInfo> superherofilterllist) {
        basicInfoArrayList = superherofilterllist;
        notifyDataSetChanged();
    }
    public void genderlist(ArrayList<BasicInfo> Genderlist)
    {
        basicInfoArrayList=Genderlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView_Adapter.View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(context).inflate(R.layout.hero_item, parent, false);
        return new View_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position)
    {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        BasicInfo currenthero=basicInfoArrayList.get(position);
        int id=currenthero.getId();
        String name=currenthero.getName();
        Images image=currenthero.getImages();
        String html = "<b>"+currenthero.getId()+"."+currenthero.getName()+"</b>";

               holder.name.setText(Html.fromHtml(html));

        Picasso.get().load(currenthero.getImages().getSize()).fit().centerInside().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                holder.name.setText("Sorry error has occured");
            }
        });

    }

    @Override
    public int getItemCount() {

        return basicInfoArrayList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {

         ImageView imageView;
        private TextView name;

        public View_Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.heroname);
            imageView=itemView.findViewById(R.id.superheroimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onitemclick(position);
                    }
                }
            });

        }

        }
    public void setOnitemclicklistenerList( OnItemClickListener onitemclicklistener)
    {
        listener = onitemclicklistener;
    }
}