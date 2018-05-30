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
import com.wjma.spring.dto.OrderDTO;

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
	public List<DetailDTO> findDetailsByOrderID(int orderID) {
		return iUserRRatingDao.findProductsByOrderId(orderID);
	}

	@Override
	public List<NoteDTO> findNotesByOrderID(int orderID) {
		return iUserRRatingDao.findNotesByOrderId(orderID);
	}

	/**
	 * insert into (master - detail) for tables mysql, it has rollback if error
	 * exists
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class, DataIntegrityViolationException.class })
	public void saveDetail(OrderDTO order) throws Exception {
		for (DetailDTO p : order.getDetails()) {
			if (iUserRRatingDao.saveProduct(p) != 1) {
				throw new Exception("Error - save product in detailSave");
			}
		}

		for (NoteDTO n : order.getNotes()) {
			if (iUserRRatingDao.saveNote(n) != 1) {
				throw new Exception("Error - save note in detailSave");
			}
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, DataIntegrityViolationException.class })
	public void updateRatingProduct(int orderID, String productName, int rating) throws Exception {
		if (iUserRRatingDao.updateRatingProduct(orderID, productName, rating) == 0) {
			throw new Exception("Error - save rating in detailSave");
		}
	}

	private List<Integer> findOrdersByPhoneNumber(String phoneNumber) {
		List<Integer> listOrderID = null;
		try {
			listOrderID = iUserRRatingDao.findOrderIdByPhoneNumber();
		} catch (Exception e) {
			logger.error("[findOrderIdByPhoneNumber] error ");

			// simulate data from orders
			listOrderID = new ArrayList<Integer>();
			listOrderID.add(1);
			listOrderID.add(2);
		}
		return listOrderID;
	}

	@Override
	public List<OrderDTO> findOrdersListByPhoneNumber(String phoneNumber) {
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		
		List<Integer> listID = findOrdersByPhoneNumber(phoneNumber);
		
		OrderDTO order = null;
		for(Integer orderID : listID){
			order = new OrderDTO();
			order.setOrderID(orderID);
			order.setDetails(this.findDetailsByOrderID(orderID));
			order.setNotes(this.findNotesByOrderID(orderID));
			list.add(order);
		}
		return list;
	}

}
