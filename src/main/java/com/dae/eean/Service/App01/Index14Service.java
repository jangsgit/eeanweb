package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.*;
import com.dae.eean.Mapper.App01.Index14Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public List<IndexDa024Dto> SelectDa024ListLike(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListLike(parm);
    }

//    public List<IndexDa024Dto> SelectDa024ListPrt(IndexDa024Dto parm){
//        return  appMapper.SelectDa024ListPrt(parm);
//    }

    public List<IndexDa024Dto> SelectDa024ListPrt(IndexDa024Dto parm) {
        return  appMapper.SelectDa024ListPrt(parm);
    };
    public List<IndexDa024Dto> SelectDa024ListPrtGroup(HashMap<String,String> parm) {
        return  appMapper.SelectDa024ListPrtGroup(parm);
    };
    public List<IndexDa024Dto> SelectDa024ListDevGroup(HashMap<String,String> parm) {
        return  appMapper.SelectDa024ListDevGroup(parm);
    };

    public List<IndexDa024Dto> SelectDa026List(IndexDa024Dto parm){
        return  appMapper.SelectDa026List(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListLike(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListLike(parm);
    }

    public List<IndexDa024Dto> SelectDa024ListPerid(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListPerid(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListPeridGroup(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListPeridGroup(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpumArea(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumArea(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpumAreaGugun(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumAreaGugun(parm);
    }

    public List<IndexDa024Dto> SelectDa026ListPerid(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListPerid(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListCltcd(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListCltcd(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListCltcdGroup(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListCltcdGroup(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListCltcd(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListCltcd(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpum(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpum(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpumGroup(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumGroup(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListJpum(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListJpum(parm);
    }
    public IndexDa024Dto SelectDa026Detail(IndexDa024Dto parm){
        return  appMapper.SelectDa026Detail(parm);
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
    public Boolean DeleteDA023Ord(IndexDa024Dto parm){ return  appMapper.DeleteDA023Ord(parm);}
    public Boolean DeleteDA024Ord(IndexDa024Dto parm){ return  appMapper.DeleteDA024Ord(parm);}



    public Boolean DeleteDA025(IndexDa024Dto parm){ return  appMapper.DeleteDA025(parm);}
    public Boolean DeleteDA026(IndexDa024Dto parm){ return  appMapper.DeleteDA026(parm);}
    public Boolean UpdateDA023Unsong(IndexDa023Dto parm){ return  appMapper.UpdateDA023Unsong(parm);}
    public Boolean UpdateDA024(IndexDa024Dto parm){ return  appMapper.UpdateDA024(parm);}
    public Boolean UpdateDA024Dev(IndexDa024Dto parm){ return  appMapper.UpdateDA024Dev(parm);}
    public Boolean UpdateDA026(IndexDa024Dto parm){ return  appMapper.UpdateDA026(parm);}
    public Boolean UpdateDA024rkaa(IndexDa024Dto parm){ return  appMapper.UpdateDA024rkaa(parm);}
    public Boolean UpdateDA024rkbb(IndexDa024Dto parm){ return  appMapper.UpdateDA024rkbb(parm);}
    public Boolean UpdateDA026rkaa(IndexDa024Dto parm){ return  appMapper.UpdateDA026rkaa(parm);}
    public Boolean UpdateDA026rkbb(IndexDa024Dto parm){ return  appMapper.UpdateDA026rkbb(parm);}



}
