package com.dae.eean.controller.appadmin;

import com.dae.eean.DTO.App01.Index01Dto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.TBXLoginDTO;
import com.dae.eean.DTO.TBXa012VO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index01Service;
import com.dae.eean.Service.master.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/admin", method = RequestMethod.POST)
@Controller
@RequiredArgsConstructor
public class AppIndexController {
    private final AuthService service;
    private final Index01Service service01;
    List<UserFormDto> appUserFormListDto ;
    List<TBXLoginDTO> appUserLoginListDto ;
    UserFormDto appUserFormDto  = new UserFormDto();

    protected Log log =  LogFactory.getLog(this.getClass());

    // 사용자관리 index
    @GetMapping(value="/index01")
    public String AppIndex01Form(Model model, HttpServletRequest request) throws  Exception{

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("AA");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUsername("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("사용자현황");
        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        model.addAttribute("userformDto",userformDto);
        try {
            _index01Dto.setCom_cls("002");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex01";
    }


    // 사용자관리 index
    @GetMapping(value="/index02")
    public String AppIndex02Form(Model model, HttpServletRequest request) throws  Exception{

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("BB");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("거래처현황");
        model.addAttribute("userformDto",userformDto);
        try {
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex02";
    }


    // 사용자로그 index
    @GetMapping(value="/index03")
    public String AppIndex03Form(Model model, HttpServletRequest request) throws  Exception{

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setUserid("%");
        appUserFormDto.setCustnm(userformDto.getCustnm());
        appUserLoginListDto = service.GetLogListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("사용자로그");
        model.addAttribute("userformDto",userformDto);
        try {
            model.addAttribute("appUserListDto",appUserLoginListDto);
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex03";
    }



    // 사용자관리 index
    @GetMapping(value="/index04")
    public String AppIndex04Form(Model model, HttpServletRequest request) throws  Exception{

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        appUserFormDto.setFlag("CC");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("영업사원별현황");
        model.addAttribute("userformDto",userformDto);
        try {
            _index01Dto.setCom_cls("002");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex04";
    }


    // 사용자관리 index
    @GetMapping(value="/index05")
    public String AppIndex05Form(Model model, HttpServletRequest request) throws  Exception{

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("DD");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("접수사원별현황");
        model.addAttribute("userformDto",userformDto);
        try {
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex05";
    }




}
