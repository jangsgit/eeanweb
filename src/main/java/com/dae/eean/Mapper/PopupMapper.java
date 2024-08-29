package com.dae.eean.Mapper;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App05ElvlrtDto;
import com.dae.eean.DTO.Popup.PopupDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PopupMapper {

    public List<PopupDto> GetInsaList(PopupDto parm) ;
    public List<PopupDto> getCifCodeList(PopupDto parm) ;
    public Boolean InsertCifCode(PopupDto parm) ;
    public Boolean UpdateCifCode(PopupDto  parm) ;
    public Boolean DeleteCifCode(PopupDto  parm) ;
    public String getMNoticeMaxSeq(String  parm) ;
    public int InsertMNotice(App05ElvlrtDto perm);
    public int UpdateMNotice(App05ElvlrtDto perm);
    public int DeleteMNotice(App05ElvlrtDto perm);


    public List<App05ElvlrtDto>  GetMNoticeList(App05ElvlrtDto perm);
    public List<App05ElvlrtDto>  GetTodayMNoticeList(App05ElvlrtDto perm);



}
