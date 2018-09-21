package him.com.hri3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    String type[] = {"Login Type----","Client","Trainer","Admin"};
    EditText et1,et2;
    Spinner lpsp1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login");

        sp = getSharedPreferences("login_details",MODE_PRIVATE);
        spe = sp.edit();


        et1 = findViewById(R.id.lpet1);
        et2 = findViewById(R.id.lpet2);
        lpsp1 = findViewById(R.id.lpsp1);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,type);
        lpsp1.setAdapter(aa);

        long cno  = sp.getLong("k1",1212);
        if(cno != 1212)
        {
            int pos = 0;
            String pwd = sp.getString("k2",null);
            String atype = sp.getString("k3",null);
            et1.setText(""+cno);
            et2.setText(pwd);
            if(atype.equals(type[1]))
                pos = 1;
            if(atype.equals(type[2]))
                pos = 2;
            if(atype.equals(type[3]))
                pos = 3;
            lpsp1.setSelection(pos);
            userLogin(null);
            //startActivity(new Intent(this,WelcomeActivity.class));
        }
    }

    public void registration_form(View view)
    {
        Intent i =new Intent(this,Registration_form.class);
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void userLogin(View view)
    {
        String cno = et1.getText().toString().trim();
        String upwd = et2.getText().toString();

        if(cno.isEmpty() || upwd.isEmpty())
        {
            Toast.makeText(this, "FIELDS ARE EMPTY", Toast.LENGTH_SHORT).show();
        }
        else
        {
            long ucno = Long.parseLong(cno);
            MyDatabase md = new MyDatabase(this);
            SQLiteDatabase db = md.getWritableDatabase();

            String uatype = lpsp1.getSelectedItem().toString();
            if(uatype.equals("Client"))
            {


                Cursor c = db.rawQuery("select FNAME,LNAME from CLIENT where CONTACT = " + ucno + " and PASSWORD = '" + upwd + "'", null);
                if (c.moveToFirst()) {
                    // String[] columns = {MyDatabase.FNAME,MyDatabase.LNAME};
                    // Cursor c = db.query(MyDatabase.TABLE_NAME1,columns,null,null,null,null,null);

                    String ufname = c.getString(0);
                    String ulname = c.getString(1);
                    Intent i = new Intent(this, WelcomeClient.class);
                    i.putExtra("k1", ufname);
                    i.putExtra("k2", ulname);
                    i.putExtra("k3", ucno);

//Storing data in Shared Prefereneces--------------------------------------------------------------------------------------
                    spe.putLong("k1",ucno);
                    spe.putString("k2",upwd);
                    spe.putString("k3","Client");
                    spe.commit();
                    finish();
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
            }
            if(uatype.equals("Trainer"))
            {
                Cursor c = db.rawQuery("select FNAME,LNAME from TRAINER where CONTACT = " + ucno + " and PASSWORD = '" + upwd + "'", null);
                if (c.moveToFirst()) {
                    // String[] columns = {MyDatabase.FNAME,MyDatabase.LNAME};
                    // Cursor c = db.query(MyDatabase.TABLE_NAME1,columns,null,null,null,null,null);

                    String ufname = c.getString(0);
                    String ulname = c.getString(1);
                    Intent i = new Intent(this, WelcomeTrainer.class);
                    i.putExtra("k1", ufname);
                    i.putExtra("k2", ulname);
                    i.putExtra("k3", ucno);
//Storing data in Shared Prefereneces--------------------------------------------------------------------------------------
                    spe.putLong("k1",ucno);
                    spe.putString("k2",upwd);
                    spe.putString("k3","Trainer");
                    spe.commit();
                    finish();

                    startActivity(i);

                }
                else
                {
                    Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
            }
            if(uatype.equals("Admin"))
            {
                long admincno = 9755404637l;
                if(ucno == admincno && upwd.equals("9755404637"))
                {
                    //Storing data in Shared Prefereneces--------------------------------------------------------------------------------------
                    spe.putLong("k1",ucno);
                    spe.putString("k2",upwd);
                    spe.putString("k3","Admin");
                    spe.commit();
                    finish();

                    Intent i = new Intent(this, WelcomeAdmin.class);
                    startActivity(i);
                }
            }
        }
            et1.setText("");
            et2.setText("");
            et1.requestFocus();
    }


    public void toServices(View view)
    {
        Intent i = new Intent(this, ActivityServices.class);
        startActivity(i);
    }
}
