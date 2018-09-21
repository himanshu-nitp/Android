package him.com.hri3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllClient extends AppCompatActivity
{
    String[] keys = {"k1","k2","k3","k4","k5","k6","k7"};
    ArrayList al = new ArrayList();
    ListView lv;
    int idno;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_client);
        this.setTitle("All Employees");

        MyDatabase mdb = new MyDatabase(this);
        SQLiteDatabase db = mdb.getWritableDatabase();
        Cursor c = db.query(MyDatabase.TABLE_NAME1,null,null,null,null,null,null);
        if(c.moveToFirst())
        {
            do{


                String fname = c.getString(0);
                String lname = c.getString(1);
                String sex = c.getString(2);
                String dob = c.getString(3);
              //  String type = c.getString(4);
                long cno = c.getLong(5);
              //  byte[] image = c.getBlob(7);

                HashMap hm = new HashMap();
                hm.put(keys[0],fname);
                hm.put(keys[1],lname);
                hm.put(keys[2],sex);
                hm.put(keys[3],dob);
              //  hm.put(keys[4],type);
                hm.put(keys[5],cno);
              //  hm.put(keys[6],image);

                al.add(hm);

            }while (c.moveToNext());

            lv = findViewById(R.id.avaclv1);
            MyCustomAdapter mca = new MyCustomAdapter(this,al,keys);
            lv.setAdapter(mca);

        }
        else
        {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}
