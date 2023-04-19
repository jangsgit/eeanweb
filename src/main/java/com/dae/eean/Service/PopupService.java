package com.dae.eean.Service;

import com.dae.eean.DTO.App01.Index02Dto;
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

}
