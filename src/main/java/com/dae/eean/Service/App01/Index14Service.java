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
    public String SelectMaxMisnumWish(IndexDa023Dto parm){
        return  appMapper.SelectMaxMisnumWish(parm);
    }
    public String SelectCheckMisnum(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnum(parm);
    }
    public String SelectCheckMisnumOrd(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumOrd(parm);
    }

    public String SelectCheckMisnumWish(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumWish(parm);
    }
    public String SelectMaxSeq(IndexDa023Dto parm){
        return  appMapper.SelectMaxSeq(parm);
    }
    public String SelectMaxSeqWish(IndexDa023Dto parm){
        return  appMapper.SelectMaxSeqWish(parm);
    }
    public List<IndexDa024Dto> SelectDa024List(IndexDa024Dto parm){
        return  appMapper.SelectDa024List(parm);
    }
    public List<IndexDa024Dto> SelectDa026List(IndexDa024Dto parm){
        return  appMapper.SelectDa026List(parm);
    }
    public Boolean InsertDa023(IndexDa023Dto parm){ return  appMapper.InsertDa023(parm);}
    public Boolean InsertDa024(IndexDa024Dto parm){ return  appMapper.InsertDa024(parm);}
    public Boolean InsertDa025(IndexDa023Dto parm){ return  appMapper.InsertDa025(parm);}
    public Boolean InsertDa026(IndexDa024Dto parm){ return  appMapper.InsertDa026(parm);}
    public Boolean InsertDa023Order(IndexDa024Dto parm){ return  appMapper.InsertDa023Order(parm);}
    public Boolean InsertDa024Order(IndexDa024Dto parm){ return  appMapper.InsertDa024Order(parm);}

    public Boolean InsertIa011(IndexIa011Dto parm){ return  appMapper.InsertIa011(parm);}
    public Boolean InsertIa012(IndexIa012Dto parm){ return  appMapper.InsertIa012(parm);}
    public Boolean DeleteDA023(IndexDa024Dto parm){ return  appMapper.DeleteDA023(parm);}
    public Boolean DeleteDA024(IndexDa024Dto parm){ return  appMapper.DeleteDA024(parm);}
    public Boolean DeleteDA024Ord(IndexDa024Dto parm){ return  appMapper.DeleteDA024Ord(parm);}



    public Boolean DeleteDA025(IndexDa024Dto parm){ return  appMapper.DeleteDA025(parm);}
    public Boolean DeleteDA026(IndexDa024Dto parm){ return  appMapper.DeleteDA026(parm);}
    public Boolean UpdateDA024(IndexDa024Dto parm){ return  appMapper.UpdateDA024(parm);}
    public Boolean UpdateDA026(IndexDa024Dto parm){ return  appMapper.UpdateDA026(parm);}



}
