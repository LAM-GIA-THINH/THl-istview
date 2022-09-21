package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMenu;
    ArrayList<Menu> arrayMenu;
    Menu_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        adapter = new Menu_adapter( MainActivity.this,R.layout.thuc_don,arrayMenu);
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("Tên",arrayMenu.get(i).getTen());
                intent.putExtra("Mô tả",arrayMenu.get(i).getMoTa());
                intent.putExtra("Hinh",arrayMenu.get(i).getHinh());
                startActivity(intent);


            }
        });
        lvMenu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                openDialog(i);
                return false;
            }
        });
    }

    private void openDialog(int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xoá?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrayMenu.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }

    private void AnhXa() {
        lvMenu = (ListView) findViewById(R.id.listViewMenu);
        arrayMenu = new ArrayList<>();
        arrayMenu.add(new Menu("Burger","Burger",R.drawable.burger));
        arrayMenu.add(new Menu("Pizza","Pizza phô mai",R.drawable.pizza));
        arrayMenu.add(new Menu("Taco","Taco thịt heo",R.drawable.taco));


    }
}