package mock.project.hotelmanagement.mapper.service.impl;

import jakarta.annotation.PostConstruct;
import mock.project.hotelmanagement.mapper.converter.LocalDateTimeToStringConverter;
import mock.project.hotelmanagement.mapper.converter.StringToLocalDateTimeConverter;
import mock.project.hotelmanagement.mapper.service.ConverterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService {
    @Autowired
    private StringToLocalDateTimeConverter stringToLocalDateConverter;

    @Autowired
    private LocalDateTimeToStringConverter localDateToStringConverter;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.addConverter(stringToLocalDateConverter);
        modelMapper.addConverter(localDateToStringConverter);
    }

    @Override
    public <T, U> T convertToEntity(U dto, Class<T> entityClass) {
        T entity = modelMapper.map(dto, entityClass);
        return entity;
    }

    @Override
    public <T, U> T convertToDto(U entity, Class<T> dtoClass) {
        T dto = modelMapper.map(entity, dtoClass);
        return dto;
    }
}
