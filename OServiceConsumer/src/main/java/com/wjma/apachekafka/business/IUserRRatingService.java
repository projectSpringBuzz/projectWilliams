package com.wjma.apachekafka.business;

import com.wjma.spring.dto.OrderDTO;

public interface IUserRRatingService {

	public void saveDetail(OrderDTO order) throws Exception;
}
