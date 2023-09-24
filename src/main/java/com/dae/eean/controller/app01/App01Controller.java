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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    //사업장정보조회
    @GetMapping(value="/index01")
    public String App01_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index01ListDto = service01.getComCodeList(index01Dto);

            model.addAttribute("comcodeList",index01ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }
        return "App01/index01";
    }


    //거래처등록
    @GetMapping(value="/index02")
    public String App02_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
//        userformDto.setPagetree01("거래처등록");
//        userformDto.setPagenm("본사기준정보");
//        model.addAttribute("CommDto", CommDto);

        try {
            index02Dto.setAcorp("%");
            index02List = service02.GetCifList(index02Dto);
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("index02List",index02List);
            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
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
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
//        userformDto.setPagetree01("거래처등록");
//        userformDto.setPagenm("본사기준정보");
//        model.addAttribute("CommDto", CommDto);

        try {
            index02Dto.setAcorp("%");
            index02List = service02.GetCifList(index02Dto);
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("index02List",index02List);
            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index020";
    }

    //제품등록
    @GetMapping(value="/index03")
    public String App03_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03Dto.setJpum("%");
            index03List = service03.GetJpumList(index03Dto);

            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index03";
    }


    //재고등록
    @GetMapping(value="/index04")
    public String App04_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index04");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03List = service03.GetJcustomCodeTot(index03Dto);
            model.addAttribute("index04List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index04";
    }


    //기초코드등록 emdfhr
    @GetMapping(value="/index13")
    public String App13_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("기초코드");
        CommDto.setMenuUrl("기준정보>기초코드");
        CommDto.setMenuCode("index13");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index13";
    }


    //주문등록
    @GetMapping(value="/index15")
    public Object App15_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처주문등록");
        CommDto.setMenuUrl("기준정보>거래처주문등록");
        CommDto.setMenuCode("index15");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        if(userformDto.getFlag().equals("AA")){
            if(userformDto.getUserid().substring(0,2).equals("pv")){
                index03Dto.setJpb_gubn("P");
            }else if(userformDto.getUserid().substring(0,2).equals("bl")){
                index03Dto.setJpb_gubn("B");
            }else{
                index03Dto.setJpb_gubn("%");
            }
        }else if(userformDto.getFlag().equals("BB")){
            if(userformDto.getPerid().substring(0,2).equals("02")){
                index03Dto.setJpb_gubn("P");
            }else{
                index03Dto.setJpb_gubn("B");
            }
        }else{
            index03Dto.setJpb_gubn("%");
        }

        try {
            index03List = service03.GetJcustomCode(index03Dto);

            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App15_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index15";
    }


    //주문등록
    @GetMapping(value="/index150")
    public Object App150_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처주문등록");
        CommDto.setMenuUrl("기준정보>거래처주문등록");
        CommDto.setMenuCode("index15");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        String ls_acorp1 = userformDto.getPerid();
        String ls_flag = userformDto.getFlag();
        if (ls_flag.equals("BB")){
            log.debug("ls_acorp1 =====>" + ls_acorp1.substring(0,2) );
            if(ls_acorp1.substring(0,2).equals("02")){
                index03Dto.setJpb_gubn("P");
            }else{
                index03Dto.setJpb_gubn("B");
            }
        }else{
            index03Dto.setJpb_gubn("%");
        }

        try {
            index03List = service03.GetJcustomCode(index03Dto);

            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App15_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index150";
    }


    //주문현황
    @GetMapping(value="/index151")
    public Object App151_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문현황");
        CommDto.setMenuUrl("기준정보>주문현황");
        CommDto.setMenuCode("index151");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index03List = service03.GetJcustomCode(index03Dto);
//            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App151_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index151";
    }


    //예약현황
    @GetMapping(value="/index152")
    public Object App152_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index152");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index03List = service03.GetJcustomCode(index03Dto);
//            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App152_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index152";
    }


    //주문등록
    @GetMapping(value="/index14")
    public String App14_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03Dto.setJpb_gubn("%");
            index03List = service03.GetJBonsaCodeList(index03Dto);
            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App14_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index14";
    }

    //주문등록
    @GetMapping(value="/index140")
    public String App140_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03List = service03.GetJBonsaCodeList(index03Dto);

            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App140_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index140";
    }


    //주문등록
    @GetMapping(value="/index141")
    public String App141_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문현황");
        CommDto.setMenuUrl("기준정보>주문현황");
        CommDto.setMenuCode("index141");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App141_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index141";
    }


    //예약현황
    @GetMapping(value="/index142")
    public String App142_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index142");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App142_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index142";
    }


    //주문등록
    @GetMapping(value="/index143")
    public String App143_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index143");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App143_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index143";
    }


    //주문등록
    @GetMapping(value="/index140m")
    public String App140m_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index140m");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03List = service03.GetJBonsaCodeList(index03Dto);

            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App140m_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index140m";
    }


    //주문등록 거래처
    @GetMapping(value="/index150m")
    public String App150m_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처주문등록");
        CommDto.setMenuUrl("기준정보>거래처주문등록");
        CommDto.setMenuCode("index150m");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        String ls_acorp1 = userformDto.getPerid();
        String ls_flag = userformDto.getFlag();
        if (ls_flag.equals("BB")){
            log.debug("ls_acorp1 =====>" + ls_acorp1.substring(0,2) );
            if(ls_acorp1.substring(0,2).equals("02")){
                index03Dto.setJpb_gubn("P");
            }else{
                index03Dto.setJpb_gubn("B");
            }
        }else{
            index03Dto.setJpb_gubn("%");
        }
        try {
            index03List = service03.GetJcustomCode(index03Dto);

            model.addAttribute("index15List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index150m_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index150m";
    }

    //주문등록
    @GetMapping(value="/index16")
    public String App16_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문확정");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03Dto.setJpb_gubn("%");
            index03List = service03.GetJBonsaCodeList(index03Dto);
            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App16_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index16";
    }

    //예약현황
    @GetMapping(value="/index17")
    public String App17_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("예약현황");
        CommDto.setMenuUrl("기준정보>예약현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index03Dto.setJpb_gubn("%");
//            index03List = service03.GetJBonsaCodeList(index03Dto);
//            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App17_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index17";
    }

    //사원별매출현황
    @GetMapping(value="/index06")
    public String App06_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index06");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App06_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index06";
    }


    //거래별매출현황
    @GetMapping(value="/index07")
    public String App07_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래별매출현황");
        CommDto.setMenuUrl("기준정보>사원별매출현황");
        CommDto.setMenuCode("index07");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App07_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index07";
    }

    //제품별매출현황
    @GetMapping(value="/index08")
    public String App08_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품별매출현황");
        CommDto.setMenuUrl("기준정보>제품별매출현황");
        CommDto.setMenuCode("index08");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App08_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index08";
    }

    //지역별매출현황
    @GetMapping(value="/index09")
    public String App09_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("지역별매출현황");
        CommDto.setMenuUrl("기준정보>지역별매출현황");
        CommDto.setMenuCode("index09");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App08_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index09";
    }

    //AS접수 배송현황
    @GetMapping(value="/index11")
    public String App11_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS접수배송현황");
        CommDto.setMenuCode("index11");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index11";
    }

    //AS접수 배송현황
    @GetMapping(value="/index110")
    public String App110_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("통계관리");
        CommDto.setMenuUrl("통계관리>AS접수배송현황");
        CommDto.setMenuCode("index110");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            popupListDto = svcpopup.getCifCodeList(popupDto);

            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index110";
    }

}
