package samletcher.huckordump;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import samletcher.huckordump.User;

/**
 * Created by iyudkovich on 4/11/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HuckOrDump";

    // Users table name
    private static final String TABLE_Users = "Users";

    // Users Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_FIRST_NAME = "First Name";
    private static final String KEY_LAST_NAME = "Last name";
    private static final String KEY_GENDER = "Gender";
    private static final String KEY_Interested_In = "InterestedIn";
    private static final String KEY_TEAM = "Team";
    private static final String KEY_POSITION = "Position";
    private static final String KEY_PICTURE = "Picture";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT" +
                KEY_GENDER + " INTEGER" +
                KEY_Interested_In + " INTEGER" +
                KEY_TEAM + " INTEGER" +
                KEY_POSITION + " TEXT" +
                KEY_PICTURE + " BLOB" +
                ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);

        // Create tables again
        onCreate(db);
    }

    // Adding a new User
    public void addUser(User user) {
        // get the database
        SQLiteDatabase db = this.getWritableDatabase();

        // the value that we will eventually add
        ContentValues values = new ContentValues();

        values.put(KEY_FIRST_NAME, user.getFirst_name());
        values.put(KEY_LAST_NAME, user.getLast_name());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_Interested_In, user.getInterestedIn());
        values.put(KEY_TEAM, user.getTeam_id());
        values.put(KEY_Interested_In, user.getInterestedIn());
        values.put(KEY_POSITION, user.getPosition());
        values.put(KEY_PICTURE, user.getPicture());

        // insert the values for new user
        db.insert(TABLE_Users, null, values);
        db.close();
    }

}
