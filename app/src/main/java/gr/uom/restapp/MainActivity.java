package gr.uom.restapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RestApp";

    private static final String REMOTE_API = "https://jsonplaceholder.typicode.com/posts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: STARTING Download of Web Service Data");

        // create the adapter
        ListView postListView = findViewById(R.id.postListView);

        PostArrayAdapter postArrayAdapter = new PostArrayAdapter(this,
                R.layout.list_record,
                new ArrayList<Post>(),
                postListView
        );

        GetDataTask getDataTaskObject = new GetDataTask(postArrayAdapter);
        getDataTaskObject.execute(REMOTE_API);


        Log.d(TAG, "STARTED ASYNC Downloading of Web Service Data");
    }


}