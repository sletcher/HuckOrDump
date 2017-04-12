package samletcher.huckordump;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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
    private static final String KEY_EM = "Email";
    private static final String KEY_PW = "PW";
    private static final String KEY_FIRST_NAME = "First Name";
    private static final String KEY_LAST_NAME = "Last name";
    private static final String KEY_GENDER = "Gender";
    private static final String KEY_Interested_In = "InterestedIn";
    private static final String KEY_TEAM = "Team";
    private static final String KEY_BIO = "Bio";
    private static final String KEY_POSITION = "Position";

    private static final String KEY_PICTURE = "Picture";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_EM + " STRING " +
                KEY_PW + " STRING " +
                KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT " +
                KEY_GENDER + " INTEGER " +
                KEY_Interested_In + " INTEGER " +
                KEY_TEAM + " INTEGER " +
                KEY_POSITION + " TEXT " +
                KEY_BIO + " TEXT " +
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

    // Get highest id
    public int getId() {
        SQLiteDatabase db = this.getReadableDatabase();

        // get the highest id
        String selectQuery = "SELECT " + KEY_ID +
                                " FROM " + TABLE_Users +
                                " ORDER BY " + KEY_ID + " DESC " +
                                "LIMIT 1";

        // execute the query
        Cursor cursor = db.rawQuery(selectQuery, null);
        // if we get something back, the next highest id is the highest id + 1
        if (cursor.moveToFirst()) {
            return Integer.parseInt(cursor.getString(0)) + 1;
        // otherwise return 0
        } else {
            return 0;
        }
    }
    // Adding a new User
    public void addUser(User user) {
        // get the database
        SQLiteDatabase db = this.getWritableDatabase();

        // the value that we will eventually add
        ContentValues values = new ContentValues();

        // add all the columns for the user while generating an id
        // ideally will want to figure out a way to generate a better id
        values.put(KEY_ID, getId());
//        values.put(KEY_EM, user.getEmail());
//        values.put(KEY_PW, user.getPw());
        values.put(KEY_FIRST_NAME, user.getFirst_name());
        values.put(KEY_LAST_NAME, user.getLast_name());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_Interested_In, user.getInterestedIn());
        values.put(KEY_TEAM, user.getTeam_id());
        values.put(KEY_POSITION, user.getPosition());
        values.put(KEY_BIO, user.getBio());
//        values.put(KEY_PICTURE, user.getPicture());

        // insert the values for new user
        db.insert(TABLE_Users, "Bio", values);
        db.close();
    }

    public boolean userExists(String email) {
        // get the database
        SQLiteDatabase db = this.getReadableDatabase();

        // the query
        String query = "SELECT * FROM " + TABLE_Users
                    +  " WHERE Email = " + email;

        Cursor cursor = db.rawQuery(query, null);
        return cursor != null;
    }

    // reading a user by id
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Users, new String[] {KEY_ID, KEY_EM, KEY_PW, KEY_FIRST_NAME, KEY_LAST_NAME, KEY_GENDER, KEY_Interested_In, KEY_TEAM, KEY_POSITION, KEY_PICTURE},
                KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(Integer.parseInt(cursor.getString(0)), // id
                cursor.getString(1), // email
                cursor.getString(2), // pw
                cursor.getString(3), // first name
                cursor.getString(4), // last name
                Integer.parseInt(cursor.getString(5)), //gender
                Integer.parseInt(cursor.getString(6)), // interest
                Integer.parseInt(cursor.getString(7)),// team_id
                cursor.getString(8), // position
                cursor.getString(9), // bio
                cursor.getString(10));// picture

        return user;
    }


    // get all the users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        // query to select all users
        String query = "SELECT * FROM " + TABLE_Users;

        // execute the query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                userList.add(new User(Integer.parseInt(cursor.getString(0)), // id
                        cursor.getString(1), // email
                        cursor.getString(2), // pw
                        cursor.getString(3), // first name
                        cursor.getString(4), // last name
                        Integer.parseInt(cursor.getString(5)), //gender
                        Integer.parseInt(cursor.getString(6)), // interest
                        Integer.parseInt(cursor.getString(7)),// team_id
                        cursor.getString(8), // position
                        cursor.getString(9), // bio
                        cursor.getString(10))); // picture
            } while (cursor.moveToNext());
        }

        // return the list of users
        return  userList;
    }

    // get number of users
    public int getNumberOfUsers() {
        // the query to get all the users from the table
        String query = "SELECT * FROM " + TABLE_Users;
        // connect to db
        SQLiteDatabase db = this.getReadableDatabase();
        // execute the query
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();

        return cursor.getCount();
    }



}
