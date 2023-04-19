package com.dae.eean.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TBXLoginDTO {
    private String userid;
    private String ipaddr;
    private String onoffdt;
    private String custnm;
    private String usernm;
    private String ondate;
    private String ofdate;
    private String onoffdv;
}
