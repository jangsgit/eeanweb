package com.dae.eean.Mapper.App01;

import com.dae.eean.DTO.App01.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index14Mapper {
    public String SelectMaxMisnum(IndexDa023Dto parm) ;
    public String SelectCheckMisnum(IndexDa023Dto parm) ;
    public String SelectMaxSeq(IndexDa023Dto parm) ;
    public String SelectMaxMisnumWish(IndexDa023Dto parm) ;
    public String SelectCheckMisnumWish(IndexDa023Dto parm) ;
    public String SelectMaxSeqWish(IndexDa023Dto parm) ;

    public Boolean InsertDa023(IndexDa023Dto parm) ;
    public Boolean InsertDa024(IndexDa024Dto parm) ;
    public Boolean InsertDa025(IndexDa023Dto parm) ;
    public Boolean InsertDa026(IndexDa024Dto parm) ;
    public Boolean InsertIa011(IndexIa011Dto parm) ;
    public Boolean InsertIa012(IndexIa012Dto parm) ;
    public Boolean InsertDa023Order(IndexDa024Dto parm) ;
    public Boolean InsertDa024Order(IndexDa024Dto parm) ;



    public Boolean DeleteDA023(IndexDa024Dto parm) ;

    public Boolean DeleteDA024(IndexDa024Dto parm) ;
    public Boolean DeleteDA025(IndexDa024Dto parm) ;

    public Boolean DeleteDA026(IndexDa024Dto parm) ;


    public Boolean UpdateDA024(IndexDa024Dto parm) ;
    public Boolean UpdateDA026(IndexDa024Dto parm) ;

    public List<IndexDa024Dto> SelectDa024List(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa026List(IndexDa024Dto parm) ;


}
