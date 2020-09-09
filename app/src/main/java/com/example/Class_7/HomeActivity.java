package com.example.Class_7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    String ID,USERNAME,USERROLE;
    Session session;
    Button btnaddemp,btndelemp,btnlistemp;
    EditText etid,etname,etdesg,etemail,etaddrs,etcontcno,etlocation;
    Spinner spnrdept;
    Dialog dialog;
    String EMPID,EMPNAME,EMPDESG,EMPEMAIL,EMPCONTACTNO,EMPADDRS,EMPLOCATION,EMPDEPT;
    String [] DEPARTMENTS={"WEB DEVELOPMENT","ANDROID DEVELOPMENT","SERVER TEAM","ACCOUNTS TEAM","HR DEPT","HOUSE KEEPING"};
    TextView tvabtcmpny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnaddemp=findViewById(R.id.addemployeeBTN);
        btndelemp=findViewById(R.id.delemployeeBTN);
        btnlistemp=findViewById(R.id.listemployeeBTN);
        tvabtcmpny=findViewById(R.id.abtcmpnyTV);
        session=new Session(HomeActivity.this);

        tvabtcmpny.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this,AboutCompanyActivity.class));
        });


        ID=session.getUId();
        USERNAME=session.getUName();
        USERROLE=session.getUrole();


        if(USERROLE.equals("HR")){
            btnaddemp.setVisibility(View.VISIBLE);
            btndelemp.setVisibility(View.VISIBLE);
        }else{
            btnaddemp.setVisibility(View.GONE);
            btndelemp.setVisibility(View.GONE);
        }


        btnaddemp.setOnClickListener(view -> {
             dialog=new Dialog(HomeActivity.this);
            dialog.setContentView(R.layout.addemployee);
            Objects.requireNonNull(dialog.getWindow()).setLayout(LinearLayout.MarginLayoutParams.MATCH_PARENT,LinearLayout.MarginLayoutParams.MATCH_PARENT);
            empIDS();
            empDATA();

            dialog.show();
        });

        btnlistemp.setOnClickListener(view -> {
           startActivity(new Intent(HomeActivity.this,EmployeeListActivity.class));
        });
    }

    private void empIDS(){
        etid=dialog.findViewById(R.id.empidET);
        etname=dialog.findViewById(R.id.empnameET);
        etdesg=dialog.findViewById(R.id.empdesgET);
        etemail=dialog.findViewById(R.id.empemailidET);
        etcontcno=dialog.findViewById(R.id.empcntcnoET);
        etaddrs=dialog.findViewById(R.id.empaddrsET);
        etlocation=dialog.findViewById(R.id.emplocaET);
        spnrdept=dialog.findViewById(R.id.empdepSPNR);

    }

    private void empDATA(){
        ArrayAdapter <String> adapter=new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,DEPARTMENTS);
        spnrdept.setAdapter(adapter);
        spnrdept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String pos= String.valueOf(adapterView.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item = menu.add("Logout");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT|MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setIcon(R.drawable.logout);
        item.setOnMenuItemClickListener(
                item1 -> {

                   session.logout();
                   startActivity(new Intent(HomeActivity.this,MainActivity.class));
                   return true;
                }
        );

        return super.onCreateOptionsMenu(menu);
    }
}