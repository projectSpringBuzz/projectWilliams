package com.wjma.apachekafka.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wjma.spring.dto.NoteDTO;

@Repository
public interface NoteRepository extends CrudRepository<NoteDTO, Integer>{

}
