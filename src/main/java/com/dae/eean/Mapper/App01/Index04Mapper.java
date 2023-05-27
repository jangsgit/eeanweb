package com.dae.eean.Mapper.App01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index04Dto;
import com.dae.eean.DTO.App01.IndexDa023Dto;
import com.dae.eean.DTO.App01.Index11Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index04Mapper {

    public List<Index04Dto> SelectJegoIpgo(Index04Dto parm) ;
    public Boolean InsertJegoIpgo(Index04Dto parm) ;
    public Boolean DeleteJaegoIpgo(Index04Dto parm) ;
    public List<Index04Dto> SelectJegoList(Index04Dto parm) ;
}
