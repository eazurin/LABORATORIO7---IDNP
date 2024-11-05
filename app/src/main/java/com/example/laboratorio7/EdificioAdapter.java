package com.example.laboratorio7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EdificioAdapter extends RecyclerView.Adapter<EdificioAdapter.EdificioViewHolder> {

    private List<Edificio> edificios;

    public EdificioAdapter(List<Edificio> edificios) {
        this.edificios = edificios;
    }

    @NonNull
    @Override
    public EdificioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edificio, parent, false);
        return new EdificioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EdificioViewHolder holder, int position) {
        Edificio edificio = edificios.get(position);
        holder.tvTitulo.setText(edificio.getTitulo());
        holder.tvCategoria.setText(edificio.getCategoria());
        holder.tvDescripcion.setText(edificio.getDescripcion());
        holder.imageView.setImageResource(edificio.getImagen());
    }

    @Override
    public int getItemCount() {
        return edificios.size();
    }

    public static class EdificioViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvCategoria, tvDescripcion;
        ImageView imageView;

        public EdificioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

