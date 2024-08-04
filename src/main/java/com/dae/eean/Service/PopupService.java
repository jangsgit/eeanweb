package com.dae.eean.Service;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App05ElvlrtDto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.Mapper.PopupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("PopupService")
public class PopupService {
    @Autowired
    PopupMapper appMapper;


    /** 사원 리스트 */
    public List<PopupDto> GetInsaList(PopupDto parm){
                return  appMapper.GetInsaList(parm);
    }
    /** 거래처구분 코드 리스트 */
    public List<PopupDto> getCifCodeList(PopupDto parm){
        return  appMapper.getCifCodeList(parm);
    }

    public Boolean InsertCifCode(PopupDto parm){ return  appMapper.InsertCifCode(parm);}
    public Boolean UpdateCifCode(PopupDto parm){  return  appMapper.UpdateCifCode(parm);  }
    public Boolean DeleteCifCode(PopupDto parm){  return  appMapper.DeleteCifCode(parm);  }
    public String getMNoticeMaxSeq(String parm){  return  appMapper.getMNoticeMaxSeq(parm);  }

    public boolean InsertMNotice(App05ElvlrtDto parm){
        int queryResult = 1;
        queryResult = appMapper.InsertMNotice(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);

    }
    public boolean UpdateMNotice(App05ElvlrtDto parm){
        int queryResult = 1;
        queryResult = appMapper.UpdateMNotice(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);

    }
    public boolean DeleteMNotice(App05ElvlrtDto parm){
        int queryResult = 1;
        queryResult = appMapper.DeleteMNotice(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);

    }

    /** 글 목록 */
    public Object GetMNoticeList(App05ElvlrtDto parm){
       return  appMapper.GetMNoticeList(parm);
    }
}
