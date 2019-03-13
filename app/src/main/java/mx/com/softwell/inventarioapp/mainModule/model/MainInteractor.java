package mx.com.softwell.inventarioapp.mainModule.model;

import mx.com.softwell.inventarioapp.common.pojo.Producto;

public interface MainInteractor {
    void subscribeToProducts();
    void unsubscribeToProducts();

    void removeProduct(Producto producto);
}
