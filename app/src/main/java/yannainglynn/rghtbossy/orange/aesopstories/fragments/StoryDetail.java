package yannainglynn.rghtbossy.orange.aesopstories.fragments;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import yannainglynn.rghtbossy.orange.aesopstories.R;
import yannainglynn.rghtbossy.orange.aesopstories.database.DatabaseHelper;
import yannainglynn.rghtbossy.orange.aesopstories.models.Stories;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetail extends Fragment {
    private DatabaseHelper mDBHelper;
    Stories stories;
    TextView tvTitle,tvFirstBody,tvSecondBody,tvThirdBody;
    ImageView imgStoryDetail;

    public StoryDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgStoryDetail = view.findViewById(R.id.imgViewStoryDetail);
        tvTitle = view.findViewById(R.id.tvStoryDetailTitle);
        tvFirstBody = view.findViewById(R.id.tvStoryDetailFirstBody);
        tvSecondBody = view.findViewById(R.id.tvStoryDetailSecondBody);
        tvThirdBody = view.findViewById(R.id.tvStoryDetailThirdBody);

        mDBHelper = new DatabaseHelper(getActivity());
        stories = new Stories();

        //Check exists database
        File database = getActivity().getDatabasePath(DatabaseHelper.DBNAME);
        if(!database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(getActivity())) {
                Toast.makeText(getActivity(), "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        assert getArguments() != null;
        int id = getArguments().getInt("storiesAllId");
        stories = mDBHelper.getStory(id);

        byte[] img = stories.getImage();
        imgStoryDetail.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
        tvTitle.setText(stories.getTitle());
        tvFirstBody.setText(stories.getFirstBody());
        tvSecondBody.setText(stories.getSecondBody());
        tvThirdBody.setText(stories.getThirdBody());

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
