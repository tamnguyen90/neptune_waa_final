package edu.miu.cs.neptune.core.service.impl;

import edu.miu.cs.neptune.core.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<CREATE, UPDATE, DTO, ENTITY, ID> implements BaseService<CREATE, UPDATE, DTO, ENTITY, ID> {

  private JpaRepository<ENTITY, ID> repository;

  public BaseServiceImpl(JpaRepository<ENTITY, ID> repository) {
    this.repository = repository;
  }

  @Override
  public List<DTO> findAll() {
    List<ENTITY> entityList = repository.findAll();
    return fromEntityToDTO(entityList);
  }

  @Override
  public DTO findById(ID id) {
    return null;
  }

  @Override
  public DTO save(CREATE create) {
    ENTITY entity = fromCreateToEntity(create);
    repository.save(entity);
    return fromEntityToDTO(entity);
  }

  @Override
  public DTO update(ID id, UPDATE update) {
    ENTITY entity = repository.findById(id)
        .orElse(null);
    return fromEntityToDTO(entity);
  }

  @Override
  public void deleteById(ID id) {
    repository.deleteById(id);
  }

  @Override
  public long count() {
    return repository.count();
  }

//  @Override
//  public ENTITY fromCreateToEntity(CREATE create) {
//    ENTITY entity = newEntity();
//    return entity;
//  }
//
//  @Override
//  public ENTITY fromUpdateToEntity(UPDATE update) {
//    ENTITY entity = newEntity();
//    return entity;
//  }
//
//  @Override
//  public DTO fromEntityToDTO(ENTITY entity) {
//    DTO dto = newDTO();
//    return dto;
//  }

  @Override
  public List<DTO> fromEntityToDTO(List<ENTITY> entityList) {
    return entityList.stream()
        .map(this::fromEntityToDTO)
        .collect(Collectors.toList());
  }
}
