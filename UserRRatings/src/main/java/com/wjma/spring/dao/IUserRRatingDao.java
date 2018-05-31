package com.wjma.spring.dao;

import java.util.List;

import com.wjma.spring.dto.NoteDTO;
import com.wjma.spring.dto.ProductDTO;

public interface IUserRRatingDao {

	List<Integer> findOrderIdByPhoneNumber();
	List<ProductDTO> findProductsByOrderId(int orderId);
	List<NoteDTO> findNotesByOrderId(int orderId);
	int saveProduct(ProductDTO product, int orderId);
	int saveNote(NoteDTO note, int orderId);
	
	int updateRatingProduct(int productId, int rating);
}
