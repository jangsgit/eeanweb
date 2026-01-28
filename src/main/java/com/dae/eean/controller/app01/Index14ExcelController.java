package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.IndexDa024Dto;
import com.dae.eean.Service.App01.Index14ExcelService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/app14m", method = RequestMethod.GET)
public class Index14ExcelController {

    private final Index14ExcelService excelService;

    public Index14ExcelController(Index14ExcelService excelService) {
        this.excelService = excelService;
    }



    @GetMapping("/index14/listjkeyexl")
    public void downloadExcel(
            @RequestParam String frdate,
            @RequestParam String todate,
            @RequestParam String acode,
            @RequestParam String perid,
            @RequestParam(required = false, defaultValue = "") String jkey,
            @RequestParam(required = false, defaultValue = "1") String fixflag,
            @RequestParam(required = false, defaultValue = "P") String jpbgubn,
            HttpServletResponse response
    ) throws Exception {
        String year = frdate.substring(0,4) ;
        String month = frdate.substring(5,7) ;
        String day   = frdate.substring(8,10) ;
        frdate = year + month + day ;
        year = todate.substring(0,4) ;
        month = todate.substring(5,7) ;
        day   = todate.substring(8,10) ;
        todate = year + month + day ;

        IndexDa024Dto dto = new IndexDa024Dto();
        dto.setPcode(jkey);
        dto.setFrdate(frdate);
        dto.setTodate(todate);
        dto.setFixflag(fixflag);
        dto.setJpbgubn(jpbgubn);

        excelService.downloadDa024Excel(dto, response);
    }



}
