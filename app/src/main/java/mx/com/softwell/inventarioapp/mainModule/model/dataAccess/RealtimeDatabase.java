package mx.com.softwell.inventarioapp.mainModule.model.dataAccess;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import mx.com.softwell.inventarioapp.R;
import mx.com.softwell.inventarioapp.common.BasicErrorEventCallback;
import mx.com.softwell.inventarioapp.common.pojo.Producto;
import mx.com.softwell.inventarioapp.common.model.dataAccess.FirebaseRealtimeDatabaseAPI;
import mx.com.softwell.inventarioapp.mainModule.events.MainEvent;


public class RealtimeDatabase {
    private static final String PATH_PRODUCTS = "productos";

    private FirebaseRealtimeDatabaseAPI mDatabaseAPI;
    private ChildEventListener mProductsChildEventListener;

    public RealtimeDatabase() {
        mDatabaseAPI = FirebaseRealtimeDatabaseAPI.getInstance();
    }

    private DatabaseReference getProductsReference() {
        return mDatabaseAPI.getmReference().child(PATH_PRODUCTS);
    }

    public void subscribeToProducts(final ProductsEventListener listener) {
        if (mProductsChildEventListener == null) {
            mProductsChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    listener.onChildAdded(getProduct(dataSnapshot));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    listener.onChildUpdated(getProduct(dataSnapshot));
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    listener.onChildRemoved(getProduct(dataSnapshot));
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    switch (databaseError.getCode()) {
                        case DatabaseError.PERMISSION_DENIED:
                            listener.onError(R.string.main_error_permission_denied);
                            break;
                        default:
                            listener.onError(R.string.main_error_server);
                    }
                }
            };
        }
        getProductsReference().addChildEventListener(mProductsChildEventListener);
    }

    private Producto getProduct(DataSnapshot dataSnapshot) {
        Producto producto = dataSnapshot.getValue(Producto.class);
        if (producto != null)
            producto.setIdProducto(dataSnapshot.getKey());
        return producto;
    }

    public void unsubscribeToProducts() {
        if (mProductsChildEventListener != null) {
            getProductsReference().removeEventListener(mProductsChildEventListener);
        }
    }

    public void removeProduct(Producto producto, final BasicErrorEventCallback callback) {
        getProductsReference().child(producto.getIdProducto())
                .removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            callback.onSuccess();
                        } else {
                            switch (databaseError.getCode()) {
                                case DatabaseError.PERMISSION_DENIED:
                                    callback.onError(MainEvent.ERROR_TO_REMOVE,
                                            R.string.main_error_remove);
                                default:
                                    callback.onError(MainEvent.ERROR_SERVER, R.string.main_error_server);
                            }
                        }
                    }
                });
    }
}
