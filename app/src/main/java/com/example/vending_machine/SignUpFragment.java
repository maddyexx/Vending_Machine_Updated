package com.example.vending_machine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText name, email, pass, re_pass;
    Button register;
    SharedPreferences pref;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void settingUpListeners() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = pass.getText().toString();
                String re_passwordValue = re_pass.getText().toString();
                if(passwordValue.equals(re_passwordValue)) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username", nameValue);
                    editor.putString("userEmail", emailValue);
                    editor.putString("password", passwordValue);
                    editor.putString("re_password", re_passwordValue);
                    editor.apply();
                    Toast.makeText(getActivity(), "User Registered", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Password do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initComponents();
        settingUpListeners();
        // Inflate the layout for this fragment
        return rootView;
    }
    private void initComponents() {
        name = rootView.findViewById(R.id.name);
        email = rootView.findViewById(R.id.signup_email);
        pass = rootView.findViewById(R.id.signup_password);
        re_pass = rootView.findViewById(R.id.re_password);
        register = rootView.findViewById(R.id.signup_register_btn);
//        cancel = findViewById(R.id.signup_cancel_btn);
        pref = getContext().getSharedPreferences("user_info", 0);
    }
}