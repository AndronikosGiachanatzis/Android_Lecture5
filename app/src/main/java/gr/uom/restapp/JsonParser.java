package gr.uom.restapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static final String TAG = "JsonParser";
    private static final String USER_ID_LITERAL = "userId";
    private static final String POST_ID_LITERAL = "id";
    private static final String TITLE_ID_LITERAL = "title";
    private static final String BODY_ID_LITERAL = "body";

    // postJsonData contains in raw format ALL posts that will be separated
    public List<Post> parsePostData(String postJsonData){

        // the list that will contain all the posts
        List<Post> postList = new ArrayList<>();

        try{
            // get a vector that contais the elemtents of the parent Json Array
            JSONArray jsonPostArray = new JSONArray(postJsonData);

            // iterate through the elements of the json array
            for (int i=0; i<jsonPostArray.length(); i++){
                // get the i-th post
                JSONObject postJsonObject = jsonPostArray.getJSONObject(i);

                // parse the chcracteristics of the current post
                int userId = postJsonObject.getInt(USER_ID_LITERAL);
                int postId = postJsonObject.getInt(POST_ID_LITERAL);
                String title = postJsonObject.getString(TITLE_ID_LITERAL);
                String body = postJsonObject.getString(BODY_ID_LITERAL);

                // create the post
                Post post = new Post();
                post.setPostID(postId);
                post.setUerID(userId);
                post.setPostTitle(title);
                post.setPostBody(body);

                // add the post to the final list
                postList.add(post);
            }


        } catch (Exception ex){
            Log.e(TAG, "Error in Json parsing", ex);
        }


        return postList;
    }
}
