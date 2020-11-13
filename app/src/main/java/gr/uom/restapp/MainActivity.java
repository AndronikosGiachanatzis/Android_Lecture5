package gr.uom.restapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RestApp";

    private static final String REMOTE_API = "https://jsonplaceholder.typicode.com/posts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize library
        AndroidNetworking.initialize(getApplicationContext());

        // set the JacksonParserFactory
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: STARTING Download of Web Service Data");

        GetDataTask getDataTaskObject = new GetDataTask();
        getDataTaskObject.execute(REMOTE_API);


        Log.d(TAG, "STARTED ASYNC Downloading of Web Service Data");
    }


}