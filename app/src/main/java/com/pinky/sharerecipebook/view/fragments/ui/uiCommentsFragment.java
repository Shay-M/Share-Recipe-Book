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
import com.pinky.sharerecipebook.utils.HidesKeyboard;

import java.util.HashMap;
import java.util.Map;


public class uiCommentsFragment extends Fragment {

    private TextInputLayout commentEditText;
    private CommentAdapter commentAdapter;
    private Map<String, Comment> commentHashMap;
    private String firebaseUserId;


    public uiCommentsFragment(Map<String, Comment> commentHashMapGet, String firebaseUserIdGet) {

        if (commentHashMapGet == null)
            this.commentHashMap = new HashMap<>();
        else
            this.commentHashMap = commentHashMapGet;

        firebaseUserId = firebaseUserIdGet;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        commentAdapter = new CommentAdapter(commentHashMap, getActivity());


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(commentAdapter);


        commentEditText.setEndIconOnClickListener(v -> {
            String commentTxt = commentEditText.getEditText().getText().toString();
            sendAcomment(commentTxt);
        });


    }

    private void sendAcomment(String commentTxt) {
        HidesKeyboard.hideKeyboard(this.getActivity());

        Comment comment = new Comment(commentTxt, firebaseUserId);


    }
}