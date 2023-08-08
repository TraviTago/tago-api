package com.tago.domain.common.converter;

import com.tago.domain.member.domain.vo.TripType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class TripTypeEnumArrayConverter implements AttributeConverter<List<TripType>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<TripType> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        List<String> values = convertEnumToString(attribute);

        return String.join(DELIMITER, values);
    }

    @Override
    public List<TripType> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return new ArrayList<>();
        }
        return Arrays.stream(dbData.split(DELIMITER))
                .map(TripType::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> convertEnumToString(List<TripType> attribute) {
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
