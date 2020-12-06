package edu.miu.cs.neptune.core.rest.impl;

import edu.miu.cs.neptune.constant.ControllerConstant;
import edu.miu.cs.neptune.core.rest.BaseController;
import edu.miu.cs.neptune.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseControllerImpl<CREATE, UPDATE, DTO, ENTITY, ID> implements BaseController<CREATE, UPDATE, DTO, ID> {

  private BaseService<CREATE, UPDATE, DTO, ENTITY, ID> service;

  public BaseControllerImpl(BaseService<CREATE, UPDATE, DTO, ENTITY, ID> service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<DTO> findAll() {
    return service.findAll();
  }

  @GetMapping(value = ControllerConstant.PATH_ID)
  @Override
  public DTO findById(@PathVariable("id") ID id) {
    return service.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DTO create(@Valid @RequestBody CREATE create) {
    return service.save(create);
  }

  @PutMapping(value = ControllerConstant.PATH_ID)
  @ResponseStatus(HttpStatus.OK)
  public void update(@PathVariable( "id" ) ID id, @Valid @RequestBody UPDATE update) {
    service.update(id, update);
  }

  @DeleteMapping(value = ControllerConstant.PATH_ID)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") ID id) {
    service.deleteById(id);
  }

  @DeleteMapping(value = ControllerConstant.PATH_COUNT)
  @ResponseStatus(HttpStatus.OK)
  public void count(@PathVariable("id") ID id) {
    service.deleteById(id);
  }

}
