package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.*;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.TBXuserMenuDTO;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.*;
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
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app01m", method = RequestMethod.POST)
public class App01CrudController {
    private final AuthService authService;
    UserFormDto userformDto = new UserFormDto();

    private final Index01Service service01;
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
    IndexDa024Dto indexDa024OrdDto = new IndexDa024Dto();
    IndexIa011Dto indexia011Dto = new IndexIa011Dto();
    IndexIa012Dto indexia012Dto = new IndexIa012Dto();

    Index01Dto index01Dto = new Index01Dto();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    EncryptionController enc = new EncryptionController();
    protected Log log =  LogFactory.getLog(this.getClass());


    //거래처등록
    @GetMapping(value="/index02/list")
    public Object App02List_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        Index02Dto _index02Dto = new Index02Dto();
        List<Index02Dto> _index02List = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App02List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _index02Dto.setAcorp(searchtxt);
            _index02List = service02.GetCifList(_index02Dto);
            model.addAttribute("index02List",_index02List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return _index02List;
    }


    //거래처등록
    @GetMapping(value="/index02/listtot")
    public Object App02ListTot_index(@RequestParam("conacorp1") String conacorp1,
                                     @RequestParam("conacorp") String conacorp,
                                     @RequestParam("conagita") String conagita,
                                     @RequestParam("abonsadam1") String abonsadam1,
                                     @RequestParam("jpbgubn") String jpbgubn,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        Index02Dto _index02Dto = new Index02Dto();
        List<Index02Dto> _index02List = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App02ListTot_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            _index02Dto.setAcorp1(conacorp1);
            _index02Dto.setAcorp(conacorp);
            _index02Dto.setAgita(conagita);
            _index02Dto.setAbonsadam1(abonsadam1);
            if (jpbgubn.equals("P")){
                _index02Dto.setAcorp1("02");
            }else if(jpbgubn.equals("B")){
                _index02Dto.setAcorp1("03");
            }else{
                //index02Dto.setAcorp1("%");
            }
//            log.info("001 =====>" + _index02Dto.getAcorp1());
//            log.info("002 =====>" + _index02Dto.getAcorp());
//            log.info("003 =====>" + _index02Dto.getAgita());
//            log.info("004 =====>" + _index02Dto.getAbonsadam1());
//            log.info("005 =====>" + jpbgubn);
            _index02List = service02.GetCifListTot(_index02Dto);
            model.addAttribute("index02List",_index02List);

        } catch (Exception ex) {
            log.info("App02ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index02List;
    }


    @RequestMapping(value="/index02/save")
    public String index02Save(@RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        try {
            Index02Dto _index02Dto = new Index02Dto();
            param.forEach((key, values) -> {
                switch (key) {
                    case "acorp1":
                        _index02Dto.setAcorp1(values.toString());
                        break;
                    case "acorp2":
                        _index02Dto.setAcorp2(values.toString());
                        break;
                    case "acorp3":
                        _index02Dto.setAcorp3(values.toString());
                        break;
                    case "acode":
                        _index02Dto.setAcode(values.toString());
                        break;
                    case "acorp":
                        _index02Dto.setAcorp(values.toString());
                        break;
                    case "asano1":
                        _index02Dto.setAsano1(values.toString());
                        break;
                    case "asano2":
                        _index02Dto.setAsano2(values.toString());
                        break;
                    case "asano3":
                        _index02Dto.setAsano3(values.toString());
                        break;
                    case "aname":
                        _index02Dto.setAname(values.toString());
                        break;
                    case "aupte":
                        _index02Dto.setAupte(values.toString());
                        break;
                    case "ajong":
                        _index02Dto.setAjong(values.toString());
                        break;
                    case "apost1":
                        _index02Dto.setApost1(values.toString());
                        break;
                    case "ajuso1":
                        _index02Dto.setAjuso1(values.toString());
                        break;
                    case "ajuso2":
                        _index02Dto.setAjuso2(values.toString());
                        break;
                    case "abigo":
                        _index02Dto.setAbigo(values.toString());
                        break;
                    case "agita":
                        _index02Dto.setAgita(values.toString());
                        break;
                    case "ajumi1":
                        _index02Dto.setAjumi1(values.toString());
                        break;
                    case "ajumi2":
                        _index02Dto.setAjumi2(values.toString());
                        break;
                    case "aascode1":
                        _index02Dto.setAascode1(values.toString());
                        break;
                    case "aascode2":
                        _index02Dto.setAascode2(values.toString());
                        break;
                    case "atelno":
                        _index02Dto.setAtelno(values.toString());
                        break;
                    case "atelno2":
                        _index02Dto.setAtelno2(values.toString());
                        break;
                    case "aemail":
                        _index02Dto.setAemail(values.toString());
                        break;
                    case "ahand":
                        _index02Dto.setAhand(values.toString());
                        break;
                    case "abonsadam1":
                        _index02Dto.setAbonsadam1(values.toString());
                        break;
                    case "abonsadam2":
                        _index02Dto.setAbonsadam2(values.toString());
                        break;
                    case "abonsadam3":
                        _index02Dto.setAbonsadam3(values.toString());
                        break;
                    case "adomain":
                        _index02Dto.setAdomain(values.toString());
                        break;
                    case "afax":
                        _index02Dto.setAfax(values.toString());
                        break;
                    default:
                        break;
                }
            });
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index02Save Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            String ls_acode = _index02Dto.getAcode();
            String ls_acorp2 = "";
            Boolean result = false;
            if (ls_acode == null || ls_acode.equals("")) {
                Integer ll_acorp2 = Integer.parseInt(service02.getIndex02MaxSeq(_index02Dto.getAcorp1())) + 1;
                ls_acorp2 = ll_acorp2.toString();
                _index02Dto.setAcorp2(ls_acorp2);
                result = service02.InsertCif(_index02Dto);
                if (!result) {
                    return "error";
                }
            } else {
                result = service02.UpdateCif(_index02Dto);
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
        Index02Dto _index02Dto = new Index02Dto();
        if(userformDto == null){
            log.info("index02Delete Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        _index02Dto.setAcode(ascode);
        Boolean result = service02.DeleteCif(_index02Dto);
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
        Index03Dto _index03Dto = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App03List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _index03Dto.setJpum(searchtxt);
            _index03List = service03.GetJpumList(_index03Dto);
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return _index03List;
    }

    //기간별수불현황
    @GetMapping(value="/index03/subul01")
    public Object App03SubulList_index(@RequestParam("frdate") String frdate,
                                       @RequestParam("todate") String todate,
                                       @RequestParam("jpbgubn") String jpbgubn,
                                       @RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>기간별수불현황");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App03SubulList_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
                    year = todate.substring(0,4) ;
                    month = todate.substring(5,7) ;
                    day   = todate.substring(8,10) ;
            todate = year + month + day ;
            index03Dto_S.setJkey(searchtxt);
            index03Dto_S.setFrdate(frdate);
            index03Dto_S.setTodate(todate);
            index03Dto_S.setJpb_gubn(jpbgubn);

            _index03List = service03.GetJpumSubul02(index03Dto_S);
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App03SubulList_index Exception =====>" + ex.toString());
        }

        return _index03List;
    }

    //기간별수불현황
    @GetMapping(value="/index03/subul02")
    public Object App03SubulList02_index(@RequestParam("frdate") String frdate,
                                       @RequestParam("todate") String todate,
                                         @RequestParam("jpbgubn") String jpbgubn,
                                       @RequestParam("searchtxt") String searchtxt,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품별수불현황");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App03SubulList02_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
                    year = todate.substring(0,4) ;
                    month = todate.substring(5,7) ;
                    day   = todate.substring(8,10) ;
            todate = year + month + day ;
            index03Dto_S.setJkey(searchtxt);
            index03Dto_S.setFrdate(frdate);
            index03Dto_S.setTodate(todate);
            index03Dto_S.setJpb_gubn(jpbgubn);

            _index03List = service03.GetJpumSubul02(index03Dto_S);
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App03SubulList02_index Exception =====>" + ex.toString());
        }

        return _index03List;
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
        if(userformDto == null){
            log.info("App03ListTot_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();

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
            index03Dto_S.setJpb_gubn(jpbgubn);
//            log.info("jpbgubn =====>" + jpbgubn);
            index03Dto_S.setJmodel_code(jmodelcode);
            index03Dto_S.setJpum(conagita);
            _index03List = service03.GetJpumListTot(index03Dto_S);
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App03ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index03List;
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
        if(userformDto == null){
            log.info("App03CustList_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index03Dto_S.setJcustomer_code(searchtxt);
            _index03List = service03.GetJpumCustList(index03Dto_S);
            model.addAttribute("index03CustList",_index03List);

        } catch (Exception ex) {
            log.info("searchtxt  Exception =====>" + searchtxt);
            log.info("GetJpumCustList  Exception =====>" + ex.toString());
        }

        return _index03List;
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
        if(userformDto == null){
            log.info("App03ModelList_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        try {

            if(jcust == null || jcust.equals("")){
                jcust = "%";
            }
            index03Dto_S.setJcustomer_code(jcust);
            index03Dto_S.setJmodel_code(jmodel);
            index03Dto_S.setFrdate("20100101");
            index03Dto_S.setTodate(getToDate());
            _index03List = service03.GetJpumModelList(index03Dto_S);
            model.addAttribute("index03MdelList",_index03List);

        } catch (Exception ex) {
            log.info("jcust  Exception =====>" + jcust);
            log.info("jmodel  Exception =====>" + jmodel);
            log.info("GetJpumModelList  Exception =====>" + ex.toString());
        }

        return _index03List;
    }


    @RequestMapping(value="/index03/save")
    public String index03Save(@RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        try {
            Index03Dto _index03Dto = new Index03Dto();
            param.forEach((key, values) -> {
                switch (key) {
                    case "jkey":
                        _index03Dto.setJkey(values.toString());
                        break;
                    case "jpbgubn":
                        _index03Dto.setJpb_gubn(values.toString());
                        break;
                    case "jgongcode":
                        _index03Dto.setJgong_code(values.toString());
                        break;
                    case "jdancode":
                        _index03Dto.setJdan_code(values.toString());
                        break;
                    case "jmodelcode":
                        _index03Dto.setJmodel_code(values.toString());
                        break;
                    case "jmodelcode2":
                        _index03Dto.setJmodel_code2(values.toString());
                        break;
                    case "jcolorcode":
                        _index03Dto.setJcolor_code(values.toString());
                        break;
                    case "jcolorcode2":
                        _index03Dto.setJcolor_code2(values.toString());
                        break;
                    case "jcolorcode3":
                        _index03Dto.setJcolor_code3(values.toString());
                        break;
                    case "jcustomercode":
                        _index03Dto.setJcustomer_code(values.toString());
                        break;
                    case "jcustomercodeseq":
                        _index03Dto.setJcustomer_code_seq(values.toString());
                        break;
                    case "jbonsacode":
                        _index03Dto.setJbonsa_code(values.toString());
                        break;
                    case "jbonsacode2":
                        _index03Dto.setJbonsa_code2(values.toString());
                        break;
                    case "jbonsacodeseq":
                        _index03Dto.setJbonsa_code_seq(values.toString());
                        break;
                    case "jbonsacodeseq2":
                        _index03Dto.setJbonsa_code_seq2(values.toString());
                        break;
                    case "jsayonggubn":
                        _index03Dto.setJsayong_gubn(values.toString());
                        break;
                    case "jpum":
                        _index03Dto.setJpum(values.toString());
                        break;
                    case "jgugek":
                        _index03Dto.setJgugek(values.toString());
                        break;
                    case "jsize":
                        _index03Dto.setJsize(values.toString());
                        break;
                    case "jchgoga0":
                        _index03Dto.setJchgoga0(values.toString());
                        break;
                    case "jbigo":
                        _index03Dto.setJbigo(values.toString());
                        break;
                    default:
                        break;
                }
            });
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index03Save Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            String ls_acode = service03.GetJpumCheck(_index03Dto);
            Boolean result = false;
            log.info("ls_acode");
            log.info(_index03Dto.getJbigo());
            if (ls_acode == null || ls_acode.equals("")) {
                result = service03.InsertJpum(_index03Dto);
                log.info("result1");
                log.info(result);
                if (!result) {
                    return "error";
                }
            } else {
                result = service03.UpdateJpum(_index03Dto);
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
        if(userformDto == null){
            log.info("index03Delete Exception =====> relogin userformDto null");
            return "relogin";
        }
        Index03Dto _index03Dto = new Index03Dto();
        model.addAttribute("userformDto",userformDto);
        _index03Dto.setJkey(ascode);
        Boolean result = service03.DeleteJpum(_index03Dto);
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

        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App03GanList01_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            index03Dto_S.setJbonsa_code(jbonsacode);
            index03Dto_S.setJpb_gubn(jpbgubn);
            _index03List = service03.GetGanListBonsa01(index03Dto_S);
            model.addAttribute("index03GanList01",_index03List);

        } catch (Exception ex) {
            log.info("jbonsacode =====>" + jbonsacode);
            log.info("jpbgubn =====>" + jpbgubn);
            log.info("App03GanList01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index03List;
    }

    //간편주문 리스트 02 등록
    @GetMapping(value="/index03/ganlist02")
    public Object App03GanList02_index(@RequestParam("jpbgubn") String jpbgubn,
                                       @RequestParam("jbonsacode") String jbonsacode,
                                       @RequestParam("jbonsacode2") String jbonsacode2,
                                       @RequestParam("jmodelcode") String jmodelcode,
                                       @RequestParam("flag") String flag,
                                       Model model, HttpServletRequest request) throws Exception{
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App03GanList02_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            index03Dto_S.setJpb_gubn(jpbgubn);
            index03Dto_S.setJbonsa_code(jbonsacode);
            index03Dto_S.setJbonsa_code2(jbonsacode2);
            index03Dto_S.setJmodel_code(jmodelcode);
            index03Dto_S.setFrdate("20000101");
            index03Dto_S.setTodate(getToDate());
            _index03List = service03.GetGanListBonsa02(index03Dto_S);
            model.addAttribute("index03GanList02",_index03List);

        } catch (Exception ex) {
            log.info("jpbgubn =====>" + jpbgubn);
            log.info("jbonsacode =====>" + jbonsacode);
            log.info("jbonsacode2 =====>" + jbonsacode2);
            log.info("jmodelcode =====>" + jmodelcode);
            log.info("App03GanList01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index03List;
    }

    //간편주문 리스트 03 등록
    @GetMapping(value="/index03/ganlist03")
    public Object App03GanList03_index(@RequestParam("jpbgubn") String jpbgubn,
                                       @RequestParam("flag") String flag,
                                       Model model, HttpServletRequest request) throws Exception{
        Index03Dto index03Dto_S = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App03GanList03_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            index03Dto_S.setJpb_gubn(jpbgubn);
            _index03List = service03.GetJBonsaCodeList(index03Dto_S);
            model.addAttribute("index03GanList03",_index03List);

        } catch (Exception ex) {
            log.info("App03GanList03_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index03List;
    }

    @RequestMapping(value="/index04/save")
    public String index04Save(
             @RequestParam("frdate") String frdate
            ,@RequestParam( value =  "jcode[]") List<String> jcode
            ,@RequestParam( value =  "jqty[]") List<String> jqty
            , Model model
            , HttpServletRequest request){

        try {
            Index04Dto _index04Dto = new Index04Dto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index04Save Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;

            Boolean result = false;
            if( jcode.size() > 0){
                for(int i = 0; i < jcode.size(); i++){
                    _index04Dto.setAcorp("00");
                    _index04Dto.setKey1(frdate);
                    _index04Dto.setJepm(jcode.get(i));
                    _index04Dto.setIjaego_su1(Integer.parseInt(jqty.get(i)));
                    _index04Dto.setJepm_size("00");
//                    log.info("frdate  =====>" + frdate);
//                    log.info("jcode   =====>" + jcode.get(i));
//                    log.info("jqty   =====>" + jqty.get(i));
                    String ls_acorp = "";
                    //일자별로 n개등록가능하므로 업데이트 막음.
                    //ls_acorp = service04.SelectJegoCheck(_index04Dto);
                    //if(ls_acorp == null ){
                        ls_acorp = GetMaxSeqIpgo(_index04Dto);
                        _index04Dto.setAcorp(ls_acorp);
                        result = service04.InsertJegoIpgo(_index04Dto);
                   // }else{
                   //     _index04Dto.setAcorp(ls_acorp);
                   //     result = service04.UpdateJegoIpgo(_index04Dto);
                   // }
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
                                  @RequestParam("acorp") String acorp,
                                  @RequestParam("jepm") String jepm,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("index04Delete Exception =====> relogin userformDto null");
            return "relogin";
        }
        Index04Dto _index04Dto = new Index04Dto();
        model.addAttribute("userformDto",userformDto);
        _index04Dto.setKey1(ipdate);
        _index04Dto.setAcorp(acorp);
        _index04Dto.setJepm(jepm);
        Boolean result = false;
        if(jepm.length() > 0){
            result = service04.DeleteJaegoIpgoAcorp(_index04Dto);
        }else{
            result = service04.DeleteJaegoIpgo(_index04Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }


    //재고실사 리스트
    @GetMapping(value="/index04/list")
    public Object App04List_index(@RequestParam("ipfrdate") String ipfrdate,
                                  @RequestParam("iptodate") String iptodate,
                                  @RequestParam("jpbgubn") String jpbgubn,
                                  @RequestParam("jkey") String jkey,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index04");
        Index04Dto _index04Dto = new Index04Dto();
        List<Index04Dto> _index04List = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App04List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            String year = ipfrdate.substring(0,4) ;
            String month = ipfrdate.substring(5,7) ;
            String day   = ipfrdate.substring(8,10) ;
            ipfrdate = year + month + day ;
            year = iptodate.substring(0,4) ;
            month = iptodate.substring(5,7) ;
            day   = iptodate.substring(8,10) ;
            iptodate = year + month + day ;
            _index04Dto.setFrdate(ipfrdate);
            _index04Dto.setTodate(iptodate);
            _index04Dto.setJpb_gubun(jpbgubn);
            _index04Dto.setJkey(jkey);
            _index04List = service04.SelectJegoIpgo(_index04Dto);
            model.addAttribute("index04List",_index04List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return _index04List;
    }

    //재고현황 리스트
    @GetMapping(value="/index04/jaegolist")
    public Object App04JaegoList_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고현항");
        CommDto.setMenuCode("index04");
        Index04Dto _index04Dto = new Index04Dto();
        List<Index04Dto> _index04List = new ArrayList<>();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App04JaegoList_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _index04Dto.setJkey(searchtxt);
            _index04Dto.setFrdate("2000-01-01");
            _index04Dto.setTodate(searchtxt);
            _index04List = service04.SelectJegoList(_index04Dto);
            model.addAttribute("index04List",_index04List);

        } catch (Exception ex) {
            log.info("App04JaegoList_index Exception =====>" + ex.toString());
        }

        return _index04List;
    }


    //그룹별 재고현황 리스트
    @GetMapping(value="/index04/jaegocustlist")
    public Object App04JaegoCustList_index(@RequestParam("searchtxt") String searchtxt,
                                           @RequestParam("jcustcd") String jcustcd,
                                           @RequestParam("todate") String todate,
                                           @RequestParam("jpbgubn") String jpbgubn,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고현항");
        CommDto.setMenuCode("index04");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App04JaegoCustList_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        Index03Dto _index03Dto = new Index03Dto();
        List<Index03Dto> _index03List = new ArrayList<>();
        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            if(jcustcd == null || jcustcd.equals("")){
                jcustcd = "%";
            }

            //index03Dto.setJcustomer_code(jcustcd);
            _index03Dto.setJpum(searchtxt);
            _index03Dto.setFrdate("20000101");
            _index03Dto.setJpb_gubn(jpbgubn);
            String year = todate.substring(0,4) ;
            String month = todate.substring(5,7) ;
            String day   = todate.substring(8,10) ;
            todate = year + month + day ;
            _index03Dto.setTodate(todate);
//            log.info("001 ->" + _index03Dto.getJpum());
//            log.info("002 ->" + _index03Dto.getFrdate());
//            log.info("003 ->" + _index03Dto.getTodate());
//            log.info("004 ->" + _index03Dto.getJpb_gubn());
            _index03List = service03.GetJpumCustJaegoList(_index03Dto);
            //log.info("004 ->" + _index03List);
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App04JaegoCustList_index Exception =====>" + ex.toString());
        }

        return _index03List;
    }


    @RequestMapping(value="/index14/save")
    public String index14Save(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("jbonsa") String jbonsa
            ,@RequestParam("jbonsa2") String jbonsa2
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
            if(userformDto == null){
                log.info("index14Save Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index03Dto _index03Dto = new Index03Dto();
            Index02Dto _index02Dto = new Index02Dto();
            IndexDa024Dto _index024Dto = new IndexDa024Dto();

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            Integer _ll_jqty = 0;
            frdate = year + month + day ;
            _index03Dto.setJmodel_code2(jmodel);
            _index03Dto.setJcolor_code2(jcolor);
            _index03Dto.setJbonsa_code(jbonsa);
            _index03Dto.setJbonsa_code2(jbonsa2);
            _index03Dto.setJpb_gubn(jpbgubn);
            _index03Dto.setFrdate("20000101");
            _index03Dto.setTodate(frdate);
            _index02Dto.setAcode(acode);

            _index02Dto = service02.GetCifListAcode(_index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            _index03Dto = service03.GetJpumOrderJkey(_index03Dto); //품목정보
            if(_index03Dto == null){
                log.info("index14Save 재고 없음 =====> index03Dto == null"  );
                log.info("jfrdate =====> " + frdate );
                log.info("jmodel =====> " + jmodel );
                log.info("jcolor =====> " + jcolor );
                log.info("jcolor =====> " + jcolor );
                log.info("jbonsa =====> " + jbonsa );
                log.info("jbonsa2 =====> " + jbonsa2 );
                log.info("jpbgubn =====> " + jpbgubn );
                return "error";
            }
            _ll_jqty = Integer.parseInt(_index03Dto.getJqty());
            if(_ll_jqty == 0 || _ll_jqty == null){
                log.info("index14Save 재고 없음 =====> _ll_jqty == 0"  );
                log.info("jfrdate =====> " + frdate );
                log.info("jkey =====> " + _index03Dto.getJkey() );
                log.info("jqty =====> " + _index03Dto.getJqty() );
                return "jaego";
            }
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setMisdate(frdate);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnum(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = service14.SelectCheckMisnumMkflag(indexDa023Dto);  //주문된 순번max 찾기
                if(ls_misnum == null){
                    ls_misnum = "0001";
                }else{
                    Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
                    ls_misnum = ll_misnum.toString();
                    if (ls_misnum.length() == 1){
                        ls_misnum = "000" + ls_misnum;
                    }else if(ls_misnum.length() == 2){
                        ls_misnum = "00" + ls_misnum;
                    }else {
                        ls_misnum = "0" + ls_misnum;
                    }
                }
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
//            indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            indexDa024Dto.setMisgubun(mflag);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            indexDa024Dto.setPcode(_index03Dto.getJkey());
            indexDa024Dto.setPname(_index03Dto.getJpum());
            indexDa024Dto.setPsize(_index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(jbonsa);
            indexDa024Dto.setPbonsa2(_index03Dto.getJbonsa_code2());
            indexDa024Dto.setPmodel(jmodel);
            indexDa024Dto.setPcolor(jcolor);
            indexDa024Dto.setCltcd(indexDa023Dto.getCltcd());

            //재고체크 ------------------------------------------


            //--------------------------------------------------
            String ls_seq = "";
//            log.info("ls_chknull ======>" + ls_chknull);
            //신규입력
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                //동일업체 동일일자 동일품목이 있는지 체크
                _index024Dto = service14.SelectCheckJpum024(indexDa024Dto);  //주문된 순번max 찾기
                if (_index024Dto != null){
                    ls_seq = _index024Dto.getSeq();
                    if (ls_seq.length() > 0 && ls_seq != null ){
                        Integer _ll_qty = _index024Dto.getQty() + 1;
                        Integer _ll_uamt = _index024Dto.getUamt();
                        Integer _ll_samt = 0 ;
                        Integer _ll_addamt = 0 ;
                        Integer _ll_amt = 0;
                        indexDa024Dto.setSeq(ls_seq);
                        indexDa024Dto.setQty(_ll_qty);
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            indexDa024Dto.setSamt(_ll_samt);
                            indexDa024Dto.setAddamt(_ll_addamt);
                            indexDa024Dto.setAmt(_ll_amt);
                        }else{
                            indexDa024Dto.setSamt(0);
                            indexDa024Dto.setAddamt(0);
                            indexDa024Dto.setAmt(0);
                        }
                        result = service14.UpdateDA024Qty(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                        return "success";
                    }else{
                        ls_seq = GetMaxSeq(frdate);
                    }
                }else{
                    ls_seq = GetMaxSeq(frdate);
                }
            }

            String ls_chulgoga = _index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setQty(1);
            indexDa024Dto.setUamt(ll_chulgoga);
            indexDa024Dto.setSamt(ll_chulgoga);
            indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {indexDa024Dto.setAddamt(ll_chulgoga / 10);};
            indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            indexDa024Dto.setIndate(getToDate());
            indexDa024Dto.setInperid(userformDto.getPerid());
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
            ,@RequestParam("jbonsa2") String jbonsa2
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
            if(userformDto == null){
                log.info("index14SaveWish Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index03Dto _index03Dto = new Index03Dto();
            Index02Dto _index02Dto = new Index02Dto();
            IndexDa024Dto _index024Dto = new IndexDa024Dto();

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            _index03Dto.setJmodel_code2(jmodel);
            _index03Dto.setJcolor_code2(jcolor);
            _index03Dto.setJbonsa_code(jbonsa);
            _index03Dto.setJbonsa_code2(jbonsa2);
            _index03Dto.setJpb_gubn(jpbgubn);
            _index02Dto.setAcode(acode);
            _index02Dto = service02.GetCifListAcode(_index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            _index03Dto = service03.GetJpumOrderJkey(_index03Dto); //품목정보
            if(_index03Dto == null){
                log.info("error Exception =====> WISH GetJpumOrderJkey NULL" );
                return "error";
            }

            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            indexDa023Dto.setMisgubun(mflag);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnumWish(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = service14.SelectCheckMisnumWishMkflag(indexDa023Dto);  //주문된 순번max 찾기
                if(ls_misnum == null){
                    ls_misnum = "0001";
                }else{
                    Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
                    ls_misnum = ll_misnum.toString();
                    if (ls_misnum.length() == 1){
                        ls_misnum = "000" + ls_misnum;
                    }else if(ls_misnum.length() == 2){
                        ls_misnum = "00" + ls_misnum;
                    }else {
                        ls_misnum = "0" + ls_misnum;
                    }
                }
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
//            indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            indexDa024Dto.setMisgubun(mflag);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            indexDa024Dto.setPcode(_index03Dto.getJkey());
            indexDa024Dto.setPname(_index03Dto.getJpum());
            indexDa024Dto.setPsize(_index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(jbonsa);
            indexDa024Dto.setPbonsa2(_index03Dto.getJbonsa_code2());
            indexDa024Dto.setPmodel(jmodel);
            indexDa024Dto.setPcolor(jcolor);
            String ls_seq = "";
            //신규입력
//            log.info("ls_chknull ======>" + ls_chknull);
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                //동일업체 동일일자 동일품목이 있는지 체크
                _index024Dto = service14.SelectCheckJpum026(indexDa024Dto);  //주문된 순번max 찾기
                if(_index024Dto != null){
                    ls_seq = _index024Dto.getSeq();
                    if (ls_seq.length() > 0 && ls_seq != null ){
                        Integer _ll_qty = _index024Dto.getQty() + 1;
                        Integer _ll_uamt = _index024Dto.getUamt();
                        Integer _ll_samt = 0 ;
                        Integer _ll_addamt = 0 ;
                        Integer _ll_amt = 0;
                        indexDa024Dto.setSeq(ls_seq);
                        indexDa024Dto.setQty(_ll_qty);
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            indexDa024Dto.setSamt(_ll_samt);
                            indexDa024Dto.setAddamt(_ll_addamt);
                            indexDa024Dto.setAmt(_ll_amt);
                        }else{
                            indexDa024Dto.setSamt(0);
                            indexDa024Dto.setAddamt(0);
                            indexDa024Dto.setAmt(0);
                        }
                        result = service14.UpdateDA026Qty(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                        return "success";
                    }else{
                        ls_seq = GetMaxSeqWish(frdate);
                    }
                }else{
                    ls_seq = GetMaxSeqWish(frdate);
                }
            }

            String ls_chulgoga = _index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setQty(1);
            indexDa024Dto.setUamt(ll_chulgoga);
            indexDa024Dto.setSamt(ll_chulgoga);
            indexDa024Dto.setCltcd(indexDa023Dto.getCltcd());
            indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {indexDa024Dto.setAddamt(ll_chulgoga / 10);};
            indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            indexDa024Dto.setIndate(getToDate());
            indexDa024Dto.setInperid(userformDto.getPerid());
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
            ,@RequestParam("jqty") Integer jqty
            ,@RequestParam("jremark") String jremark
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("misdate") String misdate
            ,@RequestParam("misnum") String misnum
            ,@RequestParam("seq") String seq
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            ,@RequestParam( value =  "misqty[]") List<String> misqty
            , Model model
            , HttpServletRequest request){

        try {

            //Index03Dto index03Dto_S = new Index03Dto();

            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCust Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            Index03Dto _index03Dto = new Index03Dto();
            Index02Dto _index02Dto = new Index02Dto();

            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            Integer _ll_jqty = 0;
            frdate = year + month + day ;
            _index02Dto.setAcode(acode);
            _index03Dto.setJkey(jkey);
            _index03Dto.setFrdate("20000101");
            _index03Dto.setTodate(frdate);
            _index02Dto = service02.GetCifListAcode(_index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            _index03Dto = service03.GetJpumOrderJkey02(_index03Dto); //품목
            if(_index03Dto == null){
                log.info("error Exception index14SaveCust =====> _index03Dto NULL" );
                log.info("jfrdate =====> " + frdate );
                log.info("jkey =====> " + jkey);
                log.info("jqty =====> " + _index03Dto.getJqty() );
                return "error";
            }
            _ll_jqty = Integer.parseInt(_index03Dto.getJqty());
            if(_ll_jqty == 0 || _ll_jqty == null){
                log.info("재고 없음 index14SaveCust=====> " + _ll_jqty.toString() );
                return "jaego";
            }

            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            //비고업데이트
            if (!misdate.equals("0000")){
                year = misdate.substring(0,4) ;
                month = misdate.substring(5,7) ;
                day   = misdate.substring(8,10) ;
                misdate = year + month + day ;
                indexDa024Dto.setQty(jqty);
                indexDa024Dto.setMisdate(misdate);
                indexDa024Dto.setMisnum(misnum);
                indexDa024Dto.setSeq(seq);
                indexDa024Dto.setCltcd(acode);
                indexDa024Dto.setMisgubun(mflag);
                switch (mflag){
                    case "AA" :
                        indexDa024Dto.setRemarkaa(jremark);
                        result = service14.UpdateDA024rkaa(indexDa024Dto);
                        break;
                    case "BB":
                        indexDa024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA024rkbb(indexDa024Dto);
                        break;
                    case "CC":
                        indexDa024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA024rkbb(indexDa024Dto);
                        break;
                    default:
                        break;
                }
                if (!result){
                    return "error";
                }
                //수량업데이트
                if( misdateArr.size() > 0){
                    for(int i = 0; i < misdateArr.size(); i++){
                        year = misdateArr.get(i).substring(0,4);
                        month = misdateArr.get(i).substring(5,7);
                        day = misdateArr.get(i).substring(8,10);
                        String ls_misdate = year + month + day ;
                        indexDa024Dto.setMisdate(ls_misdate);
                        indexDa024Dto.setMisnum(misnumArr.get(i));
                        indexDa024Dto.setSeq(seqArr.get(i));
                        indexDa024Dto.setQty(Integer.parseInt(misqty.get(i)));

                        _indexDa024Dto.setMisdate(indexDa024Dto.getMisdate());
                        _indexDa024Dto.setMisnum(indexDa024Dto.getMisnum());
                        _indexDa024Dto.setSeq(indexDa024Dto.getSeq());
                        _indexDa024Dto.setCltcd(indexDa024Dto.getCltcd());
                        _indexDa024Dto.setMisgubun(indexDa024Dto.getMisgubun());
                        _indexDa024Dto = service14.SelectDa024Detail(_indexDa024Dto);
                        Integer _ll_uamt = _indexDa024Dto.getUamt();
                        Integer _ll_samt = 0;
                        Integer _ll_addamt = 0;
                        Integer _ll_amt = 0;
                        Integer _ll_qty = indexDa024Dto.getQty();
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            indexDa024Dto.setSamt(_ll_samt);
                            indexDa024Dto.setAddamt(_ll_addamt);
                            indexDa024Dto.setAmt(_ll_amt);
                        }else{
                            indexDa024Dto.setSamt(0);
                            indexDa024Dto.setAddamt(0);
                            indexDa024Dto.setAmt(0);
                        }
                        result = service14.UpdateDA024Qty(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                    }
                }
                return "success";
            }
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setYyyymm(year + month);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnum(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = service14.SelectCheckMisnumMkflag(indexDa023Dto);  //주문된 순번max 찾기
                if(ls_misnum == null){
                    ls_misnum = "0001";
                }else{
                    Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
                    ls_misnum = ll_misnum.toString();
                    if (ls_misnum.length() == 1){
                        ls_misnum = "000" + ls_misnum;
                    }else if(ls_misnum.length() == 2){
                        ls_misnum = "00" + ls_misnum;
                    }else {
                        ls_misnum = "0" + ls_misnum;
                    }
                }
            }else{
                ls_misnum = ls_chknull;
            }
            indexDa023Dto.setMisnum(ls_misnum);

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
            indexDa023Dto.setBigo(jremark);
            indexDa023Dto.setRemark(jremark);
//            indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeq(frdate);
            }

            String ls_chulgoga = _index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            indexDa024Dto.setSeq(ls_seq);
            indexDa024Dto.setMisdate(frdate);
            indexDa024Dto.setMisnum(ls_misnum);
            indexDa024Dto.setMisgubun(mflag);
            indexDa024Dto.setPcode(_index03Dto.getJkey());
            indexDa024Dto.setPname(_index03Dto.getJpum());
            indexDa024Dto.setPsize(_index03Dto.getJgugek());
            indexDa024Dto.setPbonsa(_index03Dto.getJbonsa_code());
            indexDa024Dto.setPmodel(_index03Dto.getJmodel_code());
            indexDa024Dto.setPcolor(_index03Dto.getJcolor_code());
            indexDa024Dto.setQty(jqty);
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
            indexDa024Dto.setInperid(userformDto.getPerid());
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


    @RequestMapping(value="/index14/savecustdel")
    public String index14SaveCustDel(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("misdate") String misdate
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            , Model model
            , HttpServletRequest request){

        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCustDel Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index02Dto _index02Dto = new Index02Dto();
            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            _index02Dto.setAcode(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            //장바구니 삭제
            if (!misdate.equals("0000")){
                if( misdateArr.size() > 0){
                    for(int i = 0; i < misdateArr.size(); i++){
                        year = misdateArr.get(i).substring(0,4);
                        month = misdateArr.get(i).substring(5,7);
                        day = misdateArr.get(i).substring(8,10);
                        String ls_misdate = year + month + day ;
                        _index02Dto.setAcode(acode);
                        indexDa024Dto.setCltcd(acode);
                        indexDa024Dto.setMisgubun(mflag);
                        indexDa024Dto.setMisdate(ls_misdate);
                        indexDa024Dto.setMisnum(misnumArr.get(i));
                        indexDa024Dto.setSeq(seqArr.get(i));
                        result = service14.DeleteDA024Mkflag(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                    }
                }
                result = service14.DeleteDA023(indexDa024Dto);
                if (!result){
                   //return "error";
                }
                return "success";
            }

        }catch (IllegalStateException e){
            model.addAttribute("index14Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index14/savecustwishdel")
    public String index14SaveCustWisDel(
            @RequestParam("ipdate") String frdate
            ,@RequestParam("acode") String acode
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("misdate") String misdate
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            , Model model
            , HttpServletRequest request){

        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCustWisDel Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index02Dto _index02Dto = new Index02Dto();
            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            _index02Dto.setAcode(acode);
            indexDa023Dto.setMisgubun(mflag);
            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            //장바구니 삭제
            if (!misdate.equals("0000")){
                if( misdateArr.size() > 0){
                    for(int i = 0; i < misdateArr.size(); i++){
                        year = misdateArr.get(i).substring(0,4);
                        month = misdateArr.get(i).substring(5,7);
                        day = misdateArr.get(i).substring(8,10);
                        String ls_misdate = year + month + day ;
                        indexDa024Dto.setCltcd(acode);
                        indexDa024Dto.setMisgubun(mflag);
                        indexDa024Dto.setMisdate(ls_misdate);
                        indexDa024Dto.setMisnum(misnumArr.get(i));
                        indexDa024Dto.setSeq(seqArr.get(i));
                        result = service14.DeleteDA026Mkflag(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                    }
                }
                result = service14.DeleteDA025(indexDa024Dto);
                if (!result){
                    return "error";
                }
                return "success";
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
        if(userformDto == null){
            log.info("App14Del_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
                //return "error";
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
        if(userformDto == null){
            log.info("App14DelWish_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            ,@RequestParam("jqty") Integer jqty
            ,@RequestParam("jremark") String jremark
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("misdate") String misdate
            ,@RequestParam("misnum") String misnum
            ,@RequestParam("seq") String seq
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            ,@RequestParam( value =  "misqty[]") List<String> misqty
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCustWish Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index03Dto index03Dto_S = new Index03Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            Index02Dto _index02Dto = new Index02Dto();
            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            _index02Dto.setAcode(acode);
            index03Dto_S.setJkey(jkey);
            _index02Dto = service02.GetCifListAcode(_index02Dto);  //거래처정보
            index02BonsaDto = service02.GetCifBonsa(index02BonsaDto);
            index03Dto_S = service03.GetJpumOrderJkey02(index03Dto_S); //품목

            //비고업데이트
            if (!misdate.equals("0000")){
                year = misdate.substring(0,4) ;
                month = misdate.substring(5,7) ;
                day   = misdate.substring(8,10) ;
                misdate = year + month + day ;
                indexDa024Dto.setQty(jqty);
                indexDa024Dto.setMisdate(misdate);
                indexDa024Dto.setMisnum(misnum);
                indexDa024Dto.setSeq(seq);
                indexDa024Dto.setCltcd(acode);
                indexDa024Dto.setMisgubun(mflag);
                switch (mflag){
                    case "AA" :
                        indexDa024Dto.setRemarkaa(jremark);
                        result = service14.UpdateDA026rkaa(indexDa024Dto);
                        break;
                    case "BB":
                        indexDa024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA026rkbb(indexDa024Dto);
                        break;
                    case "CC":
                        indexDa024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA026rkbb(indexDa024Dto);
                        break;
                    default:
                        break;
                }
                if (!result){
                    return "error";
                }
                //수량업데이트
                if( misdateArr.size() > 0){
                    for(int i = 0; i < misdateArr.size(); i++){
                        year = misdateArr.get(i).substring(0,4);
                        month = misdateArr.get(i).substring(5,7);
                        day = misdateArr.get(i).substring(8,10);
                        String ls_misdate = year + month + day ;
                        indexDa024Dto.setMisdate(ls_misdate);
                        indexDa024Dto.setMisnum(misnumArr.get(i));
                        indexDa024Dto.setSeq(seqArr.get(i));
                        indexDa024Dto.setQty(Integer.parseInt(misqty.get(i)));


                        _indexDa024Dto.setMisdate(indexDa024Dto.getMisdate());
                        _indexDa024Dto.setMisnum(indexDa024Dto.getMisnum());
                        _indexDa024Dto.setSeq(indexDa024Dto.getSeq());
                        _indexDa024Dto.setCltcd(indexDa024Dto.getCltcd());
                        _indexDa024Dto.setMisgubun(indexDa024Dto.getMisgubun());

                        _indexDa024Dto = service14.SelectDa026Detail02(_indexDa024Dto);
                        Integer _ll_uamt = _indexDa024Dto.getUamt();
                        Integer _ll_samt = 0;
                        Integer _ll_addamt = 0;
                        Integer _ll_amt = 0;
                        Integer _ll_qty = indexDa024Dto.getQty();
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            indexDa024Dto.setSamt(_ll_samt);
                            indexDa024Dto.setAddamt(_ll_addamt);
                            indexDa024Dto.setAmt(_ll_amt);
                        }else{
                            indexDa024Dto.setSamt(0);
                            indexDa024Dto.setAddamt(0);
                            indexDa024Dto.setAmt(0);
                        }

                        result = service14.UpdateDA026Qty(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                    }
                }
                return "success";
            }


            indexDa023Dto.setCltcd(acode);
            indexDa023Dto.setMisdate(frdate);
            indexDa023Dto.setMisgubun(mflag);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnumWish(indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = service14.SelectCheckMisnumWishMkflag(indexDa023Dto);  //주문된 순번max 찾기
                if(ls_misnum == null){
                    ls_misnum = "0001";
                }else{
                    Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
                    ls_misnum = ll_misnum.toString();
                    if (ls_misnum.length() == 1){
                        ls_misnum = "000" + ls_misnum;
                    }else if(ls_misnum.length() == 2){
                        ls_misnum = "00" + ls_misnum;
                    }else {
                        ls_misnum = "0" + ls_misnum;
                    }
                }
            }else{
                ls_misnum = ls_chknull;
            }
            indexDa023Dto.setMisnum(ls_misnum);
            indexDa023Dto.setCltcd(acode);
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
            indexDa023Dto.setBigo(jremark);
            indexDa023Dto.setRemark(jremark);
//            indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            indexDa023Dto.setGubun("");
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeqWish(frdate);
            }

            String ls_chulgoga = index03Dto_S.getJchgoga0();
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
            indexDa024Dto.setPcode(index03Dto_S.getJkey());
            indexDa024Dto.setPname(index03Dto_S.getJpum());
            indexDa024Dto.setPsize(index03Dto_S.getJgugek());
            indexDa024Dto.setPbonsa(index03Dto_S.getJbonsa_code());
            indexDa024Dto.setPmodel(index03Dto_S.getJmodel_code());
            indexDa024Dto.setPcolor(index03Dto_S.getJcolor_code());
            indexDa024Dto.setQty(jqty);
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
            indexDa024Dto.setInperid(userformDto.getPerid());
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
                                  @RequestParam("devflag") String devflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("misgubun") String misgubun,
                                  @RequestParam("makflag") String makflag,
                                  @RequestParam("mflag") String mflag,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setDevflag(devflag);
            indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun(misgubun);
                    mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            indexDa024Dto.setPerid(perid);
            //indexDa024Dto.setMisgubun(mflag);
            //log.info("misgubun =====>" + misgubun);
            if(acode.equals("%")){
                indexDa024ListDto = service14.SelectDa024ListLike(indexDa024Dto);
            }else{
                indexDa024ListDto = service14.SelectDa024List(indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트 장바구니
    @GetMapping(value="/index14/listjang")
    public Object App14Listjang_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("devflag") String devflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("misgubun") String misgubun,
                                  @RequestParam("makflag") String makflag,
                                  @RequestParam("mflag") String mflag,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setDevflag(devflag);
            indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun(misgubun);
                    mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            indexDa024Dto.setPerid(perid);
            indexDa024Dto.setMisgubun(mflag);
            //log.info("misgubun =====>" + misgubun);
            if(acode.equals("%")){
                indexDa024ListDto = service14.SelectDa024ListLike(indexDa024Dto);
            }else{
                indexDa024ListDto = service14.SelectDa024List(indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황 리스트
    @GetMapping(value="/index14/listdel")
    public Object App14ListDel_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("jcode") String jcode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("misgubun") String misgubun,
                                  @RequestParam("makflag") String makflag,
                                  @RequestParam("mflag") String mflag,
                                  @RequestParam("jpbgubn") String jpbgubn,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문삭제현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
            indexDa024Dto.setPcode(jcode);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            indexDa024Dto.setPerid(perid);
            //indexDa024Dto.setMisgubun(mflag);
            //log.info("misgubun =====>" + misgubun);
            indexDa024ListDto = service14.SelectDa024ListDel(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황 리스트
    @GetMapping(value="/index14/listprt")
    public Object App14ListPrt_index(@RequestParam(value = "misdatearr[]") List<String> misdatearr
                                    ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
                                    ,@RequestParam( value =  "seqarr[]") List<String> seqarr
                                    ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
                                    ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
                                    ,Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListPrt_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            HashMap hm = new HashMap();
            String[] itemString =new String[misdatearr.size()];
            String ls_tempItem = "";
            Integer ll_count = 0;
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    if(ls_tempItem.equals( ls_misdate + misnumarr.get(i)  + cltcdarr.get(i))){
                        continue;
                    }
                    itemString[ll_count] = ls_misdate + misnumarr.get(i)  + cltcdarr.get(i);  //
                    ll_count++;
                    ls_tempItem = ls_misdate  + misnumarr.get(i) + cltcdarr.get(i);  //
//                    log.info("itemString =====>" + ls_misdate + misnumarr.get(i) + seqarr.get(i) + cltcdarr.get(i));
                }
                hm.put("itemcdArr", itemString);
                indexDa024ListDto = service14.SelectDa024ListPrtGroup(hm);

            }

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황 리스트
    @GetMapping(value="/index14/listdev")
    public Object App14ListDev_index(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListDev_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            HashMap hm = new HashMap();
            String[] itemString =new String[misdatearr.size()];
            String ls_tempItem = "";
            Integer ll_count = 0;
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    if(ls_tempItem.equals( ls_misdate + misnumarr.get(i)  + cltcdarr.get(i))){
                        continue;
                    }
                    itemString[ll_count] = ls_misdate + misnumarr.get(i)  + cltcdarr.get(i);
                    ll_count++;
                    ls_tempItem = ls_misdate + misnumarr.get(i)  + cltcdarr.get(i);
//                    log.info("itemString =====>" + ls_misdate + misnumarr.get(i) + seqarr.get(i) + cltcdarr.get(i));
                }
                hm.put("itemcdArr", itemString);
                indexDa024ListDto = service14.SelectDa024ListDevGroup(hm);

            }

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황 리스트
    @GetMapping(value="/index14/listprtdetail")
    public Object App14ListPrtDetail_index(@RequestParam("misdatearr") String misdatearr,
                                           @RequestParam("misnumarr") String misnumarr,
                                           @RequestParam("cltcdarr") String cltcdarr,
                                           @RequestParam("gubunarr") String gubunarr,
            Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListPrtDetail_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {

            indexDa024Dto.setMisdate(misdatearr);
            indexDa024Dto.setMisnum(misnumarr);
            indexDa024Dto.setCltcd(cltcdarr);
//            log.info("itemString =====>" + misdatearr + '/' + misnumarr + '/' + cltcdarr);
            indexDa024ListDto = service14.SelectDa024ListPrt(indexDa024Dto);

        } catch (Exception ex) {
            log.info("App14ListPrtDetail_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황상세 리스트
    @GetMapping(value="/index14/listperid")
    public Object App14ListPerid_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("mflag") String mflag,
                                  @RequestParam("makflag") String makflag,
                                  @RequestParam("jpbgubn") String jpbgubn,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListPerid_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
//            log.info("frdate =====>" + frdate);
//            log.info("fixflag =====>" + fixflag);
//            log.info("mflag =====>" + mflag);
//            log.info("perid =====>" + perid);
            indexDa024ListDto = service14.SelectDa024ListPerid(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황집계 리스트
    @GetMapping(value="/index14/listperidgroup")
    public Object App14ListPeridGroup_index(@RequestParam("frdate") String frdate,
                                       @RequestParam("todate") String todate,
                                       @RequestParam("acode") String acode,
                                       @RequestParam("fixflag") String fixflag,
                                       @RequestParam("perid") String perid,
                                       @RequestParam("mflag") String mflag,
                                       @RequestParam("makflag") String makflag,
                                       @RequestParam("jpbgubn") String jpbgubn,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListPeridGroup_index  =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
//            log.info("frdate =====>" + frdate);
//            log.info("fixflag =====>" + fixflag);
//            log.info("mflag =====>" + mflag);
//            log.info("perid =====>" + perid);
            indexDa024ListDto = service14.SelectDa024ListPeridGroup(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/listcltcd")
    public Object App14ListCltcd_index(@RequestParam("frdate") String frdate,
                                       @RequestParam("todate") String todate,
                                       @RequestParam("acode") String acode,
                                       @RequestParam("fixflag") String fixflag,
                                       @RequestParam("perid") String perid,
                                       @RequestParam("mflag") String mflag,
                                       @RequestParam("makflag") String makflag,
                                       @RequestParam("jpbgubn") String jpbgubn,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListCltcd_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
            indexDa024ListDto = service14.SelectDa024ListCltcd(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 거래처별주문현황 리스트
    @GetMapping(value="/index14/listcltcdgroup")
    public Object App14ListCltcdGroup_index(@RequestParam("frdate") String frdate,
                                       @RequestParam("todate") String todate,
                                       @RequestParam("acode") String acode,
                                       @RequestParam("fixflag") String fixflag,
                                       @RequestParam("perid") String perid,
                                       @RequestParam("mflag") String mflag,
                                       @RequestParam("makflag") String makflag,
                                       @RequestParam("jpbgubn") String jpbgubn,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListCltcdGroup_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
            indexDa024ListDto = service14.SelectDa024ListCltcdGroup(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/listjkey")
    public Object App14ListJkey_index(@RequestParam("frdate") String frdate,
                                       @RequestParam("todate") String todate,
                                       @RequestParam("acode") String acode,
                                       @RequestParam("jkey") String jkey,
                                       @RequestParam("fixflag") String fixflag,
                                       @RequestParam("perid") String perid,
                                       @RequestParam("mflag") String mflag,
                                       @RequestParam("makflag") String makflag,
                                      @RequestParam("jpbgubn") String jpbgubn,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListJkey_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setPcode(jkey);
            indexDa024Dto.setJpbgubn(jpbgubn);
//            log.info("jpbgubn 111=====>" + jpbgubn);
            indexDa024ListDto = service14.SelectDa024ListJpum(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 품목별집계 리스트
    @GetMapping(value="/index14/listjkeygroup")
    public Object App14ListJkeyGroup_index(@RequestParam("frdate") String frdate,
                                      @RequestParam("todate") String todate,
                                      @RequestParam("acode") String acode,
                                      @RequestParam("jkey") String jkey,
                                      @RequestParam("fixflag") String fixflag,
                                      @RequestParam("perid") String perid,
                                      @RequestParam("mflag") String mflag,
                                      @RequestParam("makflag") String makflag,
                                      @RequestParam("jpbgubn") String jpbgubn,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListJkeyGroup_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setPcode(jkey);
            indexDa024Dto.setJpbgubn(jpbgubn);
            indexDa024ListDto = service14.SelectDa024ListJpumGroup(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListJkeyGroup_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //지역별현황 시도 품목별집계 리스트
    @GetMapping(value="/index14/listsido")
    public Object App14ListSido_index(@RequestParam("frdate") String frdate,
                                           @RequestParam("todate") String todate,
                                           @RequestParam("acode") String acode,
                                           @RequestParam("fixflag") String fixflag,
                                           @RequestParam("perid") String perid,
                                           @RequestParam("mflag") String mflag,
                                           @RequestParam("makflag") String makflag,
                                           @RequestParam("jpbgubn") String jpbgubn,
                                           Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>지역별통계");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListSido_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
            indexDa024ListDto = service14.SelectDa024ListJpumArea(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListSido_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //지역별현황 시도 품목별집계 리스트
    @GetMapping(value="/index14/listgugun")
    public Object App14ListGugun_index(@RequestParam("frdate") String frdate,
                                      @RequestParam("todate") String todate,
                                      @RequestParam("acode") String acode,
                                      @RequestParam("fixflag") String fixflag,
                                      @RequestParam("perid") String perid,
                                      @RequestParam("mflag") String mflag,
                                      @RequestParam("makflag") String makflag,
                                       @RequestParam("jpbgubn") String jpbgubn,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>지역별통계");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListGugun_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
            indexDa024ListDto = service14.SelectDa024ListJpumAreaGugun(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListGugun_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/listwish")
    public Object App14ListWish_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("jcode") String jcode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("misgubun") String misgubun,
                                  @RequestParam("makflag") String makflag,
                                  @RequestParam("mflag") String mflag,
                                  @RequestParam("jpbgubn") String jpbgubn,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListWish_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
            indexDa024Dto.setPcode(jcode);
            indexDa024Dto.setJpbgubn(jpbgubn);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun(misgubun);
                   //mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            //indexDa024Dto.setMisgubun(mflag);
            //log.info("jpbgubn Exception =====>" + indexDa024Dto.getJpbgubn());
            if(acode.equals("%")){
                indexDa024ListDto = service14.SelectDa026ListLike(indexDa024Dto);
            }else{
                indexDa024ListDto = service14.SelectDa026List(indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListWish_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트 장바구니
    @GetMapping(value="/index14/listwishjang")
    public Object App14ListWishjang_index(@RequestParam("frdate") String frdate,
                                      @RequestParam("todate") String todate,
                                      @RequestParam("acode") String acode,
                                      @RequestParam("jcode") String jcode,
                                      @RequestParam("fixflag") String fixflag,
                                      @RequestParam("perid") String perid,
                                      @RequestParam("misgubun") String misgubun,
                                      @RequestParam("makflag") String makflag,
                                      @RequestParam("mflag") String mflag,
                                      @RequestParam("jpbgubn") String jpbgubn,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListWish_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
            indexDa024Dto.setPcode(jcode);
            indexDa024Dto.setJpbgubn(jpbgubn);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun(misgubun);
                    //mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            indexDa024Dto.setMisgubun(mflag);
            //log.info("jpbgubn Exception =====>" + indexDa024Dto.getJpbgubn());
            if(acode.equals("%")){
                indexDa024ListDto = service14.SelectDa026ListLike(indexDa024Dto);
            }else{
                indexDa024ListDto = service14.SelectDa026List(indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListWish_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황 리스트
    @GetMapping(value="/index14/listwishdel")
    public Object App14ListWishDel_index(@RequestParam("frdate") String frdate,
                                      @RequestParam("todate") String todate,
                                      @RequestParam("acode") String acode,
                                      @RequestParam("jcode") String jcode,
                                      @RequestParam("fixflag") String fixflag,
                                      @RequestParam("perid") String perid,
                                      @RequestParam("misgubun") String misgubun,
                                      @RequestParam("makflag") String makflag,
                                      @RequestParam("mflag") String mflag,
                                      @RequestParam("jpbgubn") String jpbgubn,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약삭제현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListWish_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
            indexDa024Dto.setPcode(jcode);
            indexDa024Dto.setJpbgubn("%");
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    //mflag = "%";
                    break;
                case "BB":
                    indexDa024Dto.setPerid(perid);
                    indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    indexDa024Dto.setPerid(userformDto.getPerid());
                    indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            //indexDa024Dto.setMisgubun(mflag);
//            log.info("Misgubun Exception =====>" + indexDa024Dto.getMisgubun());
            indexDa024ListDto = service14.SelectDa026ListDel(indexDa024Dto);

            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListWish_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문현황 리스트
    @GetMapping(value="/index14/listwishperid")
    public Object App14ListWishPerid_index(@RequestParam("frdate") String frdate,
                                      @RequestParam("todate") String todate,
                                      @RequestParam("acode") String acode,
                                      @RequestParam("fixflag") String fixflag,
                                      @RequestParam("perid") String perid,
                                      @RequestParam("mflag") String mflag,
                                      @RequestParam("makflag") String makflag,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListWishPerid_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024ListDto = service14.SelectDa026ListPerid(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListWish_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/listwishcltcd")
    public Object App14ListWishCltcd_index(@RequestParam("frdate") String frdate,
                                           @RequestParam("todate") String todate,
                                           @RequestParam("acode") String acode,
                                           @RequestParam("fixflag") String fixflag,
                                           @RequestParam("perid") String perid,
                                           @RequestParam("mflag") String mflag,
                                           @RequestParam("makflag") String makflag,
                                           @RequestParam("jpbgubn") String jpbgubn,
                                           Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListWishCltcd_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
            indexDa024ListDto = service14.SelectDa026ListCltcd(indexDa024Dto);
            model.addAttribute("indexDa024ListDto",indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListWish_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/listwishjkey")
    public Object App14ListWishJkey_index(@RequestParam("frdate") String frdate,
                                           @RequestParam("todate") String todate,
                                           @RequestParam("acode") String acode,
                                           @RequestParam("jkey") String jkey,
                                           @RequestParam("fixflag") String fixflag,
                                           @RequestParam("perid") String perid,
                                           @RequestParam("mflag") String mflag,
                                           @RequestParam("makflag") String makflag,
                                          @RequestParam("jpbgubn") String jpbgubn,
                                           Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>예약현황");
        CommDto.setMenuCode("index14wish");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App14ListWishJkey_index Exception =====> relogin userformDto null");
            return "relogin";
        }
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
            indexDa024Dto.setPcode(jkey);
            indexDa024Dto.setFixflag(fixflag);
            indexDa024Dto.setMakflag(makflag);
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
            indexDa024Dto.setJpbgubn(jpbgubn);
            log.info("jpbgubn 222=====>" + jpbgubn);
            indexDa024ListDto = service14.SelectDa026ListJpum(indexDa024Dto);
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
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam( value =  "fixflagarr[]") List<String> fixflagarr
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    indexDa024Dto.setMisdate(ls_misdate);
                    indexDa024Dto.setMisnum(misnumarr.get(i));
                    indexDa024Dto.setSeq(seqarr.get(i));
                    indexDa024Dto.setCltcd(cltcdarr.get(i));
                    indexDa024Dto.setMisgubun(gubunarr.get(i));
                    if (mflag.equals("AA")){
                        indexDa024Dto.setFixflag("1");
                        result = service14.UpdateDA024(indexDa024Dto);
                    }else{
                        indexDa024Dto.setDevflag("1");
                        result = service14.UpdateDA024Dev(indexDa024Dto);
                    }

                    //출력(확정)취소는 없앰. 7.23
//                    if(fixflagarr.get(i).equals("0")){
//                        indexDa024Dto.setFixflag("1");
//                    }else{
//                        indexDa024Dto.setFixflag("0");
//                    }
                    if (!result){
                        return "error";
                    }
                }

            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index16/modify")
    public String index16Modify(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam( value =  "qtyarr[]") List<Integer> qtyarr
            ,@RequestParam( value =  "uamtarr[]") List<Integer> uamtarr
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;

            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    indexDa024Dto.setMisdate(ls_misdate);
                    indexDa024Dto.setMisnum(misnumarr.get(i));
                    indexDa024Dto.setSeq(seqarr.get(i));
                    indexDa024Dto.setCltcd(cltcdarr.get(i));
                    indexDa024Dto.setMisgubun(gubunarr.get(i));
                    indexDa024Dto.setQty(qtyarr.get(i));
                    indexDa024Dto.setUamt(uamtarr.get(i));
                    //_indexDa024Dto = service14.SelectDa024Detail(indexDa024Dto);
                    Integer _ll_uamt = indexDa024Dto.getUamt();
                    Integer _ll_samt = 0;
                    Integer _ll_addamt = 0;
                    Integer _ll_amt = 0;
                    Integer _ll_qty = indexDa024Dto.getQty();
                    if(_ll_uamt > 0){
                        _ll_samt = _ll_qty * _ll_uamt;
                        _ll_addamt = _ll_samt / 10 ;
                        _ll_amt = _ll_samt + _ll_addamt;
                        indexDa024Dto.setSamt(_ll_samt);
                        indexDa024Dto.setAddamt(_ll_addamt);
                        indexDa024Dto.setAmt(_ll_amt);
                    }else{
                        indexDa024Dto.setSamt(0);
                        indexDa024Dto.setAddamt(0);
                        indexDa024Dto.setAmt(0);
                    }

                    result = service14.UpdateDA024Amt(indexDa024Dto);

                    if (!result){
                        return "error";
                    }
                }

            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/index16/delarr")
    public String index16DelArr(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;

            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    indexDa024Dto.setMisdate(ls_misdate);
                    indexDa024Dto.setMisnum(misnumarr.get(i));
                    indexDa024Dto.setSeq(seqarr.get(i));
                    indexDa024Dto.setCltcd(cltcdarr.get(i));
                    indexDa024Dto.setMisgubun(gubunarr.get(i));
                    result = service14.DeleteDA024(indexDa024Dto);
                    if (!result){
                        return "error";
                    }
                    result = service14.DeleteDA023(indexDa024Dto);
                    if (!result){
                        //return "error";
                    }
                }

            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/index16/savedev")
    public String index16SaveDev(@RequestParam(value = "devnum01[]") List<String> devnum01
            ,@RequestParam( value =  "devnum02[]") List<String> devnum02
            ,@RequestParam( value =  "devnum03[]") List<String> devnum03
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            if( devnum01.size() > 0){
                for(int i = 0; i < devnum01.size(); i++){
                    String ls_cltcd = "";
                    String ls_unsongnum = "";
                    ls_unsongnum = devnum02.get(i);
                    if(ls_unsongnum == null || ls_unsongnum.equals("")){
                        break;
                    }
                    indexDa023Dto.setReservnum(devnum01.get(i));
                    indexDa023Dto.setUnsongnum(devnum02.get(i));
                    indexDa023Dto.setMisdate(devnum03.get(i).substring(0,8));
                    indexDa023Dto.setMisnum(devnum03.get(i).substring(8,12));
                    ls_cltcd = devnum03.get(i);
                    ls_cltcd = ls_cltcd.substring(12, ls_cltcd.length());
                    indexDa023Dto.setCltcd(ls_cltcd);
                    result = service14.UpdateDA023Unsong(indexDa023Dto);
                    if (!result){
                        return "error";
                    }

                }
                return "success";
            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/index16/savewish")
    public String index16SaveWish(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam( value =  "fixflagarr[]") List<String> fixflagarr
            ,@RequestParam("ipdate") String ipdate
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            String ls_tempchk = "";
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;

//                    year = omisdatearr.get(i).substring(0,4);
//                    month = omisdatearr.get(i).substring(5,7);
//                    day = omisdatearr.get(i).substring(8,10);
//                    String ls_omisdate = year + month + day ;

                    year = ipdate.substring(0,4);
                    month = ipdate.substring(5,7);
                    day = ipdate.substring(8,10);
                    String ls_ipdate  = year + month + day ;

//                    log.info("ls_misdate =====>" + ls_misdate);
                    indexDa024Dto.setMisdate(ls_misdate);       //예약 tb_da025 misdate
                    indexDa024Dto.setMisnum(misnumarr.get(i));  //예약 tb_da025 misnum
                    indexDa024Dto.setSeq(seqarr.get(i));
                    indexDa024Dto.setCltcd(cltcdarr.get(i));
                    indexDa024Dto.setMisgubun(gubunarr.get(i));
//                    indexDa024Dto.setOmisdate(ls_omisdate);
                    indexDa024Dto.setOmisnum(misnumarr.get(i));
                    indexDa024Dto.setOseq(seqarr.get(i));

                    indexDa023Dto.setMisdate(ls_misdate);
                    indexDa023Dto.setMisnum(misnumarr.get(i));
                    indexDa023Dto.setCltcd(cltcdarr.get(i));
                    indexDa023Dto.setMisgubun(gubunarr.get(i));

                    String ls_omisnum = "";
                    String ls_chknull = service14.SelectCheckMisnumOrd(indexDa023Dto);
                    String ls_getdata = ls_misdate + misnumarr.get(i) + cltcdarr.get(i); //
//                    log.info("getCltcd =====>" + indexDa024Dto.getCltcd());
//                    log.info("getMisgubun =====>" + indexDa024Dto.getMisgubun());
//                    log.info("getMisdate =====>" + indexDa024Dto.getMisdate());
//                    log.info("getMisnum =====>" + indexDa024Dto.getMisnum());
//                    log.info("getSeq =====>" + indexDa024Dto.getSeq());
                    if(fixflagarr.get(i).equals("0")){
                        indexDa024Dto.setFixflag("1");
                        indexDa024Dto.setOmisdate(ls_ipdate);
                        if(indexDa023Dto.getMisdate() == null){
                            return "error";
                        }
                        if(!ls_tempchk.equals(ls_getdata)){
                            ls_omisnum = GetMaxNum(ls_ipdate);
                            indexDa024Dto.setOmisnum(ls_omisnum);
                            result = service14.InsertDa023Order(indexDa024Dto);
                            if (!result){
                                return "error";
                            }
                        }else{
                            //indexDa024Dto.setOmisnum(ls_chknull);
                        }
                        result = service14.InsertDa024Order(indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                        ls_tempchk = ls_misdate + misnumarr.get(i) + cltcdarr.get(i);


                    }else{
                        indexDa024OrdDto.setOmisdate("");
                        indexDa024OrdDto.setOmisnum("");
                        indexDa024OrdDto.setOseq("");
                        //주문등록정보조회
                        indexDa024OrdDto = service14.SelectDa026Detail(indexDa024Dto);
                        result = service14.DeleteDA024Ord(indexDa024OrdDto);
                        if (!result){
                            //return "error";
                        }
                        result = service14.DeleteDA023Ord(indexDa024OrdDto);
                        if (!result){
                            //return "error";
                        }
                        indexDa024Dto.setFixflag("0");
                        indexDa024Dto.setOmisdate("");
                        indexDa024Dto.setOmisnum("");
                        indexDa024Dto.setOseq("");
                    }
                    result = service14.UpdateDA026(indexDa024Dto);
                    if (!result){
                        return "error";
                    }
                }

            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index16/modifywish")
    public String index16ModifyWish(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam( value =  "qtyarr[]") List<Integer> qtyarr
            ,@RequestParam( value =  "uamtarr[]") List<Integer> uamtarr
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;

            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    indexDa024Dto.setMisdate(ls_misdate);
                    indexDa024Dto.setMisnum(misnumarr.get(i));
                    indexDa024Dto.setSeq(seqarr.get(i));
                    indexDa024Dto.setCltcd(cltcdarr.get(i));
                    indexDa024Dto.setMisgubun(gubunarr.get(i));
                    indexDa024Dto.setQty(qtyarr.get(i));
                    indexDa024Dto.setUamt(uamtarr.get(i));
                    //_indexDa024Dto = service14.SelectDa026Detail02(indexDa024Dto);
                    Integer _ll_uamt = indexDa024Dto.getUamt();
                    Integer _ll_samt = 0;
                    Integer _ll_addamt = 0;
                    Integer _ll_amt = 0;
                    Integer _ll_qty = indexDa024Dto.getQty();
                    if(_ll_uamt > 0){
                        _ll_samt = _ll_qty * _ll_uamt;
                        _ll_addamt = _ll_samt / 10 ;
                        _ll_amt = _ll_samt + _ll_addamt;
                        indexDa024Dto.setSamt(_ll_samt);
                        indexDa024Dto.setAddamt(_ll_addamt);
                        indexDa024Dto.setAmt(_ll_amt);
                    }else{
                        indexDa024Dto.setSamt(0);
                        indexDa024Dto.setAddamt(0);
                        indexDa024Dto.setAmt(0);
                    }
                    result = service14.UpdateDA026Amt(indexDa024Dto);

                    if (!result){
                        return "error";
                    }
                }

            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index16/delarrwish")
    public String index16DelArrWish(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam("mflag") String mflag
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;

            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    indexDa024Dto.setMisdate(ls_misdate);
                    indexDa024Dto.setMisnum(misnumarr.get(i));
                    indexDa024Dto.setSeq(seqarr.get(i));
                    indexDa024Dto.setCltcd(cltcdarr.get(i));
                    indexDa024Dto.setMisgubun(gubunarr.get(i));
                    result = service14.DeleteDA026(indexDa024Dto);
                    if (!result){
                        return "error";
                    }
                    result = service14.DeleteDA025(indexDa024Dto);
                    if (!result){
                        //return "error";
                    }
                }

            }

        }catch (IllegalStateException e){
            model.addAttribute("index16Save errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/comcodesave")
    public String App01ComCodeSave_index(  @RequestParam("com_cls") String com_cls,
                                           @RequestParam("com_cnam") String com_cnam,
                                           Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App01ComCodeSave_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01ListDto = service01.getComCodeList(index01Dto);
        if(index01ListDto.isEmpty() || index01ListDto.size() == 0){
            result = service01.InsertComCode(index01Dto);
        }else{
            result = service01.UpdateComCode(index01Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/comcodedel")
    public String App01ComCodeDel_index(  @RequestParam("com_cls") String com_cls,
                                          @RequestParam("com_cnam") String com_cnam,
                                          Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App01ComCodeDel_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01ListDto = service01.getComCodeList(index01Dto);
        result = service01.DeleteComCode(index01Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }

    //업체분류현황
    @GetMapping(value="/comcodelist")
    public Object App01ComCodeList_index(@RequestParam("searchtxt") String searchtxt,
                                         Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service01.getComCodeList(index01Dto);

            model.addAttribute("comcodeList",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index01ListDto;
    }

    //업체분류현황
    @GetMapping(value="/comcodelists")
    public Object App01ComCodeLists_index(@RequestParam("searchtxt") String searchtxt,
                                          Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service01.getComCodeLists(index01Dto);

            model.addAttribute("comcodeLists",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index01ListDto;
    }

    @RequestMapping(value="/comcodedetailsave")
    public String App01ComCodeDetailSave_index(  @RequestParam("com_cls") String com_cls,
                                                 @RequestParam("com_cnam") String com_cnam,
                                                 @RequestParam("com_code") String com_code,
                                                 @RequestParam("com_rem1") String com_rem1,
                                                 @RequestParam("com_rem2") String com_rem2,
                                                 @RequestParam("com_work") String com_work,
                                                 Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App01ComCodeDetailSave_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01Dto.setCom_code(com_code);
        index01Dto.setCom_rem1(com_rem1);
        index01Dto.setCom_rem2(com_rem2);
        index01Dto.setCom_work(com_work);
        String ls_comcode = service01.GetComCodeCheck(index01Dto);
        if(ls_comcode == null || ls_comcode.equals("")){
            result = service01.InsertComCodeDetail(index01Dto);
        }else{
            result = service01.UpdateComCodeDetail(index01Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/comcodedetaildel")
    public String App01ComCodeDetailDel_index(  @RequestParam("com_cls") String com_cls,
                                                @RequestParam("com_cnam") String com_cnam,
                                                @RequestParam("com_code") String com_code,
                                                Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App01ComCodeDetailDel_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01Dto.setCom_code(com_code);
        //index01ListDto = service01.getComCodeDetailList(index01Dto);
        result = service01.DeleteComCodeDetail(index01Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }

    @GetMapping(value="/comcodedetaillist")
    public Object App01ComdodeDetailList_index(@RequestParam("searchtxt") String searchtxt,
                                               @RequestParam("com_cls") String com_cls,
                                               Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App01ComdodeDetailList_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            if(com_cls == null || com_cls.equals("")){
                com_cls = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );

            index01Dto.setCom_cls(com_cls);
            index01Dto.setCom_cnam(searchtxt);;
            index01ListDto = service01.GetComcodeDetailList(index01Dto);
            model.addAttribute("index01ListDto",index01ListDto);

        } catch (Exception ex) {
            log.info("App01ComdodeDetailList_index Exception =====>" + ex.toString());
        }

        return index01ListDto;
    }









    public String GetMaxNum(String agDate){

        String ls_misnum = "";
        ls_misnum = service14.SelectMaxMisnum(indexDa023Dto);
        if (ls_misnum == null){ ls_misnum = "0";}
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
    public String GetMaxSeqIpgo(Index04Dto asDto){
        String ls_seq = service04.SelectMaxSeqIpgo(asDto);
        if(ls_seq == null || ls_seq.equals("00")){
            ls_seq = "01";
        }else{
            Integer ll_seq = Integer.parseInt(ls_seq) + 1;
            ls_seq = ll_seq.toString();
            if (ls_seq.length() == 1){
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
