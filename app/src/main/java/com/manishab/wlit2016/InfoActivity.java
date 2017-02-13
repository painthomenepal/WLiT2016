package com.manishab.wlit2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {
    ImageView imageContact;
    TextView name,address,mobileno,email,info,college;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent=getIntent();

       Entity entity= (Entity) intent.getSerializableExtra("entity");
        name=(TextView)findViewById(R.id.name);
        address=(TextView)findViewById(R.id.address);
        email=(TextView)findViewById(R.id.email);
        mobileno=(TextView)findViewById(R.id.mobieno);
        college=(TextView)findViewById(R.id.college);
        info=(TextView)findViewById(R.id.info);
        imageContact=(ImageView)findViewById(R.id.picture);

        name.setText((entity.getName()));
        address.setText((entity.getAddress()));
        email.setText((entity.getEmail()));
        college.setText((entity.getUniversity()));
        info.setText((entity.getInfo()));

        Picasso.with(this).load(entity.getPicture()).into(imageContact);



       // String name=intent.getStringExtra("name");
    }
}
