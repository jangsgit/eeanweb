package com.dae.eean.controller;

import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.master.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    PopupDto popParmDto = new PopupDto();


    List<CommonDto> com750Dto;

    protected Log log =  LogFactory.getLog(this.getClass());


    UserFormDto userformDto = new UserFormDto();

    // 보수업체 유지보수 회원가입
    @GetMapping(value="/cltcdnew")
    public String memberForm(Model model){
        userformDto.setFlag("AA");
        model.addAttribute("userFormDto", userformDto);
        return "register";
    }

    // 고객 유지보수 회원가입
    @GetMapping(value="/actcdnew")
    public String memberActcdForm(Model model){
        userformDto.setFlag("CC");
        model.addAttribute("userFormDto", userformDto);
        return "registeractcd";
    }


    // 관리자 화면
    @GetMapping(value="/admin")
    public String AdminLoginForm(Model model){

        UserFormDto custReturnDto = authService.GetCustInfo(userformDto);
        model.addAttribute("custReturnDto", custReturnDto);
        return "loginFormAdmin";
    }


    // 보수업체 대시보드
    @GetMapping(value="/emmsdashboard")
    public String memberEmmsBoardForm( Model model
                                    , HttpServletRequest request){

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        String ls_flag = userformDto.getFlag();
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("Dashboard");
        model.addAttribute("userFormDto", userformDto);

        if (ls_flag.equals("AA")){

            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();

            return "mainframe";
        } else if (userformDto == null) {
            model.addAttribute("msg", "로그인실패");
            return "/";
        } else if (ls_flag.equals("ZZ")){
            //현재날짜기준 월초(1일) 구하기
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date  = new Date(System.currentTimeMillis());
            String time = formatter.format(date);
            String time2 = time.substring(0,6) + "01";
            log.info(time2);


            //현재날짜기준 당월말일 구하기
            String year = time.substring(0,4);
            String month = time.substring(4,6);
            String day = time.substring(6,8);


            int year1 = Integer.parseInt(year);
            int month1 = Integer.parseInt(month);
            int day1 = Integer.parseInt(day);

            Calendar cal = Calendar.getInstance();
            cal.set(year1, month1-1, day1);


            String lastday1 = String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            String lastday = year+month+lastday1;
            log.info(lastday);

            /*popParmDto.setFrdate(time2);
            popParmDto.setTodate(lastday);*/
            popParmDto.setFrdate(time2);
            popParmDto.setTodate(lastday);

            log.info(time2);
            log.info(lastday);

            return "mainframadmin";
        } else{

            //현재날짜기준 월초(1일) 구하기
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date  = new Date(System.currentTimeMillis());
            String time = formatter.format(date);
            String time2 = time.substring(0,6) + "01";
            log.info(time2);


            //현재날짜기준 당월말일 구하기
             String year = time.substring(0,4);
            String month = time.substring(4,6);
            String day = time.substring(6,8);


            int year1 = Integer.parseInt(year);
            int month1 = Integer.parseInt(month);
            int day1 = Integer.parseInt(day);

            Calendar cal = Calendar.getInstance();
            cal.set(year1, month1-1, day1);


            String lastday1 = String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            String lastday = year+month+lastday1;
            log.info(lastday);

            /*popParmDto.setFrdate(time2);
            popParmDto.setTodate(lastday);*/
            popParmDto.setFrdate(time2);
            popParmDto.setTodate(lastday);

            log.info(time2);
            log.info(lastday);

            return "mainframcustom";
        }


    }



}
