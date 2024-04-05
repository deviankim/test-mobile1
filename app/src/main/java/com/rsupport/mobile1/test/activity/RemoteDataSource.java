package com.rsupport.mobile1.test.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

// 원격 데이터 소스 클래스
public class RemoteDataSource {
    // 기본 URL
    private static final String BASE_URL = "https://www.gettyimages.com/photos/collaboration";

    // 이미지 URL을 가져오는 메서드
    public LiveData<ArrayList<String>> getImageUrls(int currentPage) {
        MutableLiveData<ArrayList<String>> imageUrlsLiveData = new MutableLiveData<>();

        // 다음 페이지 URL 생성
        String nextPageUrl = BASE_URL + "?assettype=image&sort=mostpopular&phrase=collaboration&license=rf%2Crm&page=" + currentPage;

        // 네트워크 요청을 위한 쓰레드 생성
        new Thread(() -> {
            try {
                // Jsoup을 사용하여 웹 페이지 파싱
                Document doc = Jsoup.connect(nextPageUrl).get();
                // 이미지 URL 파싱
                ArrayList<String> imageUrls = parseImageUrls(doc);
                // LiveData에 이미지 URL 전달
                imageUrlsLiveData.postValue(imageUrls);
            } catch (IOException e) {
                e.printStackTrace();
                // 에러가 발생하면 LiveData에 null 전달
                imageUrlsLiveData.postValue(null);
            }
        }).start();

        return imageUrlsLiveData;
    }

    // 이미지 URL 파싱 메서드
    private ArrayList<String> parseImageUrls(Document doc) {
        ArrayList<String> imageUrls = new ArrayList<>();
        // img 태그 중 이미지 URL을 포함한 요소들을 선택
        Elements elements = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        // 각 요소에서 이미지 URL 추출하여 리스트에 추가
        for (Element element : elements) {
            String imageUrl = element.attr("src");
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }
}
