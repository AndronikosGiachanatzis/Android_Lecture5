package gr.uom.restapp;

import android.os.AsyncTask;
import android.util.Log;

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

public class GetDataTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "RestApp";
    public String jsonResult;

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
    protected String doInBackground(String... strings) {
        String url = strings[0];
        Log.d(TAG, "doing task in background for url " + url);
//        return downloadRestData(url);

        final StringBuilder result = new StringBuilder();
        AndroidNetworking.get(url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Response: " + response);
                        result.append(response.toString());
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "error");
                    }
                });
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        jsonResult = result;
        Log.d(TAG, "Just got result");
        Log.d(TAG, jsonResult);

    }
}
