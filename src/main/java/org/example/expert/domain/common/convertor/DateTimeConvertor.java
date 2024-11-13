package org.example.expert.domain.common.convertor;

import org.example.expert.domain.common.exception.InvalidRequestException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeConvertor {
    public static LocalDateTime convertStringToLocalDatetime(String str) {
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Not valid datetime format");
        }
    }
}
