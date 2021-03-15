package com.example.appvacov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    Button registrar, cancelar;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        registrar = (Button) findViewById(R.id.button1);
        spinner1= (Spinner)findViewById(R.id.spinner1);
        cancelar = (Button) findViewById(R.id.button2);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.0.227/appvacov/registro.php");
            }
        });

        cancelar = (Button) findViewById(R.id.button2);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this,MainActivity.class));
            }
        });


    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
            @Override
            public void onResponse (String Response){
            Toast.makeText(getApplicationContext(),"Usuario Registrado", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse (VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
            Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("cedula", editText1.getText().toString());
                parametros.put("correo", editText2.getText().toString());
                parametros.put("nombres", editText3.getText().toString());
                parametros.put("apellidos", editText4.getText().toString());
                parametros.put("clave", editText6.getText().toString());
                parametros.put("usuario", spinner1.getSelectedItem().toString());
                parametros.put("edad", editText5.getText().toString());
                return parametros;
                }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }



    }
