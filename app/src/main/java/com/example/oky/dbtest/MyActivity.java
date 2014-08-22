package com.example.oky.dbtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    ListView listView;
    Button button;
    ArrayList<CustomListItem> labelList;
    customeListItemAdapter adapter;
    CreateProductHelper cph;
    AlertDialog.Builder alertDialog;


    /* データベース名 */
    private final static String DB_NAME = "DBTest";
    /* データベースのバージョン */
    private final static int DB_VER = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        findViews();
        setListeners();
        setAdapters();

        cph = new CreateProductHelper(this,DB_NAME,null,DB_VER);
        alertDialog = new AlertDialog.Builder(MyActivity.this);

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
                        addData();
                        break;
                }

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
                    adapter.add(new CustomListItem(title, content));
                    adapter.notifyDataSetChanged();
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
        labelList = new ArrayList<CustomListItem>();

        for (int i=0;i <= 20;i++){
            labelList.add(new CustomListItem("test","test"));
        }

        adapter = new customeListItemAdapter(MyActivity.this,labelList);
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
