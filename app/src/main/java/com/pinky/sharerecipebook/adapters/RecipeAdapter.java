package com.pinky.sharerecipebook.adapters;/* Created by Shay Mualem 23/07/2021 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements Filterable {
    private ArrayList<Recipe> recipeItemList;
    private ArrayList<Recipe> recipeItemListFull;

    //callback fun
    private RecyclerViewListener clicksListener;
    private Context context;

    // search filter
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Recipe> filteredList = new ArrayList<Recipe>();
            Log.d("performFiltering", "constraint: " + constraint);
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipeItemListFull); // show all items
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Recipe item : recipeItemListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeItemList.clear();
            recipeItemList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    // constructor
    public RecipeAdapter(ArrayList<Recipe> listOfRecipesItems, Context context) {
        this.recipeItemList = listOfRecipesItems;
        this.context = context;
        recipeItemListFull = new ArrayList<>(recipeItemList); //copy to the new list
    }

    // allows clicks events to be caught
    public void setClicksListener(RecyclerViewListener clicksListener) {
        this.clicksListener = clicksListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_cell, parent, false);
        new Boom(view);

        return new RecipeViewHolder(view); //@return Inflated the view xml
    }

    //call to load a cell ,this fun get parameter for a cell
    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe RecipeItem = recipeItemList.get(position);

        //set recipe title
        holder.recipeTitleTv.setText(RecipeItem.getTitle());
        //set recipe rating
        holder.recipeRatingTv.setText(String.valueOf(RecipeItem.getRank()));
//        holder.recipeRatingTv.setText(RecipeItem.getRank());
        //set img
        ImageView imageView = holder.recipeImageIv;///////

        //todo Shimmer ? https://stackoverflow.com/questions/61076174/how-to-use-a-view-shimmer-as-a-placeholder-for-an-imageview-glide
        if (RecipeItem.getImagePath() != null)
            Glide.with(holder.recipeImageIv.getContext())
                    .load(RecipeItem.getImagePath())
                    .thumbnail(0.10f)
                    .centerCrop()
//                    .placeholder(R.drawable.common_google_signin_btn_icon_dark) // todo change img or not need?
                    .error(android.R.drawable.ic_dialog_info)
                    .into(imageView);
        else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(context).clear(holder.recipeImageIv);
            // remove the placeholder (optional)
            holder.recipeImageIv.setImageDrawable(null);
            Log.d("Glide", "onBindViewHolder: is null");
        }

    }

    // return how many item have
    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }

    // when we have only one type of cell, this fun first call. | position the next cell to show
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    // interface to manager all RecyclerView events
    public interface RecyclerViewListener {

        void onItemClick(int position, View view);

        //void onLongClick(int position, View view);

        void onImgRatingClick(int position, View view);
    }

    //crete view holder, for hold reference in a cell
    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView recipeTitleTv;
        TextView recipeRatingTv;
        ImageView recipeImageIv;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeTitleTv = itemView.findViewById(R.id.recipe_item_title_text);
            recipeRatingTv = itemView.findViewById(R.id.recipe_item_rating_text);
            recipeImageIv = itemView.findViewById(R.id.recipe_item_img);


            itemView.setOnClickListener(view -> {
                if (clicksListener != null)
                    clicksListener.onItemClick(getAdapterPosition(), view);
            });


            /*recipeRatingTv.setOnClickListener(view -> {
                if (clicksListener != null)
                    clicksListener.onImgRatingClick(getAdapterPosition(), view);
            });*/

        }


    }

}

