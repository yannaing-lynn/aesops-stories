package yannainglynn.rghtbossy.orange.aesopstories.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import yannainglynn.rghtbossy.orange.aesopstories.R;
import yannainglynn.rghtbossy.orange.aesopstories.adapters.RecyclerAdapter;
import yannainglynn.rghtbossy.orange.aesopstories.database.DatabaseHelper;
import yannainglynn.rghtbossy.orange.aesopstories.models.Stories;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalTales extends Fragment {
    private RecyclerView recyclerView;
    private List<Stories> mProductList;
    private DatabaseHelper mDBHelper;


    public AnimalTales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("တိရိစာၦန္ပံုျပင္မ်ား");
        return inflater.inflate(R.layout.fragment_animal_tales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerAnimalTales);
        mDBHelper = new DatabaseHelper(getActivity());

        //Check Database
        File database = getActivity().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(getActivity())) {
                Toast.makeText(getActivity(), "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //Get product list in db when db exists
        mProductList = mDBHelper.getStoriesListByCategory("animal");
        //Init adapter
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), mProductList);
        //Set adapter for listview
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }
    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
