package mock.project.hotelmanagement.mapper.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;

public class StringToLocalDateTimeConverter extends AbstractConverter<String, LocalDateTime> {

    private final DateTimeFormatter dateFormat;

    public StringToLocalDateTimeConverter(String formater) {
        this.dateFormat = DateTimeFormatter.ofPattern(formater);
    }

    @Override
    protected LocalDateTime convert(String s) {
        if (s == null) {
            return null;
        }
        return LocalDateTime.parse(s, dateFormat);
    }
}
