package him.com.hri3;

import android.app.MediaRouteButton;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper
{
    public static final String dbname = "hri";
    public static final int ver = 1;

    public static final String TABLE_NAME3 = "BLOGS";
    public static final String BLOG = "BLOG";

    public static final String TABLE_NAME2 = "TRAINER";

    public static final String TABLE_NAME1 = "CLIENT";
    public static final String FNAME = "FNAME";//COL1
    public static final String LNAME = "LNAME";//COL2
    public static final String SEX = "SEX";//COL3
    public static final String DOB = "DOB";//COL4
    public static final String TYPE = "TYPE";//COL5
    public static final String CONTACT = "CONTACT";//COL6
    public static final String PASSWORD = "PASSWORD";//COL7
    public static final String CPHOTO = "CPHOTO";//COL8

    public MyDatabase(Context context)
    {
        super(context,dbname,null,ver);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String qry1 = "create table "+TABLE_NAME1+" ("+FNAME+" text,"+LNAME+" text,"+SEX+" text,"
                +DOB+" text,"+TYPE+" text,"+CONTACT+" number primary key,"+PASSWORD+" text,"+CPHOTO+" blob)";

        String qry2 = "create table "+TABLE_NAME2+" ("+FNAME+" text,"+LNAME+" text,"+SEX+" text,"
                +DOB+" text,"+TYPE+" text,"+CONTACT+" number primary key,"+PASSWORD+" text,"+CPHOTO+" blob)";


        String qry3 = "create table "+TABLE_NAME3+" ("+BLOG+" text)";

        db.execSQL(qry1);
        db.execSQL(qry2);
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
