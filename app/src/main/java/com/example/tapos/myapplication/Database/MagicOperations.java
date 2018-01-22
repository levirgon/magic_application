package com.example.tapos.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.models.Magic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 1/22/18.
 */

public class MagicOperations {

    private static MagicOperations mInstance;
    SQLiteOpenHelper dbHandler;
    private Context mContext;
    SQLiteDatabase database;
    private String[] allColumns = {
            "id",
            "title",
            "description",
            "thumbnail"
    };

    public static synchronized MagicOperations getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MagicOperations(context);
        }
        return mInstance;
    }

    private MagicOperations(Context context) {
        dbHandler = new DatabaseHandler(context);
        mContext = context;
        open();
    }


    private void open() {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public Magic addMagic(Magic magic) {
        ContentValues values = new ContentValues();
        values.put("title", magic.getTitle());
        values.put("description", magic.getDescription());
        values.put("thumbnail", magic.getThumbnail());
        long insertId = database.insert("magics", null, values);
        magic.setId(insertId);
        return magic;

    }

    public Magic getMagic(long id) {

        Cursor cursor = database.query("magics", allColumns, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Magic magic = new Magic(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

        return magic;
    }

    public List<Magic> getAllMagics() {

        Cursor cursor = database.query("magics", allColumns, null, null, null, null, null);
        List<Magic> magics = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Magic magic = new Magic(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
                magics.add(magic);
            }
        }
        return magics;
    }

    public void removeMagic(Magic magic) {
        database.delete("magics", "id=" + magic.getId(), null);
    }
}
