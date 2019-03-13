package mx.com.softwell.inventarioapp.common.model.dataAccess;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseRealtimeDatabaseAPI {
    private DatabaseReference mReference;

    private static FirebaseRealtimeDatabaseAPI instance = null;

    private FirebaseRealtimeDatabaseAPI() {
        mReference = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseRealtimeDatabaseAPI getInstance() {
        if (instance == null)
            instance = new FirebaseRealtimeDatabaseAPI();
        return instance;
    }

    public DatabaseReference getmReference(){
        return mReference;
    }
}
