package com.dae.eean.Service.impl;

import com.dae.eean.DTO.App01.IndexDa024Dto;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlRunListImpl implements RowMapper<IndexDa024Dto> {
    @Override
    public IndexDa024Dto mapRow(ResultSet rs, int rowNum) throws SQLException {
        IndexDa024Dto _index024Dto = new IndexDa024Dto();
        _index024Dto.setCltcd(rs.getString("cltcd"));
        _index024Dto.setMisdate(rs.getString("misdate"));
        _index024Dto.setMisnum(rs.getString("misnum"));
        _index024Dto.setSeq(rs.getString("seq"));
        _index024Dto.setPcode(rs.getString("pcode"));
        _index024Dto.setPname(rs.getString("pname"));
        _index024Dto.setPsize(rs.getString("psize"));
        _index024Dto.setPunit(rs.getString("punit"));
        _index024Dto.setQty(rs.getInt("qty"));
        _index024Dto.setUamt(rs.getInt("uamt"));
        _index024Dto.setSamt(rs.getInt("samt"));
        _index024Dto.setAddamt(rs.getInt("addamt"));
        _index024Dto.setRemarkaa(rs.getString("remarkaa"));
        _index024Dto.setRemarkbb(rs.getString("remarkbb"));
        _index024Dto.setMakflag(rs.getString("makflag"));
        _index024Dto.setAmt(rs.getInt("amt"));
        _index024Dto.setAcorp(rs.getString("acorp"));
        _index024Dto.setPbonsa(rs.getString("pbonsa"));
        _index024Dto.setPmodel(rs.getString("pmodel"));
        _index024Dto.setPcolor(rs.getString("pcolor"));
        _index024Dto.setInname(rs.getString("inname"));
        _index024Dto.setMisgubun(rs.getString("misgubun"));
        _index024Dto.setFixflag(rs.getString("fixflag"));
        _index024Dto.setDevflag(rs.getString("devflag"));
        _index024Dto.setPernm(rs.getString("pernm"));
        _index024Dto.setUnsongnum(rs.getString("unsongnum"));
        _index024Dto.setFixdate(rs.getString("fixdate"));
        _index024Dto.setFixdatetime(rs.getString("fixdatetime"));
        _index024Dto.setJqty(rs.getInt("jqty"));
        //_index024Dto.setIndate(rs.getString("indate"));
        //_index024Dto.setInperid(rs.getString("inperid"));
//        _index024Dto.setPbonsa2(rs.getString("pbonsa2"));
//        _index024Dto.setFrdate(rs.getString("frdate"));
//        _index024Dto.setTodate(rs.getString("todate"));
//        _index024Dto.setJfrdate(rs.getString("jfrdate"));
//        _index024Dto.setJtodate(rs.getString("jtodate"));
//        _index024Dto.setPerid(rs.getString("perid"));
//        _index024Dto.setOmisdate(rs.getString("omisdate"));
//        _index024Dto.setOmisnum(rs.getString("omisnum"));
//        _index024Dto.setOseq(rs.getString("oseq"));
//        _index024Dto.setRownum(rs.getInt("rownum"));
//        _index024Dto.setAsano(rs.getString("asano"));
//        _index024Dto.setAname(rs.getString("aname"));
//        _index024Dto.setAupte(rs.getString("aupte"));
//        _index024Dto.setAjong(rs.getString("ajong"));
//        _index024Dto.setAjuso(rs.getString("ajuso"));
//        _index024Dto.setEeacorp(rs.getString("eeacorp"));
//        _index024Dto.setEeasano(rs.getString("eeasano"));
//        _index024Dto.setEeaname(rs.getString("eeaname"));
//        _index024Dto.setEeupte(rs.getString("eeupte"));
//        _index024Dto.setEeajong(rs.getString("eeajong"));
//        _index024Dto.setEeajuso(rs.getString("eeajuso"));
//        _index024Dto.setAtelno(rs.getString("atelno"));
//        _index024Dto.setEeatelno(rs.getString("eeatelno"));
//        _index024Dto.setMisdatetxt(rs.getString("misdatetxt"));
//        _index024Dto.setEeacodetxt(rs.getString("eeacodetxt"));
//        _index024Dto.setRemark(rs.getString("remark"));
//        _index024Dto.setMessage(rs.getString("message"));
//        _index024Dto.setJpbgubn(rs.getString("jpbgubn"));
//        _index024Dto.setDeltime(rs.getString("deltime"));
        return _index024Dto;
    }


}

