package br.edu.ifsc.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SQLiteDatabase db = openOrCreateDatabase("mydata", MODE_PRIVATE, null);

        db.execSQL("DROP TABLE IF EXISTS alunos");
        db.execSQL("CREATE TABLE IF NOT EXISTS alunos (id INTEGER  PRIMARY KEY AUTOINCREMENT, nome VARCHAR)");

        db.execSQL("INSERT INTO alunos (id, nome) VALUES ('Diego')");
        db.execSQL("INSERT INTO alunos (id, nome) VALUES ('Cruz')");
        db.execSQL("INSERT INTO alunos (id, nome) VALUES ('Mendes')");
        db.execSQL("INSERT INTO alunos (id, nome) VALUES ('Tereza Cruz')");
        db.execSQL("INSERT INTO alunos (id, nome) VALUES ('Aurora Mendes')");

        Cursor cursor = db.rawQuery("SELECT id, nome FROM alunos", null);
        cursor.moveToFirst();
        ArrayList<String> item = new ArrayList<>();

        do {
            String s = cursor.getString(cursor.getColumnIndex("nome"));
            Log.i("Resultado SQL:", s);
            item.add(s);
        }while (cursor.moveToNext());

        list =  findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1, item);

        list.setAdapter(adapter);

    }
}