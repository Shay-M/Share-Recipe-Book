package com.pinky.sharerecipebook.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.FirebaseStorgeRepository;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;
import com.pinky.sharerecipebook.view.LoadingDialog;

public class UserProfileFragment extends Fragment {
    //((AppCompatActivity) getActivity()).getSupportActionBar();

    ActivityResultLauncher<Uri> cameraFullSizeResultLauncher; //big img
    ActivityResultLauncher<String> pickContentResultLauncher;
    Uri photoURI;
    private TextInputLayout userNameTInput;

    private TextView userEmailTV;
    private Button saveButton;

    private ImageView userImage;
    private User LoginUserGet;
    private CameraManagerUrl cameraManagerUrl;

    private LoadingDialog loadingDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");

        userImage = view.findViewById(R.id.fragment_user_profile_user_image);
        userNameTInput = view.findViewById(R.id.fragment_user_profile_name_input);
        userEmailTV = view.findViewById(R.id.fragment_user_profile_email_textView);

        //switchEdit = view.findViewById(R.id.fragment_user_profile_switch_edit);
        saveButton = view.findViewById(R.id.fragment_user_profile_save_button);

        //userNameTInput.setEnabled(false);
        loadingDialog = new LoadingDialog(this);

        new Boom(userImage);

        initLaunchers();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginUserGet = (User) requireArguments().getSerializable("expandLoginUser");

        userNameTInput.getEditText().setText(LoginUserGet.getName());
        userEmailTV.setText(LoginUserGet.getEmailAddress());

        Glide.with(this)
                .load(LoginUserGet.getUserImagePath())
                .circleCrop()
                .error(R.drawable.ic_twotone_person_outline_24)
                .into(userImage);

        userImage.setOnClickListener(v -> {
            picFromGalleria();
        });

        // save
        saveButton.setOnClickListener(v -> {


            String userName = userNameTInput.getEditText().getText().toString();

            if (userName.isEmpty()) {
                userNameTInput.setError("Give a name");
                userNameTInput.requestFocus();

            } else if (photoURI == null) {
                Log.d("floating_attach_recipe", "photoURI is Empty!");
            }

            loadingDialog.startLoadingDialog();

            FirebaseStorgeRepository.getInstance().UploadFile(photoURI, "users", new FirebaseStorgeRepository.OnTaskDownloadUri() {

                @Override
                public void onSuccess(Uri downloadUri) {
                    loadingDialog.dismissLoadingDialog();

                }

                @Override
                public void onFailure() {
                    loadingDialog.dismissLoadingDialog();

                }

                @Override
                public void onProgress(double progressNum) {

                }
            });
        });


    }

    private void initLaunchers() {
        // gallery
        pickContentResultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        photoURI = result;
                        Glide.with(this)
                                .load(photoURI)
                                .circleCrop()
                                .error(android.R.drawable.ic_dialog_info)
                                .into(userImage);

                    }
                });
    }

    private void picFromGalleria() {
        pickContentResultLauncher.launch("image/*");


    }


}