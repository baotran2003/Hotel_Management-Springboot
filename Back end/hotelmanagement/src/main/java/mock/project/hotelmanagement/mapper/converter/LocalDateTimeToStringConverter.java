package mock.project.hotelmanagement.mapper.converter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;

public class LocalDateTimeToStringConverter extends AbstractConverter<LocalDateTime, String> {

    private final DateTimeFormatter dateFormat;

    public LocalDateTimeToStringConverter(String formater) {
        this.dateFormat = DateTimeFormatter.ofPattern(formater);
    }

    @Override
    protected String convert(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(dateFormat);
    }
}
