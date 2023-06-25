package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.*;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.TBXuserMenuDTO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index02Service;
import com.dae.eean.Service.App01.Index03Service;
import com.dae.eean.Service.App01.Index04Service;
import com.dae.eean.Service.App01.Index14Service;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private final Index14Service service14;
    CommonDto CommDto = new CommonDto();
    Index04Dto index04Dto = new Index04Dto();
    List<Index04Dto> index04List = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();
    List<Index02Dto> index02List = new ArrayList<>();
    List<IndexDa024Dto> indexDa024ListDto = new ArrayList<>();

    Index02Dto index02Dto = new Index02Dto();
    Index02Dto index02BonsaDto = new Index02Dto();
    Index03Dto index03Dto = new Index03Dto();
    IndexDa023Dto indexDa023Dto = new IndexDa023Dto();
    IndexDa024Dto indexDa024Dto = new IndexDa024Dto();
    IndexIa011Dto indexia011Dto = new IndexIa011Dto();
    IndexIa012Dto indexia012Dto = new IndexIa012Dto();

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
                                     @RequestParam("abonsadam1") String abonsadam1,
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
            if(abonsadam1 == null || abonsadam1.equals("")){
                abonsadam1 = "%";
            }
            index02Dto.setAcorp1(conacorp1);
//            log.info("conacorp1 =====>" + conacorp1);
            index02Dto.setAcorp(conacorp);
            index02Dto.setAgita(conagita);
            index02Dto.setAbonsadam1(abonsadam1);
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
            index03Dto.setFrdate("20100101");
            index03Dto.setTodate(getToDate());
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
                                       @RequestParam("jcode") String jbonsacode,
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
            index03Dto.setJbonsa_code(jbonsacode);
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
            index03Dto.setFrdate("20000101");
            index03Dto.setTodate(getToDate());
            index03List = service03.GetGanListBonsa02(index03Dto);
            model.addAttribute("index03GanList02",index03List);

        } catch (Exception ex) {
            log.info("App03GanList01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index03List;
    }

    //간편주문 리스트 03 등록
    @GetMapping(value="/index03/ganlist03")
    public Object App03GanList03_index(@RequestParam("jpbgubn") String jpbgubn,
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
            index03List = service03.GetJBonsaCodeList(index03Dto);
            model.addAttribute("index03GanList03",index03List);

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


    @RequestMapping(value="/index14/save")
    public String index14Save(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("jbonsa") String jbonsa
            ,@RequestParam("jmodel") String jmodel
            ,@RequestParam("jcolor") String jcolor
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("jpbgubn") String jpbgubn
            ,@RequestParam("ordercd") String ordercd
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            index03Dto.setJmodel_code(jmodel);
            index03Dto.setJcolor_code(jcolor);
            index03Dto.setJbonsa_code(jbonsa);
            index03Dto.setJpb_gubn(jpbgubn);
            index02Dto.setAcode(acode);
            index02Dto = service02.GetCifListAcode(index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            index03Dto = service03.GetJpumOrderJkey(index03Dto); //품목정보
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setMisdate(frdate);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnum(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = "0001";
            }else{
                ls_misnum = ls_chknull;
            }
            indexDa023Dto.setMisnum(ls_misnum);
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    indexDa023Dto.setPerid("");
                    indexDa023Dto.setGgubun("AA");
                    break;
                case "BB":
                    indexDa023Dto.setPerid("");
                    indexDa023Dto.setGgubun("BB");
                    break;
                case "CC":
                    indexDa023Dto.setPerid(userformDto.getPerid());
                    indexDa023Dto.setGgubun("CC");
                    break;
                default:
                    break;
            }
            indexDa023Dto.setContamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setMisamt(0);
            indexDa023Dto.setAmt(0);
            indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            indexDa023Dto.setBigo("");
            indexDa023Dto.setRemark("");
            indexDa023Dto.setVatemail(index02Dto.getAemail());  //계산서 메일주소
            indexDa023Dto.setVatpernm(index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            indexDa024Dto.setMisgubun(mflag);
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeq(frdate);
            }

            String ls_chulgoga = index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            log.info("ls_misnum=============>");
            log.info(ls_misnum);
            indexDa024Dto.setPcode(index03Dto.getJkey());
            indexDa024Dto.setPname(index03Dto.getJpum());
            indexDa024Dto.setPsize(index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(jbonsa);
            indexDa024Dto.setPmodel(jmodel);
            indexDa024Dto.setPcolor(jcolor);
            indexDa024Dto.setQty(1);
            indexDa024Dto.setUamt(ll_chulgoga);
            indexDa024Dto.setSamt(ll_chulgoga);
            indexDa024Dto.setCltcd(indexDa023Dto.getCltcd());
            indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {indexDa024Dto.setAddamt(ll_chulgoga / 10);};
            indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            indexDa024Dto.setIndate(getToDate());
            indexDa024Dto.setInperid(userformDto.getPernm());
            indexDa024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa023(indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa024(indexDa024Dto);
            if (!result){
                return "error";
            }

        }catch (IllegalStateException e){
            model.addAttribute("index14Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index14/savewish")
    public String index14SaveWish(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("jbonsa") String jbonsa
            ,@RequestParam("jmodel") String jmodel
            ,@RequestParam("jcolor") String jcolor
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("jpbgubn") String jpbgubn
            ,@RequestParam("ordercd") String ordercd
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            index03Dto.setJmodel_code(jmodel);
            index03Dto.setJcolor_code(jcolor);
            index03Dto.setJbonsa_code(jbonsa);
            index03Dto.setJpb_gubn(jpbgubn);
            index02Dto.setAcode(acode);
            index02Dto = service02.GetCifListAcode(index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            index03Dto = service03.GetJpumOrderJkey(index03Dto); //품목정보
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            indexDa023Dto.setMisgubun(mflag);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnumWish(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = "0001";
            }else{
                ls_misnum = ls_chknull;
            }
            indexDa023Dto.setMisnum(ls_misnum);
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    indexDa023Dto.setPerid("");
                    indexDa023Dto.setGgubun("AA");
                    break;
                case "BB":
                    indexDa023Dto.setPerid("");
                    indexDa023Dto.setGgubun("BB");
                    break;
                case "CC":
                    indexDa023Dto.setPerid(userformDto.getPerid());
                    indexDa023Dto.setGgubun("CC");
                    break;
                default:
                    break;
            }
            indexDa023Dto.setContamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setMisamt(0);
            indexDa023Dto.setAmt(0);
            indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            indexDa023Dto.setBigo("");
            indexDa023Dto.setRemark("");
            indexDa023Dto.setVatemail(index02Dto.getAemail());  //계산서 메일주소
            indexDa023Dto.setVatpernm(index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            indexDa024Dto.setMisgubun(mflag);
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeqWish(frdate);
            }

            String ls_chulgoga = index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            log.info("ls_misnum=============>");
            log.info(ls_misnum);
            indexDa024Dto.setPcode(index03Dto.getJkey());
            indexDa024Dto.setPname(index03Dto.getJpum());
            indexDa024Dto.setPsize(index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(jbonsa);
            indexDa024Dto.setPmodel(jmodel);
            indexDa024Dto.setPcolor(jcolor);
            indexDa024Dto.setQty(1);
            indexDa024Dto.setUamt(ll_chulgoga);
            indexDa024Dto.setSamt(ll_chulgoga);
            indexDa024Dto.setCltcd(indexDa023Dto.getCltcd());
            indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {indexDa024Dto.setAddamt(ll_chulgoga / 10);};
            indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            indexDa024Dto.setIndate(getToDate());
            indexDa024Dto.setInperid(userformDto.getPernm());
            indexDa024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa025(indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa026(indexDa024Dto);
            if (!result){
                return "error";
            }

        }catch (IllegalStateException e){
            model.addAttribute("index14SaveWish errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/index14/savecust")
    public String index14SaveCust(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("jkey") String jkey
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("ordercd") String ordercd
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            index02Dto.setAcode(acode);
            index03Dto.setJkey(jkey);
            index02Dto = service02.GetCifListAcode(index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            index03Dto = service03.GetJpumOrderJkey02(index03Dto); //품목

            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnum(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = "0001";
            }else{
                ls_misnum = ls_chknull;
            }
            indexDa023Dto.setMisnum(ls_misnum);
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    indexDa023Dto.setPerid("");
                    break;
                case "BB":
                    indexDa023Dto.setPerid("");
                    break;
                case "CC":
                    indexDa023Dto.setPerid(userformDto.getPerid());
                    break;
                default:
                    break;
            }
            indexDa023Dto.setContamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setMisamt(0);
            indexDa023Dto.setAmt(0);
            indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            indexDa023Dto.setBigo("");
            indexDa023Dto.setRemark("");
            indexDa023Dto.setVatemail(index02Dto.getAemail());  //계산서 메일주소
            indexDa023Dto.setVatpernm(index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeq(frdate);
            }

            String ls_chulgoga = index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            indexDa024Dto.setMisgubun(mflag);
//            log.info("ls_misnum=============>");
//            log.info(ls_misnum);
            indexDa024Dto.setPcode(index03Dto.getJkey());
            indexDa024Dto.setPname(index03Dto.getJpum());
            indexDa024Dto.setPsize(index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(index03Dto.getJbonsa_code());
            indexDa024Dto.setPmodel(index03Dto.getJmodel_code());
            indexDa024Dto.setPcolor(index03Dto.getJcolor_code());
            indexDa024Dto.setQty(1);
            indexDa024Dto.setUamt(ll_chulgoga);
            indexDa024Dto.setSamt(ll_chulgoga);
            indexDa024Dto.setCltcd(indexDa023Dto.getCltcd());
            indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {
                indexDa024Dto.setAddamt(ll_chulgoga / 10);
                indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            }else{
                indexDa024Dto.setAddamt(0);
                indexDa024Dto.setAmt(0);
            };

            indexDa024Dto.setIndate(getToDate());
            indexDa024Dto.setInperid(userformDto.getPernm());
            indexDa024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa023(indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa024(indexDa024Dto);
            if (!result){
                return "error";
            }

        }catch (IllegalStateException e){
            model.addAttribute("index14Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    //주문삭제
    @GetMapping(value="/index14/del")
    public String App14Del_index(@RequestParam("argmisdate") String misdate,
                                  @RequestParam("argmisnum") String misnum,
                                  @RequestParam("argmisseq") String seq,
                                  @RequestParam("argcltcd") String cltcd,
                                 @RequestParam("mflag") String mflag,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            String year = misdate.substring(0,4) ;
            String month = misdate.substring(5,7) ;
            String day   = misdate.substring(8,10) ;
            misdate = year + month + day ;
            indexDa024Dto.setMisdate(misdate);
            indexDa024Dto.setMisnum(misnum);
            indexDa024Dto.setSeq(seq);
            indexDa024Dto.setCltcd(cltcd);
            indexDa024Dto.setMisgubun(mflag);
            Boolean result = false;
            result = service14.DeleteDA024(indexDa024Dto);
            if (!result){
                return "error";
            }
            result = service14.DeleteDA023(indexDa024Dto);
            if (!result){
                return "error";
            }

        } catch (Exception ex) {
            log.info("App14Del_index Exception =====>" + ex.toString());
        }
        return "SUCCESS";
    }


    //주문삭제
    @GetMapping(value="/index14/delwish")
    public String App14DelWish_index(@RequestParam("argmisdate") String misdate,
                                 @RequestParam("argmisnum") String misnum,
                                 @RequestParam("argmisseq") String seq,
                                 @RequestParam("argcltcd") String cltcd,
                                     @RequestParam("mflag") String mflag,
                                 Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            String year = misdate.substring(0,4) ;
            String month = misdate.substring(5,7) ;
            String day   = misdate.substring(8,10) ;
            misdate = year + month + day ;
            indexDa024Dto.setMisdate(misdate);
            indexDa024Dto.setMisnum(misnum);
            indexDa024Dto.setSeq(seq);
            indexDa024Dto.setCltcd(cltcd);
            indexDa024Dto.setMisgubun(mflag);
            Boolean result = false;
            result = service14.DeleteDA026(indexDa024Dto);
            if (!result){
                return "error";
            }
            result = service14.DeleteDA025(indexDa024Dto);
            if (!result){
                return "error";
            }

        } catch (Exception ex) {
            log.info("App14DelWish_index Exception =====>" + ex.toString());
        }
        return "SUCCESS";
    }

    @RequestMapping(value="/index14/savecustwish")
    public String index14SaveCustWish(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("jkey") String jkey
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("ordercd") String ordercd
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            index02Dto.setAcode(acode);
            index03Dto.setJkey(jkey);
            index02Dto = service02.GetCifListAcode(index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            index03Dto = service03.GetJpumOrderJkey02(index03Dto); //품목

            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnumWish(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = "0001";
            }else{
                ls_misnum = ls_chknull;
            }
            indexDa023Dto.setMisnum(ls_misnum);
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    indexDa023Dto.setPerid("");
                    break;
                case "BB":
                    indexDa023Dto.setPerid("");
                    break;
                case "CC":
                    indexDa023Dto.setPerid(userformDto.getPerid());
                    break;
                default:
                    break;
            }
            indexDa023Dto.setContamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setAddamt(0);
            indexDa023Dto.setMisamt(0);
            indexDa023Dto.setAmt(0);
            indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            indexDa023Dto.setBigo("");
            indexDa023Dto.setRemark("");
            indexDa023Dto.setVatemail(index02Dto.getAemail());  //계산서 메일주소
            indexDa023Dto.setVatpernm(index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeqWish(frdate);
            }

            String ls_chulgoga = index03Dto.getJchgoga0();
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            if( ls_chulgoga == null ||  ll_chulgoga == 0 || ll_chulgoga == null ){
                ls_chulgoga = "0";
            }
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            indexDa024Dto.setMisgubun(mflag);
//            log.info("ls_misnum=============>");
//            log.info(ls_misnum);
            indexDa024Dto.setPcode(index03Dto.getJkey());
            indexDa024Dto.setPname(index03Dto.getJpum());
            indexDa024Dto.setPsize(index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(index03Dto.getJbonsa_code());
            indexDa024Dto.setPmodel(index03Dto.getJmodel_code());
            indexDa024Dto.setPcolor(index03Dto.getJcolor_code());
            indexDa024Dto.setQty(1);
            indexDa024Dto.setUamt(ll_chulgoga);
            indexDa024Dto.setSamt(ll_chulgoga);
            indexDa024Dto.setCltcd(indexDa023Dto.getCltcd());
            indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {
                indexDa024Dto.setAddamt(ll_chulgoga / 10);
                indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            }else{
                indexDa024Dto.setAddamt(0);
                indexDa024Dto.setAmt(0);
            };

            indexDa024Dto.setIndate(getToDate());
            indexDa024Dto.setInperid(userformDto.getPernm());
            indexDa024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa025(indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa026(indexDa024Dto);
            if (!result){
                return "error";
            }

        }catch (IllegalStateException e){
            model.addAttribute("index14SaveWish errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    //주문현황 리스트
    @GetMapping(value="/index14/list")
    public Object App14List_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("mflag") String mflag,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            year = todate.substring(0,4) ;
            month = todate.substring(5,7) ;
            day   = todate.substring(8,10) ;
            todate = year + month + day ;
            indexDa024Dto.setFrdate(frdate);
            indexDa024Dto.setTodate(todate);
            indexDa024Dto.setCltcd(acode);
            indexDa024Dto.setFixflag(fixflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    break;
                default:
                    break;
            }
            indexDa024Dto.setPerid(perid);
            indexDa024Dto.setMisgubun(mflag);
            log.info("mflag =====>" + mflag);
            indexDa024ListDto = service14.SelectDa024List(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/listwish")
    public Object App14ListWish_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("mflag") String mflag,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            year = todate.substring(0,4) ;
            month = todate.substring(5,7) ;
            day   = todate.substring(8,10) ;
            todate = year + month + day ;
            indexDa024Dto.setFrdate(frdate);
            indexDa024Dto.setTodate(todate);
            indexDa024Dto.setCltcd(acode);
            indexDa024Dto.setFixflag(fixflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    break;
                default:
                    break;
            }
            indexDa024Dto.setMisgubun(mflag);
            indexDa024ListDto = service14.SelectDa026List(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListWish_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }




    @RequestMapping(value="/index16/save")
    public String index16Save(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {



        }catch (IllegalStateException e){
            model.addAttribute("index14Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    public String GetMaxNum(String agDate){

        String ls_misnum = "";
        ls_misnum = service14.SelectMaxMisnum(indexDa023Dto);
        Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
        ls_misnum = ll_misnum.toString();
        if (ls_misnum.length() == 1){
            ls_misnum = "000" + ls_misnum;
        }else if(ls_misnum.length() == 2){
            ls_misnum = "00" + ls_misnum;
        }else if(ls_misnum.length() == 3){
            ls_misnum = "0" + ls_misnum;
        }

        return ls_misnum;
    }

    public String GetMaxSeq(String agDate){

        String ls_seq = service14.SelectMaxSeq(indexDa023Dto);
        if(ls_seq == null){
            ls_seq = "001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_seq) + 1;
            ls_seq = ll_misnum.toString();
            if (ls_seq.length() == 1){
                ls_seq = "00" + ls_seq;
            }else if(ls_seq.length() == 2){
                ls_seq = "0" + ls_seq;
            }
        }
        return ls_seq;
    }
    public String GetMaxSeqWish(String agDate){

        String ls_seq = service14.SelectMaxSeqWish(indexDa023Dto);
        if(ls_seq == null){
            ls_seq = "001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_seq) + 1;
            ls_seq = ll_misnum.toString();
            if (ls_seq.length() == 1){
                ls_seq = "00" + ls_seq;
            }else if(ls_seq.length() == 2){
                ls_seq = "0" + ls_seq;
            }
        }
        return ls_seq;
    }

    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

}
