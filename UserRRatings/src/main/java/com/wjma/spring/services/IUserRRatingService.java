package com.wjma.spring.services;

import java.util.List;

import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.dto.NoteDTO;
import com.wjma.spring.dto.OrderDTO;

public interface IUserRRatingService {

	public List<OrderDTO> findOrdersListByPhoneNumber(String phoneNumber);
	public List<DetailDTO> findDetailsByOrderID(int orderID);
	public List<NoteDTO> findNotesByOrderID(int orderID);
	public void saveDetail(OrderDTO order) throws Exception;
	public void updateRatingProduct(int orderID, String productName, int rating) throws Exception;
}
