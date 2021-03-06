package him.com.hri3;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class WelcomeClient extends AppCompatActivity
{
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    TextView tv1,tv2;
    Bitmap bit = null;
    ImageView iv;
    long ucno;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_client);
        this.setTitle("Client");

        lv = findViewById(R.id.wclv);
        tv1 = findViewById(R.id.wctv1);
        tv2 = findViewById(R.id.wctv2);
        iv = findViewById(R.id.wciv);

        sp = getSharedPreferences("login_details",MODE_PRIVATE);
        spe = sp.edit();

        Intent i2 = getIntent();
        Bundle b = i2.getExtras();
        String ufname = b.getString("k1");
        String ulname = b.getString("k2");
        ucno = b.getLong("k3");

        tv1.setText(ufname + " " + ulname);
        MyDatabase md = new MyDatabase(this);
        SQLiteDatabase db = md.getWritableDatabase();
        String[] columns = {MyDatabase.DOB};

//Retrieving data from database----------------------------------------------------------------------------------
        Cursor c = db.rawQuery("select DOB,CPHOTO from CLIENT where CONTACT = " + ucno, null);
        if (c.moveToFirst()) {
            String udob = c.getString(0);
            byte[] image = c.getBlob(1);
           // Bitmap bit = BitmapFactory.decodeByteArray(image, 0, image.length);
           // iv.setImageBitmap(bit);
            tv2.setText(udob);
        }
// ListView-------------------------------------------------------------------------------------------------------
        String keys[] = {"k1"};
        ArrayList al = new ArrayList();
        String col[] = {MyDatabase.BLOG};
    //Retrieving data from database----------------------------------------------------------------------------
        //  Cursor c2 = db.query(MyDatabase.TABLE_NAME2, col, null, null, null, null, null);
        Cursor c2 = db.rawQuery("select BLOG from BLOGS ", null);
        int i = 0;
        if (c2.moveToFirst()) {
            do {
                String blog = c2.getString(0);
                HashMap hm = new HashMap();
                hm.put(keys[0], blog);
                al.add(hm);
                i++;

            } while (c2.moveToNext());

            int ids[] = {R.id.blvstv1};
            Collections.reverse(al);
            SimpleAdapter sa = new SimpleAdapter(this,al,R.layout.blog_listview_style,keys,ids);
            lv.setAdapter(sa);
        }
    }
//Capturing Pic
    public void capturePhoto(View view)
    {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,1212);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1212)
        {
            if(resultCode == RESULT_OK)
            {
                Bundle b = data.getExtras();
                bit = (Bitmap)b.get("data");
                iv.setImageBitmap(bit);

                //Storing Image in Database
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                 bit.compress(Bitmap.CompressFormat.JPEG,100,bout);
                byte[] image = bout.toByteArray();

                MyDatabase mdb = new MyDatabase(this);
                SQLiteDatabase db = mdb.getWritableDatabase();
                //db.execSQL("update CLIENT set CPHOTO = "+image+" where CONTACT = "+ucno+",null");

                ContentValues values = new ContentValues();
                values.put(MyDatabase.CPHOTO, image);

                db.update(MyDatabase.TABLE_NAME1, values,"CONTACT="+ucno, null);
                Toast.makeText(this, "Image Stored", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void sevices(View view)
    {
        Intent i = new Intent(this,ActivityServices.class);
        startActivity(i);
    }

    public void logout(View view)
    {
        spe.clear();
        spe.commit();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
