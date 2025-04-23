package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.myapplication.DataBase.DBHandler;
import com.example.myapplication.Model.PostModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostDAO {
    private DBHandler dbHandler;

    public PostDAO(Context context) {
        dbHandler = new DBHandler(context);
    }

    public List<PostModel> getAllPosts(int userId) {
        List<PostModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String query = "SELECT p.id, p.content, u.name, u.img AS avatar, pi.img AS post_img, " +
                "i.likes, " +
                "(SELECT COUNT(*) FROM interact WHERE id_post = p.id AND likes = 1) AS like_count " +
                "FROM post p " +
                "JOIN users u ON p.id_user = u.id " +
                "LEFT JOIN post_image pi ON pi.id_post = p.id " +
                "LEFT JOIN interact i ON i.id_post = p.id AND i.id_user = ? " +
                "ORDER BY p.id DESC";

        Cursor c = db.rawQuery(query, new String[]{String.valueOf(userId)});
        HashMap<Integer, PostModel> postMap = new HashMap<>();

        if (c.moveToFirst()) {
            do {
                int postId = c.getInt(0);
                String content = c.getString(1);
                String userName = c.getString(2);
                String avatar = c.getString(3);
                String imagePath = c.getString(4); // post_img
                boolean isLiked = c.getInt(5) == 1;
                int likeCount = c.getInt(6); // <-- lấy từ like_count
                PostModel post;
                if (postMap.containsKey(postId)) {
                    post = postMap.get(postId);
                } else {
                    post = new PostModel(postId, content, userName, avatar, null, likeCount, isLiked);
                    post.imageList = new ArrayList<>();
                    postMap.put(postId, post);
                }

                if (imagePath != null && !imagePath.isEmpty()) {
                    post.imageList.add(imagePath);
                }
            } while (c.moveToNext());
        }
        c.close();

        list.addAll(postMap.values());
        return list;
    }

    public long insertPost(int userId, String content, List<Uri> imageUris) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_user", userId);
        values.put("content", content);
        long postId = db.insert("post", null, values);

        if (postId != -1 && imageUris != null) {
            for (Uri uri : imageUris) {
                ContentValues imgValues = new ContentValues();
                imgValues.put("id_post", postId);
                imgValues.put("img", uri.toString());
                db.insert("post_image", null, imgValues);
            }
        }

        return postId;
    }

    public boolean isPostLikedByUser(int postId, int userId) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM interact WHERE id_post = ? AND id_user = ? AND likes = 1",
                new String[]{String.valueOf(postId), String.valueOf(userId)});
        boolean liked = cursor.moveToFirst();
        cursor.close();
        return liked;
    }

    public void toggleLike(int postId, int userId) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        if (isPostLikedByUser(postId, userId)) {
            db.delete("interact", "id_post = ? AND id_user = ? AND likes = 1",
                    new String[]{String.valueOf(postId), String.valueOf(userId)});
        } else {
            ContentValues values = new ContentValues();
            values.put("id_post", postId);
            values.put("id_user", userId);
            values.put("likes", true);
            db.insert("interact", null, values);
        }
    }
}
