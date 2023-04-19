package com.dae.eean.Mapper.App01;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.dae.eean.DTO.App01.Index02Dto;

import java.util.List;

@Repository
@Mapper
public interface Index02Mapper {

    public List<Index02Dto> GetCifList(Index02Dto parm) ;
    public List<Index02Dto> GetCifListTot(Index02Dto parm) ;

    public String getIndex02MaxSeq(String  parm) ;
    public Boolean InsertCif(Index02Dto  parm) ;
    public Boolean UpdateCif(Index02Dto  parm) ;
    public Boolean DeleteCif(Index02Dto  parm) ;


}
