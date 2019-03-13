package mx.com.softwell.inventarioapp.mainModule;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import mx.com.softwell.inventarioapp.common.pojo.Producto;
import mx.com.softwell.inventarioapp.mainModule.events.MainEvent;
import mx.com.softwell.inventarioapp.mainModule.model.MainInteractor;
import mx.com.softwell.inventarioapp.mainModule.model.MainInteractorClass;
import mx.com.softwell.inventarioapp.mainModule.view.MainView;


public class MainPresenterClass implements MainPresenter {
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenterClass(MainView mView) {
        this.mView = mView;
        this.mInteractor = new MainInteractorClass();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        mInteractor.unsubscribeToProducts();
    }

    @Override
    public void onResume() {
        mInteractor.subscribeToProducts();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mView = null;
    }

    @Override
    public void remove(Producto producto) {
        if (setProgress()) {
            mInteractor.removeProduct(producto);
        }
    }

    private boolean setProgress() {
        if (mView != null) {
            mView.showProgress();
            return true;
        }
        return false;
    }

    @Subscribe
    @Override
    public void onEventListener(MainEvent event) {
        if (mView != null) {
            mView.hideProgress();

            switch (event.getTypeEvent()) {
                case MainEvent.SUCCESS_ADD:
                    mView.add(event.getProducto());
                    break;
                case MainEvent.SUCCESS_UPDATE:
                    mView.update(event.getProducto());
                    break;
                case MainEvent.SUCCESS_REMOVE:
                    mView.remove(event.getProducto());
                    break;
                case MainEvent.ERROR_SERVER:
                    mView.onShowError(event.getResMsg());
                    break;
                case MainEvent.ERROR_TO_REMOVE:
                    mView.removeFail();
                    break;
            }
        }
    }
}
