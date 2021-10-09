package com.pinky.sharerecipebook.view.fragments;
// https://android-arsenal.com/details/1/7136

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.viewmodels.LoginViewModel;

public class LoginPageFragment extends Fragment {
    private TextInputLayout passwordEditText;
    private TextInputLayout emailEditText;
    private Button loginButton;
    private Button registerButton;
    private String email = "";
    private TextView ResatPasswordTextView;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // if  login go to addNewRecipeFragment
        loginViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) { //skype login fragment
                    //UserLoginHelper.getInstance().setUser(firebaseUser.getEmail());

                    Navigation.findNavController(getView()).navigate(R.id.action_loginPageFragment_to_addNewRecipeFragment);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Login");

        emailEditText = view.findViewById(R.id.fragment_login_email_textinput);
        passwordEditText = view.findViewById(R.id.fragment_login_password_textinput);
        loginButton = view.findViewById(R.id.fragment_login_login_button);
        registerButton = view.findViewById(R.id.fragment_login_register_button);
        ResatPasswordTextView = view.findViewById(R.id.fragment_login_resat_password);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //user click button login
        loginButton.setOnClickListener(view1 -> {

            //String email ??need like that?
            email = emailEditText.getEditText().getText().toString();
            String password = passwordEditText.getEditText().getText().toString();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.setError("Enter Email");
                emailEditText.requestFocus();
                Log.d("onClick", "email.isEmpty()");

            } else if (password.isEmpty() || password.length() < 6) {
//                Toast.makeText(getContext(), "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
                passwordEditText.setError("Password length must be more than 6");
                passwordEditText.requestFocus();
                Log.d("onClick", "password.isEmpty() || password.length()");

            } else {
//                Snackbar.make(this.getView(), "Login... Please wait", BaseTransientBottomBar.LENGTH_SHORT).show();
                loginViewModel.login(email, password);

            }
        });


        registerButton.setOnClickListener(view2 -> {

            Navigation.findNavController(getView()).navigate(R.id.action_loginPageFragment_to_registerFragment);
        });

        ResatPasswordTextView.setOnClickListener(v -> {
            if (!email.isEmpty()) {
                loginViewModel.resatPassword(email);
                Snackbar.make(this.getView(), "We'll send you a password reset email", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
    }
}

// https://material.io/components/text-fields/android#filled-text-field