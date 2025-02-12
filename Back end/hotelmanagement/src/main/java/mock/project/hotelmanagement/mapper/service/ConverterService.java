package mock.project.hotelmanagement.mapper.service;

public interface ConverterService {
  public <T, U> T  convertToEntity(U dto, Class<T> entityClass);

  public <T, U> T convertToDto(U entity, Class<T> dtoClass);

}
