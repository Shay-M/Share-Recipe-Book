package com.pinky.sharerecipebook.view.fragments.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.CommentAdapter;
import com.pinky.sharerecipebook.models.Comment;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;
import com.pinky.sharerecipebook.utils.HidesKeyboard;

import java.util.HashMap;
import java.util.Map;


public class uiCommentsFragment extends Fragment {

    private TextInputLayout commentEditText;
    private CommentAdapter commentAdapter;
    private Map<String, Comment> mcommentHashMap;
    private String mrecipeId;
    private String mfirebaseUserIdCommentl;


    public uiCommentsFragment(String recipeId, Map<String, Comment> commentHashMap, String firebaseUserIdComment) {

        if (commentHashMap == null)
            mcommentHashMap = new HashMap<>();
        else
            mcommentHashMap = commentHashMap;

        mfirebaseUserIdCommentl = firebaseUserIdComment;
        mrecipeId = recipeId;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Collections.sort(arrayList, new Comparator<HashMap< String,Comment >>() {});

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_comments, container, false);
        commentEditText = view.findViewById(R.id.ui_fragment_comments_textinput);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_of_comments);
        recyclerView.setHasFixedSize(true);


        commentAdapter = new CommentAdapter(mcommentHashMap, getActivity());


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(commentAdapter);


        commentEditText.setEndIconOnClickListener(v -> {
            String commentTxt = commentEditText.getEditText().getText().toString();

            if (commentEditText.getEditText().getText().toString().length() > 2) {
                sendComment(commentTxt);
                commentEditText.getEditText().setText("");
            } //else commentEditText.setError("Write a comment");


        });

    }

    private void sendComment(String commentTxt) {
        HidesKeyboard.hideKeyboard(this.getActivity());

        Comment comment = new Comment(commentTxt, mfirebaseUserIdCommentl);

        String folder = "recipe";
        String fieldToChange = "commentArrayListHashMap";
        FirebaseDatabaseRepository.getInstance().changeDataFirebase(folder, mrecipeId, fieldToChange, comment);
        mcommentHashMap.put("temp", comment);
        commentAdapter.UpdateAdapter(comment);
//        commentAdapter.notifyDataSetChanged();
        commentAdapter.notifyItemInserted(0);

    }
}