package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index03Dto;
import com.dae.eean.DTO.App01.Index04Dto;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.TBXuserMenuDTO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index02Service;
import com.dae.eean.Service.App01.Index03Service;
import com.dae.eean.Service.App01.Index04Service;
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
    private final Index03Service service03;
    private final Index04Service service04;
    CommonDto CommDto = new CommonDto();
    Index04Dto index04Dto = new Index04Dto();
    List<Index04Dto> index04List = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();
    List<Index02Dto> index02List = new ArrayList<>();
    Index02Dto index02Dto = new Index02Dto();
    Index03Dto index03Dto = new Index03Dto();

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
//            log.info("conacorp1 =====>" + conacorp1);
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



    //제품등록
    @GetMapping(value="/index03/list")
    public Object App03List_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index03Dto.setJpum(searchtxt);
            index03List = service03.GetJpumList(index03Dto);
            model.addAttribute("index03List",index03List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index03List;
    }


    //거래처등록
    @GetMapping(value="/index03/listtot")
    public Object App03ListTot_index(@RequestParam("jpbgubn") String jpbgubn,
                                     @RequestParam("jmodelcode") String jmodelcode,
                                     @RequestParam("conagita") String conagita,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            if(jmodelcode == null || jmodelcode.equals("")){
                jmodelcode = "%";
            }
            if(conagita == null || conagita.equals("")){
                conagita = "%";
            }
            index03Dto.setJpb_gubn(jpbgubn);
            log.info("jpbgubn =====>" + jpbgubn);
            index03Dto.setJmodel_code(jmodelcode);
            index03Dto.setJpum(conagita);
            index03List = service03.GetJpumListTot(index03Dto);
            model.addAttribute("index03List",index03List);

        } catch (Exception ex) {
            log.info("App02ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index03List;
    }



    //고객분류코드로 모델리스트  list
    @GetMapping(value="/index03/custlist")
    public Object App03CustList_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index03Dto.setJcustomer_code(searchtxt);
            index03List = service03.GetJpumCustList(index03Dto);
            model.addAttribute("index03CustList",index03List);

        } catch (Exception ex) {
            log.info("GetJpumCustList  Exception =====>" + ex.toString());
        }

        return index03List;
    }

    //고객코드와 모델코드로 제품정보 list
    @GetMapping(value="/index03/modellist")
    public Object App03ModelList_index(@RequestParam("jcust") String jcust,
                                       @RequestParam("jmodel") String jmodel,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            if(jcust == null || jcust.equals("")){
                jcust = "%";
            }
            index03Dto.setJcustomer_code(jcust);
            index03Dto.setJmodel_code(jmodel);
            index03List = service03.GetJpumModelList(index03Dto);
            model.addAttribute("index03MdelList",index03List);

        } catch (Exception ex) {
            log.info("GetJpumModelList  Exception =====>" + ex.toString());
        }

        return index03List;
    }


    @RequestMapping(value="/index03/save")
    public String index03Save(@RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        try {
            param.forEach((key, values) -> {
                switch (key) {
                    case "jkey":
                        index03Dto.setJkey(values.toString());
                        break;
                    case "jpbgubn":
                        index03Dto.setJpb_gubn(values.toString());
                        break;
                    case "jgongcode":
                        index03Dto.setJgong_code(values.toString());
                        break;
                    case "jdancode":
                        index03Dto.setJdan_code(values.toString());
                        break;
                    case "jmodelcode":
                        index03Dto.setJmodel_code(values.toString());
                        break;
                    case "jcolorcode":
                        index03Dto.setJcolor_code(values.toString());
                        break;
                    case "jcustomercode":
                        index03Dto.setJcustomer_code(values.toString());
                        break;
                    case "jbonsacode":
                        index03Dto.setJbonsa_code(values.toString());
                        break;
                    case "jsayonggubn":
                        index03Dto.setJsayong_gubn(values.toString());
                        break;
                    case "jpum":
                        index03Dto.setJpum(values.toString());
                        break;
                    case "jgugek":
                        index03Dto.setJgugek(values.toString());
                        break;
                    case "jsize":
                        index03Dto.setJsize(values.toString());
                        break;
                    case "jchgoga0":
                        index03Dto.setJchgoga0(values.toString());
                        break;
                    case "jbigo":
                        index03Dto.setJbigo(values.toString());
                        break;
                    default:
                        break;
                }
            });
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            String ls_acode = service03.GetJpumCheck(index03Dto);
            Boolean result = false;
            log.info("ls_acode");
            log.info(index03Dto.getJbigo());
            if (ls_acode == null || ls_acode.equals("")) {
                result = service03.InsertJpum(index03Dto);
                log.info("result1");
                log.info(result);
                if (!result) {
                    return "error";
                }
            } else {
                result = service03.UpdateJpum(index03Dto);
                log.info("result2");
                log.info(result);
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

    @RequestMapping(value="/index03/del")
    public String index03Delete(  @RequestParam("ascode") String ascode,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        index03Dto.setJkey(ascode);
        Boolean result = service03.DeleteJpum(index03Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }


    //간편주문 리스트 01 등록
    @GetMapping(value="/index03/ganlist01")
    public Object App03GanList01_index(@RequestParam("jpbgubn") String jpbgubn,
                                     @RequestParam("flag") String flag,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            index03Dto.setJpb_gubn(jpbgubn);
            index03List = service03.GetGanListBonsa01(index03Dto);
            model.addAttribute("index03GanList01",index03List);

        } catch (Exception ex) {
            log.info("App03GanList01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index03List;
    }

    //간편주문 리스트 02 등록
    @GetMapping(value="/index03/ganlist02")
    public Object App03GanList02_index(@RequestParam("jpbgubn") String jpbgubn,
                                       @RequestParam("jbonsacode") String jbonsacode,
                                       @RequestParam("jmodelcode") String jmodelcode,
                                       @RequestParam("flag") String flag,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            index03Dto.setJpb_gubn(jpbgubn);
            index03Dto.setJbonsa_code(jbonsacode);
            index03Dto.setJmodel_code(jmodelcode);
            index03List = service03.GetGanListBonsa02(index03Dto);
            model.addAttribute("index03GanList02",index03List);

        } catch (Exception ex) {
            log.info("App03GanList01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index03List;
    }





    @RequestMapping(value="/index04/save")
    public String index04Save(
             @RequestParam("frdate") String frdate
            ,@RequestParam( value =  "jcode[]") List<String> jcode
            ,@RequestParam( value =  "jqty[]") List<String> jqty
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;

            Boolean result = false;
            if( jcode.size() > 0){
                for(int i = 0; i < jcode.size(); i++){
                     index04Dto.setAcorp("00");
                     index04Dto.setKey1(frdate);
                     index04Dto.setJepm(jcode.get(i));
                     index04Dto.setIjaego_su1(Integer.parseInt(jqty.get(i)));
                     index04Dto.setJepm_size("00");
//                    log.info("frdate Exception =====>" + frdate);
//                    log.info("jcode  Exception =====>" + jcode.get(i));
//                    log.info("jqty  Exception =====>" + jqty.get(i));
                     result = service04.InsertJegoIpgo(index04Dto);
                    if (!result){
                        return "error";
                    }
                }
            }

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index04/del")
    public String index04Delete(  @RequestParam("ipdate") String ipdate,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        String year = ipdate.substring(0,4) ;
        String month = ipdate.substring(5,7) ;
        String day   = ipdate.substring(8,10) ;
        ipdate = year + month + day ;
        index04Dto.setKey1(ipdate);
        Boolean result = service04.DeleteJaegoIpgo(index04Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }


    //재고실사 리스트
    @GetMapping(value="/index04/list")
    public Object App04List_index(@RequestParam("ipdate") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index04");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            String year = searchtxt.substring(0,4) ;
            String month = searchtxt.substring(5,7) ;
            String day   = searchtxt.substring(8,10) ;
            searchtxt = year + month + day ;
            log.debug("searchtxt =====>" + searchtxt );
            index04Dto.setKey1(searchtxt);
            index04List = service04.SelectJegoIpgo(index04Dto);
            model.addAttribute("index04List",index04List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index04List;
    }

    //재고현황 리스트
    @GetMapping(value="/index04/jaegolist")
    public Object App04JaegoList_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고현항");
        CommDto.setMenuCode("index04");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );
            index04Dto.setJkey(searchtxt);
            index04Dto.setFrdate("2000-01-01");
            index04Dto.setTodate(searchtxt);
            index04List = service04.SelectJegoList(index04Dto);
            model.addAttribute("index04List",index04List);

        } catch (Exception ex) {
            log.info("App04JaegoList_index Exception =====>" + ex.toString());
        }

        return index04List;
    }


    //그룹별 재고현황 리스트
    @GetMapping(value="/index04/jaegocustlist")
    public Object App04JaegoCustList_index(@RequestParam("searchtxt") String searchtxt,
                                           @RequestParam("jcustcd") String jcustcd,
                                           @RequestParam("todate") String todate,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고현항");
        CommDto.setMenuCode("index04");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            if(jcustcd == null || jcustcd.equals("")){
                jcustcd = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );

            index03Dto.setJcustomer_code(jcustcd);
            index03Dto.setJpum(searchtxt);
            index03Dto.setFrdate("2000-01-01");
            index03Dto.setTodate(todate);
            index03List = service03.GetJpumCustJaegoList(index03Dto);
            model.addAttribute("index03List",index03List);

        } catch (Exception ex) {
            log.info("App04JaegoList_index Exception =====>" + ex.toString());
        }

        return index03List;
    }



}
