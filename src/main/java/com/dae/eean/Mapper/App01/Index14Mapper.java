package com.dae.eean.Mapper.App01;

import com.dae.eean.DTO.App01.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface Index14Mapper {
    public String SelectMaxMisnum(IndexDa023Dto parm) ;
    public String SelectCheckMisnum(IndexDa023Dto parm) ;
    public String SelectCheckMisnumOrd(IndexDa023Dto parm) ;

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
    public Boolean DeleteDA023Ord(IndexDa024Dto parm) ;

    public Boolean DeleteDA024Ord(IndexDa024Dto parm) ;

    public Boolean DeleteDA026(IndexDa024Dto parm) ;


    public Boolean UpdateDA024(IndexDa024Dto parm) ;
    public Boolean UpdateDA024Dev(IndexDa024Dto parm) ;
    public Boolean UpdateDA024rkaa(IndexDa024Dto parm) ;
    public Boolean UpdateDA024rkbb(IndexDa024Dto parm) ;
    public Boolean UpdateDA026(IndexDa024Dto parm) ;
    public Boolean UpdateDA026rkaa(IndexDa024Dto parm) ;
    public Boolean UpdateDA026rkbb(IndexDa024Dto parm) ;

    public List<IndexDa024Dto> SelectDa024List(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListLike(IndexDa024Dto parm) ;


//    public List<IndexDa024Dto> SelectDa024ListPrt(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListPrt(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListPrtGroup(HashMap<String,String> parm) ;
    public List<IndexDa024Dto> SelectDa024ListDevGroup(HashMap<String,String> parm) ;

    public List<IndexDa024Dto> SelectDa026List(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa026ListLike(IndexDa024Dto parm) ;

    public IndexDa024Dto SelectDa026Detail(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListPerid(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListPeridGroup(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListJpumArea(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListJpumAreaGugun(IndexDa024Dto parm) ;

    public List<IndexDa024Dto> SelectDa026ListPerid(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListCltcd(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListCltcdGroup(IndexDa024Dto parm) ;

    public List<IndexDa024Dto> SelectDa026ListCltcd(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListJpum(IndexDa024Dto parm) ;
    public List<IndexDa024Dto> SelectDa024ListJpumGroup(IndexDa024Dto parm) ;

    public List<IndexDa024Dto> SelectDa026ListJpum(IndexDa024Dto parm) ;



}
