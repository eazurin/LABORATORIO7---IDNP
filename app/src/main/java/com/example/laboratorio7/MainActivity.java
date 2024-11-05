package com.example.laboratorio7;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Edificio> edificios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarEdificios();
        EdificioAdapter adapter = new EdificioAdapter(edificios);
        recyclerView.setAdapter(adapter);
    }

    private void cargarEdificios() {

        int[] imagenesResIds = {
                R.drawable.catedral_arequipa,
                R.drawable.santa_catalina,
                R.drawable.mundo_alpaca

        };

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("edificios.txt")));
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String titulo = parts[0].trim();
                String categoria = parts[1].trim();
                String descripcion = parts[2].trim();


                int imagenResId = (index < imagenesResIds.length) ? imagenesResIds[index] : R.drawable.catedral_arequipa;


                edificios.add(new Edificio(titulo, categoria, descripcion, imagenResId));

                index++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
