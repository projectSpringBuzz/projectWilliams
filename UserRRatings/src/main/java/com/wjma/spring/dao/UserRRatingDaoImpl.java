package com.wjma.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.dto.NoteDTO;

@Repository
public class UserRRatingDaoImpl implements IUserRRatingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveProduct(DetailDTO detail) {
		String sql = "insert into details(orderID, product_name, rating) values(?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {detail.getOrderID(), detail.getProductName(), detail.getRating()});
	}
	
	@Override
	public int saveNote(NoteDTO note) {
		String sql = "insert into notes(orderID, notes) values(?, ?)";
		return jdbcTemplate.update(sql, new Object[] {note.getOrderID(), note.getNotes()});
	}

	@Override
	public List<DetailDTO> findProductsByOrderId(int orderId) {
		String sql = "{call maddoxBD.SP_FIND_PRODUCTS_BY_ORDERID(?)}";
		return jdbcTemplate.query(sql, new Object[] { orderId }, new RowMapper<DetailDTO>() {

			@Override
			public DetailDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				DetailDTO p = new DetailDTO();
				p.setOrderID(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setRating(rs.getInt(3));
				p.setId(rs.getInt(4));
				return p;
			}
			
		});
	}

	@Override
	public List<NoteDTO> findNotesByOrderId(int orderId) {
		String sql = "{call SP_FIND_NOTES_BY_ORDERID(?)}";
		return jdbcTemplate.query(sql, new Object[] { orderId }, new RowMapper<NoteDTO>() {

			@Override
			public NoteDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				NoteDTO n = new NoteDTO();
				n.setId(rs.getInt(1));
				n.setNotes(rs.getString(2));
				n.setOrderID(rs.getInt(3));
				return n;
			}
			
		});
	}

	@Override
	public List<Integer> findOrderIdByPhoneNumber() {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public int updateRatingProduct(int orderID, String productName, int rating) {
		String sql = "update details set rating = ? where orderID = ? and product_name = ?";
		return jdbcTemplate.update(sql, new Object[] { rating, orderID, productName});
	}

}
