package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index04Dto;
import com.dae.eean.DTO.App01.Index11Dto;
import com.dae.eean.Mapper.App01.Index04Mapper;
import com.dae.eean.Mapper.App01.Index11Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index04Service")
public class Index04Service {
    @Autowired
    Index04Mapper appMapper;

    public List<Index04Dto> SelectJegoIpgo(Index04Dto parm){
        return  appMapper.SelectJegoIpgo(parm);
    }
    public String SelectJegoCheck(Index04Dto parm){
        return  appMapper.SelectJegoCheck(parm);
    }
    public String SelectMaxSeqIpgo(Index04Dto parm){
        return  appMapper.SelectMaxSeqIpgo(parm);
    }

    public Boolean InsertJegoIpgo(Index04Dto parm){ return  appMapper.InsertJegoIpgo(parm);}
    public Boolean DeleteJaegoIpgo(Index04Dto parm){ return  appMapper.DeleteJaegoIpgo(parm);}
    public Boolean DeleteJaegoIpgoAcorp(Index04Dto parm){ return  appMapper.DeleteJaegoIpgoAcorp(parm);}
    public Boolean UpdateJegoIpgo(Index04Dto parm){ return  appMapper.UpdateJegoIpgo(parm);}

    public List<Index04Dto> SelectJegoList(Index04Dto parm){
        return  appMapper.SelectJegoList(parm);
    }

}
