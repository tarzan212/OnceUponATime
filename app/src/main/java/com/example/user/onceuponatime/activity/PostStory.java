package com.example.user.onceuponatime.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.onceuponatime.R;
import com.example.user.onceuponatime.other.ParagraphHelper;
import com.example.user.onceuponatime.other.StoryHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PostStory extends AppCompatActivity {

    private static final String TAG = PostStory.class.getSimpleName();

    private static final String DATABASE_CHILD_STORIES = "stories";
    private static final String DATABASE_CHILD_PARAGRAPHS = "paragraphs";

    private Button postItButton;
    private EditText storyInput;
    private EditText titleStoryInput;
    private SwitchCompat switchPrivacy;

    private FirebaseAuth mAuth;
    private DatabaseReference mPostRef;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_story);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        postItButton = (Button) findViewById(R.id.postButton);
        storyInput = (EditText) findViewById(R.id.storyBodyInput);
        titleStoryInput = (EditText) findViewById(R.id.storyTitleInput);

        postItButton.setOnClickListener(new PostButtonListener()); //TODO a completer




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class PostButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final String userID = mUser.getUid();
            mPostRef = FirebaseDatabase.getInstance().getReference();
            //Updating to the stories node first
            final String storyKey = mPostRef.child(DATABASE_CHILD_STORIES).push().getKey();

            Map<String,String> mapParag = new HashMap<String,String>();
            final String paragKey = mPostRef.child(DATABASE_CHILD_STORIES).child(storyKey).push().getKey();
            mapParag.put(paragKey,"Leparag");


            StoryHelper storyHelper = new StoryHelper(storyKey,userID,mapParag);
            mPostRef.child("stories").child(storyKey).setValue(storyHelper,new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if(databaseError == null) {
                        ParagraphHelper paragraphHelper =new ParagraphHelper(paragKey,userID,storyKey,"");

                        mPostRef.child(DATABASE_CHILD_PARAGRAPHS).child(paragKey).setValue(paragraphHelper,
                                new DatabaseReference.CompletionListener(){
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        if(databaseError == null) {
                                            Toast.makeText(PostStory.this,"Successfully posted the story !",Toast.LENGTH_LONG)
                                                    .show();
                                            finish();
                                        }
                                    }
                                });
                    }

                }
            });

        }
    }
}
