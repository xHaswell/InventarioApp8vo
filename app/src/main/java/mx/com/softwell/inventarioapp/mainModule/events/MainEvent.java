package mx.com.softwell.inventarioapp.mainModule.events;

import mx.com.softwell.inventarioapp.common.pojo.Producto;

public class MainEvent {
    public static final int SUCCESS_ADD = 0;
    public static final int SUCCESS_UPDATE = 1;
    public static final int SUCCESS_REMOVE = 2;
    public static final int ERROR_SERVER = 100;
    public static final int ERROR_TO_REMOVE = 101;

    private Producto producto;
    private int typeEvent;
    private int resMsg;

    public MainEvent() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getResMsg() {
        return resMsg;
    }

    public void setResMsg(int resMsg) {
        this.resMsg = resMsg;
    }
}
