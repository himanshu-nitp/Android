package him.com.hri3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationForm2 extends AppCompatActivity
{
    EditText cno,pwd;
    String[] TYPE = {"Client","Trainer"};
    String ufname,ulname ,udob, usex, uatype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form2);
        this.setTitle("Register");

        cno = findViewById(R.id.rf2et1);
        pwd = findViewById(R.id.rf2et2);

        Intent i2 = getIntent();
        Bundle b = i2.getExtras();
        ufname = b.getString("k1");
        ulname = b.getString("k2");
        udob = b.getString("k3");
        usex = b.getString("k5");
        uatype = b.getString("k4");

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void Submit(View view)
    {
        String phno = cno.getText().toString().trim();
        String upwd = pwd.getText().toString();

        if(phno.isEmpty() || upwd.isEmpty())
        {
            Toast.makeText(this, "FIELDS ARE EMPTY ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            long ucno = Long.parseLong(phno);

            ContentValues cv = new ContentValues();
            cv.put(MyDatabase.FNAME,ufname);
            cv.put(MyDatabase.LNAME,ulname);
            cv.put(MyDatabase.SEX,usex);
            cv.put(MyDatabase.DOB,udob);
            cv.put(MyDatabase.TYPE,uatype);
            cv.put(MyDatabase.CONTACT,ucno);
            cv.put(MyDatabase.PASSWORD,upwd);

            MyDatabase md = new MyDatabase(this);
            SQLiteDatabase db = md.getWritableDatabase();
            if(uatype.equals(TYPE[0]))
            {
                long res = db.insert(MyDatabase.TABLE_NAME1, null, cv);
                if (res != -1) {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
                cno.requestFocus();
            }
            if(uatype.equals(TYPE[1]))
            {
                long res = db.insert(MyDatabase.TABLE_NAME2, null, cv);
                if (res != -1) {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
                cno.requestFocus();
            }
        }
    }
}
