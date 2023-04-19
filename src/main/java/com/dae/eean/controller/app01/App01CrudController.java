package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.TBXuserMenuDTO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index02Service;
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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app01m", method = RequestMethod.POST)
public class App01CrudController {
    private final AuthService authService;
    UserFormDto userformDto = new UserFormDto();

    private final Index02Service service02;
    CommonDto CommDto = new CommonDto();
    List<Index02Dto> index02List = new ArrayList<>();
    Index02Dto index02Dto = new Index02Dto();

    EncryptionController enc = new EncryptionController();
    protected Log log =  LogFactory.getLog(this.getClass());


    //거래처등록
    @GetMapping(value="/index02/list")
    public Object App02List_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index02Dto.setAcorp(searchtxt);
            index02List = service02.GetCifList(index02Dto);
            model.addAttribute("index02List",index02List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index02List;
    }


    //거래처등록
    @GetMapping(value="/index02/listtot")
    public Object App02ListTot_index(@RequestParam("conacorp1") String conacorp1,
                                     @RequestParam("conacorp") String conacorp,
                                     @RequestParam("conagita") String conagita,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(conacorp1 == null || conacorp1.equals("")){
                conacorp1 = "%";
            }
            if(conacorp == null || conacorp.equals("")){
                conacorp = "%";
            }
            if(conagita == null || conagita.equals("")){
                conagita = "%";
            }
            index02Dto.setAcorp1(conacorp1);
            log.info("conacorp1 =====>" + conacorp1);
            index02Dto.setAcorp(conacorp);
            index02Dto.setAgita(conagita);
            index02List = service02.GetCifListTot(index02Dto);
            model.addAttribute("index02List",index02List);

        } catch (Exception ex) {
            log.info("App02ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index02List;
    }


    @RequestMapping(value="/index02/save")
    public String index02Save(@RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        try {
            param.forEach((key, values) -> {
                switch (key) {
                    case "acorp1":
                        index02Dto.setAcorp1(values.toString());
                        break;
                    case "acorp2":
                        index02Dto.setAcorp2(values.toString());
                        break;
                    case "acorp3":
                        index02Dto.setAcorp3(values.toString());
                        break;
                    case "acode":
                        index02Dto.setAcode(values.toString());
                        log.info("setAcode");
                        log.info(values.toString());
                        break;
                    case "acorp":
                        index02Dto.setAcorp(values.toString());
                        break;
                    case "asano1":
                        index02Dto.setAsano1(values.toString());
                        break;
                    case "asano2":
                        index02Dto.setAsano2(values.toString());
                        break;
                    case "asano3":
                        index02Dto.setAsano3(values.toString());
                        break;
                    case "aname":
                        index02Dto.setAname(values.toString());
                        break;
                    case "aupte":
                        index02Dto.setAupte(values.toString());
                        break;
                    case "ajong":
                        index02Dto.setAjong(values.toString());
                        break;
                    case "apost1":
                        index02Dto.setApost1(values.toString());
                        break;
                    case "ajuso1":
                        index02Dto.setAjuso1(values.toString());
                        break;
                    case "ajuso2":
                        index02Dto.setAjuso2(values.toString());
                        break;
                    case "abigo":
                        index02Dto.setAbigo(values.toString());
                        break;
                    case "agita":
                        index02Dto.setAgita(values.toString());
                        break;
                    case "ajumi1":
                        index02Dto.setAjumi1(values.toString());
                        break;
                    case "ajumi2":
                        index02Dto.setAjumi2(values.toString());
                        break;
                    case "aascode1":
                        index02Dto.setAascode1(values.toString());
                        break;
                    case "aascode2":
                        index02Dto.setAascode2(values.toString());
                        break;
                    case "atelno":
                        index02Dto.setAtelno(values.toString());
                        break;
                    case "atelno2":
                        index02Dto.setAtelno2(values.toString());
                        break;
                    case "aemail":
                        index02Dto.setAemail(values.toString());
                        break;
                    case "ahand":
                        index02Dto.setAhand(values.toString());
                        break;
                    case "abonsadam1":
                        index02Dto.setAbonsadam1(values.toString());
                        break;
                    case "abonsadam2":
                        index02Dto.setAbonsadam2(values.toString());
                        break;
                    case "abonsadam3":
                        index02Dto.setAbonsadam3(values.toString());
                        break;
                    case "adomain":
                        index02Dto.setAdomain(values.toString());
                        break;
                    case "afax":
                        index02Dto.setAfax(values.toString());
                        break;
                    default:
                        break;
                }
            });
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            String ls_acode = index02Dto.getAcode();
            String ls_acorp2 = "";
            Boolean result = false;
            if (ls_acode == null || ls_acode.equals("")) {
                Integer ll_acorp2 = Integer.parseInt(service02.getIndex02MaxSeq(index02Dto.getAcorp1())) + 1;
                ls_acorp2 = ll_acorp2.toString();
                index02Dto.setAcorp2(ls_acorp2);
                result = service02.InsertCif(index02Dto);
                if (!result) {
                    return "error";
                }
            } else {
                result = service02.UpdateCif(index02Dto);
                if (!result) {
                    return "error";
                }
            }
//            model.addAttribute("userformDto",userformDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/index02/del")
    public String index02Delete(  @RequestParam("ascode") String ascode,
            Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        index02Dto.setAcode(ascode);
        Boolean result = service02.DeleteCif(index02Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }



}
