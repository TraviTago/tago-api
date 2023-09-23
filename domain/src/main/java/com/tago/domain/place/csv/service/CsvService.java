//package com.tago.domain.place.csv.service;
//
//import com.opencsv.bean.CsvToBeanBuilder;
//import com.tago.domain.place.csv.mapping.PlaceCsvMapping;
//import com.tago.domain.place.domain.Place;
//import com.tago.domain.place.domain.vo.Info;
//import org.springframework.stereotype.Service;
//
//import java.io.FileReader;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CsvService {
//
//    public List<Place> readCsv(String path) throws Exception {
//        try (FileReader fileReader = new FileReader(path)) {
//            List<PlaceCsvMapping> csvMappings = new CsvToBeanBuilder<PlaceCsvMapping>(fileReader)
//                    .withType(PlaceCsvMapping.class)
//                    .build()
//                    .parse();
//
//            return csvMappings.stream()
//                    .map(this::convertToPlace)
//                    .collect(Collectors.toList());
//        }
//    }
//
//    private Place convertToPlace(PlaceCsvMapping csvMapping) {
//        Info info = Info.builder()
//                .homepage(csvMapping.getHomepage())
//                .telephone(csvMapping.getTelephone())
//                .restDate(csvMapping.getRestDate())
//                .openTime(csvMapping.getOpenTime())
//                .parking(csvMapping.getParking())
//                .build();
//
//        return Place.builder()
//                .id(csvMapping.getId())
//                .title(csvMapping.getTitle())
//                .address(csvMapping.getAddress())
//                .typeId(csvMapping.getTypeId())
//                .createdTime(csvMapping.getCreatedTime())
//                .modifiedTime(csvMapping.getModifiedTime())
//                .imgUrl(csvMapping.getImgUrl())
//                .mapX(csvMapping.getMapX())
//                .mapY(csvMapping.getMapY())
//                .visit(csvMapping.getVisit())
//                .recommendTag(csvMapping.getRecommendTag())
//                .overview(csvMapping.getOverview())
//                .info(info)
//                .build();
//    }
//}
//
