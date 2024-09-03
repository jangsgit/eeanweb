package com.dae.eean.controller.appadmin;

import com.dae.eean.DTO.App01.Index01Dto;
import com.dae.eean.DTO.App05ElvlrtDto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.TBXLoginDTO;
import com.dae.eean.DTO.TBXa012VO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index01Service;
import com.dae.eean.Service.PopupService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping(value = "/admin", method = RequestMethod.POST)
@Controller
@RequiredArgsConstructor
public class AppIndexController {
    private final AuthService service;
    private final Index01Service service01;
    private final PopupService popservice;
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


    // 공지사항 index
    @GetMapping(value="/index06")
    public String AppIndex06Form(Model model, HttpServletRequest request) throws  Exception{


        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("DD");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("공지사항");
        model.addAttribute("userformDto",userformDto);
        try {
            List<App05ElvlrtDto> App05ListDto;
            App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
            _index01Dto.setCom_cls("004");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);


            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            _App05Dto.setYyyymm(indate.substring(0,6));
            _App05Dto.setNinputdate(indate);
            _App05Dto.setNgourpcd("%");
            model.addAttribute("App05Dto",popservice.GetMNoticeList(_App05Dto));


        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex06";
    }


    // 공지사항 영업사원 index
    @GetMapping(value="/index07")
    public String AppIndex07Form(Model model, HttpServletRequest request) throws  Exception{


        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("DD");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("공지사항");
        model.addAttribute("userformDto",userformDto);
        try {
            List<App05ElvlrtDto> App05ListDto;
            App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
            _index01Dto.setCom_cls("004");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);


            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            _App05Dto.setYyyymm(indate.substring(0,6));
            _App05Dto.setNinputdate(indate);
            _App05Dto.setNgourpcd("01");
            model.addAttribute("App05Dto",popservice.GetMNoticeList(_App05Dto));


        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex07";
    }

    // 공지사항 영업점 index
    @GetMapping(value="/index08")
    public String AppIndex08Form(Model model, HttpServletRequest request) throws  Exception{


        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("DD");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("공지사항");
        model.addAttribute("userformDto",userformDto);
        try {
            List<App05ElvlrtDto> App05ListDto;
            App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
            _index01Dto.setCom_cls("004");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);


            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            _App05Dto.setYyyymm(indate.substring(0,6));
            _App05Dto.setNinputdate(indate);
            _App05Dto.setNgourpcd("02");
            model.addAttribute("App05Dto",popservice.GetMNoticeList(_App05Dto));
            log.debug("indate =====>" +indate );

        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex08";
    }


    // 공지사항 전체보기 index
    @GetMapping(value="/index09")
    public String AppIndex09Form(Model model, HttpServletRequest request) throws  Exception{


        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("DD");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("공지사항");
        model.addAttribute("userformDto",userformDto);
        try {
            List<App05ElvlrtDto> App05ListDto;
            App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
            _index01Dto.setCom_cls("004");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);


            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            _App05Dto.setYyyymm(indate.substring(0,6));
            _App05Dto.setNinputdate(indate);
            _App05Dto.setNgourpcd("03");
            model.addAttribute("App05Dto",popservice.GetMNoticeList(_App05Dto));
            log.debug("indate =====>" +indate );

        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex09";
    }


    // 공지사항 as index
    @GetMapping(value="/index10")
    public String AppIndex10Form(Model model, HttpServletRequest request) throws  Exception{


        Index01Dto _index01Dto = new Index01Dto();
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        appUserFormDto.setFlag("DD");
        appUserFormDto.setUsername("%");
        appUserFormDto.setCustcd(userformDto.getCustcd());
        appUserFormDto.setUserid("%");
        appUserFormListDto = service.GetUserListDto(appUserFormDto);
        userformDto.setPagetree01("관리자모드");
        userformDto.setPagenm("공지사항");
        model.addAttribute("userformDto",userformDto);
        try {
            List<App05ElvlrtDto> App05ListDto;
            App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
            _index01Dto.setCom_cls("004");
            _index01ListDto    = service01.GetComcodeDetailList(_index01Dto);
            model.addAttribute("index01ListDto",_index01ListDto);
            model.addAttribute("appUserListDto",appUserFormListDto);
            model.addAttribute("userformDto",userformDto);


            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            _App05Dto.setYyyymm(indate.substring(0,6));
            _App05Dto.setNinputdate(indate);
            _App05Dto.setNgourpcd("03");
            model.addAttribute("App05Dto",popservice.GetMNoticeList(_App05Dto));
            log.debug("indate =====>" +indate );

        } catch (Exception ex) {
//                dispatchException = ex;
            log.debug("Exception =====>" + ex.toString() );
        }
        return "appadmin/appindex10";
    }

}
