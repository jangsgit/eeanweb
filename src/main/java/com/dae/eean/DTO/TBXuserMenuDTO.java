package com.dae.eean.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TBXuserMenuDTO {
    private String custcd;          //회사 코드 .
    private String spjangcd;        //사업장코드 .
    private Integer idx;
    private String menudv;
    private String menuid;
    private String userid;
    private String msysname;
    private String mname;
    private String mtext;
    private Integer mlevel;
    private Integer msort;
    private String windowname;
    private String visible;
    private String mflag;
    private String uflag;
    private String sflag;
    private String indate;
    private String inuserid;
    private String username;


}
