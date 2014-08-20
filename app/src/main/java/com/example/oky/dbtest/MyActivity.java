package com.example.oky.dbtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    ListView listView;
    Button button;
    ArrayList<String> labelList;
    ArrayAdapter<String> adapter;


    /* データベース名 */
    private final static String DB_NAME = "DBTest";
    /* データベースのバージョン */
    private final static int DB_VER = 1;
    /* コンテキスト */
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        findViews();
        setListeners();
        setAdapters();

        CreateProductHelper cph = new CreateProductHelper(this,DB_NAME,null,DB_VER);


    }

    private void findViews(){
        listView = (ListView)findViewById(R.id.listView);
        button = (Button)findViewById(R.id.button);
    }

    protected void setListeners(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button:

                        break;
                }

            }
        });

    }

    protected void setAdapters(){
        labelList = new ArrayList<String>();

        for (int i=0;i <= 20;i++){
            labelList.add("item" + i);
        }

        adapter = new ArrayAdapter<String>(MyActivity.this,R.layout.cell,labelList);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
