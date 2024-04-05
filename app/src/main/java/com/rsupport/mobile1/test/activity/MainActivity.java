package com.rsupport.mobile1.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsupport.mobile1.test.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private int currentPage = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        // Adapter 설정
        imageAdapter = new ImageAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(imageAdapter);

        // 초기 페이지 로드
        loadNextPage();

        // 스크롤 리스너 설정
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                // 스크롤이 끝에 도달하면 다음 페이지 로드
                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0 && totalItemCount >= 20) {
                    loadNextPage();
                }
            }
        });
    }

    // 다음 페이지 로드
    private void loadNextPage() {
        if (isLoading) {
            return;
        }

        isLoading = true;

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // ViewModel을 통해 이미지 URL 가져오기
        ImageViewModel imageViewModel = new ViewModelProvider(MainActivity.this).get(ImageViewModel.class);
        imageViewModel.getImageUrls(currentPage).observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> imageUrls) {
                progressBar.setVisibility(View.GONE);

                // 이미지 로드 실패시 토스트 메시지 표시
                if (imageUrls == null) {
                    Toast.makeText(MainActivity.this, "이미지를 로드하는데 실패했습니다", Toast.LENGTH_SHORT).show();
                }
                // 이미지 없음 토스트 메시지 표시
                else if (imageUrls.isEmpty()) {
                    Toast.makeText(MainActivity.this, "더 이상 로드할 이미지가 없습니다", Toast.LENGTH_SHORT).show();
                }
                // 이미지 추가 및 페이지 증가
                else {
                    imageAdapter.addImages(imageUrls);
                    currentPage++;
                }
                isLoading = false;
            }
        });
    }
}
