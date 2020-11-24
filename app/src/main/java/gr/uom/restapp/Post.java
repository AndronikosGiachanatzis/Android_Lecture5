package gr.uom.restapp;

public class Post {
    
    private int uerID;
    private int postID;
    private String postTitle;
    private String postBody;

    public int getUerID() {
        return uerID;
    }

    public void setUerID(int uerID) {
        this.uerID = uerID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return "Post{" +
                "uerID=" + uerID +
                ", postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", postBody='" + postBody + '\'' +
                '}';
    }
}
