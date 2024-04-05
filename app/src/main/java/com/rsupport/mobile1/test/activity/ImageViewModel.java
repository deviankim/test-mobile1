package com.rsupport.mobile1.test.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

// 이미지 URL을 가져오는 ViewModel 클래스
public class ImageViewModel extends ViewModel {
    private ImageRepository imageRepository;

    // 생성자
    public ImageViewModel() {
        imageRepository = new ImageRepository();
    }

    // 현재 페이지에 해당하는 이미지 URL을 가져오는 메서드
    public LiveData<ArrayList<String>> getImageUrls(int currentPage) {
        return imageRepository.getImageUrls(currentPage);
    }
}
