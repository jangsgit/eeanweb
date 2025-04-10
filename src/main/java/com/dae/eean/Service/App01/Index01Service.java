package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.Index01Dto;
import com.dae.eean.DTO.App01.Index03Dto;
import com.dae.eean.DTO.App01.Index20Dto;
import com.dae.eean.Mapper.App01.Index01Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index01Service")
public class Index01Service {

    @Autowired
    Index01Mapper indexMapper;

    public List<Index01Dto> getComCodeList(Index01Dto parm){
        return indexMapper.getComCodeList(parm);
    }


    public Boolean InsertComCode(Index01Dto parm){ return  indexMapper.InsertComCode(parm);}
    public Boolean UpdateComCode(Index01Dto parm){  return  indexMapper.UpdateComCode(parm);}
    public Boolean DeleteComCode(Index01Dto parm){  return  indexMapper.DeleteComCode(parm);}


    public Boolean InsertJupsu(Index20Dto parm){ return  indexMapper.InsertJupsu(parm);}
    public Boolean UpdateJupsu(Index20Dto parm){ return  indexMapper.UpdateJupsu(parm);}
    public Boolean UpdateJupsuMisnum(Index20Dto parm){ return  indexMapper.UpdateJupsuMisnum(parm);}

    public Boolean DeleteJupsu(Index20Dto parm){ return  indexMapper.DeleteJupsu(parm);}

    public Boolean ModifyJupsuMemo(Index20Dto parm){ return  indexMapper.ModifyJupsuMemo(parm);}
    public Boolean UpdateDevJupsu(Index20Dto parm){ return  indexMapper.UpdateDevJupsu(parm);}
    public Boolean UpdateDevJupsuUnsong(Index20Dto parm){ return  indexMapper.UpdateDevJupsuUnsong(parm);}
    public Boolean UpdateDevJupsuUnsongDevnum(Index20Dto parm){ return  indexMapper.UpdateDevJupsuUnsongDevnum(parm);}

    public Boolean UpdateMisJupsu(Index20Dto parm){ return  indexMapper.UpdateMisJupsu(parm);}

    public Index20Dto GetAsJumsuDataYU(Index20Dto parm){
        return indexMapper.GetAsJumsuDataYU(parm);
    }
    public Index20Dto GetAsJumsuDataMU(Index20Dto parm){
        return indexMapper.GetAsJumsuDataMU(parm);
    }

    public List<Index01Dto> getComCodeLists(Index01Dto parm){
        return indexMapper.getComCodeLists(parm);
    }

    public List<Index01Dto> GetComcodeDetailList(Index01Dto parm){
        return  indexMapper.GetComcodeDetailList(parm);
    }
    public List<Index01Dto> getWperidlist(Index01Dto parm){
        return  indexMapper.getWperidlist(parm);
    }
    public List<Index03Dto> getAsyumulist(Index03Dto parm){
        return  indexMapper.getAsyumulist(parm);
    }

    public Boolean InsertComCodeDetail(Index01Dto parm){ return  indexMapper.InsertComCodeDetail(parm);}
    public Boolean UpdateComCodeDetail(Index01Dto parm){  return  indexMapper.UpdateComCodeDetail(parm);}
    public Boolean DeleteComCodeDetail(Index01Dto parm){  return  indexMapper.DeleteComCodeDetail(parm);}

    public String GetComCodeCheck(Index01Dto parm){  return  indexMapper.GetComCodeCheck(parm);  }

    public String SelectMaxJupsu(Index20Dto parm){  return  indexMapper.SelectMaxJupsu(parm);  }


}
