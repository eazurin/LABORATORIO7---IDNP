package com.example.laboratorio7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EdificioAdapter adapter;
    private List<Edificio> edificios = new ArrayList<>();
    private List<Edificio> edificiosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarEdificios();
        edificiosFiltrados.addAll(edificios);

        adapter = new EdificioAdapter(edificiosFiltrados);
        recyclerView.setAdapter(adapter);

        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
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

    private void filtrar(String texto) {
        edificiosFiltrados.clear();
        if (texto.isEmpty()) {
            edificiosFiltrados.addAll(edificios);
        } else {
            for (Edificio edificio : edificios) {
                if (edificio.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                    edificiosFiltrados.add(edificio);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
