package com.wjma.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjma.spring.dao.IUserRRatingDao;
import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.dto.NoteDTO;
import com.wjma.spring.dto.ProductDTO;

@Service
public class UserRRatingServiceImpl implements IUserRRatingService {

	private static final Logger logger = LoggerFactory.getLogger(UserRRatingServiceImpl.class);

	@Autowired
	private IUserRRatingDao iUserRRatingDao;

	/**
	 * It may be that a phone number is in more than one order
	 * 
	 * @return List
	 */
	@Override
	public List<DetailDTO> findDetailsByPhoneNumber(String phoneNumber) {
		List<DetailDTO> list = new ArrayList<DetailDTO>();

		List<Integer> listOrderID = null;
		try {
			listOrderID = iUserRRatingDao.findOrderIdByPhoneNumber();
		} catch (Exception e) {
			logger.error("[findOrderIdByPhoneNumber] error ");
			listOrderID = new ArrayList<Integer>();
			listOrderID.add(1);
			listOrderID.add(2);
		}

		DetailDTO detail = null;
		for (Integer orderID : listOrderID) {
			detail = new DetailDTO();
			detail.setOrderID(orderID);
			detail.getProducts().addAll(iUserRRatingDao.findProductsByOrderId(orderID));
			detail.getNotes().addAll(iUserRRatingDao.findNotesByOrderId(orderID));

			list.add(detail);
		}
		return list;
	}

	/**
	 * insert into (master - detail) for tables mysql, it has rollback if error exists
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class, DataIntegrityViolationException.class })
	public void saveDetail(DetailDTO detail) throws Exception {
		for(ProductDTO p : detail.getProducts()) {
			if(iUserRRatingDao.saveProduct(p, detail.getOrderID()) != 1 ) {
				throw new Exception("Error - save product in detailSave");
			}
		}
		
		for(NoteDTO n : detail.getNotes()) {
			if(iUserRRatingDao.saveNote(n, detail.getOrderID()) != 1) {
				throw new Exception("Error - save note in detailSave");
			}
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, DataIntegrityViolationException.class })
	public void updateRatingProduct(int rating, int productId) throws Exception {
		if(iUserRRatingDao.updateRatingProduct(productId, rating) == 0) {
			throw new Exception("Error - save rating in detailSave");
		}
	}

}
