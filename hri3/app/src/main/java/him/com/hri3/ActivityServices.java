package him.com.hri3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Array;

public class ActivityServices extends AppCompatActivity
{
    Spinner asspin1;
    String[] activity = {"Select--Activity----------------","Yoga","Cricket","Football","Judo","Karate","Swimming","Taekwondo","Badminton","Volleyball",
                               "Basketball","Cycling" };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        asspin1 = findViewById(R.id.asspin1);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,activity);
        asspin1.setAdapter(aa);
    }

    public void contactUs(View view)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","atulshubh9@gmail.com", null));
        //  emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        //  emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }
}
