package com.dae.eean.Service.master;

import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.TBXLoginDTO;
import com.dae.eean.DTO.TBXuserMenuDTO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Mapper.master.AuthDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service("AuthService")
public class AuthService {
    @Autowired
    AuthDBMapper authMapper;


    int queryResult = 1;

    public void authInsert(UserFormDto parm){authMapper.TBXUSERS_Insert(parm);}
    public UserFormDto GetUserInfo(UserFormDto parm){return authMapper.GetUserInfo(parm);}
    public UserFormDto GetCustInfo(UserFormDto parm){return authMapper.GetUserInfo(parm);}
    public UserFormDto GetFlagInfo(UserFormDto parm){return authMapper.GetFlagInfo(parm);}

    public UserFormDto GetAdminInfo(UserFormDto parm){return authMapper.GetAdminInfo(parm);}
    public List<UserFormDto> GetUserListDto(UserFormDto parm){return authMapper.GetUserListDto(parm);}
    public List<TBXLoginDTO> GetLogListDto(UserFormDto parm){return authMapper.GetLogListDto(parm);}
    public List<TBXuserMenuDTO> GetXusersMenuList(TBXuserMenuDTO parm){return authMapper.GetXusersMenuList(parm);}
    public List<TBXuserMenuDTO> GetXMenuList(TBXuserMenuDTO parm){return authMapper.GetXMenuList(parm);}


    public UserFormDto GetUserInfoDto(UserFormDto parm){return authMapper.GetUserInfoDto(parm);}
    public UserFormDto GetUserInfoDto2(UserFormDto parm){return authMapper.GetUserInfoDto2(parm);}

    public String GetClientInfo(UserFormDto parm){return authMapper.GetClientInfo(parm);}
    public String GetClientInfoName(UserFormDto parm){return authMapper.GetClientInfoName(parm);}

    public void UpdateDbInfo(UserFormDto parm){authMapper.UpdateDbInfo(parm);}

    public String TB_XUSER_DUPCHK(UserFormDto parm){
        return authMapper.TB_XUSER_DUPCHK(parm);
    }

    public String TB_XUSER_PHDUPCHK(UserFormDto parm){
        return authMapper.TB_XUSER_PHDUPCHK(parm);
    }
    public String TB_XCLIENT_SELECT(UserFormDto parm){
        return authMapper.TB_XCLIENT_SELECT(parm);
    }

    public void TB_XLOGIN_INSERT(UserFormDto parm) { authMapper.TB_XLOGIN_INSERT(parm);}
    public void TB_XLOGOUT_INSERT(UserFormDto parm) { authMapper.TB_XLOGOUT_INSERT(parm);}

    public void TB_XUSERS_LOGFAIL(UserFormDto parm) { authMapper.TB_XUSERS_LOGFAIL(parm);}

    public void TB_XUSERS_LOGSUCC(UserFormDto parm) { authMapper.TB_XUSERS_LOGSUCC(parm);}


    public String TB_XUSER_DBNM(UserFormDto parm) {return authMapper.TB_XUSER_DBNM(parm);}

    public void TB_XUSER_UPDATE(UserFormDto dto) {authMapper.TB_XUSER_UPDATE(dto);}



    //사용자사용여부
    @Transactional
    public boolean UpdateUserInfo(UserFormDto parm){
        int queryResult = 1;
        queryResult = authMapper.UpdateUserInfo(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }

    //사용자 메뉴사용여부
    @Transactional
    public boolean UpdateUserMenuInfo(TBXuserMenuDTO parm){
        int queryResult = 1;
        queryResult = authMapper.UpdateUserMenuInfo(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }

//    pushid
    @Transactional
    public boolean Updatepushid(UserFormDto parm){
        int queryResult = 1;
        queryResult = authMapper.Updatepushid(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }


    public String TB_GET_PUSHID(PopupDto parm){
        return authMapper.TB_GET_PUSHID(parm);
    }

}
