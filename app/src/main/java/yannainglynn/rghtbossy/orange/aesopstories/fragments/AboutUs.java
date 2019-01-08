package yannainglynn.rghtbossy.orange.aesopstories.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Permission;

import yannainglynn.rghtbossy.orange.aesopstories.MainActivity;
import yannainglynn.rghtbossy.orange.aesopstories.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {


    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 7;

    public AboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("About Us");
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final TextView etPhoneNum = view.findViewById(R.id.tvPhono);
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tvEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendEmail();
            }
        });
        view.findViewById(R.id.tvPhono).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkPermission(Manifest.permission.CALL_PHONE)) {
                            String number2 = etPhoneNum.getText().toString();
                            Uri num2 = Uri.parse("tel:" + number2);
                            Intent sudd = new Intent(Intent.ACTION_CALL, num2);
                            startActivity(sudd);
                        } else {
                            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    MAKE_CALL_PERMISSION_REQUEST_CODE);
                        }
                    }
                }
        );

    }

    private void SendEmail() {
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, "yannainglinn174716@gmail.com");
        email.putExtra(Intent.EXTRA_SUBJECT, "I want to contact you.");
        email.putExtra(Intent.EXTRA_TEXT, "From Aesop' Fables App");

        try {
            startActivity(Intent.createChooser(email, "Choose Email Client from...."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No Email app in your phone", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission(String Permission) {
        return ContextCompat.checkSelfPermission(getActivity(), Permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    ;
                Toast.makeText(getActivity(), "you can call by clicking button", Toast.LENGTH_SHORT).show();
        }
        return;
    }

}

