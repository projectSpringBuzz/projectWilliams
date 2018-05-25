package com.wjma.spring.dao;

import java.util.List;

import com.wjma.spring.dto.DetailDTO;

public interface IUserRRatingDao {

	List<DetailDTO> findByPhoneNumber(String phoneNumber);
	int saveDetail(DetailDTO detail);
}
