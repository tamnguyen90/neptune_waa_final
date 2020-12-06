package edu.miu.cs.neptune.core.service;

import java.util.List;

public interface BaseService<CREATE, UPDATE, DTO, ENTITY, ID> {

  List<DTO> findAll();

  DTO findById(ID id);

  DTO save(CREATE create);

  DTO update(ID id, UPDATE update);

  void deleteById(ID id);

  long count();

  ENTITY fromCreateToEntity(CREATE create);

  ENTITY fromUpdateToEntity(UPDATE update);

  DTO fromEntityToDTO(ENTITY entity);

  List<DTO> fromEntityToDTO(List<ENTITY> entityList);

}
