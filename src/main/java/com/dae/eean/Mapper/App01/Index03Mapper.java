package com.dae.eean.Mapper.App01;

import com.dae.eean.DTO.App01.Index03Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index03Mapper {

    public List<Index03Dto> GetJpumList(Index03Dto parm) ;
    public List<Index03Dto> GetJpumListTot(Index03Dto parm) ;
    public List<Index03Dto> GetGanListBonsa01(Index03Dto parm) ;
    public List<Index03Dto> GetGanListBonsa02(Index03Dto parm) ;
    public List<Index03Dto> GetJcustomCode(Index03Dto parm) ;
    public List<Index03Dto> GetJcustomCode_BB(Index03Dto parm) ;
    public List<Index03Dto> GetJcustomCode_CC(Index03Dto parm) ;
    public List<Index03Dto> GetJcustomCodeTot(Index03Dto parm) ;
    public List<Index03Dto> GetJpumCustList(Index03Dto parm) ;
    public List<Index03Dto> GetJpumModelList(Index03Dto parm) ;
    public List<Index03Dto> GetJpumCustJaegoList(Index03Dto parm) ;
    public List<Index03Dto> GetJBonsaCodeList(Index03Dto parm) ;
    public List<Index03Dto> GetJBonsaCodeList_AA(Index03Dto parm) ;
    public List<Index03Dto> GetJBonsaCodeList_BB(Index03Dto parm) ;
    public List<Index03Dto> GetJBonsaCodeList_CC(Index03Dto parm) ;
    public List<Index03Dto> GetJpumSubul01(Index03Dto parm) ;
    public List<Index03Dto> GetJpumSubul02(Index03Dto parm) ;

    public Index03Dto GetJpumOrderJkey(Index03Dto parm) ;
    public Index03Dto GetJpumOrderJkey02(Index03Dto parm) ;


    public Boolean InsertJpum(Index03Dto  parm) ;
    public Boolean UpdateJpum(Index03Dto  parm) ;
    public Boolean DeleteJpum(Index03Dto  parm) ;
    public String GetJpumCheck(Index03Dto  parm) ;


}
