package com.example.oky.dbtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {

    ListView listView;
    Button button;
    customeListItemAdapter adapter;
    CreateProductHelper cph;
    SQLiteDatabase db;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        cph = CreateProductHelper.getInstance(this);

        findViews();
        setListeners();

        alertDialog = new AlertDialog.Builder(MyActivity.this);

        setAdapters();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void findViews(){
        listView = (ListView)findViewById(R.id.listView);
    }

    protected void setListeners(){

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               ListView lv = (ListView) parent;
               CustomListItem item = (CustomListItem) lv.getItemAtPosition(position);

               Intent intent = new Intent(MyActivity.this, MyActivity2.class);
               intent.putExtra("item", item);
               startActivity(intent);

           }

       });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MyActivity.this,"tttte",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    protected void addData(){

        /*
        参考サイト
        http://gupuru.hatenablog.jp/entry/2014/03/28/225644
        */

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialoglayout,(ViewGroup)findViewById(R.id.alertdialog_layout));

        alertDialog.setView(layout);
        alertDialog.setTitle("たいとる");          //タイトル

        alertDialog.setPositiveButton("ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("DEBUG", "dialogのojボタンがタップされたよ");

                EditText et = (EditText) layout.findViewById(R.id.editText);
                EditText et2 = (EditText) layout.findViewById(R.id.editText3);

                String title = et.getText().toString();
                String content = et2.getText().toString();

                Log.d("DEBUG", "title\n" + title + "content\n" + content);

                if (title.length() > 0 && content.length() > 0) {

                    CustomListItem item = new CustomListItem(title, content);
                    db = cph.getWritableDatabase();
                    long id = cph.insert(db,item);

                    if(id > 0) {
                        item.setId(id);
                        adapter.add(item);
                        adapter.notifyDataSetChanged();
                    }

                }

            }
        });

            alertDialog.setNegativeButton("no",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("DEBUG","dialogのnoボタンがタップされたよ");

            }
            });

        // ダイアログの作成と表示
        alertDialog.show();
    }

    protected void setAdapters(){

        db = cph.getReadableDatabase();

        adapter = new customeListItemAdapter(MyActivity.this,cph.retrive(db));
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
        if(id == R.id.add_data){
            addData();
        }
        else if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
