package mock.project.hotelmanagement.config;

import mock.project.hotelmanagement.mapper.converter.LocalDateTimeToStringConverter;
import mock.project.hotelmanagement.mapper.converter.StringToLocalDateTimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class Appconfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public StringToLocalDateTimeConverter stringToLocalDateConverter() {
    return new StringToLocalDateTimeConverter("yyyy-MM-dd'T'HH:mm");
  }

  @Bean
  public LocalDateTimeToStringConverter localDateToStringConverter() {
    return new LocalDateTimeToStringConverter("yyyy-MM-dd'T'HH:mm");
  }
}
