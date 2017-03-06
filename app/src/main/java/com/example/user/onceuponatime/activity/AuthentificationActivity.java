package com.example.user.onceuponatime.activity;


import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.user.onceuponatime.R;
import com.example.user.onceuponatime.databinding.ActivityAuthentificationBinding;
import com.example.user.onceuponatime.fragment.SignInFragment;
import com.example.user.onceuponatime.fragment.SignUpFragment;

public class AuthentificationActivity extends AppCompatActivity implements SignInFragment.SignInFragmentCallBack,
        SignUpFragment.SignUpFragmentCallBack
        {

    private ActivityAuthentificationBinding activityAuthentificationBinding;

    public static final String SIGNINFRAGMENT_TAG = "signinfragment";
    public static final String SIGNUPFRAGMENT_TAG = "signupfragment";

    public static final int SIGNIN_FRAGMENT_ID = 846464;
    public static final int SIGNUP_FRAGMENT_ID = 125478;
    public static final int LOST_PWD_FRAGMENT_ID = 85546;



    private FragmentManager mFragmentManager;
    private SignInFragment mSignInFragment;
    private SignUpFragment mSignUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);


        mSignInFragment = SignInFragment.getInstance();

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.fragment_container,mSignInFragment,SIGNINFRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    @Override
    public void onFragmentSwapRequested(int FragmentId) {

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fgTransaction = mFragmentManager.beginTransaction();

        switch(FragmentId) {
            case SIGNUP_FRAGMENT_ID:

                mSignUpFragment = (SignUpFragment) mFragmentManager.findFragmentByTag(SIGNUPFRAGMENT_TAG);

                if (mSignUpFragment == null) {
                    mSignUpFragment = SignUpFragment.getInstance();
                    Log.d(AuthentificationActivity.class.getSimpleName(),"*************"+mSignUpFragment.toString());
                    fgTransaction.replace(R.id.fragment_container, mSignUpFragment, SIGNUPFRAGMENT_TAG);
                } else {
                    fgTransaction.show(mSignUpFragment);
                }
                fgTransaction.addToBackStack(null).commit();

                break;
            case LOST_PWD_FRAGMENT_ID:
                break;


        }



    }
}
