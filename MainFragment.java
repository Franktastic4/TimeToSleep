package com.example.gftlhackathon.timetosleep;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends android.support.v4.app.Fragment implements TimePickerFragment.TimePickerFragmentCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainFragment";

    TextView messageTextField;
    TextView contactTextField;
    TextView passwordTextField;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "OnCreateView()");
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("Time", Context.MODE_PRIVATE);

        // TODO remove this, just for testing.
        prefs.edit().putInt("LockedBoolean", 0).apply();

        messageTextField = (TextView) view.findViewById(R.id.msg);
        contactTextField = (TextView) view.findViewById(R.id.contact);
        passwordTextField = (TextView) view.findViewById(R.id.password);

        Button disableButton = (Button) view.findViewById(R.id.disable);
        disableButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Attempt to disable alarm
                disableAlarm();
            }
        });;

        Button timeButton = (Button) view.findViewById(R.id.time);
        timeButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "someTag");
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SharedPreferences prefs = getActivity().getSharedPreferences("Time", Context.MODE_PRIVATE);
                if (prefs.getInt("LockedBoolean", 0) == 1) {
                    Log.d(TAG, "Touch detected after alarm!");
                    misbehaved();
                }else{
                    Log.d(TAG, "Touch detected, but is ok");
                }

                    return false;
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void disableAlarm(){
        Log.d(TAG, "Attempt to disable alarm");
        SharedPreferences prefs = getActivity().getSharedPreferences("Time", Context.MODE_PRIVATE);

        if(passwordTextField.equals(prefs.getString("Password", "ladvbuyiadfbvpidaobodifv"))){
            Log.d(TAG, "Eligible to remove alarm");
            Log.d(TAG, "Will not alert: " + prefs.getString("Contact", ""));
        }

    }

    public void misbehaved(){
        Log.d(TAG, "You misbehaved. I'm going to tell on you.");


    }

    public void timeSet(){
        SharedPreferences prefs = getActivity().getSharedPreferences("Time", Context.MODE_PRIVATE);
        Log.d(TAG, "Callback from timeset");
        // if we went from 0->1, means we save the message, contact, and password.
        if(prefs.getInt("LockedBoolean",0) == 1){
            prefs.edit().putString("Message", messageTextField.getText().toString());
            prefs.edit().putString("Contact", contactTextField.getText().toString());
            prefs.edit().putString("Password", passwordTextField.getText().toString());
            passwordTextField.setText("");
        }

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction();
    }

}
