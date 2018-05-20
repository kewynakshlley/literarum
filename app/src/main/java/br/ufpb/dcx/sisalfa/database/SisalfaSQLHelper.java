package br.ufpb.dcx.sisalfa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SisalfaSQLHelper extends SQLiteOpenHelper {

    public SisalfaSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Name and version of the database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbLiterarum";

    // Table names
    public static final String CONTEXT_TABLE = "context_table";
    public static final String CHALLENGE_TABLE = "challenge_table";
    public static final String USER_TABLE = "user_table";

    // Common column names between all
    public static final String COLUMN_PKEY_ID = "pkey_id";

    // Common columns of the challenge and context
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_SOUND = "sound";
    public static final String COLUMN_VIDEO = "video";

    // SisContext specific column
    public static final String COLUMN_NAME = "name";

    // User columns
    public static final String COLUMN_USERNAME = "user_name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHOTO = "photo";

    // Fkeys
    public static final String COLUMN_USER_FKEY = "user_fkey";
    public static final String COLUMN_CONTEXT_FKEY = "context_fkey";



    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "+ USER_TABLE +" (" +
            COLUMN_PKEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
            COLUMN_PASSWORD +" TEXT NOT NULL, "+
            COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
            COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
            COLUMN_LAST_NAME +" TEXT, " +
            COLUMN_PHOTO + " TEXT)";

    private static final String CREATE_CONTEXT_TABLE = "CREATE TABLE IF NOT EXISTS "+ CONTEXT_TABLE +" (" +
            COLUMN_PKEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT NOT NULL UNIQUE, " +
            COLUMN_IMAGE +" TEXT NOT NULL, "+
            COLUMN_SOUND + " TEXT, " +
            COLUMN_VIDEO + " TEXT, " +
            COLUMN_USER_FKEY + " INTEGER NOT NULL)";

    private static final String CREATE_CHALLENGE_TABLE = "CREATE TABLE IF NOT EXISTS "+ CHALLENGE_TABLE +" (" +
            COLUMN_PKEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_WORD + " TEXT NOT NULL, " +
            COLUMN_IMAGE +" TEXT NOT NULL, "+
            COLUMN_SOUND + " TEXT, " +
            COLUMN_VIDEO + " TEXT, " +
            COLUMN_CONTEXT_FKEY + " INTEGER NOT NULL, " +
            COLUMN_USER_FKEY + " INTEGER NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_CONTEXT_TABLE);
        sqLiteDatabase.execSQL(CREATE_CHALLENGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONTEXT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CHALLENGE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
