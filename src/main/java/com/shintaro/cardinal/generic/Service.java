package com.shintaro.cardinal.generic;


import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.shintaro.cardinal.filter.PropertyFilter;


public class Service<
	entity extends Object ,
	repository extends Repository<entity>
	> extends PropertyFilter {
	
	private repository repository;
	
	protected Service(repository repository) {
		this.repository = repository;
	}
	
	public Iterable<entity> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public entity getByID(UUID iD){
		return repository.findById(iD).get();
	}
	
	public entity save(entity model) {
		return repository.save(model);
	}
	
	public void delete(UUID iD) {
		repository.deleteById(iD);
	}
	
	public entity edit(UUID iD, entity model) {
		entity savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<entity> list(entity params, Specification spec,Pageable page) {
		return repository.findAll(spec,page);
	}

}

