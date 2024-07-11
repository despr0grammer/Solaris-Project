package com.example.solarisproject.data;

import android.provider.BaseColumns;

public final class UserContract {

    private UserContract() {}

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_USER_TYPE = "user_type";
    }
}
