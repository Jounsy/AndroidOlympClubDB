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
    public Uri insert(Uri uri, ContentValues values) {
        String firstName = values.getAsString(MemberEntry.COLUMN_FIRST_NAME);
        if(firstName == null){
            throw new IllegalArgumentException("You have no input first name");
        }
        String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
           if(lastName == null) {
               throw new IllegalArgumentException("You have no input last name");
           }
        Integer gender = values.getAsInteger(MemberEntry.COLUMN_GENDER);
           if(gender == null || !(gender == MemberEntry.GENDER_FEMALE || gender == MemberEntry.GENDER_MALE || gender == MemberEntry.GENDER_UNKNOWN)) {
               throw new IllegalArgumentException("You have no input sport");
           }
        String sport = values.getAsString(MemberEntry.COLUMN_SPORT);
           if(sport == null) {
               throw new IllegalArgumentException("You have no input sport");
           }

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
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                return db.delete(MemberEntry.TABLE_NAME, selection,selectionArgs);
            case MEMBER_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return db.delete(MemberEntry.TABLE_NAME,selection,selectionArgs);
            default:
                Toast.makeText(getContext(), "Unknown URI", Toast.LENGTH_SHORT).show();

                throw new IllegalStateException("Can't delete incorrect URI: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(MemberEntry.COLUMN_FIRST_NAME)) {
            String firstName = values.getAsString(MemberEntry.COLUMN_FIRST_NAME);
            if(firstName == null){
                throw new IllegalArgumentException("You have no input first name");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_LAST_NAME)) {
            String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
            if(lastName == null) {
                throw new IllegalArgumentException("You have no input last name");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_GENDER)) {
            Integer gender = values.getAsInteger(MemberEntry.COLUMN_GENDER);
            if(gender == null || !(gender == MemberEntry.GENDER_FEMALE || gender == MemberEntry.GENDER_MALE || gender == MemberEntry.GENDER_UNKNOWN)) {
                throw new IllegalArgumentException("You have no input sport");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_SPORT)) {
            String sport = values.getAsString(MemberEntry.COLUMN_SPORT);
            if(sport == null) {
                throw new IllegalArgumentException("You have no input sport");
            }
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                return db.update(MemberEntry.TABLE_NAME,values, selection,selectionArgs);
            case MEMBER_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return db.update(MemberEntry.TABLE_NAME,values,selection,selectionArgs);
            default:
                Toast.makeText(getContext(), "Unknown URI", Toast.LENGTH_SHORT).show();

                throw new IllegalStateException("Can't update incorrect URI: " + uri);
        }

    }

    public String getType(Uri uri){
        int match = uriMatcher.match(uri);
        switch(match) {
            case MEMBERS:
                return MemberEntry.CONTENT_MULTIPLE_ITEMS;
            case MEMBER_ID:
                return MemberEntry.CONTENT_SINGLE_ITEM;
            default:
                Toast.makeText(getContext(), "Unknown URI", Toast.LENGTH_SHORT).show();

                throw new IllegalStateException("Unknown URI: " + uri);
        }
        }
    }

