package mx.com.softwell.inventarioapp.mainModule.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.softwell.inventarioapp.common.pojo.Producto;
import mx.com.softwell.inventarioapp.R;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> productos;
    private Context context;
    private OnItemClickListener listener;

    public ProductoAdapter(List<Producto> productos, OnItemClickListener listener) {
        this.productos = productos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_producto, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Producto producto = productos.get(i);
        viewHolder.setOnClickListener(producto, listener);

        viewHolder.txtDatos.setText(context.getString(R.string.item_product_data, producto.getNombre(),
                "" + producto.getCantidad()));

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(context)
                .load(producto.getFotoUrl())
                .apply(options)
                .into(viewHolder.imgFoto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void add(Producto producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
            notifyItemInserted(productos.size() - 1);
        } else {
            update(producto);
        }
    }

    public void update(Producto producto) {
        if (productos.contains(producto)) {
            final int index = productos.indexOf(producto);
            productos.set(index, producto);
            notifyItemChanged(productos.size() - 1);
        }
    }

    public void remove(Producto producto) {
        if (productos.contains(producto)) {
            final int index = productos.indexOf(producto);
            productos.remove(index);
            notifyItemRemoved(index);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgFoto)
        AppCompatImageView imgFoto;
        @BindView(R.id.txtDatos)
        AppCompatTextView txtDatos;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Producto producto, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongItemClick(producto);
                    return true;
                }
            });
        }
    }
}
