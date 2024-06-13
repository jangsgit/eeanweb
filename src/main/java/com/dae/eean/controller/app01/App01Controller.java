package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.Index01Dto;
import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index03Dto;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index01Service;
import com.dae.eean.Service.App01.Index02Service;
import com.dae.eean.Service.App01.Index03Service;
import com.dae.eean.Service.Appcom01Service;
import com.dae.eean.Service.PopupService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/app01", method = RequestMethod.POST)
public class App01Controller {
    private final Index01Service service01;
    private final Index02Service service02;
    private final Index03Service service03;
    private final PopupService svcpopup;

    CommonDto CommDto = new CommonDto();
    List<Index02Dto> index02List = new ArrayList<>();
    Index02Dto index02Dto = new Index02Dto();
    List<Index03Dto> index03List = new ArrayList<>();
    Index03Dto index03Dto = new Index03Dto();
    PopupDto popupDto = new PopupDto();
    List<PopupDto> popupListDto = new ArrayList<>();

    Index01Dto index01Dto = new Index01Dto();
    List<Index01Dto> index01ListDto = new ArrayList<>();


    protected Log log =  LogFactory.getLog(this.getClass());
    //기초정보
    @GetMapping(value="/index01")
    public String App01_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index01Dto.setCom_cls("%");
            index01ListDto = service01.getComCodeList(index01Dto);

