package yannainglynn.rghtbossy.orange.aesopstories.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yannainglynn.rghtbossy.orange.aesopstories.models.Stories;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "storiesList.db";
    @SuppressLint("SdCardPath")
    public static final String DBLOCATION = "/data/data/yannainglynn.rghtbossy.orange.aesopstories/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private int id;
    ArrayList<String> ID_Array;
    ListView LISTVIEW;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    public DatabaseHelper(Context context, int id) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
        this.id = id;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public List<Stories> getListProduct() {
        Stories stories = null;
        List<Stories> storiesList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM stories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stories = new Stories(cursor.getInt(0),cursor.getBlob(2),cursor.getString(1),cursor.getString(3), cursor.getString(4),cursor.getString(5));
            storiesList.add(stories);
            cursor.moveToNext();
        }
        cursor.close();
        //closeDatabase();
        return storiesList;
    }
    public Stories getStory(int id) {
        Stories stories = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM stories Where id=?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stories = new Stories(cursor.getInt(0),cursor.getBlob(2),cursor.getString(1),cursor.getString(4), cursor.getString(5),cursor.getString(6));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return stories;
    }
    public List<Stories> getStoriesListByCategory(String name) {
        Stories stories = null;
        List<Stories> storiesList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM stories Where category=?", new String[]{name});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stories = new Stories(cursor.getInt(0),cursor.getBlob(2),cursor.getString(1),cursor.getString(3), cursor.getString(4),cursor.getString(5));
            storiesList.add(stories);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return storiesList;
    }

}
