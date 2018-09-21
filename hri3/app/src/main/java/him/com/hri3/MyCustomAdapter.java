package him.com.hri3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCustomAdapter extends BaseAdapter
{
    Context context;
    ArrayList arrayList;
    String keys[];

    public MyCustomAdapter(Context context, ArrayList arrayList,String[] keys)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.keys = keys;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.my_list_view_style,parent,false);

        HashMap hm = (HashMap)arrayList.get(position);

        String fname = hm.get(keys[0]).toString();
        String lname = hm.get(keys[1]).toString();
        String sex = hm.get(keys[2]).toString();
        String dob = hm.get(keys[3]).toString();
        long cno = (long)hm.get(keys[5]);
      //  byte image[] = (byte[])hm.get(keys[6]);
      //  Bitmap bit = BitmapFactory.decodeByteArray(image,0,image.length);
        TextView tv1 = v.findViewById(R.id.vactv1);
        TextView tv2 = v.findViewById(R.id.vactv2);
        TextView tv3 = v.findViewById(R.id.vactv3);
        TextView tv4 = v.findViewById(R.id.vactv4);
        tv1.setText(fname+" "+lname);
        tv2.setText(Long.toString(cno));
        tv3.setText(sex);
        tv4.setText(dob);
        //ImageView iv = v.findViewById(R.id.vaciv);
        //iv.setImageBitmap(bit);

        return v;
    }
}

