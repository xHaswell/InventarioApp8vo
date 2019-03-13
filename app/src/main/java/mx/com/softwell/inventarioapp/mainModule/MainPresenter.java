package mx.com.softwell.inventarioapp.mainModule;


import mx.com.softwell.inventarioapp.common.pojo.Producto;
import mx.com.softwell.inventarioapp.mainModule.events.MainEvent;

public interface MainPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

    void remove(Producto producto);

    void onEventListener(MainEvent event);
}
