package com.pinky.sharerecipebook.adapters;/* Created by Shay Mualem 21/08/2021 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.Recipe;


import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.NoteHolder> {
    private List<Recipe> recipeArrayList = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_cell, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Recipe currentNote = recipeArrayList.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        //holder.textViewDescription.setText(currentNote.getDescription());
        //holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    /**
     * get the notes from liveDate
     */
    public void setNotes(List<Recipe> recipes) {
        this.recipeArrayList = recipes;
        notifyDataSetChanged();
    }


    class NoteHolder extends RecyclerView.ViewHolder {//

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.recipe_item_title_text);
            //textViewDescription = itemView.findViewById(R.id.text_view_description);
            //textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }

//    https://www.youtube.com/watch?v=RhGMd8SsA14&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=7
}
