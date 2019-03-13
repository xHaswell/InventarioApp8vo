package mx.com.softwell.inventarioapp.common;






public interface BasicErrorEventCallback {
    void onSuccess();
    void onError(int typeEvent, int resMsg);
}
