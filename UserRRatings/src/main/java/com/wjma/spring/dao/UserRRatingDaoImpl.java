package com.wjma.spring.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.wjma.spring.dto.DetailDTO;

@Repository
public class UserRRatingDaoImpl implements IUserRRatingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DetailDTO> findByPhoneNumber(String phoneNumber) {
		String SP = "SP_FIND_DETAILS_BY_PHONENUMBER";
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(SP);

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("phoneNumber", phoneNumber);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

		Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			ResultSet result = (ResultSet) entry.getValue();
			String key = (String) entry.getKey();
			Object value = (Object) entry.getValue();
			System.out.println("Key: " + key);
			System.out.println("Value: " + value);
		}
		return null;
	}

	@Override
	public int saveDetail(DetailDTO detail) {
		// TODO Auto-generated method stub
		return 0;
	}

}
