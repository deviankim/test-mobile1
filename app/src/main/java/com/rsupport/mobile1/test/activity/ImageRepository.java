package com.rsupport.mobile1.test.activity;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;

// 이미지 URL을 가져오는 레포지토리 클래스
public class ImageRepository {
    private RemoteDataSource remoteDataSource;

    // 생성자
    public ImageRepository() {
        remoteDataSource = new RemoteDataSource();
    }

    // 현재 페이지에 해당하는 이미지 URL을 가져오는 메서드
    public LiveData<ArrayList<String>> getImageUrls(int currentPage) {
        return remoteDataSource.getImageUrls(currentPage);
    }
}
