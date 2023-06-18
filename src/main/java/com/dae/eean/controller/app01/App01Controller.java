package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index03Dto;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.UserFormDto;
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

    protected Log log =  LogFactory.getLog(this.getClass());
    //사업장정보조회
    @GetMapping(value="/index01")
    public String App01_index(Model model, HttpServletRequest request) throws Exception{
//        CommDto.setMenuTitle("사업장정보");
//        CommDto.setMenuUrl("기준정보>사업장정보");
//        CommDto.setMenuCode("appcom01");
//
//
//        itemDtoList = appcom01Service.GetXa012List();
//        itemDto.setSpjangcd(spcode);
//        if(appcom01Service.Getbxa012Detail(itemDto) == null){
//            model.addAttribute("itemDto", appcom01Service.GetTBXa012Blank());
//        }else{
//            model.addAttribute("itemDto", appcom01Service.Getbxa012Detail(itemDto));
//        }
//        log.debug("map check=====>" );
//        model.addAttribute("itemDtoList", itemDtoList);
//        model.addAttribute("itemDtoInput", itemDtoInput);
        model.addAttribute("CommDto", CommDto);
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


}
