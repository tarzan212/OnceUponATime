package com.example.user.onceuponatime.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.onceuponatime.R;
import com.example.user.onceuponatime.activity.AuthentificationActivity;
import com.example.user.onceuponatime.activity.MainActivity;
import com.example.user.onceuponatime.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpFragment extends Fragment {

    FragmentSignUpBinding signUpBinding;

    private EditText mEmailEdit,mPasswordEdit;
    private Button btnRegister,btnLogin;
    private ProgressBar mProgressBar;
    private CoordinatorLayout mCoordinatorLayout;

    private FirebaseAuth mAuth;

    private SignUpFragmentCallBack mCallBack;

    public interface SignUpFragmentCallBack {
        void onFragmentSwapRequested(int fragmentId);
    }

    public SignUpFragment() {
    }



    public static SignUpFragment getInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (SignUpFragmentCallBack) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implements the SignUpFragmentCallBack");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mEmailEdit = signUpBinding.emailSignup;
        mPasswordEdit = signUpBinding.passwordSignup;
        btnLogin = signUpBinding.loginRedirectButton;
        btnRegister = signUpBinding.registerButton;
        mProgressBar = signUpBinding.progressBarSignup;
        mCoordinatorLayout = signUpBinding.coordinatorSignup;


        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack instanceof SignUpFragmentCallBack)
                    mCallBack.onFragmentSwapRequested(AuthentificationActivity.SIGNIN_FRAGMENT_ID);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClicked();
                Toast.makeText(getContext(),"Hello",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        signUpBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false);

        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void onRegisterClicked() {
        String email = mEmailEdit.getText().toString().trim();
        final String pwd = mPasswordEdit.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Snackbar.make(mCoordinatorLayout,getString(R.string.no_email_entered),Snackbar.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pwd)) {
            Snackbar.make(mCoordinatorLayout,getString(R.string.no_password_entered),Snackbar.LENGTH_LONG).show();
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
                else {
                    mProgressBar.setVisibility(View.GONE);
                    Snackbar.make(mCoordinatorLayout,task.getException().toString(),Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
