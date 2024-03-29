package com.dae.eean.Service.impl;


import com.dae.eean.DTO.App01.IndexDa024Dto;
import com.dae.eean.Service.SqlRunner;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class SqlRunQueryImpl implements SqlRunner {

	@Autowired(required = true)
    private NamedParameterJdbcTemplate  jdbcTemplate;

	
    public List<Map<String, Object>> getRows(String sql, MapSqlParameterSource dicParam){    	
    	
    	List<Map<String, Object>> rows = null;
    	
    	
    	try {
    		rows = this.jdbcTemplate.queryForList(sql, dicParam);
		} 
    	catch(DataAccessException de) {
    		System.out.println(de);
    	}
    	catch (Exception e) {
			// TODO: handle exception
			//logWriter.addDbLog("error", "SqlRunQueryImpl.getRows", e);
		}
    	return rows;
    }
    
    public Map<String, Object> getRow(String sql, MapSqlParameterSource dicParam){    	

    	Map<String, Object> row = null;
    	
    	try {
    		row = this.jdbcTemplate.queryForMap(sql, dicParam);
		} 
    	catch(DataAccessException de) {
    		
    	
    	}
    	catch (Exception e) {
			// TODO: handle exception
			//logWriter.addDbLog("error", "SqlRunQueryImpl.getRow", e);
		}
    	return row;
    }

	public List<IndexDa024Dto> getListRows(String sql, MapSqlParameterSource dicParam){
		return jdbcTemplate.query(sql, dicParam, new SqlRunListImpl());
	}

    public int execute(String sql, MapSqlParameterSource dicParam) {
    	
    	int rowEffected = 0;
    	// TODO Auto-generated method stub
    	try {
    		rowEffected = this.jdbcTemplate.update(sql, dicParam);
		} catch (Exception e) {
			// TODO: handle exception
			//logWriter.addDbLog("error", "SqlRunQueryImpl.excute", e);
		}
    	
    	return rowEffected;
    }
    
    public int queryForCount(String sql,  MapSqlParameterSource dicParam) {
    	//select count(*) from xxx where ~
    	return this.jdbcTemplate.queryForObject(sql, dicParam, int.class);
    }
    
    public <T> T queryForObject(String sql,  MapSqlParameterSource dicParam, RowMapper<T> mapper) throws DataException {
    	T rr= this.jdbcTemplate.queryForObject(sql, dicParam, mapper); 
    	return rr;    	
    }

    
}
