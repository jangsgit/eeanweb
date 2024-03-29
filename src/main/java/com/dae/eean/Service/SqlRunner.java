package com.dae.eean.Service;

import com.dae.eean.DTO.App01.IndexDa024Dto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

public interface SqlRunner {

	public List<Map<String, Object>> getRows(String sql, MapSqlParameterSource mapParam);
	public List<IndexDa024Dto> getListRows(String sql, MapSqlParameterSource mapParam);


	public Map<String, Object> getRow(String sql, MapSqlParameterSource mapParam);
	
	public int execute(String sql, MapSqlParameterSource mapParam);
	
	public int queryForCount(String sql,  MapSqlParameterSource mapParam);
	
	public <T> T queryForObject(String sql,  MapSqlParameterSource mapParam, RowMapper<T> mapper);
}
