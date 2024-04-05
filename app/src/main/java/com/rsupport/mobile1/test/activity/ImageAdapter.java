package com.rsupport.mobile1.test.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.util.LruCache;

import com.rsupport.mobile1.test.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private ArrayList<String> imageUrls;
    private LruCache<String, Bitmap> imageCache;

    // 생성자
    public ImageAdapter(Context context, ArrayList<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;

        // 이미지 캐시 생성
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
    }

    // ViewHolder 생성
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ImageViewHolder(view);
    }

    // ViewHolder에 데이터 바인딩
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);
        holder.loadBitmap(imageUrl);
    }

    // 데이터 개수 반환
    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    // ViewHolder 클래스 정의
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        // ViewHolder 생성자
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        // 비트맵 로드
        public void loadBitmap(String imageUrl) {
            Bitmap bitmap = imageCache.get(imageUrl);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                // 캐시에 없으면 네트워크에서 비트맵 로드
                new Thread(() -> {
                    try {
                        URL url = new URL(imageUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                        final Bitmap newBitmap = BitmapFactory.decodeStream(inputStream);

                        // 메인 스레드로 비트맵 적용
                        new Handler(Looper.getMainLooper()).post(() -> {
                            imageView.setImageBitmap(newBitmap);
                            imageCache.put(imageUrl, newBitmap);
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    // 새로운 이미지 추가
    public void addImages(ArrayList<String> newImageUrls) {
        imageUrls.addAll(newImageUrls);
        notifyDataSetChanged();
    }
}
