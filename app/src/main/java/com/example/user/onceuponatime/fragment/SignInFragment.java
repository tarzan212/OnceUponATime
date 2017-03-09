package com.example.user.onceuponatime.fragment;

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
import android.util.Log;
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
import com.example.user.onceuponatime.databinding.FragmentSignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInFragment extends Fragment {
    FragmentSignInBinding signInBinding;

    private CoordinatorLayout mCoordinatorLayout;
    private EditText mEmailEdit,mPasswordEdit;
    private Button btnSignIn,btnSignUp,btnLostPwd;
    private ProgressBar mProgressBar;

    private FirebaseAuth mAuth;

    private SignInFragmentCallBack mCallBack;



    public SignInFragment() {}

    public static SignInFragment getInstance() {
        return new SignInFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (SignInFragmentCallBack) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements SignInCallaback");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signInBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_sign_in);

        mEmailEdit = signInBinding.emailSignin;
        mPasswordEdit = signInBinding.passwordSignin;
        mProgressBar = signInBinding.progressBarSignIn;
        btnSignIn = signInBinding.signInButton;
        btnSignUp = signInBinding.signUpButton;
        btnLostPwd = signInBinding.passwordReset;
        mCoordinatorLayout = signInBinding.coordinatorSignin;



        mAuth = FirebaseAuth.getInstance();



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAction();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack instanceof SignInFragmentCallBack)
                    mCallBack.onFragmentSwapRequested(AuthentificationActivity.SIGNUP_FRAGMENT_ID);
            }
        });

        btnLostPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack instanceof SignInFragmentCallBack)
                    mCallBack.onFragmentSwapRequested(AuthentificationActivity.LOST_PWD_FRAGMENT_ID);
            }
        });
    }

    private void signInAction() {
        String email = mEmailEdit.getText().toString().trim();
        final String password = mPasswordEdit.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Snackbar.make(mCoordinatorLayout,getString(R.string.no_email_entered),Snackbar.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Snackbar.make(mCoordinatorLayout,getString(R.string.no_password_entered),Snackbar.LENGTH_LONG).show();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Snackbar.make(mCoordinatorLayout,getString(R.string.login_success),Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
                else
                {
                    Snackbar.make(mCoordinatorLayout,getString(R.string.login_failed),Snackbar.LENGTH_LONG).show();
                    Log.d(SignInFragment.class.getSimpleName(),"---- "+task.getException().toString());

                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }

    public interface SignInFragmentCallBack {
        void onFragmentSwapRequested(int FragmentId);
    }
}
