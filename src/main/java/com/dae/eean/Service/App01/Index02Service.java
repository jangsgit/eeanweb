package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.Mapper.App01.Index02Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index02Service")
public class Index02Service {
    @Autowired
    Index02Mapper appMapper;

    public List<Index02Dto> GetCifList(Index02Dto parm){
                return  appMapper.GetCifList(parm);
    }
    public List<Index02Dto> GetCifListTot(Index02Dto parm){
        return  appMapper.GetCifListTot(parm);
    }
    public Index02Dto GetCifListAcode(Index02Dto parm){
        return  appMapper.GetCifListAcode(parm);
    }
    public Index02Dto GetCifBonsa(Index02Dto parm){
        return  appMapper.GetCifBonsa(parm);
    }

    public String getIndex02MaxSeq(String parm){
        return  appMapper.getIndex02MaxSeq(parm);
    }
    public Boolean InsertCif(Index02Dto parm){ return  appMapper.InsertCif(parm);}
    public Boolean UpdateCif(Index02Dto parm){  return  appMapper.UpdateCif(parm);  }
    public Boolean DeleteCif(Index02Dto parm){  return  appMapper.DeleteCif(parm);  }


}
