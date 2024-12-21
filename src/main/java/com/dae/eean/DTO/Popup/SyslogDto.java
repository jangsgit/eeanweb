package com.dae.eean.DTO.Popup;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SyslogDto {
    private int id;
    private String type;
    private String menunm;
    private String source;
    private String message;
    private String userid;
    private String usernm;
    private String frdate;
    private String todate;
    private LocalDateTime _created;
}
