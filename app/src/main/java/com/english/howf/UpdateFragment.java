package com.english.howf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Context;

import java.util.HashMap;
import java.util.Map;


public class UpdateFragment extends Fragment {
    EditText edt_Name,edt_Gender,edt_Location,edt_Birthday,edt_Email;
    Button btn_submit,btn_show;
    Firebase root;
    User user;
    public UpdateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getFromWidget();
        //phai co ham nay


    }

    public void getDataToEditText(String uid){
       user= new User();
        user.setUid(uid);
        Firebase root2 = root.child(user.getUid());
        root2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user = dataSnapshot.getValue(User.class);
//                edt_Name.setText(user.getName().toString());
//                edt_Email.setText(user.getEmail().toString());
//                edt_Gender.setText(user.getGender().toString());
//                edt_Location.setText(user.getLocation().toString());
//                edt_Birthday.setText(user.getBirthday().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public void UpdateDataForFB(String uid){
        User u = new User();
        u.setUid(uid);
        u.setName(edt_Name.getText().toString());
        u.setGender(edt_Gender.getText().toString());
        u.setBirthday(edt_Birthday.getText().toString());
        u.setLocation(edt_Location.getText().toString());
        u.setEmail(edt_Email.getText().toString());
        root.child(u.getUid()).setValue(u);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_update, container, false);
        edt_Name = (EditText)view.findViewById(R.id.edt_name);
        edt_Gender = (EditText)view.findViewById(R.id.edt_gender);
        edt_Birthday = (EditText)view.findViewById(R.id.edt_birthday);
        edt_Location = (EditText)view.findViewById(R.id.edt_location);
        edt_Email = (EditText)view.findViewById(R.id.edt_email);
        btn_submit = (Button)view.findViewById(R.id.btn_submit);
        btn_show = (Button)view.findViewById(R.id.btn_show);

        Bundle bundle =getArguments();
        Log.i("uid",bundle.getString("uid"));
        final String uid = bundle.getString("uid");
        Firebase.setAndroidContext(getActivity());
        root = new Firebase("https://howf-56d85.firebaseio.com/account");

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataToEditText(uid);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDataForFB(uid);
            }
        });
        return view;
    }


}
