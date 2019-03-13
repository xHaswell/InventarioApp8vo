package mx.com.softwell.inventarioapp.mainModule.model.dataAccess;


import mx.com.softwell.inventarioapp.common.pojo.Producto;


public interface ProductsEventListener {
    void onChildAdded(Producto producto);
    void onChildUpdated(Producto producto);
    void onChildRemoved(Producto producto);

    void onError(int resMsg);
}
