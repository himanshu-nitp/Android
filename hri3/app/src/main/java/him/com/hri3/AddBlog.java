package him.com.hri3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddBlog extends AppCompatActivity
{
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_blog);

        et = findViewById(R.id.abet1);
    }

    public void saveBlog(View view)
    {
        String blog = et.getText().toString().trim();

        if(blog.isEmpty())
        {
            et.setError("Empty");
        }
        else
        {
            MyDatabase md = new MyDatabase(this);
            SQLiteDatabase db = md.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(MyDatabase.BLOG,blog);
            long res = db.insert(MyDatabase.TABLE_NAME3,null,cv);
            if (res != -1)
            {
                Toast.makeText(this, "Blog added", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Unable to save", Toast.LENGTH_SHORT).show();
            }
            et.requestFocus();
        }
    }

}
