package com.snowman.sportclubolymp.data;
import com.snowman.sportclubolymp.data.ClubOlympusContract.MemberEntry;
import com.snowman.sportclubolymp.model.Member;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OlympDBHelper extends SQLiteOpenHelper {
    public OlympDBHelper(Context context) {
        super(context, ClubOlympusContract.DATABASE_NAME, null, ClubOlympusContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBERS_TABLE = "CREATE TABLE " + MemberEntry.TABLE_NAME + "("
                + MemberEntry.ID + " INTEGER PRIMARY KEY, "
                + MemberEntry.COLUMN_FIRST_NAME + " TEXT, "
                + MemberEntry.COLUMN_LAST_NAME + " TEXT, "
                + MemberEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + MemberEntry.COLUMN_SPORT + " TEXT" + ")";
        db.execSQL(CREATE_MEMBERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                + MemberEntry.TABLE_NAME);
    }
    public void addMember (Member member){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MemberEntry.COLUMN_FIRST_NAME,member.getFirstName());
        contentValues.put(MemberEntry.COLUMN_LAST_NAME,member.getLastName());
        contentValues.put(MemberEntry.COLUMN_GENDER,member.getGender());
        contentValues.put(MemberEntry.COLUMN_SPORT,member.getSport());

        db.insert(MemberEntry.TABLE_NAME,null,contentValues);
        db.close();
    }
    public void deleteMember(Member member){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(MemberEntry.TABLE_NAME, MemberEntry.ID + "=?", new String[]{String.valueOf(member.getId())});
    }
}
