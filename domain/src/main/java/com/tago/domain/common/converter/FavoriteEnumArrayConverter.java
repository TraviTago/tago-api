package com.tago.domain.common.converter;

import com.tago.domain.tag.domain.vo.TagType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class FavoriteEnumArrayConverter implements AttributeConverter<List<TagType>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<TagType> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        List<String> values = convertEnumToString(attribute);

        return String.join(DELIMITER, values);
    }

    @Override
    public List<TagType> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return new ArrayList<>();
        }
        return Arrays.stream(dbData.split(DELIMITER))
                .map(TagType::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> convertEnumToString(List<TagType> attribute) {
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
