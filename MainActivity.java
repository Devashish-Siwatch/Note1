package com.example.notesfinal2;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.content.ContentValues;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int button_count=1;
    int i =1;
    DBHelper helper = new DBHelper(this);
    SQLiteDatabase database;

    EditText mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }







    public void saveData(View view) {
        TextView show =findViewById(R.id.NoteShow);
       /*
        if (button_count>1){
         database.delete("TAB", "_id = ?", new String[]{""+i});
         i++;
       }

        */
        //show.setVisibility(View.GONE);
        if (button_count==1){
            database = helper.getWritableDatabase();

        }
        StringBuilder builder = new StringBuilder();
        button_count++;
        mess = (EditText) findViewById(R.id.Note);
        String message = mess.getText().toString();
        ContentValues values = new ContentValues();
        values.put("NOTE", message);
        database.insert("TAB", null, values);
        Cursor cursor = database.rawQuery("SELECT NOTE FROM TAB", new String[]{});

        if(cursor != null){
            cursor.moveToFirst();
        }
        do {
            String a =cursor.getString(0);
            builder.append("\n"+ a);
        }while (cursor.moveToNext());


        show.setText(builder.toString());




    }

}
