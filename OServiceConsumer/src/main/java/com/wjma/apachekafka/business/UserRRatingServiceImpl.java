package com.wjma.apachekafka.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjma.apachekafka.dao.DetailRepository;
import com.wjma.apachekafka.dao.NoteRepository;
import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.dto.NoteDTO;
import com.wjma.spring.dto.OrderDTO;

@Service
public class UserRRatingServiceImpl implements IUserRRatingService {
	
	@Autowired
	private DetailRepository detailRepository;
	
	@Autowired
	private NoteRepository noteRepository;

	/**
	 * insert into (master - detail) for tables mysql, it has rollback if error
	 * exists
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class, DataIntegrityViolationException.class })
	public void saveDetail(OrderDTO order) throws Exception {
		for (DetailDTO p : order.getDetails()) {
			detailRepository.save(p);
		}

		for (NoteDTO n : order.getNotes()) {
			noteRepository.save(n);
		}
	}
}
