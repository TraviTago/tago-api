//package com.tago.api.place.application;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final PlaceService placeService;
//
//    public DataLoader(PlaceService placeService) {
//        this.placeService = placeService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String csvFilePath = "/Users/yell/Downloads/place_v2.csv"; // 실제 CSV 파일 경로로 변경
//        placeService.savePlaceFromCsv(csvFilePath);
//    }
//
//}
