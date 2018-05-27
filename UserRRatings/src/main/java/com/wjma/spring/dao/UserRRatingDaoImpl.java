package com.wjma.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wjma.spring.dto.NoteDTO;
import com.wjma.spring.dto.ProductDTO;

@Repository
public class UserRRatingDaoImpl implements IUserRRatingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveProduct(ProductDTO product, int orderId) {
		String sql = "insert into details(orderID, productName, rating) values(?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {orderId, product.getProductName(), product.getRating()});
	}
	
	@Override
	public int saveNote(NoteDTO note, int orderId) {
		String sql = "insert into notes(orderID, notes) values(?, ?)";
		return jdbcTemplate.update(sql, new Object[] {orderId, note.getNotes()});
	}

	@Override
	public List<ProductDTO> findProductsByOrderId(int orderId) {
		String sql = "{call maddoxBD.SP_FIND_PRODUCTS_BY_ORDERID(?)}";
		return jdbcTemplate.query(sql, new Object[] { orderId }, new RowMapper<ProductDTO>() {

			@Override
			public ProductDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				ProductDTO p = new ProductDTO();
				p.setId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setRating(rs.getInt(3));
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
				return n;
			}
			
		});
	}

	@Override
	public List<Integer> findOrderIdByPhoneNumber() {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public int updateRatingProduct(int productId, int rating) {
		String sql = "update details set rating = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { rating, productId});
	}

}