            model.addAttribute("comcodeList",index01ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
//            log.debug("Exception =====>" + ex.toString() );
        }
        return "App01/index01";
    }

    @GetMapping(value="/index01m")
    public String App01_indexm(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01m");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index01Dto.setCom_cls("%");
            index01ListDto = service01.getComCodeList(index01Dto);

            model.addAttribute("comcodeList",index01ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
//            log.debug("Exception =====>" + ex.toString() );
        }
        return "App01/index01m";
    }

    //거래처등록
    @GetMapping(value="/index02")
    public String App02_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index01Dto.setCom_cls("002");
            _index01ListDto    = service01.GetComcodeDetailList(index01Dto);
            index02Dto.setAcorp("%");
            index02List = service02.GetCifList(index02Dto);
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("index02List",index02List);
            model.addAttribute("cifcodeList",popupListDto);
            model.addAttribute("index01ListDto",_index01ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App02_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index02";
    }

    //거래처등록02
    @GetMapping(value="/index020")
    public String App020_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index020");
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//        userformDto.setPagetree01("거래처등록");
//        userformDto.setPagenm("본사기준정보");
//        model.addAttribute("CommDto", CommDto);

            index01Dto.setCom_cls("002");
            _index01ListDto    = service01.GetComcodeDetailList(index01Dto);

            index02Dto.setAcorp("%");
            index02List = service02.GetCifList(index02Dto);
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("index02List",index02List);
            model.addAttribute("cifcodeList",popupListDto);
            model.addAttribute("index01ListDto",_index01ListDto);
        } catch (Exception ex) {
            log.info("App020_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index020";
    }


    //거래처등록03
    @GetMapping(value="/index021")
    public String App021_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index020");
        List<Index01Dto> _index01ListDto = new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//        userformDto.setPagetree01("거래처등록");
//        userformDto.setPagenm("본사기준정보");
//        model.addAttribute("CommDto", CommDto);

            index01Dto.setCom_cls("002");
            _index01ListDto    = service01.GetComcodeDetailList(index01Dto);

            index02Dto.setAcorp("%");
            index02List = service02.GetCifList(index02Dto);
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("index02List",index02List);
            model.addAttribute("cifcodeList",popupListDto);
            model.addAttribute("index01ListDto",_index01ListDto);
        } catch (Exception ex) {
            log.info("App021_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index021";
    }

    //제품등록
    @GetMapping(value="/index03")
    public String App03_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index03Dto.setJpum("%");
            index03List = service03.GetJpumList(index03Dto);

            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App03_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index03";
    }


    //재고등록
    @GetMapping(value="/index04")
    public String App04_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index04");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index03List = service03.GetJcustomCodeTot(index03Dto);
            model.addAttribute("index04List",index03List);
        } catch (Exception ex) {
            log.info("App04_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index04";
    }


    //기초코드등록 emdfhr
    @GetMapping(value="/index13")
    public String App13_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("기초코드");
        CommDto.setMenuUrl("기준정보>기초코드");
        CommDto.setMenuCode("index13");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
            log.info("App13_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index13";
    }


    //주문등록
    @GetMapping(value="/index15")
    public Object App15_index( @RequestParam("userid") String userid,
                               @RequestParam("flag") String flag,
                               @RequestParam("role") String role,
                               @RequestParam("perid") String perid,
                               Model model, HttpServletRequest request) throws Exception{

        Index03Dto _index03Dto = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        CommDto.setMenuTitle("거래처주문등록");
        CommDto.setMenuUrl("기준정보>거래처주문등록");
        CommDto.setMenuCode("index15");
        String ls_flag, ls_userid, ls_role, ls_perid;
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            ls_flag = flag;
            ls_userid = userid;
            ls_role = role;
            ls_perid = perid;
            if(ls_flag.equals("AA")){
                _index03Dto.setJpb_gubn(ls_role);
//                if(ls_userid.substring(0,2).equals("pv")){
//                    _index03Dto.setJpb_gubn("P");
//                }else if(ls_userid.substring(0,2).equals("bl")){
//                    _index03Dto.setJpb_gubn("B");
//                }else{
//                    _index03Dto.setJpb_gubn("%");
//                }
//                log.info("flag =====>" + flag);
//                log.info("userid =====>" + userid);
//                log.info("getJpb_gubn =====>" + _index03Dto.getJpb_gubn());
                _index03List = service03.GetJcustomCode(_index03Dto);
            }else if(ls_flag.equals("BB")){
                if(ls_perid.substring(0,2).equals("02")){
                    _index03Dto.setJpb_gubn("P");
                }else{
                    _index03Dto.setJpb_gubn("B");
                }
                _index03List = service03.GetJcustomCode(_index03Dto);
            }else{
                if (ls_flag.equals("CC")){
                    _index03Dto.setJpb_gubn(ls_role);
                }else{
                    _index03Dto.setJpb_gubn("%");
                }
                _index03List = service03.GetJcustomCode_CC(_index03Dto);
            }
            model.addAttribute("index15List",_index03List);
        } catch (Exception ex) {
            log.info("App15_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index15";
    }



    //주문등록
    @GetMapping(value="/index150")
    public Object App150_index( Model model, HttpServletRequest request) throws Exception{
        List<Index03Dto> _index03List = new ArrayList<>();
        Index03Dto _index03Dto = new Index03Dto();
        String ls_flag, ls_userid, ls_role, ls_perid;

        CommDto.setMenuTitle("거래처주문등록");
        CommDto.setMenuUrl("기준정보>거래처주문등록");
        CommDto.setMenuCode("index150");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

//            if(ls_flag.equals("AA")){
//                if(ls_userid.substring(0,2).equals("pv")){
//                    _index03Dto.setJpb_gubn("P");
//                }else if(ls_userid.substring(0,2).equals("bl")){
//                    _index03Dto.setJpb_gubn("B");
//                }else{
//                    _index03Dto.setJpb_gubn("%");
//                }
//                _index03List = service03.GetJcustomCode_BB(index03Dto);
//            }else if(ls_flag.equals("BB")){
//                if(ls_perid.substring(0,2).equals("02")){
//                    _index03Dto.setJpb_gubn("P");
//                }else{
//                    _index03Dto.setJpb_gubn("B");
//                }
//                _index03List = service03.GetJcustomCode_BB(index03Dto);
//            }else{
//                _index03Dto.setJpb_gubn(ls_role);
//                _index03List = service03.GetJcustomCode_CC(_index03Dto);
//            }
            if(userformDto.getPerid().substring(0,2).equals("02") ){
                _index03Dto.setJpb_gubn("P");
            }else if(userformDto.getPerid().substring(0,2).equals("03") ){
                _index03Dto.setJpb_gubn("B");
            }else{
                _index03Dto.setJpb_gubn("P");
            }
            _index03List = service03.GetJcustomCode_BB(_index03Dto);
            model.addAttribute("index15List",_index03List);
        } catch (Exception ex) {
            log.info("App150_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index150";
    }


    //주문등록
    @GetMapping(value="/index150b")
    public Object App150b_index( @RequestParam("userid") String userid,
                                 @RequestParam("flag") String flag,
                                 @RequestParam("role") String role,
                                 @RequestParam("perid") String perid,
                                 Model model, HttpServletRequest request) throws Exception{
        List<Index03Dto> _index03List = new ArrayList<>();
        Index03Dto _index03Dto = new Index03Dto();
        CommDto.setMenuTitle("영업사원일반등록");
        CommDto.setMenuUrl("기준정보>영업사원일반주문등록");
        CommDto.setMenuCode("index15");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            String ls_flag = flag;
            String ls_role = role;
            if (ls_flag.equals("CC")){
                _index03Dto.setJpb_gubn(ls_role);
            }else{
                _index03Dto.setJpb_gubn("%");
            }
            _index03List = service03.GetJcustomCode_CC(_index03Dto);

            model.addAttribute("index15List",_index03List);
        } catch (Exception ex) {
            log.info("App150b_index Exception  ===================>"  + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index150b";
    }

    //주문등록
    @GetMapping(value="/index150c")
    public Object App150C_index(  Model model, HttpServletRequest request) throws Exception{
        Index03Dto _index03Dto = new Index03Dto();
        CommDto.setMenuTitle("영업사원일반등록");
        CommDto.setMenuUrl("기준정보>영업사원일반주문등록");
        CommDto.setMenuCode("index150c");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            if(perid.substring(0,2).equals("02")){
//                _index03Dto.setJpb_gubn("P");
//            }else{
//                _index03Dto.setJpb_gubn("B");
//            }
            _index03Dto.setJpb_gubn("P");
            index03List = service03.GetJcustomCode_BB(_index03Dto);

            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
            log.info("App150c_index Exception  ===================>"  + ex.toString());
            return "redirect:http://eean.co.kr:8900/m";
        }

        return "App01/index150c";
    }

    //주문현황
    @GetMapping(value="/index151")
    public Object App151_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문현황");
        CommDto.setMenuUrl("기준정보>주문현황");
        CommDto.setMenuCode("index151");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJcustomCode(index03Dto);
//            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
            log.info("App151_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index151";
    }


    //예약현황
    @GetMapping(value="/index152")
    public Object App152_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index152");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJcustomCode(index03Dto);
//            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
            log.info("App152_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index152";
    }


    //주문등록 본사
    @GetMapping(value="/index14")
    public String App14_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index03Dto.setJpb_gubn("%");
            index03List = service03.GetJBonsaCodeList_AA(index03Dto);
            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App14_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index14";
    }

    //주문등록
    @GetMapping(value="/index140")
    public String App140_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index03List = service03.GetJBonsaCodeList_CC(index03Dto);

            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App140_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index140";
    }


    //주문등록
    @GetMapping(value="/index141")
    public String App141_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문현황");
        CommDto.setMenuUrl("기준정보>주문현황");
        CommDto.setMenuCode("index141");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App141_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index141";
    }


    //주문등록
    @GetMapping(value="/index141m")
    public String App141m_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문현황");
        CommDto.setMenuUrl("기준정보>주문현황");
        CommDto.setMenuCode("index141m");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App141m_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr:8900/m";
        }

        return "App01/index141m";
    }


    //예약현황
    @GetMapping(value="/index142")
    public String App142_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index142");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App142_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index142";
    }


    //예약현황
    @GetMapping(value="/index142m")
    public String App142m_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index142");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App142_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr:8900/m";
        }

        return "App01/index142m";
    }

    //주문등록
    @GetMapping(value="/index143")
    public String App143_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index143");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App143_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr/";
        }

        return "App01/index143";
    }

    //주문등록
    @GetMapping(value="/index143m")
    public String App143m_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index143m");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App143m_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr:8900/m";
        }

        return "App01/index143m";
    }


    //주문등록
    @GetMapping(value="/index140m")
    public String App140m_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index140m");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index03List = service03.GetJBonsaCodeList_CC(index03Dto);

            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("index140m Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr:8900/m";
        }

        return "App01/index140m";
    }


    //주문등록 거래처
    @GetMapping(value="/index150m")
    public String App150m_index(  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처주문등록");
        CommDto.setMenuUrl("기준정보>거래처주문등록");
        CommDto.setMenuCode("index150m");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            String ls_flag = userformDto.getFlag();
            String ls_role = userformDto.getRole();
            if (ls_flag.equals("CC")){
                index03Dto.setJpb_gubn(ls_role);
            }else{
                index03Dto.setJpb_gubn("%");
            }
            index03List = service03.GetJcustomCode_CC(index03Dto);
            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
            log.info("App150m_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr:8900/m";
        }

        return "App01/index150m";
    }

    //주문등록
    @GetMapping(value="/index16")
    public String App16_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문확정");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index03Dto.setJpb_gubn("%");
            index03List = service03.GetJBonsaCodeList(index03Dto);
            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App16_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index16";
    }

    //예약현황
    @GetMapping(value="/index17")
    public String App17_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index14");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            index03Dto.setJpb_gubn("%");
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
            log.info("App17_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index17";
    }

    //사원별매출현황
    @GetMapping(value="/index06")
    public String App06_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index06");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
            log.info("App06_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index06";
    }


    //거래별매출현황
    @GetMapping(value="/index07")
    public String App07_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index07");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
            log.info("App07_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index07";
    }

    //제품별매출현황
    @GetMapping(value="/index08")
    public String App08_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품별매출현황");
        CommDto.setMenuUrl("기준정보>제품별매출현황");
        CommDto.setMenuCode("index08");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
            log.info("App08_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index08";
    }

    //지역별매출현황
    @GetMapping(value="/index09")
    public String App09_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("지역별매출현황");
        CommDto.setMenuUrl("기준정보>지역별매출현황");
        CommDto.setMenuCode("index09");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
        } catch (Exception ex) {
            log.info("App09_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index09";
    }

    //AS접수 배송현황
    @GetMapping(value="/index11")
    public String App11_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS접수배송현황");
        CommDto.setMenuCode("index11");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
            log.info("App11_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index11";
    }

    //AS접수 배송현황
    @GetMapping(value="/index110")
    public String App110_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS접수배송현황");
        CommDto.setMenuCode("index110");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
            log.info("App110_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index110";
    }


    //AS접수 등록
    @GetMapping(value="/index20")
    public String App20_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS접수배송등록");
        CommDto.setMenuCode("index20");
        try {

            List<Index01Dto> _index01UserDto = new ArrayList<>();
            List<Index01Dto> _index02UserDto = new ArrayList<>();
            List<Index03Dto> _index03YumuDto = new ArrayList<>();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            index01Dto.setCom_cls("002");
            index01ListDto    = service01.GetComcodeDetailList(index01Dto);
            _index01UserDto   = service01.getWperidlist(index01Dto);
            _index03YumuDto   = service01.getAsyumulist(index03Dto);

            index01Dto.setCom_cls("003");
            _index02UserDto    = service01.GetComcodeDetailList(index01Dto);


            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
            model.addAttribute("yumucodeList",_index03YumuDto);
            model.addAttribute("index01ListDto",index01ListDto);
            model.addAttribute("index01UserDto",_index01UserDto);
            model.addAttribute("index02ListDto",_index02UserDto);

        } catch (Exception ex) {
            log.info("App20_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index20";
    }


    //AS접수 현황
    @GetMapping(value="/index21")
    public String App21_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS접수현황");
        CommDto.setMenuCode("index21");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
            log.info("App21_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index21";
    }
    //AS주문 현황
    @GetMapping(value="/index22")
    public String App22_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS주문현황");
        CommDto.setMenuCode("index22");
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
//            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
            log.info("App22_index Exception  ====");
            log.info("Exception =====>" + ex.toString());
            return "redirect:http://eean.co.kr";
        }

        return "App01/index22";
    }

}
