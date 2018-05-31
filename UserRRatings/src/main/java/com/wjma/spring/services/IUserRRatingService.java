package com.wjma.spring.services;

import java.util.List;

import com.wjma.spring.dto.DetailDTO;

public interface IUserRRatingService {

	public List<DetailDTO> findDetailsByPhoneNumber(String phoneNumber);
	public void saveDetail(DetailDTO detail) throws Exception;
	public void updateRatingProduct(int rating, int productId) throws Exception;
}
