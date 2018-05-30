package com.wjma.apachekafka.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wjma.spring.dto.DetailDTO;

@Repository
public interface DetailRepository extends CrudRepository<DetailDTO, Integer> {

}
