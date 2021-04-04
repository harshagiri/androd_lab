package com.example.expno5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmployeeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editEmpId, editName, editSalary;
    Button btnAdd, btnDelete, btnModify, btnView, btnViewAll;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmpId = (EditText)findViewById(R.id.editEmpId);
        editName = (EditText)findViewById(R.id.editName);
        editSalary = (EditText)findViewById(R.id.editSalary);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnModify = (Button)findViewById(R.id.btnModify);
        btnView = (Button)findViewById(R.id.btnView);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);

        db = openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(empid VARCHAR, name VARCHAR, salary VARCHAR)");
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText(){
        editEmpId.setText("");
        editName.setText("");
        editSalary.setText("");
        editEmpId.requestFocus();
    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            if (editEmpId.getText().toString().trim().length() == 0 ||
                    editName.getText().toString().trim().length() == 0 ||
                    editSalary.getText().toString().trim().length() == 0 ) {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO employee VALUES("+editEmpId.getText()+",'"+editName.getText()+"',"+editSalary.getText()+")");
            showMessage("Success","Record Added");
            clearText();
        }

        if (view == btnDelete) {
            if (editEmpId.getText().toString().trim().length() == 0 ) {
                showMessage("Error", "Please enter EmployeeID");
                return;
            }
            Cursor cursor = db.rawQuery("SELECT * FROM employee WHERE empId="+editEmpId.getText(), null);
            if(cursor.moveToFirst()) {
                db.execSQL("DELETE FROM  employee WHERE empId = "+editEmpId.getText());
                showMessage("Success","Record Deleted");
            } else {
                showMessage("Success","Invalid Employee ID");
            }

            clearText();
        }

        if (view == btnModify) {
            if (editEmpId.getText().toString().trim().length() == 0 ) {
                showMessage("Error", "Please enter EmployeeID");
                return;
            }
            Cursor cursor = db.rawQuery("SELECT * FROM employee WHERE empId="+editEmpId.getText(), null);
            if(cursor.moveToFirst()) {
                db.execSQL("UPDATE employee SET name = '"+editName.getText()+"',"+ "salary = "+editSalary.getText()
                +" WHERE empId = "+editEmpId.getText());
                showMessage("Success","Record Modified");
            } else {
                showMessage("Success","Invalid Employee ID");
            }

            clearText();
        }

        if (view == btnView) {
            if (editEmpId.getText().toString().trim().length() == 0 ) {
                showMessage("Error", "Please enter EmployeeID");
                return;
            }
            Cursor cursor = db.rawQuery("SELECT * FROM employee WHERE empId="+editEmpId.getText(), null);
            if(cursor.moveToFirst()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Employee ID:"+cursor.getString(0)+"\n");
                stringBuffer.append("Name:"+cursor.getString(1)+"\n");
                stringBuffer.append("Salary:"+cursor.getString(2)+"\n");
                showMessage("Employee Details", stringBuffer.toString());
            } else {
                showMessage("Success","Invalid Employee ID");
            }

            clearText();
        }

        if (view == btnViewAll) {
            Cursor cursor = db.rawQuery("SELECT * FROM employee", null);
            if(cursor.getCount() != 0) {
               StringBuffer stringBuffer = new StringBuffer();
               while(cursor.moveToNext()){
                   stringBuffer.append("Employee ID:"+cursor.getString(0)+"\n");
                   stringBuffer.append("Name:"+cursor.getString(1)+"\n");
                   stringBuffer.append("Salary:"+cursor.getString(2)+"\n");
               }
               showMessage("Employee Details", stringBuffer.toString());
            } else {
                showMessage("Error","No records found");
            }

            clearText();
        }
    }
}