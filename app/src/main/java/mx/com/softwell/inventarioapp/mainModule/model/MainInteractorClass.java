package mx.com.softwell.inventarioapp.mainModule.model;


import org.greenrobot.eventbus.EventBus;

import mx.com.softwell.inventarioapp.common.BasicErrorEventCallback;
import mx.com.softwell.inventarioapp.common.pojo.Producto;
import mx.com.softwell.inventarioapp.mainModule.events.MainEvent;
import mx.com.softwell.inventarioapp.mainModule.model.dataAccess.ProductsEventListener;
import mx.com.softwell.inventarioapp.mainModule.model.dataAccess.RealtimeDatabase;


public class MainInteractorClass implements MainInteractor {
    private RealtimeDatabase mDatabase;

    public MainInteractorClass() {
        mDatabase = new RealtimeDatabase();
    }

    @Override
    public void subscribeToProducts() {
        mDatabase.subscribeToProducts(new ProductsEventListener() {
            @Override
            public void onChildAdded(Producto producto) {
                post(producto, MainEvent.SUCCESS_ADD);
            }

            @Override
            public void onChildUpdated(Producto producto) {
                post(producto, MainEvent.SUCCESS_UPDATE);
            }

            @Override
            public void onChildRemoved(Producto producto) {
                post(producto, MainEvent.SUCCESS_REMOVE);
            }

            @Override
            public void onError(int resMsg) {
                post(MainEvent.ERROR_SERVER, resMsg);
            }
        });
    }

    @Override
    public void unsubscribeToProducts() {
        mDatabase.unsubscribeToProducts();
    }

    @Override
    public void removeProduct(Producto producto) {
        mDatabase.removeProduct(producto, new BasicErrorEventCallback() {
            @Override
            public void onSuccess() {
                post(MainEvent.SUCCESS_REMOVE);
            }

            @Override
            public void onError(int typeEvent, int resMsg) {
                post(typeEvent, resMsg);
            }
        });
    }

    private void post(int typeEvent){
        post(null, typeEvent, 0);
    }

    private void post(int typeEvent, int resMsg){
        post(null, typeEvent, resMsg);
    }

    private void post(Producto producto, int typeEvent){
        post(producto, typeEvent, 0);
    }

    private void post(Producto producto, int typeEvent, int resMsg) {
        MainEvent event = new MainEvent();
        event.setProducto(producto);
        event.setTypeEvent(typeEvent);
        event.setResMsg(resMsg);
        EventBus.getDefault().post(event);
    }
}
