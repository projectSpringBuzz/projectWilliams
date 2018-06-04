package com.wjma.spring.dao;

import java.util.List;

import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.dto.NoteDTO;

public interface IUserRRatingDao {

	List<Integer> findOrderIdByPhoneNumber(String phoneNumber);
	int insertOrderIDForPhoneNumber(Integer id, String phoneNumber);
	List<DetailDTO> findProductsByOrderId(int orderId);
	List<NoteDTO> findNotesByOrderId(int orderId);
	int saveProduct(DetailDTO detail);
	int saveNote(NoteDTO note);
	
	int updateRatingProduct(int orderID, String productName, int rating);
}
