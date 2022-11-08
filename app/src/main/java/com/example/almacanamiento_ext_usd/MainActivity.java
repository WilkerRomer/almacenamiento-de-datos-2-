package com.example.almacanamiento_ext_usd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre    = (EditText)findViewById(R.id.nombre_busqueda);
        et_contenido = (EditText)findViewById(R.id.multi_contenido);
    }
    public void Guardar (View ver) {
        String nombre    = et_nombre.   getText().toString();
        String contenido = et_contenido.getText().toString();

        try {
            File targetaExt = Environment.getExternalStorageDirectory();
            Toast.makeText(this, targetaExt.getPath(), Toast.LENGTH_SHORT).show();
            File rutaArchivo = new File(targetaExt.getPath(), nombre);
            OutputStreamWriter creacionArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            creacionArchivo.write(contenido);
            creacionArchivo.flush();
            creacionArchivo.close();

            Toast.makeText(this, "Seguardó correctamente el contenido", Toast.LENGTH_SHORT).show();
            et_nombre.setText("");
            et_contenido.setText("");
        }catch (IOException e) {
            Toast.makeText(this, "NO se guardó correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void Consultar (View activo) {
        String nombre = et_nombre.getText().toString();

        try{
            File targetaSD = Environment.getExternalStorageDirectory();
            File UbicacionArch = new File(targetaSD.getPath(), nombre);
            InputStreamReader OpenElemento = new InputStreamReader(openFileInput(nombre));

            BufferedReader leerArchivo = new BufferedReader(OpenElemento);
            String lineas = leerArchivo.readLine();
            String contenidoCompleto = "";

            while(lineas != null) {
                contenidoCompleto = contenidoCompleto + lineas + "\n";
                lineas = leerArchivo.readLine();
            }

            leerArchivo.close();
            OpenElemento.close();
            et_contenido.setText(contenidoCompleto);

        }catch (IOException e) {
            Toast.makeText(this, "Error al leer el Archivo", Toast.LENGTH_SHORT).show();
        }
    }
}