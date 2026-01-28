package com.dae.eean.Mapper.App01;

import com.dae.eean.DTO.App01.IndexDa024Dto;

public interface Index14ExcelMapper {
    void selectDa024ForExcel(IndexDa024Dto param, org.apache.ibatis.session.ResultHandler<IndexDa024Dto> handler);
}
