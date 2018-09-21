package him.com.hri3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Registration_form extends AppCompatActivity
{
    String[] TYPE = {"Type--","Client","Trainer"};
    EditText lname,fname,dob;
    Spinner atype;
    RadioGroup sex;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        this.setTitle("Register");

        fname = findViewById(R.id.rfet1);
        lname = findViewById(R.id.rfet2);
        dob = findViewById(R.id.rfet3);
        sex = findViewById(R.id.crfrg1);

        atype = findViewById(R.id.rfspin1);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,TYPE);
        atype.setAdapter(aa);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void nextPage(View view)
    {
        String ufname = fname.getText().toString().trim();
        String ulname = lname.getText().toString().trim();
        String udob = dob.getText().toString().trim();
        String uatype = atype.getSelectedItem().toString();
        int rgid = sex.getCheckedRadioButtonId();
        RadioButton rb = findViewById(rgid);
        String usex = rb.getText().toString();
        if(ufname.isEmpty() || ulname.isEmpty() || udob.isEmpty() || uatype.equals(TYPE[0]) )
        {
            Toast.makeText(this, "FIELDS ARE EMPTY", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent i = new Intent(this, RegistrationForm2.class);
            i.putExtra("k1", ufname);
            i.putExtra("k2", ulname);
            i.putExtra("k3", udob);
            i.putExtra("k4", uatype);
            i.putExtra("k5", usex);
            startActivity(i);
            finish();
        }
    }

    public void pickDOB(View view)
    {
        Calendar c = Calendar.getInstance();
        int c_year = c.get(Calendar.YEAR);
        int c_month = c.get(Calendar.MONTH);
        int c_day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },c_year,c_month,c_day);
        DatePicker dp = dpd.getDatePicker();
        dp.setMaxDate(System.currentTimeMillis());
        dpd.show();
    }
}
