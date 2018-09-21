package him.com.hri3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeAdmin extends AppCompatActivity
{
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_admin);
        this.setTitle("Admin");

        sp = getSharedPreferences("login_details",MODE_PRIVATE);
        spe = sp.edit();
    }

    public void viewAllClient(View view)
    {
        Intent i = new Intent(this,ViewAllClient.class);
        startActivity(i);
    }

    public void addBlog(View view)
    {
        Intent i = new Intent(this,AddBlog.class);
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
