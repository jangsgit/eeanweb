package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.*;
import com.dae.eean.Mapper.App01.Index14Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index14Service")
public class Index14Service {
    @Autowired
    Index14Mapper  appMapper;

    public String SelectMaxMisnum(IndexDa023Dto parm){
        return  appMapper.SelectMaxMisnum(parm);
    }
    public String SelectCheckMisnum(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnum(parm);
    }
    public String SelectMaxSeq(IndexDa023Dto parm){
        return  appMapper.SelectMaxSeq(parm);
    }
    public List<IndexDa024Dto> SelectDa024List(IndexDa024Dto parm){
        return  appMapper.SelectDa024List(parm);
    }
    public Boolean InsertDa023(IndexDa023Dto parm){ return  appMapper.InsertDa023(parm);}
    public Boolean InsertDa024(IndexDa024Dto parm){ return  appMapper.InsertDa024(parm);}
    public Boolean InsertIa011(IndexIa011Dto parm){ return  appMapper.InsertIa011(parm);}
    public Boolean InsertIa012(IndexIa012Dto parm){ return  appMapper.InsertIa012(parm);}


}
