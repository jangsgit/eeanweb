package com.dae.eean.Mapper.App01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index11Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface Index11Mapper {

    public List<Index11Dto> GetAsJupsuList01(Index11Dto parm) ;
    public List<Index11Dto> GetAsJupsuList02(Index11Dto parm) ;
    public List<Index11Dto> GetAsJupsuList03(HashMap<String,String> parm)  ;
    public List<Index11Dto> GetAsJupsuList04(Index11Dto parm) ;


}
