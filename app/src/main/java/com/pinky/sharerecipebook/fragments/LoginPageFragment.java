package com.pinky.sharerecipebook.fragments;
// https://android-arsenal.com/details/1/7136

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.viewmodels.LoginViewModel;

public class LoginPageFragment extends Fragment {
    // private EditText emailEditText;
    // private EditText passwordEditText;
    private TextInputLayout passwordEditText;
    private TextInputLayout emailEditText;
    private Button loginButton;
    private Button registerButton;
    private String email = "";

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) { // if  login go to addNewRecipeFragment
                    Navigation.findNavController(getView()).navigate(R.id.action_loginPageFragment_to_addNewRecipeFragment);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);

        emailEditText = view.findViewById(R.id.fragment_loginregister_email);
        passwordEditText = view.findViewById(R.id.fragment_loginregister_password);
        loginButton = view.findViewById(R.id.fragment_loginregister_login);
        registerButton = view.findViewById(R.id.fragment_loginregister_register);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //try to login
        loginButton.setOnClickListener(view1 -> {
            email = emailEditText.getEditText().getText().toString();
            String password = passwordEditText.getEditText().getText().toString();

            if (email.isEmpty()) {
                emailEditText.setError("Enter Email");
                emailEditText.requestFocus();
                Log.d("onClick", "email.isEmpty()");

            } else if (password.isEmpty() || password.length() < 6) {
                Toast.makeText(getContext(), "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
                passwordEditText.setError("Password length must be more than 6");
                passwordEditText.requestFocus();
                Log.d("onClick", "password.isEmpty() || password.length()");

            } else {
                loginViewModel.login(email, password);
            }
        });


        registerButton.setOnClickListener(view12 -> {
            email = emailEditText.getEditText().getText().toString();
            String password = passwordEditText.getEditText().getText().toString();
            if (email.length() > 0 && password.length() > 5) {
                loginViewModel.register(email, password);
            } else {
                Toast.makeText(getContext(), "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

// https://material.io/components/text-fields/android#filled-text-field