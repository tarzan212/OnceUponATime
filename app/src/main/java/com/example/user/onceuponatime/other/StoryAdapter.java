package com.example.user.onceuponatime.other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.onceuponatime.R;



import java.util.List;

/**
 * Created by User on 09/03/2017.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>{

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.single_story_main,parent,false);

        StoryViewHolder storyViewHolder = new StoryViewHolder(view);

        return storyViewHolder;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        //TODO Utiliser Firebase database et binder les data
    }


    @Override
    public int getItemCount() {
        //TODO recuperer le count de firebase et le remplacer ici
        return 0;
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {

        TextView storyTitle;
        TextView storyBody;
        TextView storyDetails;

        public StoryViewHolder(View itemView) {
            super(itemView);

            storyTitle = (TextView) itemView.findViewById(R.id.story_title);
            storyBody = (TextView) itemView.findViewById(R.id.main_story);
            storyDetails = (TextView) itemView.findViewById(R.id.story_details);

        }
    }
}
