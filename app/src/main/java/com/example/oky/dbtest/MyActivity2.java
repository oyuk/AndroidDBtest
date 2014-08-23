package com.example.oky.dbtest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity2 extends Activity {

    EditText title;
    EditText content;
    CustomListItem item;
    CreateProductHelper cph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        item = (CustomListItem)getIntent().getSerializableExtra("item");
        String st = "id:"+item.getId()+"\ntitle:"+item.getTitle()+"\ncontent:"+item.getContent();

        findViews();
        title.setText(item.getTitle(),EditText.BufferType.NORMAL);
        content.setText(item.getContent(), EditText.BufferType.NORMAL);

        cph = CreateProductHelper.getInstance(this);

    }


    private void findViews(){
        title = (EditText)findViewById(R.id.my_activity2_editText);
        content = (EditText)findViewById(R.id.my_activity2_editText2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.menu_upload){
            String new_title = title.getText().toString();
            String new_content = content.getText().toString();


            cph.update(new_title,new_content,this.item.getId());

            finish();
        }
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
