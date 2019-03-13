package mx.com.softwell.inventarioapp.mainModule.view.adapters;


import mx.com.softwell.inventarioapp.common.pojo.Producto;





public interface OnItemClickListener {
    void onItemClick(Producto producto);
    void onLongItemClick(Producto producto);
}
