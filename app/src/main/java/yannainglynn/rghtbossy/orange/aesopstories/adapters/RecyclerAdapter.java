package yannainglynn.rghtbossy.orange.aesopstories.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yannainglynn.rghtbossy.orange.aesopstories.R;
import yannainglynn.rghtbossy.orange.aesopstories.fragments.StoryDetail;
import yannainglynn.rghtbossy.orange.aesopstories.models.Stories;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{
    Context context;
    List<Stories> storiesList;

    public RecyclerAdapter(Context context, List<Stories> productList) {
        this.context = context;
        this.storiesList = productList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listview,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, @SuppressLint("RecyclerView") final int i) {
        myHolder.tvName.setText(storiesList.get(i).getTitle());
        myHolder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StoryDetail storyDetail = new StoryDetail();
                        Bundle args = new Bundle();
                        int id = storiesList.get(i).getId();
                        args.putInt("storiesAllId",id);
                        storyDetail.setArguments(args);
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, storyDetail).addToBackStack(null).commit();

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.titleMain);
        }
    }
    public void setFilter(ArrayList<Stories> newList){
        storiesList = new ArrayList<>();
        storiesList.addAll(newList);
        notifyDataSetChanged();
    }


}
