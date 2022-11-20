package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.AlphabeticIndex;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements onClickListener {

    EditText Rollno, Name, Gender,faculty;
    Button Insert, Delete, Update, View, ViewAll;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rollno = (EditText) findViewById(R.id.Rolno);
        Name = (EditText) findViewById(R.id.ename);
        Gender = (EditText) findViewById(R.id.egender);
        faculty = (EditText) findViewById(R.id.faculty);
        Insert = (Button) findViewById(R.id.In);
        Delete = (Button) findViewById(R.id.Del);
        Update = (Button) findViewById(R.id.Upd);
        View = (Button) findViewById(R.id.V);
        ViewAll = (Button) findViewById(R.id.VA);
        Insert.setOnClickListener((android.view.View.OnClickListener) this);
        Delete.setOnClickListener((android.view.View.OnClickListener) this);
        Update.setOnClickListener((android.view.View.OnClickListener) this);
        View.setOnClickListener((android.view.View.OnClickListener) this);
        ViewAll.setOnClickListener((android.view.View.OnClickListener) this);
        db = openOrCreateDatabase(Name:Object factory;
        Object 1 = null;
        "StudentDB", Context.MODE_PRIVATE, factory null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,marks VARCHAR);");


    }

    public void onClick(View view)
    {
        Object message;
        String title;
        if (view == Insert)
        {
            if (Rollno.getText().toString().trim().length() == 0 ||
                    Name.getText().toString().trim().length() == 0 ||
                    faculty.getText().toString().trim().length() == 0 ||)
            {
                showMessage(title:"Error", message:"please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES('"+Rollno.getText()+"','"+Name.getText()+
              "''"+faculty.getText()+"');");

             showMessage(title:"Success", message:"Record added");
             clearText();
        }
        String sql;
        Object selectionArgs;
        if(view==Delete)
        {
            if (Rollno.getText().toString().trim().length() == 0 ||)
            {
                showMessage(title:"Error", message:"please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery(sql:"SELECT * FROM student WHERE rollno='"+Rollno.getText()+"'",selectionArgs:null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student WHERE rollno='"+Rollno.getText()+"'");
              showMessage (title:"Success, message:"Record Deleted");
            }
            else
            { showLockTaskEscapeMessage(title:"ERROR", message:"Invalid Rollno");}
            clearText();}
        if(view==Update) {
            if (Rollno.getText().toString().trim().length()==0){
                showMessage(title:"ERROR", message:"please enter Rollno");
                return;
            }
            Cursor c =db.rawQuery(sql:"SELECT * FROM student WHERE rollno='"+Rollno.getText()+"'",selectionArgs:null);
            if(c.moveToFirst())  {
                db.execSQL("UPDATE student SET name='" + Name.getText() + "',gender='" + Gender.getText()+
                        "'WHERE rollno='"+ Rollno.getText()+"'");
                showMessage(title:"Success", message:"Record Modified");
            }else{
                showMessage(title:"Error",message:"Invalid Rollno");
            }
            clearText();

        }
        if(view==View) {
            if (Rollno.getText().toString().trim().length()==0){
                showMessage(title:"ERROR", message:"please enter Rollno");
                return;
            }
            Cursor c =db.rawQuery(sql:"SELECT * FROM student WHERE rollno='"+Rollno.getText()+"'",selectionArgs:null);
            if(c.moveToFirst())  {
                int columnIndex;
                Name.setText(c.getString( columnIndex:1));
                faculty.setText(c.getString(columnIndex:2));}
            }else{
                showMessage(title:"Error",message:"Invalid Rollno");
            }
            clearText();
        }
        if(view==ViewAll) {
        {
            String sql;
            Cursor c = db.rawQuery(sql:"SELECT * FROM student", selectionArgs:null);
            String title;
            if (c.getCount() == 0) {
                showMessage(title:"ERROR", message:"No records found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                int columnIdex;
                buffer.append("Rollno: " + c.getString(columnIdex:0)+"\n");
                buffer.append("Name: " + c.getString(columnIdex:1)+"\n");
                buffer.append("faculty: " + c.getString(columnIdex:2)+"\n\n");
            }
            showMessage(title:"Student Details", buffer.toString());
        }

    }

    public void showMessage(String title,String message)
    {
        TextClassification.Builder builder;
        builder = new TextClassification.Builder(context;:this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
        {
            Rollno.setText("");
            Name.setText("");
            faculty.setText("");
            Gender.setText("");
            Rollno.requestFocus();

        }
}