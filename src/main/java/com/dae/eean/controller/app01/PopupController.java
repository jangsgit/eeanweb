package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.Popup.PopupDto;
import com.dae.eean.DTO.TBXuserMenuDTO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index02Service;
import com.dae.eean.Service.PopupService;
import com.dae.eean.Service.master.AuthService;
import com.dae.eean.controller.EncryptionController;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/popup", method = RequestMethod.POST)
public class PopupController {
    private final PopupService service;
    PopupDto popupDto = new PopupDto();
    List<PopupDto> popupListDto = new ArrayList<>();

    EncryptionController enc = new EncryptionController();
    protected Log log =  LogFactory.getLog(this.getClass());


    //사원현황
    @GetMapping(value="/insalist")
    public Object App02List_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }

            popupDto.setInname(searchtxt);
            popupListDto = service.GetInsaList(popupDto);
//            log.info("popupDto =====>" );
//            log.info(  popupDto);
            model.addAttribute("insalistDto",popupListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return popupListDto;
    }


    //업체분류현황
    @GetMapping(value="/cifcodelist")
    public Object App02CifCodeList_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            popupDto.setCifcode(searchtxt);
            popupListDto = service.getCifCodeList(popupDto);
//            log.info("popupDto =====>" );
//            log.info(  popupDto);
            model.addAttribute("cifcodeList",popupListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return popupListDto;
    }


    @RequestMapping(value="/cifcodesave")
    public String App02CifCodeSave_index(  @RequestParam("code") String code,
                                  @RequestParam("codenm") String codenm,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        popupDto.setCifcode(code);
        popupDto.setCifcodename(codenm);
        popupListDto = service.getCifCodeList(popupDto);
        if(popupListDto.isEmpty() || popupListDto.size() == 0){
            result = service.InsertCifCode(popupDto);
        }else{
            result = service.UpdateCifCode(popupDto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/cifcodedel")
    public String App02CifCodeDel_index(  @RequestParam("code") String code,
                                  @RequestParam("codenm") String codenm,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        popupDto.setCifcode(code);
        popupDto.setCifcodename(codenm);
        popupListDto = service.getCifCodeList(popupDto);
        result = service.DeleteCifCode(popupDto);
        if (!result) {
            return "error";
        }
        return "success";
    }



}
