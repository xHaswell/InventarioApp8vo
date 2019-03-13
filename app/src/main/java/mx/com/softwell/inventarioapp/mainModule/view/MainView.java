package mx.com.softwell.inventarioapp.mainModule.view;


import mx.com.softwell.inventarioapp.common.pojo.Producto;

public interface MainView {
    void showProgress();
    void hideProgress();

    void add(Producto producto);
    void update(Producto producto);
    void remove(Producto producto);

    void removeFail();
    void onShowError(int resMsg);
}
