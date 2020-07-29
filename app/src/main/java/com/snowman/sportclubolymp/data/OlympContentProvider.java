package com.snowman.sportclubolymp.data;

import com.snowman.sportclubolymp.data.ClubOlympusContract.MemberEntry;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


public class OlympContentProvider extends ContentProvider {
   OlympDBHelper dbHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int MEMBERS = 111;
    private static final int MEMBER_ID = 222;

    static {
        uriMatcher.addURI(ClubOlympusContract.AUTHORITY, ClubOlympusContract.PATH_MEMBERS, MEMBERS);
        uriMatcher.addURI(ClubOlympusContract.AUTHORITY, ClubOlympusContract.PATH_MEMBERS + "/#", MEMBER_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new OlympDBHelper(getContext());

        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);
        switch(match){
        case MEMBERS:
            cursor = db.query(MemberEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        break;
            case MEMBER_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MemberEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                Toast.makeText(getContext(), "Unknown URI", Toast.LENGTH_SHORT).show();

                throw new IllegalStateException("Unexpected value: " + match);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                long id = db.insert(MemberEntry.TABLE_NAME,null,values);
                if(id==-1){
                    Log.e("Error", "ID: "+ id);
                    return null;
                }
            return ContentUris.withAppendedId(uri,id);
            default:
                throw new IllegalArgumentException("Error");
        }


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
