package com.snowman.sportclubolymp.data;

import android.provider.BaseColumns;

public final class ClubOlympusContract{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Olympus";

    private ClubOlympusContract(){

    }

    public static final class MemberEntry implements BaseColumns{
        public static final String TABLE_NAME = "members";

        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME = "lastName";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_SPORT = "sport";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;


    }

}