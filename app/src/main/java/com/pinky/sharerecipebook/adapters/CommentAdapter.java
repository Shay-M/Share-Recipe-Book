package com.pinky.sharerecipebook.adapters;/* Created by Shay Mualem 23/07/2021 */

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.Comment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Map<String, Comment> commentHashMap;
    private ArrayList<Comment> commentItemList;

    //callback fun
    private RecyclerViewListener clicksListener;
    private Context context;

    // constructor
    public CommentAdapter(ArrayList<Comment> listOfCommentItems, Context context) {
        this.commentItemList = listOfCommentItems;
        this.context = context;
    }

    public CommentAdapter(Map<String, Comment> commentHashMap, Context context) {
        this.commentHashMap = commentHashMap;
        Collection<Comment> values = commentHashMap.values();
        ArrayList<Comment> listOfValues = new ArrayList<>(values);
        commentItemList = listOfValues;
        this.context = context;
    }

    // allows clicks events to be caught
    public void setClicksListener(RecyclerViewListener clicksListener) {
        this.clicksListener = clicksListener;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item_cell, parent, false);
        return new CommentViewHolder(view); //@return Inflated the view xml
    }

    //call to load a cell ,this fun get parameter for a cell
    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment CommentItem = commentItemList.get(position);

        //set Comment text
        holder.commentTv.setText(CommentItem.getTxt());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        //  .parse(CommentItem.getPostDate()).toString();

        String postDate = simpleDateFormat.format(new Date());


        //set Comment date
        holder.dateTv.setText(String.format(postDate));

        //set img
        ImageView imageUserView = holder.commentUserIv;
        //new Boom(imageUserView);




        /*Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1500) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.5f) //the alpha of the underlying children
                .setHighlightAlpha(0.3f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build();

        // This is the placeholder for the imageView
        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);

        if (RecipeItem.getImagePath() != null)
            Glide.with(holder.recipeImageIv.getContext())
                    .load(RecipeItem.getImagePath())
                    .thumbnail(0.10f)
                    .centerCrop()
                    .placeholder(shimmerDrawable)
                    .error(android.R.drawable.ic_dialog_info)
                    .into(imageView);
        else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(context).clear(holder.recipeImageIv);
            // remove the placeholder (optional)
            holder.recipeImageIv.setImageDrawable(null);
            Log.d("Glide", "onBindViewHolder: is null");
        }*/

    }

    // return how many item have
    @Override
    public int getItemCount() {
        return commentItemList.size();
    }

    // when we have only one type of cell, this fun first call. | position the next cell to show
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    // interface to manager all RecyclerView events
    public interface RecyclerViewListener {
        void onItemClick(int position, View view);
    }

    //crete view holder, for hold reference in a cell
    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView commentTv;
        TextView dateTv;
        ImageView commentUserIv;


        public CommentViewHolder(View itemView) {
            super(itemView);
            commentTv = itemView.findViewById(R.id.comment_text);
            dateTv = itemView.findViewById(R.id.comment_date);
            commentUserIv = itemView.findViewById(R.id.comment_user_img);


            itemView.setOnClickListener(view -> {
                if (clicksListener != null)
                    clicksListener.onItemClick(getAdapterPosition(), view);
            });


        }


    }

}

