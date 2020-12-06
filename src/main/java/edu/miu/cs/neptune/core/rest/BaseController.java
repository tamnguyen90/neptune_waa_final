package edu.miu.cs.neptune.core.rest;

import java.util.List;

public interface BaseController<CREATE, UPDATE, DTO, ID> {

  List<DTO> findAll();

  DTO findById(ID id);

  DTO create(CREATE create);

  void update(ID id, UPDATE update);

  void delete(ID id);
}
