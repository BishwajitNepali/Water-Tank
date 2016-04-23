package com.thanglastudio.watertank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataFeed extends AppCompatActivity {

    EditText feed1,feed2,feed3;
    Button graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_feed);
        feed1=(EditText)findViewById(R.id.editText);
        feed2=(EditText)findViewById(R.id.editText2);
        feed3=(EditText)findViewById(R.id.editText3);
        graph=(Button)findViewById(R.id.button);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1=feed1.getText().toString();
                String data2=feed2.getText().toString();
                String data3=feed3.getText().toString();

                Intent intent= new Intent(DataFeed.this,MainActivity.class);
                intent.putExtra("data1",data1);
                intent.putExtra("data2",data2);
                intent.putExtra("data3",data3);
                startActivity(intent);

            }
        });


    }
}
