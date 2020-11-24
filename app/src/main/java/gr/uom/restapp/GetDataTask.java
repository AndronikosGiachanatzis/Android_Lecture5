package gr.uom.restapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetDataTask extends AsyncTask<String, Void, List<Post>> {

    public static final String TAG = "RestApp";
    public List<Post> postList;

    private PostArrayAdapter adapter;

    public GetDataTask(PostArrayAdapter  adapter){
        this.adapter = adapter;
    }



    public String downloadRestData(String remoteUrl){
        Log.d(TAG, "downloading data...");

        StringBuilder sb = new StringBuilder();

        // catch any exception while trying to connect
        try{

            URL url = new URL(remoteUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            int responseCode = conn.getResponseCode();

            // check status code of response
            if (responseCode == 200){
                Log.d(TAG, "request accepted");

                // read the data in the response
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = reader.readLine();
                while (line != null){
                    sb.append(line).append("\n");
                    line = reader.readLine();
                }
                reader.close();

                return sb.toString();
            }
            else{
                Log.d(TAG, "something went wrong. Response code was: "+ responseCode);
            }


        }catch (Exception e){
            Log.e(TAG, "ERROR");

        }

        return "";

    }

    @Override
    protected List<Post> doInBackground(String... strings) {
        String url = strings[0];
        Log.d(TAG, "doing task in background for url " + url);

        // get posts in raw json format (just a huge string)
        String postJson = downloadRestData(url);

        // parse the json data
        JsonParser jsonParser = new JsonParser();

        // return the data (to postExecute)
        return jsonParser.parsePostData(postJson);
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        postList = posts;

        // log results
        Log.d(TAG, "Just got result");
        for (Post post: postList){
            Log.d(TAG, post.toString());
        }

        // inform the adapter that the results have come
        adapter.setPostList(postList);


    }

    public List<Post> getPostList() {
        return postList;
    }
}
