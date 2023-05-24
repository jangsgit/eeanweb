package com.dae.eean.controller.appadmin;


import com.dae.eean.DTO.*;
import com.dae.eean.Service.master.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/appadmod", method = RequestMethod.POST)
public class AppIndexCrudController {
    private final AuthService service;
    List<UserFormDto> appUserFormListDto ;
    List<TBXLoginDTO> appUserLoginListDto ;
    UserFormDto appUserFormDto  = new UserFormDto();
    TBXuserMenuDTO xusermenuDto = new TBXuserMenuDTO();
    protected Log log =  LogFactory.getLog(this.getClass());

    private static final Logger logger     = LoggerFactory.getLogger(AppIndexCrudController.class);

    @RequestMapping(value="/id01mod")   //사용자 상태 수정
    public String UserUpdate(@RequestParam("actseqz") String seq
            ,@RequestParam("actuseridz") String userid
            ,@RequestParam("actuseynz") String useyn
            ,@RequestParam("actusernmz") String usernm
            ,@RequestParam("actuserpwz") String userpw
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setSeq(Integer.parseInt(seq));
            appUserFormDto.setUserid(userid);
            appUserFormDto.setUseyn(useyn);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setPasswd1(userpw);
            appUserFormDto.setFlag("AA");
//            log.info("seq CHECK =====>" + seq.toString());
//            log.info("userid CHECK =====>" + userid.toString());
//            log.info("useyn CHECK =====>" + useyn.toString());
//            log.info("usernm CHECK =====>" + usernm.toString());
//            log.info("userpw CHECK =====>" + userpw.toString());
            boolean result = service.UpdateUserInfo(appUserFormDto);
            if (!result) {
                return "error";
            }else{
                return "success";
            }
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @RequestMapping(value="/id01menumod")   //사용자 메뉴 상태 수정
    public String UserMenuUpdate(@RequestParam("actuseridz") String userid
            ,@RequestParam("actmenuidz") String menuid
            ,@RequestParam("actviewz") String view
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            xusermenuDto.setUserid(userid);
            xusermenuDto.setMenuid(menuid);
            xusermenuDto.setVisible(view);

            boolean result = service.UpdateUserMenuInfo(xusermenuDto);
            if (!result) {
                return "error";
            }else{
                return "success";
            }
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @RequestMapping(value="/id01list")   //본사 리스트 조회
    public Object UserList(@RequestParam("actusernamez") String usernm
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setFlag("AA");
            appUserFormListDto = service.GetUserListDto(appUserFormDto);
            model.addAttribute("appUserFormListDto", appUserFormListDto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return appUserFormListDto;
    }



    @RequestMapping(value="/id02mod")   //거래처 상태 수정
    public String UserUpdate02(@RequestParam("actseqz") String seq
            ,@RequestParam("actuseridz") String userid
            ,@RequestParam("actuseynz") String useyn
            ,@RequestParam("actusernmz") String usernm
            ,@RequestParam("actuserpwz") String userpw
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setSeq(Integer.parseInt(seq));
            appUserFormDto.setUserid(userid);
            appUserFormDto.setUseyn(useyn);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setPasswd1(userpw);
            appUserFormDto.setFlag("BB");

            boolean result = service.UpdateUserInfo(appUserFormDto);
            if (!result) {
                return "error";
            }else{
                return "success";
            }
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @RequestMapping(value="/id04mod")   //영업사원 상태 수정
    public String UserUpdate04(@RequestParam("actseqz") String seq
            ,@RequestParam("actuseridz") String userid
            ,@RequestParam("actuseynz") String useyn
            ,@RequestParam("actusernmz") String usernm
            ,@RequestParam("actuserpwz") String userpw
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setSeq(Integer.parseInt(seq));
            appUserFormDto.setUserid(userid);
            appUserFormDto.setUseyn(useyn);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setPasswd1(userpw);
            appUserFormDto.setFlag("CC");

            boolean result = service.UpdateUserInfo(appUserFormDto);
            if (!result) {
                return "error";
            }else{
                return "success";
            }
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/id02list")   //거래처 리스트 조회
    public Object UserList02(@RequestParam("actusernamez") String usernm
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setFlag("BB");
            appUserFormListDto = service.GetUserListDto(appUserFormDto);
            model.addAttribute("appUserFormListDto", appUserFormListDto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return appUserFormListDto;
    }

    @RequestMapping(value="/id03list")   //사용자 접속 리스트 조회
    public Object UserList03(@RequestParam("actusernamez") String usernm
            , Model model, HttpServletRequest request){
        try {
            appUserFormDto.setUserid(usernm);
            appUserLoginListDto = service.GetLogListDto(appUserFormDto);
            model.addAttribute("appUserFormListDto", appUserLoginListDto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return appUserLoginListDto;
    }



    @RequestMapping(value="/id04list")   //거래처 리스트 조회
    public Object UserList04(@RequestParam("actusernamez") String usernm
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setFlag("CC");
            appUserFormListDto = service.GetUserListDto(appUserFormDto);
            model.addAttribute("appUserFormListDto", appUserFormListDto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return appUserFormListDto;
    }


}
