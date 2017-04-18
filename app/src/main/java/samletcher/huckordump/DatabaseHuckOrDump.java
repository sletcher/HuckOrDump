package samletcher.huckordump;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyudkovich on 4/11/17.
 */

public class DatabaseHuckOrDump {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HuckOrDump";

    // table names
    private static final String TABLE_Users = "Users";
    private static final String TABLE_TEAMS = "Teams";
    private static final String TABLE_MESSAGES = "Messages";
    private static final String TABLE_ACTIONS = "Actions";
    private static final String TABLE_LOGIN = "Login";

    // Users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EM = "email";
    private static final String KEY_PW = "pw";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_Interested_In = "interested_in";
    private static final String KEY_TEAM = "team";
    private static final String KEY_BIO = "bio";
    private static final String KEY_POSITION = "position";
    private static final String KEY_PICTURE = "picture";

    // messages table column names
    private static final String KEY_UI = "user_id";
    private static final String KEY_MN = "me_num";
    private static final String KEY_mID = "match_id";
    private static final String KEY_CDate = "created";
    private static final String KEY_MText = "text";

    // actions table column names
    private static final String KEY_AID = "action_id";
    private static final String KEY_AU1 = "user1";
    private static final String KEY_AU2 = "user2";
    private static final String KEY_ACTION = "action";

    // teams table column names
    private static final String KEY_TeamID = "team_id";
    private static final String KEY_TName = "team_name";
    private static final String KEY_DIV = "division";
    private static final String KEY_CITY = "city";



    private DbHelper dbHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {

            // create login table
            String CREATE_LOGIN_TABLE = " CREATE TABLE " + TABLE_LOGIN + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_EM + " STRING, " +
                    KEY_PW + " STRING);";

            db.execSQL(CREATE_LOGIN_TABLE);

            String CREATE_TEAM_TABLE = " CREATE TABLE " + TABLE_TEAMS + " (" +
                    KEY_TeamID + " INTEGER PRIMARY KEY, " +
                    KEY_TName + " STRING, " +
                    KEY_DIV + " STRING, " +
                    KEY_CITY + " STRING " +
                    ");";
            db.execSQL(CREATE_TEAM_TABLE);

            String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_Users + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_EM + " STRING, " +
                    KEY_PW + " STRING, " +
                    KEY_FIRST_NAME + " TEXT," +
                    KEY_LAST_NAME + " TEXT, " +
                    KEY_GENDER + " INTEGER, " +
                    KEY_Interested_In + " INTEGER, " +
                    KEY_TEAM + " INTEGER, " +
                    KEY_POSITION + " TEXT, " +
                    KEY_BIO + " TEXT, " +
                    KEY_PICTURE + " BLOB," +
                    " FOREIGN KEY (" + KEY_TEAM +") REFERENCES " + TABLE_TEAMS + "(" + KEY_TeamID + "));";
            db.execSQL(CREATE_USER_TABLE);

            String CREATE_MESSAGES_TABLE = " CREATE TABLE " + TABLE_MESSAGES + " (" +
                    KEY_mID + " INTEGER PRIMARY KEY, " +
                    KEY_UI + " INTEGER, " +
                    KEY_MN + " INTEGER, " +
                    KEY_CDate + " INTEGER, " +
                    KEY_MText + " TEXT, " +
                    " FOREIGN KEY (" + KEY_UI + ") REFERENCES " + TABLE_Users + "(" + KEY_ID + "));";
            db.execSQL(CREATE_MESSAGES_TABLE);

            String CREATE_ACTION_TABLE = " CREATE TABLE " + TABLE_ACTIONS + " (" +
                    KEY_AID + " INTEGER PRIMARY KEY, " +
                    KEY_AU1 + " INTEGER, " +
                    KEY_AU2 + " INTEGER, " +
                    KEY_ACTION + " INTEGER, " +
                    " FOREIGN KEY (" + KEY_AU1 + ") REFERENCES " + TABLE_Users + "(" + KEY_ID +"), " +
                    " FOREIGN KEY (" + KEY_AU2 + ") REFERENCES " + TABLE_Users + "(" + KEY_ID +"));";

            db.execSQL(CREATE_ACTION_TABLE);


        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older tables if they exist
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);

            // Create tables again
            onCreate(db);
        }
    }

    public DatabaseHuckOrDump(Context c){
        ourContext = c;
    }
    public DatabaseHuckOrDump open()throws SQLException {
        dbHelper = new DbHelper(ourContext);
        ourDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    // Get highest id
    public int getId() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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

    public void addLogInUser(LoginUser user) {
        // get the database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // the value that we will eventually add
        ContentValues values = new ContentValues();

        values.put(KEY_ID, user.getId());
        values.put(KEY_EM, user.getEmail());
        values.put(KEY_PW, user.getPw());

        db.insert(TABLE_LOGIN, null, values);
        Log.e("database", "added user to login database");
        db.close(); // close the connection
    }

    // add a team
    public void addTeam(Team team) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TeamID, team.getTeam_id());
        values.put(KEY_DIV, team.getDivision());
        values.put(KEY_CITY, team.getCity());
        values.put(KEY_TName, team.getTeam_name());

        db.insert(TABLE_TEAMS, null, values);
        Log.e("database", "added team to team database");
        db.close(); // close the connection


    }

    // add message
    public void addMessage(Message message) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_mID, message.getMessage_id());
        values.put(KEY_UI, message.getUser_id());
        values.put(KEY_MText, message.getText());
        values.put(KEY_CDate, message.getCreated());
        values.put(KEY_MN, message.getMessageNumber());

        db.insert(TABLE_MESSAGES, null, values);
        Log.e("database", "added message to message database");
        db.close();
    }

    // Adding a new User
    public void addUser(User user) {
        // get the database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // the value that we will eventually add
        ContentValues values = new ContentValues();

        // add all the columns for the user while generating an id
        // ideally will want to figure out a way to generate a better id
        values.put(KEY_ID, getId());
        values.put(KEY_EM, (String) null);
        values.put(KEY_PW, (String) null);
        values.put(KEY_FIRST_NAME, user.getFirst_name());
        values.put(KEY_LAST_NAME, user.getLast_name());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_Interested_In, user.getInterestedIn());
        values.put(KEY_TEAM, user.getTeam_id());
        values.put(KEY_POSITION, user.getPosition());
        values.put(KEY_BIO, user.getBio());
        values.put(KEY_PICTURE, (String) null);

        // insert the values for new user
        db.insert(TABLE_Users, null, values);
        Log.e("database", "added user to user database");
        db.close();
    }


    public boolean userExists(String email) {
        // get the database
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // the query
        String query = "SELECT * FROM " + TABLE_Users
                    +  " WHERE Email = " + email;

        Cursor cursor = db.rawQuery(query, null);
        return cursor != null;
    }

    // reading a user by id
    public User getUser(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // execute the query
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();

        return cursor.getCount();
    }



}
