package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.*;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.Popup.SyslogDto;
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
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    private final AuthService service_auth;
    CommonDto CommDto = new CommonDto();
    Index04Dto index04Dto = new Index04Dto();
    List<Index04Dto> index04List = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();
    List<Index02Dto> index02List = new ArrayList<>();
    List<IndexDa024Dto> indexDa024ListDto = new ArrayList<>();

    Index20Dto index20Dto = new Index20Dto();
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
            conacorp = escapeBrackets(conacorp);
            _index02Dto.setAcorp1(conacorp1);
            _index02Dto.setAgita(conagita);
            _index02Dto.setAcorp(conacorp);
            _index02Dto.setAbonsadam1(abonsadam1);
            if (jpbgubn.equals("P")){
                _index02Dto.setAcorp1("02");
            }else if(jpbgubn.equals("B")){
                _index02Dto.setAcorp1("03");
            }else{
                //index02Dto.setAcorp1("%");
            }
            if(conagita.equals("all")){
                _index02Dto.setAgita("%");
                //비거래 포함 접수 조회화면
                _index02List = service02.GetCifListTotJupsu(_index02Dto);
            }else{
                if (jpbgubn.equals("DD")){
                    //비거래 포함, 거래중지 포함
                    _index02List = service02.GetCifListTotJupsu(_index02Dto);
                }else{
                    _index02List = service02.GetCifListTot(_index02Dto);
                }
            }

            model.addAttribute("index02List",_index02List);

        } catch (Exception ex) {
            log.info("App02ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index02List;
    }


    //거래처등록
    @GetMapping(value="/index02/chbonsadam")
    public String App02ChbonsaDam_index(@RequestParam("acorp1") String acorp1,
                                  @RequestParam("acorp2") String acorp2,
                                  @RequestParam("abonsadam") String abonsadam,
                                  @RequestParam("inname") String inname,
                                  Model model, HttpServletRequest request) throws Exception{
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

            _index02Dto.setAcode(acorp1 + acorp2);
            _index02Dto.setAcorp1(acorp1);
            _index02Dto.setAcorp2(acorp2);
            _index02Dto.setAbonsadam1(abonsadam);
            _index02Dto.setInname01(inname);
            Boolean _result = false;
            _result = service02.UpdateBonsadam(_index02Dto);
            if(!_result){
                return "error";
            }
            _result = service02.Updateda024Damdang(_index02Dto);
            if(!_result){
                return "error";
            }

        } catch (Exception ex) {
            log.info("App02ChbonsaDam_index Exception =====>" + ex.toString());
            return "error";
        }

        return "SUCCESS";
    }


    @RequestMapping(value="/index02/save")
    public String index02Save(@RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        try {
            Index02Dto _index02Dto = new Index02Dto();
            UserFormDto _appUserFormDto  = new UserFormDto();
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
                    case "astop":
                        _index02Dto.setAstop(values.toString());
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
            String ls_userid = "";
            //사용자등록/////////////////////////////////////////////////////////////////
            ls_userid = _index02Dto.getAsano1() + _index02Dto.getAsano2() + _index02Dto.getAsano3();

            if(_index02Dto.getAgita().equals("P")){
                _appUserFormDto.setRole(_index02Dto.getAgita());
                _appUserFormDto.setCustnm("피오비노");
            }else if(_index02Dto.getAgita().equals("B")){
                _appUserFormDto.setRole(_index02Dto.getAgita());
                _appUserFormDto.setCustnm("블리스");
            }else{
                _appUserFormDto.setRole("%");
                _appUserFormDto.setCustnm("%");
            }
            _appUserFormDto.setSpjangcd("ZZ");
            _appUserFormDto.setUserid(ls_userid);
            _appUserFormDto.setSaupnum(_index02Dto.getAsano1() + _index02Dto.getAsano2() + _index02Dto.getAsano3() );
            _appUserFormDto.setPhone(_index02Dto.getAtelno());
            _appUserFormDto.setUseyn("Y");
            _appUserFormDto.setRole(_index02Dto.getAgita());
            _appUserFormDto.setUsername(_index02Dto.getAcorp());
            _appUserFormDto.setPernm(_index02Dto.getAcorp());
            _appUserFormDto.setPasswd1(_index02Dto.getAsano3());
            _appUserFormDto.setPasswd2(_index02Dto.getAsano3());
            _appUserFormDto.setFlag("BB");
            _appUserFormDto.setRnum("0");
            _appUserFormDto.setCustcd("actcd");
            _appUserFormDto.setCustnm(_index02Dto.getAcorp());
            _appUserFormDto.setPerid(_index02Dto.getAcorp1() + _index02Dto.getAcorp2());

            ///////////////////////////////////////////////////////////////////
            if (ls_acode == null || ls_acode.equals("")) {
                Integer ll_acorp2 = Integer.parseInt(service02.getIndex02MaxSeq(_index02Dto.getAcorp1())) + 1;
                ls_acorp2 = ll_acorp2.toString();
                _index02Dto.setAcorp2(ls_acorp2);
                _appUserFormDto.setPerid(_index02Dto.getAcorp1() + _index02Dto.getAcorp2());
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
            UserFormDto _appUserDto = service_auth.GetUserInfoDto3(_appUserFormDto);
            if (_appUserDto == null){
                result = service_auth.TB_XUSERS_INSERT(_appUserFormDto);
                if (!result) {
                    return "error";
                }
            }else{
                _appUserFormDto.setSeq(_appUserDto.getSeq());
                if (_index02Dto.getAgita().equals("Z")){
                    _appUserFormDto.setUseyn("N");  //비거래가 아닐경우 임의로 N을 넣은경우가 있어 비거래만 체크하여 업데이트함.
                }else{
                    //_appUserFormDto.setUseyn("N");
                }
                if(_index02Dto.getAstop().equals("Y")){
                    _appUserFormDto.setDisflag("Y");
                }else{
                    _appUserFormDto.setDisflag("N");
                }
                log.info("useyn->" + _appUserFormDto.getUseyn());
                result = service_auth.UpdateUserInfoBBDisflag(_appUserFormDto);
//                log.info("flag-2->" + _appUserFormDto.getFlag());
//                log.info("Seq-2->" + _appUserFormDto.getSeq());
                if (!result) {
                    return "error";
                }
            }
//            model.addAttribute("userformDto",userformDto);
        }catch (Exception e){
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
        result = service02.DeleteXuserBB(_index02Dto);
        if (!result) {
            //return "error";
        }

        return "success";
    }



    //제품등록
    @GetMapping(value="/index03/list")
    public Object App03List_index(@RequestParam("searchtxt") String searchtxt,
                                  @RequestParam("jpbgubn") String jpbgubn,
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
            _index03Dto.setJpb_gubn(jpbgubn);
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
                                      @RequestParam("jpbgubn") String jpbgubn,
                                      @RequestParam("flag") String flag,
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
            index03Dto_S.setJpb_gubn(jpbgubn);
            index03Dto_S.setFrdate("20100101");
            index03Dto_S.setTodate(getToDate());
            index03Dto_S.setFlag(flag);
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
                                       @RequestParam("jpbgubn") String jpbgubn,
                                       @RequestParam("flag") String flag,
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
            index03Dto_S.setJpb_gubn(jpbgubn);
            index03Dto_S.setFlag(flag);
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
            if (ls_acode == null || ls_acode.equals("")) {
                result = service03.InsertJpum(_index03Dto);
                log.info(result);
                if (!result) {
                    return "error";
                }
            } else {
                result = service03.UpdateJpum(_index03Dto);
                log.info(result);
                if (!result) {
                    return "error";
                }
            }
//            model.addAttribute("userformDto",userformDto);
        }catch (Exception e){
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
        List<Index03ColorDto> _index03List = new ArrayList<>();
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
            index03Dto_S.setTodate(getToDate());
            index03Dto_S.setJsayong_gubn(flag); //AA 본사 BB 영업점  CC 영어사원
            if(flag.equals("AA") || flag.equals("CC")) {
                _index03List = service03.GetGanListBonsa01(index03Dto_S);
            }else{
                _index03List = service03.GetGanListBonsa01BB(index03Dto_S);
            }

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
//            log.info("jpbgubn=" + jpbgubn);
//            log.info("jbonsacode=" + jbonsacode);
//            log.info("jbonsacode2=" + jbonsacode2);
//            log.info("jmodelcode=" + jmodelcode);
//            log.info("getToDate()=" + getToDate());
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
            if( flag.equals("CC")){
                _index03List = service03.GetJBonsaCodeList_CC(index03Dto_S);
            }else{
                _index03List = service03.GetJBonsaCodeList(index03Dto_S);
            }

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
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            Index04Dto _index04Dto = new Index04Dto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index04Save Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Integer ll_count = jcode.size();
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
                    }else{
                        _syslogDto.setType("저장");
                        _syslogDto.setMenunm("재고등록");
                        _syslogDto.setMessage(_index04Dto.getKey1() + "/" +  _index04Dto.getJepm() + "/" + _index04Dto.getIjaego_su1().toString());
                        _syslogDto.setSource(ll_count.toString() + "건 등록");
                        _syslogDto.setUserid(userid);
                        _syslogDto.setUsernm(usernm);
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }
                }
            }

        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index04/del")
    public String index04Delete(  @RequestParam("ipdate") String ipdate,
                                  @RequestParam("acorp") String acorp,
                                  @RequestParam("jepm") String jepm,
                                  @RequestParam("userid") String userid,
                                  @RequestParam("usernm") String usernm,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("index04Delete Exception =====> relogin userformDto null");
            return "relogin";
        }
        SyslogDto _syslogDto = new SyslogDto();
        Index04Dto _index04Dto = new Index04Dto();
        model.addAttribute("userformDto",userformDto);
        if(ipdate.length() > 8 ){
            String year = ipdate.substring(0,4) ;
            String month = ipdate.substring(5,7) ;
            String day   = ipdate.substring(8,10) ;
            ipdate = year + month + day ;
        }

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
        }else{
            _syslogDto.setType("삭제");
            _syslogDto.setMenunm("재고등록");
            _syslogDto.setMessage(_index04Dto.getKey1() + "/" +  _index04Dto.getJepm()  );
            _syslogDto.setSource(jepm + " 삭제");
            _syslogDto.setUserid(userid);
            _syslogDto.setUsernm(usernm);
            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
            if (!result) {
                //return "error";
            }
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
            _index03Dto.setJfrdate("20000101");
            _index03Dto.setJpb_gubn(jpbgubn);

            String year = todate.substring(0,4) ;
            String month = todate.substring(5,7) ;
            String day   = todate.substring(8,10) ;
            todate = year + month + day ;
            _index03Dto.setFrdate("20000101");
            _index03Dto.setTodate(todate);
            _index03Dto.setJkey(searchtxt);
//            log.info("001 ->" + _index03Dto.getJpum());
//            log.info("002 ->" + _index03Dto.getFrdate());
//            log.info("003 ->" + _index03Dto.getTodate());
//            log.info("004 ->" + _index03Dto.getJpb_gubn());
            _index03List = service03.GetJpumCustJaegoList(_index03Dto);
//            log.info("002 ->" + _index03Dto.getJpb_gubn());
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App04JaegoCustList_index Exception =====>" + ex.toString());
        }

        return _index03List;
    }


    //그룹별 재고현황 리스트
    @GetMapping(value="/index04/jaegofromlist")
    public Object App04JaegoFromList_index(@RequestParam("searchtxt") String searchtxt,
                                           @RequestParam("jcustcd") String jcustcd,
                                           @RequestParam("frdate") String frdate,
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
            _index03Dto.setJkey(searchtxt);
            _index03Dto.setJfrdate("20000101");
            _index03Dto.setJpb_gubn(jpbgubn);

            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;

            year = todate.substring(0,4) ;
            month = todate.substring(5,7) ;
            day   = todate.substring(8,10) ;
            todate = year + month + day ;
            _index03Dto.setJtodate(frdate);
            _index03Dto.setFrdate(frdate);
            _index03Dto.setTodate(todate);
//            log.info("001 ->" + _index03Dto.getJpum());
//            log.info("002 ->" + _index03Dto.getFrdate());
//            log.info("003 ->" + _index03Dto.getTodate());
            //log.info("004 ->" + _index03Dto.getJpb_gubn());
            _index03List = service03.GetJpumFromJaegoList(_index03Dto);
            //log.info("002 ->" + _index03Dto.getJpb_gubn());
            model.addAttribute("index03List",_index03List);

        } catch (Exception ex) {
            log.info("App04JaegoCustList_index Exception =====>" + ex.toString());
        }

        return _index03List;
    }


    //마감 리스트
    @GetMapping(value="/index04/monthlist")
    public Object App04MonthList_index(@RequestParam("yymm") String yymm,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고등록");
        CommDto.setMenuUrl("기준정보>재고현항");
        CommDto.setMenuCode("index04");
        List<IndexMonCDto> _indexMonCDtoList = new ArrayList<>();
        IndexMonCDto _indexMonCDto = new IndexMonCDto();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("index04MonthList Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {
            if(yymm == null || yymm.equals("")){
                yymm = "0000";
            }
            _indexMonCDto.setYymm(yymm);
            _indexMonCDtoList = service03.GetMonthYYMMList(_indexMonCDto);
            model.addAttribute("index04MonthList",_indexMonCDtoList);

        } catch (Exception ex) {
            log.info("index04MonthList Exception =====>" + ex.toString());
        }

        return _indexMonCDtoList;
    }


    @RequestMapping(value="/index04/monthsave")
    public String index04MonthSave(
            @RequestParam("yymm") String yymm
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            IndexMonCDto _indexMonCDto = new IndexMonCDto();
            HttpSession session = request.getSession();
            Boolean result = false ;
            String ls_result = "";
            Integer ll_count = 0;
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index04MonthSave Exception =====> relogin userformDto null");
                return "relogin";
            }
            _indexMonCDto.setYymm(yymm);
            ll_count = service03.GetMonthCloseCount(_indexMonCDto);
            if(ll_count == null){
                ll_count =0;
            }
            if(ll_count > 0  ){
                return "Duplicate";
            }
            service03.GetMonthJaego_PRC(_indexMonCDto);
//            if (!ls_result.equals("SUCCESS")){
//                return "error";
//            }else{
                _syslogDto.setType("저장");
                _syslogDto.setMenunm("재고월마감");
                _syslogDto.setMessage(yymm);
                _syslogDto.setSource(yymm.toString() + "월 마감등록");
                _syslogDto.setUserid(userid);
                _syslogDto.setUsernm(usernm);
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
//            }

        }catch (Exception e){
            log.info("index04MonthSave Exception =====> " + e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }



    @RequestMapping(value="/index04/monthsavedel")
    public String index04MonthSaveDel(
            @RequestParam("yymm") String yymm
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            IndexMonCDto _indexMonCDto = new IndexMonCDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index04MonthSave Exception =====> relogin userformDto null");
                return "relogin";
            }
            _indexMonCDto.setYymm(yymm);
            Boolean result = false;
            result = service03.DeleteMonthJaego(_indexMonCDto);
            // }else{
            //     _index04Dto.setAcorp(ls_acorp);
            //     result = service04.UpdateJegoIpgo(_index04Dto);
            // }
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("삭제");
                _syslogDto.setMenunm("재고월마감");
                _syslogDto.setMessage(yymm);
                _syslogDto.setSource(yymm.toString() + "월 마감삭제");
                _syslogDto.setUserid(userid);
                _syslogDto.setUsernm(usernm);
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }

        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    //재고마감 리스트
    @GetMapping(value="/index04/monthcloselist")
    public Object App04CloseList_index(
            Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index04");
        List<IndexMonCDto> _indexMonCDtoList = new ArrayList<>();
        IndexMonCDto _indexMonCDto = new IndexMonCDto();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            _indexMonCDtoList = service03.GetMonthCloseList(_indexMonCDto);

            model.addAttribute("indexMonthList",_indexMonCDtoList);
        } catch (Exception ex) {
            log.info("App04_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "error";
        }

        return _indexMonCDtoList;
    }

    //재고마감 조회 품목
    @GetMapping(value="/index04/monthcloselistPcode")
    public Object App04ClosePcodeList_index(
            @RequestParam("searchtxt") String searchtxt,
            @RequestParam("yymm") String yymm,
            Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index04");
        List<IndexMonCDto> _indexMonCDtoList = new ArrayList<>();
        IndexMonCDto _indexMonCDto = new IndexMonCDto();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            _indexMonCDto.setPcode(searchtxt);
            _indexMonCDto.setYymm(yymm);
            _indexMonCDtoList = service03.GetMonthYYMMList(_indexMonCDto);

            model.addAttribute("indexMonthList",_indexMonCDtoList);
        } catch (Exception ex) {
            log.info("App04_index Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
            return "error";
        }

        return _indexMonCDtoList;
    }

    //재고재계산
    @RequestMapping(value="/index04/savecal")
    public String index04SaveCal(
            @RequestParam("savejaedate") String savejaedate
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            Index03Dto _index03Dto = new Index03Dto();
            HttpSession session = request.getSession();
            Boolean result = false ;
            String ls_result = "";
            Integer ll_count = 0;
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index04MonthSave Exception =====> relogin userformDto null");
                return "relogin";
            }
            String searchtxt = "%";
            String jcustcd = "%";
            String jpbgubn = "%";
            _index03Dto.setJfrdate("20000101");
            _index03Dto.setJpb_gubn(jpbgubn);

            String year = savejaedate.substring(0,4) ;
            String month = savejaedate.substring(5,7) ;
            String day   = "99" ;
            savejaedate = year + month + day ;
            _index03Dto.setFrdate("20000101");
            _index03Dto.setTodate(savejaedate);
            _index03Dto.setJkey(searchtxt);

             log.info("savejaedate =====> " + savejaedate );
             service03.GetJpumCustJaegoCal(_index03Dto);
            _syslogDto.setType("저장");
            _syslogDto.setMenunm("재고재계산");
            _syslogDto.setMessage(savejaedate);
            _syslogDto.setSource(savejaedate + " : 재계산 시행");
            _syslogDto.setUserid(userid);
            _syslogDto.setUsernm(usernm);
            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
            if (!result) {
                //return "error";
            }

        }catch (Exception e){
            log.info("index04SaveCal Exception =====> " + e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
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
            ,@RequestParam("perid") String perid
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
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
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();

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
                log.info("index14Save 품목 없음 =====> index03Dto == null"  );
//                log.info("jfrdate =====> " + frdate );
//                log.info("jmodel =====> " + jmodel );
//                log.info("jcolor =====> " + jcolor );
//                log.info("jcolor =====> " + jcolor );
//                log.info("jbonsa =====> " + jbonsa );
//                log.info("jbonsa2 =====> " + jbonsa2 );
//                log.info("jpbgubn =====> " + jpbgubn );
                return "error";
            }
            _ll_jqty = Integer.parseInt(_index03Dto.getJqty());
            if(_ll_jqty == 0 || _ll_jqty == null){
                log.info("index14Save 재고 없음 =====> _ll_jqty == 0"  );
//                log.info("jfrdate =====> " + frdate );
//                log.info("jkey =====> " + _index03Dto.getJkey() );
//                log.info("jqty =====> " + _index03Dto.getJqty() );
                return "jaego";
            }
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisgubun(mflag);
            _indexDa023Dto.setMisdate(frdate);
            String ls_misnum = "";
            String ls_chknull = "";
            if(jmodel.equals("AS")){
                ls_chknull = service14.SelectCheckMisnumJupsu(_indexDa023Dto);
            }else{
                ls_chknull = service14.SelectCheckMisnum(_indexDa023Dto);
            }
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                if(jmodel.equals("AS")){
                    ls_misnum = service14.SelectCheckMisnumMkflagJupsu(_indexDa023Dto);  //주문된 순번max 찾기
                    if(ls_misnum == null){
                        ls_misnum = "5001";
                    }else{
                        Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
                        ls_misnum = ll_misnum.toString();
                        if (ls_misnum.length() == 1){
                            ls_misnum = "000" + ls_misnum;
                        }else if(ls_misnum.length() == 2){
                            ls_misnum = "00" + ls_misnum;
                        }else if(ls_misnum.length() == 3){
                            ls_misnum = "0" + ls_misnum;
                        }
                    }
                }else{
                      ls_misnum = GetTrackNum(_indexDa023Dto.getMisdate());
                      if(ls_misnum.equals("error")){
                          return "error";
                      }

//                    ls_misnum = service14.SelectCheckMisnumMkflag(_indexDa023Dto);  //주문된 순번max 찾기
//                    if(ls_misnum == null){
//                        ls_misnum = "0001";
//                    }else{
//                        Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
//                        ls_misnum = ll_misnum.toString();
//                        if (ls_misnum.length() == 1){
//                            ls_misnum = "000" + ls_misnum;
//                        }else if(ls_misnum.length() == 2){
//                            ls_misnum = "00" + ls_misnum;
//                        }else {
//                            ls_misnum = "0" + ls_misnum;
//                        }
//                    }
                }
            }else{
                ls_misnum = ls_chknull;
            }
            _indexDa023Dto.setMisnum(ls_misnum);
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    _indexDa023Dto.setPerid("");
                    _indexDa023Dto.setGgubun("AA");
                    break;
                case "BB":
                    _indexDa023Dto.setPerid("");
                    _indexDa023Dto.setGgubun("BB");
                    break;
                case "CC":
                    _indexDa023Dto.setPerid(perid);
                    _indexDa023Dto.setGgubun("CC");
                    break;
                default:
                    break;
            }
            _indexDa023Dto.setContamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setMisamt(0);
            _indexDa023Dto.setAmt(0);
            _indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            _indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            _indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            _indexDa023Dto.setBigo("");
            _indexDa023Dto.setRemark("");
//            _indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            _indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            _indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            _indexDa023Dto.setGubun("");
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setMisdate(frdate);
            _indexDa024Dto.setMisnum(ls_misnum);
            _indexDa024Dto.setPcode(_index03Dto.getJkey());
            _indexDa024Dto.setPname(_index03Dto.getJpum());
            _indexDa024Dto.setPsize(_index03Dto.getJgugek());
            _indexDa024Dto.setPbonsa(jbonsa);
            _indexDa024Dto.setPbonsa2(_index03Dto.getJbonsa_code2());
            _indexDa024Dto.setPmodel(jmodel);
            _indexDa024Dto.setPcolor(jcolor);
            _indexDa024Dto.setCltcd(_indexDa023Dto.getCltcd());
            _indexDa024Dto.setAcorp(_index02Dto.getAcorp());
            _indexDa024Dto.setPernm(_index02Dto.getInname01());
            _indexDa024Dto.setPerid(_index02Dto.getAbonsadam1());
            _indexDa024Dto.setJpb_gubn(jpbgubn);
            _indexDa024Dto.setUserid(userid);
            _indexDa024Dto.setUsernm(usernm);

            //재고체크 ------------------------------------------


            //--------------------------------------------------
            String ls_seq = "";
            //신규입력
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                //동일업체 동일일자 동일품목이 있는지 체크
                _index024Dto = service14.SelectCheckJpum024(_indexDa024Dto);  //주문된 순번max 찾기
                if (_index024Dto != null){
                    ls_seq = _index024Dto.getSeq();
                    if (ls_seq.length() > 0 && ls_seq != null ){
                        Integer _ll_qty = _index024Dto.getQty() + 1;
                        Integer _ll_uamt = _index024Dto.getUamt();
                        Integer _ll_samt = 0 ;
                        Integer _ll_addamt = 0 ;
                        Integer _ll_amt = 0;
                        _indexDa024Dto.setSeq(ls_seq);
                        _indexDa024Dto.setQty(_ll_qty);
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            _indexDa024Dto.setSamt(_ll_samt);
                            _indexDa024Dto.setAddamt(_ll_addamt);
                            _indexDa024Dto.setAmt(_ll_amt);
                        }else{
                            _indexDa024Dto.setSamt(0);
                            _indexDa024Dto.setAddamt(0);
                            _indexDa024Dto.setAmt(0);
                        }
                        result = service14.UpdateDA024QtySame(_indexDa024Dto);
                        if (!result){
                            return "error";
                        }else{
                            _syslogDto.setType("수정");
                            _syslogDto.setMenunm("주문등록");
                            _syslogDto.setSource("장바구니수정");
                            _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum());
                            _syslogDto.setUserid(userid);
                            _syslogDto.setUsernm(usernm);
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                        return "success";
                    }else{
                        ls_seq = GetMaxSeq(_indexDa023Dto, frdate);
                    }
                }else{
                    ls_seq = GetMaxSeq(_indexDa023Dto, frdate);
                }
            }

            String ls_chulgoga = _index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            _indexDa024Dto.setSeq(ls_seq);
            _indexDa024Dto.setQty(1);
            _indexDa024Dto.setUamt(ll_chulgoga);
            _indexDa024Dto.setSamt(ll_chulgoga);
            _indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {_indexDa024Dto.setAddamt(ll_chulgoga / 10);};
            _indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            _indexDa024Dto.setIndate(getToDate());
            _indexDa024Dto.setInperid(perid);
            _indexDa024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa023(_indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa024(_indexDa024Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("등록");
                _syslogDto.setMenunm("주문등록");
                _syslogDto.setSource("장바구니등록");
                _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                _syslogDto.setUserid(userid);
                _syslogDto.setUsernm(usernm);
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                   // return "error";
                }
            }

        }catch (Exception e){
            log.info("index14/save  오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            ,@RequestParam("perid") String perid
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
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
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();

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

            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisdate(frdate);
            _indexDa023Dto.setMisgubun(mflag);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnumWish(_indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = service14.SelectCheckMisnumWishMkflag(_indexDa023Dto);  //주문된 순번max 찾기
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
            _indexDa023Dto.setMisnum(ls_misnum);
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    _indexDa023Dto.setPerid("");
                    _indexDa023Dto.setGgubun("AA");
                    break;
                case "BB":
                    _indexDa023Dto.setPerid("");
                    _indexDa023Dto.setGgubun("BB");
                    break;
                case "CC":
                    _indexDa023Dto.setPerid(perid);
                    _indexDa023Dto.setGgubun("CC");
                    break;
                default:
                    break;
            }
            _indexDa023Dto.setContamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setMisamt(0);
            _indexDa023Dto.setAmt(0);
            _indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            _indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            _indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            _indexDa023Dto.setBigo("");
            _indexDa023Dto.setRemark("");
//            _indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            _indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            _indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            _indexDa023Dto.setGubun("");
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setMisdate(frdate);
            _indexDa024Dto.setMisnum(ls_misnum);
            _indexDa024Dto.setPcode(_index03Dto.getJkey());
            _indexDa024Dto.setPname(_index03Dto.getJpum());
            _indexDa024Dto.setPsize(_index03Dto.getJgugek());
            _indexDa024Dto.setPbonsa(jbonsa);
            _indexDa024Dto.setPbonsa2(_index03Dto.getJbonsa_code2());
            _indexDa024Dto.setPmodel(jmodel);
            _indexDa024Dto.setPcolor(jcolor);
            _indexDa024Dto.setAcorp(_index02Dto.getAcorp());
            _indexDa024Dto.setPernm(_index02Dto.getInname01());
            _indexDa024Dto.setPerid(_index02Dto.getAbonsadam1());
            _indexDa024Dto.setJpb_gubn(jpbgubn);
            String ls_seq = "";
            //신규입력
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                //동일업체 동일일자 동일품목이 있는지 체크
                _index024Dto = service14.SelectCheckJpum026(_indexDa024Dto);  //주문된 순번max 찾기
                if(_index024Dto != null){
                    ls_seq = _index024Dto.getSeq();
                    if (ls_seq.length() > 0 && ls_seq != null ){
                        Integer _ll_qty = _index024Dto.getQty() + 1;
                        Integer _ll_uamt = _index024Dto.getUamt();
                        Integer _ll_samt = 0 ;
                        Integer _ll_addamt = 0 ;
                        Integer _ll_amt = 0;
                        _indexDa024Dto.setSeq(ls_seq);
                        _indexDa024Dto.setQty(_ll_qty);
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            _indexDa024Dto.setSamt(_ll_samt);
                            _indexDa024Dto.setAddamt(_ll_addamt);
                            _indexDa024Dto.setAmt(_ll_amt);
                        }else{
                            _indexDa024Dto.setSamt(0);
                            _indexDa024Dto.setAddamt(0);
                            _indexDa024Dto.setAmt(0);
                        }
                        result = service14.UpdateDA026QtySame(_indexDa024Dto);
                        if (!result){
                            return "error";
                        }else{
                            _syslogDto.setType("수정");
                            _syslogDto.setMenunm("예약등록");
                            _syslogDto.setSource("주문예약수정");
                            _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum());
                            _syslogDto.setUserid(userformDto.getUserid());
                            _syslogDto.setUsernm(userformDto.getUsername());
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                        return "success";
                    }else{
                        ls_seq = GetMaxSeqWish(_indexDa023Dto, frdate);
                    }
                }else{
                    ls_seq = GetMaxSeqWish(_indexDa023Dto, frdate);
                }
            }

            String ls_chulgoga = _index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            _indexDa024Dto.setSeq(ls_seq);
            _indexDa024Dto.setQty(1);
            _indexDa024Dto.setUamt(ll_chulgoga);
            _indexDa024Dto.setSamt(ll_chulgoga);
            _indexDa024Dto.setCltcd(_indexDa023Dto.getCltcd());
            _indexDa024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {_indexDa024Dto.setAddamt(ll_chulgoga / 10);};
            _indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            _indexDa024Dto.setIndate(getToDate());
            _indexDa024Dto.setInperid(perid);
            _indexDa024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa025(_indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa026(_indexDa024Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("등록");
                _syslogDto.setMenunm("예약등록");
                _syslogDto.setSource("주문예약수정");
                _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum());
                _syslogDto.setUserid(userformDto.getUserid());
                _syslogDto.setUsernm(userformDto.getUsername());
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }

        }catch (Exception e){
            log.info("index14/savewish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            ,@RequestParam("perid") String perid
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            ,@RequestParam( value =  "misqty[]") List<String> misqty
            , Model model
            , HttpServletRequest request){

        try {

            //Index03Dto index03Dto_S = new Index03Dto();
            SyslogDto _syslogDto = new SyslogDto();
            IndexDa024Dto _index024Dto = new IndexDa024Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();
            IndexDa024Dto _Da024Dto = new IndexDa024Dto();
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
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisdate(frdate);
            _Da024Dto.setCltcd(acode);
            _Da024Dto.setUserid(userid);
            _Da024Dto.setUsernm(usernm);

            String ls_misdate = null ;
            String misdateArrStr = null ;
            String misnumArrStr = null ;
            String seqArrStr = null ;
            String misqtyStr = null ;

            //장바구니가 존재하는경우
            if (!misdate.equals("0000")){
                year = misdateArr.get(0).substring(0,4);
                month = misdateArr.get(0).substring(5,7);
                day = misdateArr.get(0).substring(8,10);
                ls_misdate = year + month + day ;
                _Da024Dto.setQty(jqty);
                _Da024Dto.setMisdate(ls_misdate);
                _Da024Dto.setMisnum(misnum);
                _Da024Dto.setSeq(seq);
                _Da024Dto.setCltcd(acode);
                _Da024Dto.setMisgubun(mflag);
                if(jremark == null){
                    jremark = "";
                }
                log.info(ls_misdate);
                log.info(misnum);
                String _lsSeq = service14.CheckedFixDA024(_Da024Dto);
                if (_lsSeq == null){

                }else if(_lsSeq.length() > 0 && _lsSeq != null){
                    //하나의 주문중에 일부 주문 후 추가 주문할경우 번호변경 후 주문등록
                    _Da024Dto.setMisdate(misdate);
                    _Da024Dto.setMisdateArr(misdateArr);
                    _Da024Dto.setMisnumArr(misnumArr);
                    _Da024Dto.setSeqArr(seqArr);
                    _Da024Dto.setMisqtyArr(misqty);

                    // Convert List<String> to comma-separated String
                    misdateArrStr = (_Da024Dto.getMisdateArr() != null && !_Da024Dto.getMisdateArr().isEmpty())
                            ? String.join(",", _Da024Dto.getMisdateArr())
                            : null;
                    misnumArrStr = (_Da024Dto.getMisnumArr() != null && !_Da024Dto.getMisnumArr().isEmpty())
                            ? String.join(",", _Da024Dto.getMisnumArr())
                            : null;
                    seqArrStr = (_Da024Dto.getSeqArr() != null && !_Da024Dto.getSeqArr().isEmpty())
                            ? String.join(",", _Da024Dto.getSeqArr())
                            : null;
                    misqtyStr = (_Da024Dto.getMisqtyArr() != null && !_Da024Dto.getMisqtyArr().isEmpty())
                            ? _Da024Dto.getMisqtyArr().stream().map(String::valueOf).collect(Collectors.joining(","))
                            : null;
                    _Da024Dto.setMisdateStr(misdateArrStr);
                    _Da024Dto.setMisnumStr(misnumArrStr);
                    _Da024Dto.setSeqStr(seqArrStr);
                    _Da024Dto.setMisqtyStr(misqtyStr);
                    Boolean _liresult01 = service14.ChangeDA024Makfix(_Da024Dto);
//                    log.info(_liresult01);
                    _syslogDto.setType("변경등록");
                    _syslogDto.setMenunm("주문번호변경등록");
                    if(!misdate.equals(ls_misdate)) {
                        _syslogDto.setSource("주문확정(번호변경)");
                        _syslogDto.setMessage(ls_misdate + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getCltcd() + "->" + misdate + "/" );
                    }else{
                        _syslogDto.setSource("주문확정(번호변경)");
                        _syslogDto.setMessage(_Da024Dto.getMisdate() + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getSeq());
                    }
                    _syslogDto.setUserid(userid);
                    _syslogDto.setUsernm(usernm);
                    result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                    if (!result) {
                        //return "error";
                    }

                    log.info("변경");
                    return "success";
                }

//                log.info(misdateArr.size() );
                // 개별확정방식으로변경  장바구니에서 체크한것만 주문으로 넘어가도록 방식 변경 23.3.7
                // 기존주문일자를 기준으로 요청내용을 변경한뒤 변경주문일자로 업데이트
                switch (mflag){
                    case "AA" :
                        _Da024Dto.setRemarkaa(jremark);
                        result = service14.UpdateDA024rkaa(_Da024Dto);
                        break;
                    case "BB":
                        _Da024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA024rkbb(_Da024Dto);
                        break;
                    case "CC":
                        _Da024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA024rkbb(_Da024Dto);
                        break;
                    default:
                        break;
                }

                if (!result){
                    return "error";
                }
                // 장바구니주문 확정 및 수량 수정 / 주문일자 변경도가능
                if( misdateArr.size() > 0) {
                    _Da024Dto.setMisdate(misdate);
                    _Da024Dto.setMisdateArr(misdateArr);
                    _Da024Dto.setMisnumArr(misnumArr);
                    _Da024Dto.setSeqArr(seqArr);
                    _Da024Dto.setMisqtyArr(misqty);

                    // Convert List<String> to comma-separated String
                    misdateArrStr = (_Da024Dto.getMisdateArr() != null && !_Da024Dto.getMisdateArr().isEmpty())
                            ? String.join(",", _Da024Dto.getMisdateArr())
                            : null;
                    misnumArrStr = (_Da024Dto.getMisnumArr() != null && !_Da024Dto.getMisnumArr().isEmpty())
                            ? String.join(",", _Da024Dto.getMisnumArr())
                            : null;
                    seqArrStr = (_Da024Dto.getSeqArr() != null && !_Da024Dto.getSeqArr().isEmpty())
                            ? String.join(",", _Da024Dto.getSeqArr())
                            : null;
                    misqtyStr = (_Da024Dto.getMisqtyArr() != null && !_Da024Dto.getMisqtyArr().isEmpty())
                            ? _Da024Dto.getMisqtyArr().stream().map(String::valueOf).collect(Collectors.joining(","))
                            : null;
                    _Da024Dto.setMisdateStr(misdateArrStr);
                    _Da024Dto.setMisnumStr(misnumArrStr);
                    _Da024Dto.setSeqStr(seqArrStr);
                    _Da024Dto.setMisqtyStr(misqtyStr);
//                    log.info(misdateArrStr);
//                    log.info(misnumArrStr);
//                    log.info(seqArrStr);
//                    log.info(misqtyStr);

                    Boolean _liresult = service14.UpdateDA024Makfix(_Da024Dto);

//                    log.info(_liresult);

                    _syslogDto.setType("등록");
                    _syslogDto.setMenunm("주문등록");
                    if(!misdate.equals(ls_misdate)) {
                        _syslogDto.setSource("주문확정(일자변경)");
                        _syslogDto.setMessage(ls_misdate + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getCltcd() + "->" + misdate + "/" );
                    }else{
                        _syslogDto.setSource("주문확정");
                        _syslogDto.setMessage(_Da024Dto.getMisdate() + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getSeq());
                    }
                    _syslogDto.setUserid(userid);
                    _syslogDto.setUsernm(usernm);
                    result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                    if (!result) {
                        //return "error";
                    }

                }
                return "success";
            }
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisgubun(mflag);
            _indexDa023Dto.setYyyymm(year + month);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnum(_indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = GetTrackNum(_indexDa023Dto.getMisdate());
                if(ls_misnum.equals("error")){
                    return "error";
                }
//                ls_misnum = service14.SelectCheckMisnumMkflag(_indexDa023Dto);  //주문된 순번max 찾기
//                if(ls_misnum == null){
//                    ls_misnum = "0001";
//                }else{
//                    Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
//                    ls_misnum = ll_misnum.toString();
//                    if (ls_misnum.length() == 1){
//                        ls_misnum = "000" + ls_misnum;
//                    }else if(ls_misnum.length() == 2){
//                        ls_misnum = "00" + ls_misnum;
//                    }else {
//                        ls_misnum = "0" + ls_misnum;
//                    }
//                }
            }else{
                ls_misnum = ls_chknull;
            }
            _indexDa023Dto.setMisnum(ls_misnum);

            switch (mflag){
                case "AA" :
                    _indexDa023Dto.setPerid("");
                    break;
                case "BB":
                    _indexDa023Dto.setPerid("");
                    break;
                case "CC":
                    _indexDa023Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa023Dto.setContamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setMisamt(0);
            _indexDa023Dto.setAmt(0);
            _indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            _indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            _indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            _indexDa023Dto.setBigo(jremark);
            _indexDa023Dto.setRemark(jremark);
//            _indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            _indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            _indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            _indexDa023Dto.setGubun("");
            _Da024Dto.setMisdate(frdate);
            _Da024Dto.setMisnum(ls_misnum);
            _Da024Dto.setMisgubun(mflag);
            _Da024Dto.setPcode(_index03Dto.getJkey());
            _Da024Dto.setPname(_index03Dto.getJpum());
            _Da024Dto.setPsize(_index03Dto.getJgugek());
            _Da024Dto.setPbonsa(_index03Dto.getJbonsa_code());
            _Da024Dto.setPmodel(_index03Dto.getJmodel_code());
            _Da024Dto.setPcolor(_index03Dto.getJcolor_code());
            _Da024Dto.setCltcd(_indexDa023Dto.getCltcd());
            _Da024Dto.setAcorp(_index02Dto.getAcorp());
            _Da024Dto.setPernm(_index02Dto.getInname01());
            _Da024Dto.setPerid(_index02Dto.getAbonsadam1());
            _Da024Dto.setJpb_gubn(_index03Dto.getJpb_gubn());

            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                //ls_seq = GetMaxSeq(_indexDa023Dto, frdate);
                //동일업체 동일일자 동일품목이 있는지 체크
                _index024Dto = service14.SelectCheckJpum024(_Da024Dto);  //주문된 순번max 찾기

                if (_index024Dto != null){
                    ls_seq = _index024Dto.getSeq();
                    if (ls_seq.length() > 0 && ls_seq != null ){
                        Integer _ll_qty = _index024Dto.getQty() + 1;
                        Integer _ll_uamt = _index024Dto.getUamt();
                        Integer _ll_samt = 0 ;
                        Integer _ll_addamt = 0 ;
                        Integer _ll_amt = 0;
                        _Da024Dto.setSeq(ls_seq);
                        _Da024Dto.setQty(_ll_qty);
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            _Da024Dto.setSamt(_ll_samt);
                            _Da024Dto.setAddamt(_ll_addamt);
                            _Da024Dto.setAmt(_ll_amt);
                        }else{
                            _Da024Dto.setSamt(0);
                            _Da024Dto.setAddamt(0);
                            _Da024Dto.setAmt(0);
                        }
                        result = service14.UpdateDA024QtySame(_Da024Dto);
                        if (!result){
                            return "error";
                        }else{
                            _syslogDto.setType("수정");
                            _syslogDto.setMenunm("주문등록");
                            _syslogDto.setSource("주문확정");
                            _syslogDto.setMessage(_Da024Dto.getMisdate() + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getSeq());
                            _syslogDto.setUserid(userid);
                            _syslogDto.setUsernm(usernm);
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                        return "success";
                    }else{
                        ls_seq = GetMaxSeq(_indexDa023Dto, frdate);
                    }
                }else{
                    ls_seq = GetMaxSeq(_indexDa023Dto, frdate);
                }
            }

            _Da024Dto.setAddamt(0);
            _Da024Dto.setSeq(ls_seq);
            _Da024Dto.setQty(jqty);
            String ls_chulgoga = _index03Dto.getJchgoga0();
            if( ls_chulgoga == null ){
                ls_chulgoga = "0";
            }
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            if(ll_chulgoga > 0 ) {
                _Da024Dto.setAddamt(ll_chulgoga / 10);
                _Da024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            }else{
                _Da024Dto.setAddamt(0);
                _Da024Dto.setAmt(0);
            };

            _Da024Dto.setUamt(ll_chulgoga);
            _Da024Dto.setSamt(ll_chulgoga);
            _Da024Dto.setIndate(getToDate());
            _Da024Dto.setInperid(perid);
            _Da024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa023(_indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa024(_Da024Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("등록");
                _syslogDto.setMenunm("일반주문등록");
                _syslogDto.setSource("일반주문 장바구니등록");
                _syslogDto.setMessage(_Da024Dto.getMisdate() + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getSeq());
                _syslogDto.setUserid(userid);
                _syslogDto.setUsernm(usernm);
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }

        }catch (Exception e){
            log.info("index14/savecust 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            ,@RequestParam( value =  "cltcdArr[]") List<String> cltcdArr
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCustDel Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index02Dto _index02Dto = new Index02Dto();
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            Boolean result = false;
            String year = "";
            String month = "";
            String day = "";
            if(frdate == null || frdate.equals("")){
                year = misdateArr.get(0).substring(0,4);
                month = misdateArr.get(0).substring(5,7);
                day = misdateArr.get(0).substring(8,10);
                frdate = year + month + day ;
            }else{
                year = frdate.substring(0,4) ;
                month = frdate.substring(5,7) ;
                day   = frdate.substring(8,10) ;
                frdate = year + month + day ;
            }
            _index02Dto.setAcode(acode);
            _indexDa023Dto.setMisgubun(mflag);
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisdate(frdate);
            //장바구니 삭제
            if (!misdate.equals("0000")){
                if( misdateArr.size() > 0){
                    for(int i = 0; i < misdateArr.size(); i++){
                        year = misdateArr.get(i).substring(0,4);
                        month = misdateArr.get(i).substring(5,7);
                        day = misdateArr.get(i).substring(8,10);
                        String ls_misdate = year + month + day ;
                        _index02Dto.setAcode(acode);
                        _indexDa024Dto.setCltcd(cltcdArr.get(i));
                        _indexDa024Dto.setMisgubun(mflag);
                        _indexDa024Dto.setMisdate(ls_misdate);
                        _indexDa024Dto.setMisnum(misnumArr.get(i));
                        _indexDa024Dto.setSeq(seqArr.get(i));
                        _indexDa024Dto.setDeluserid(userid);
                        _indexDa024Dto.setDeluser(usernm);
                        result = service14.DeleteDA024Mkflag(_indexDa024Dto);
                        if (!result){
                            log.info("error  =====> " + result);
                            return "error";
                        }
                        result = service14.DeleteDA023(_indexDa024Dto);
                        if (!result){
                            //return "error";
                        }
                        result = service14.UpdateDA024Del(_indexDa024Dto);
                        if (!result){
                            //return "error";
                        }else{
                            _syslogDto.setType("삭제");
                            _syslogDto.setMenunm("주문등록");
                            _syslogDto.setSource("주문삭제");
                            _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                            _syslogDto.setUserid(userid);
                            _syslogDto.setUsernm(usernm);
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }

                    }
                }
                return "success";
            }

        } catch (Exception e){
            log.info("index14/savecustdel 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCustWisDel Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index02Dto _index02Dto = new Index02Dto();
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            Boolean result = false;
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;
            _index02Dto.setAcode(acode);
            _indexDa023Dto.setMisgubun(mflag);
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisdate(frdate);
            //장바구니 삭제
            if (!misdate.equals("0000")){
                if( misdateArr.size() > 0){
                    for(int i = 0; i < misdateArr.size(); i++){
                        year = misdateArr.get(i).substring(0,4);
                        month = misdateArr.get(i).substring(5,7);
                        day = misdateArr.get(i).substring(8,10);
                        String ls_misdate = year + month + day ;
                        _indexDa024Dto.setCltcd(acode);
                        _indexDa024Dto.setMisgubun(mflag);
                        _indexDa024Dto.setMisdate(ls_misdate);
                        _indexDa024Dto.setMisnum(misnumArr.get(i));
                        _indexDa024Dto.setSeq(seqArr.get(i));
                        _indexDa024Dto.setDeluserid(userid);
                        _indexDa024Dto.setDeluser(usernm);
                        result = service14.DeleteDA026Mkflag(_indexDa024Dto);
                        if (!result){
                            return "error";
                        }
                        result = service14.UpdateDA026Del(_indexDa024Dto);
                        if (!result){
                            //return "error";
                        }else{
                            _syslogDto.setType("삭제");
                            _syslogDto.setMenunm("주문등록");
                            _syslogDto.setSource("주문예약삭제");
                            _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                            _syslogDto.setUserid(userid);
                            _syslogDto.setUsernm(usernm);
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                    }
                }
                result = service14.DeleteDA025(_indexDa024Dto);
                if (!result){
                    //return "error";
                }
                return "success";
            }

        } catch (Exception e){
            log.info("index14/savecustwishdel 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
                                 @RequestParam("userid") String userid,
                                 @RequestParam("usernm") String usernm,
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();

        try {
            SyslogDto _syslogDto = new SyslogDto();
            String year = misdate.substring(0,4) ;
            String month = misdate.substring(5,7) ;
            String day   = misdate.substring(8,10) ;
            misdate = year + month + day ;
            _indexDa024Dto.setMisdate(misdate);
            _indexDa024Dto.setMisnum(misnum);
            _indexDa024Dto.setSeq(seq);
            _indexDa024Dto.setCltcd(cltcd);
            _indexDa024Dto.setMisgubun(mflag);
            Boolean result = false;
            result = service14.DeleteDA024(_indexDa024Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("삭제");
                _syslogDto.setMenunm("주문등록");
                _syslogDto.setSource("주문삭제");
                _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                _syslogDto.setUserid(userid);
                _syslogDto.setUsernm(usernm);
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }
            result = service14.DeleteDA023(_indexDa024Dto);
            if (!result){
                //return "error";
            }

        } catch (Exception e){
            log.info("index14/del 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();

        try {
            SyslogDto _syslogDto = new SyslogDto();
            String year = misdate.substring(0,4) ;
            String month = misdate.substring(5,7) ;
            String day   = misdate.substring(8,10) ;
            misdate = year + month + day ;
            _indexDa024Dto.setMisdate(misdate);
            _indexDa024Dto.setMisnum(misnum);
            _indexDa024Dto.setSeq(seq);
            _indexDa024Dto.setCltcd(cltcd);
            _indexDa024Dto.setMisgubun(mflag);
            Boolean result = false;
            result = service14.DeleteDA026(_indexDa024Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("삭제");
                _syslogDto.setMenunm("예약등록");
                _syslogDto.setSource("예약삭제");
                _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                _syslogDto.setUserid(userformDto.getUserid());
                _syslogDto.setUsernm(userformDto.getUsername());
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }
            result = service14.DeleteDA025(_indexDa024Dto);
            if (!result){
                return "error";
            }

        }  catch (Exception e){
            log.info("index14/delwish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
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
            ,@RequestParam("perid") String perid
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            ,@RequestParam( value =  "misdateArr[]") List<String> misdateArr
            ,@RequestParam( value =  "misnumArr[]") List<String> misnumArr
            ,@RequestParam( value =  "seqArr[]") List<String> seqArr
            ,@RequestParam( value =  "misqty[]") List<String> misqty
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index14SaveCustWish Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);
            Index03Dto index03Dto_S = new Index03Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();
            IndexDa024Dto _Da024Dto = new IndexDa024Dto();
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
            _Da024Dto.setUserid(userid);
            _Da024Dto.setUsernm(usernm);
            //비고업데이트
            if (!misdate.equals("0000")){
                year = misdate.substring(0,4) ;
                month = misdate.substring(5,7) ;
                day   = misdate.substring(8,10) ;
                misdate = year + month + day ;
                _Da024Dto.setQty(jqty);
                _Da024Dto.setMisdate(misdate);
                _Da024Dto.setMisnum(misnum);
                _Da024Dto.setSeq(seq);
                _Da024Dto.setCltcd(acode);
                _Da024Dto.setMisgubun(mflag);
                switch (mflag){
                    case "AA" :
                        _Da024Dto.setRemarkaa(jremark);
                        result = service14.UpdateDA026rkaa(_Da024Dto);
                        break;
                    case "BB":
                        _Da024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA026rkbb(_Da024Dto);
                        break;
                    case "CC":
                        _Da024Dto.setRemarkbb(jremark);
                        result = service14.UpdateDA026rkbb(_Da024Dto);
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
                        _Da024Dto.setMisdate(ls_misdate);
                        _Da024Dto.setMisnum(misnumArr.get(i));
                        _Da024Dto.setSeq(seqArr.get(i));
                        _Da024Dto.setQty(Integer.parseInt(misqty.get(i)));


                        _indexDa024Dto.setMisdate(_Da024Dto.getMisdate());
                        _indexDa024Dto.setMisnum(_Da024Dto.getMisnum());
                        _indexDa024Dto.setSeq(_Da024Dto.getSeq());
                        _indexDa024Dto.setCltcd(_Da024Dto.getCltcd());
                        _indexDa024Dto.setMisgubun(_Da024Dto.getMisgubun());

                        _indexDa024Dto = service14.SelectDa026Detail02(_indexDa024Dto);
                        Integer _ll_uamt = _indexDa024Dto.getUamt();
                        Integer _ll_samt = 0;
                        Integer _ll_addamt = 0;
                        Integer _ll_amt = 0;
                        Integer _ll_qty = _Da024Dto.getQty();
                        if(_ll_uamt > 0){
                            _ll_samt = _ll_qty * _ll_uamt;
                            _ll_addamt = _ll_samt / 10 ;
                            _ll_amt = _ll_samt + _ll_addamt;
                            _Da024Dto.setSamt(_ll_samt);
                            _Da024Dto.setAddamt(_ll_addamt);
                            _Da024Dto.setAmt(_ll_amt);
                        }else{
                            _Da024Dto.setSamt(0);
                            _Da024Dto.setAddamt(0);
                            _Da024Dto.setAmt(0);
                        }

                        result = service14.UpdateDA026Qty(_Da024Dto);
                        if (!result){
                            return "error";
                        }else{
                            _syslogDto.setType("수정");
                            _syslogDto.setMenunm("예약등록");
                            _syslogDto.setSource("예약수정");
                            _syslogDto.setMessage(_Da024Dto.getMisdate() + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getSeq());
                            _syslogDto.setUserid(userformDto.getUserid());
                            _syslogDto.setUsernm(userformDto.getUsername());
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                    }
                }
                return "success";
            }


            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setMisdate(frdate);
            _indexDa023Dto.setMisgubun(mflag);
            String ls_misnum = "";
            String ls_chknull = service14.SelectCheckMisnumWish(_indexDa023Dto);
            if(ls_chknull == null){
                ls_chknull = "";
            }
            if(ls_chknull.length() == 0){
                ls_misnum = service14.SelectCheckMisnumWishMkflag(_indexDa023Dto);  //주문된 순번max 찾기
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
            _indexDa023Dto.setMisnum(ls_misnum);
            _indexDa023Dto.setCltcd(acode);
            _indexDa023Dto.setYyyymm(year + month);

            switch (mflag){
                case "AA" :
                    _indexDa023Dto.setPerid("");
                    break;
                case "BB":
                    _indexDa023Dto.setPerid("");
                    break;
                case "CC":
                    _indexDa023Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa023Dto.setContamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setAddamt(0);
            _indexDa023Dto.setMisamt(0);
            _indexDa023Dto.setAmt(0);
            _indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
            _indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
            _indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
            _indexDa023Dto.setBigo(jremark);
            _indexDa023Dto.setRemark(jremark);
//            _indexDa023Dto.setVatemail(_index02Dto.getAemail());  //계산서 메일주소
//            _indexDa023Dto.setVatpernm(_index02Dto.getInname01());  //계산서 담당자
            _indexDa023Dto.setSpjangnum(index02BonsaDto.getAcorp());
            _indexDa023Dto.setGubun("");
            String ls_seq = "";
            if (ls_chknull.length() == 0 ){
                ls_seq = "001";
            }else{
                ls_seq = GetMaxSeqWish(_indexDa023Dto, frdate);
            }

            String ls_chulgoga = index03Dto_S.getJchgoga0();
            Integer ll_chulgoga = Integer.parseInt(ls_chulgoga);
            if( ls_chulgoga == null ||  ll_chulgoga == 0 || ll_chulgoga == null ){
                ls_chulgoga = "0";
            }
            _Da024Dto.setSeq(ls_seq);
            _Da024Dto.setMisdate(frdate);
            _Da024Dto.setMisnum(ls_misnum);
            _Da024Dto.setMisgubun(mflag);
            _Da024Dto.setPcode(index03Dto_S.getJkey());
            _Da024Dto.setPname(index03Dto_S.getJpum());
            _Da024Dto.setPsize(index03Dto_S.getJgugek());
            _Da024Dto.setPbonsa(index03Dto_S.getJbonsa_code());
            _Da024Dto.setPmodel(index03Dto_S.getJmodel_code());
            _Da024Dto.setPcolor(index03Dto_S.getJcolor_code());
            _Da024Dto.setQty(jqty);
            _Da024Dto.setUamt(ll_chulgoga);
            _Da024Dto.setSamt(ll_chulgoga);
            _Da024Dto.setCltcd(_indexDa023Dto.getCltcd());
            _Da024Dto.setAcorp(_index02Dto.getAcorp());
            _Da024Dto.setPernm(_index02Dto.getInname01());
            _Da024Dto.setPerid(_index02Dto.getAbonsadam1());
            _Da024Dto.setJpb_gubn(index03Dto_S.getJpb_gubn());
            _Da024Dto.setAddamt(0);
            if(ll_chulgoga > 0 ) {
                _Da024Dto.setAddamt(ll_chulgoga / 10);
                _Da024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
            }else{
                _Da024Dto.setAddamt(0);
                _Da024Dto.setAmt(0);
            };

            _Da024Dto.setIndate(getToDate());
            _Da024Dto.setInperid(perid);
            _Da024Dto.setPunit("EA");
            if (ls_chknull.length() == 0){
                result = service14.InsertDa025(_indexDa023Dto);
                if (!result){
                    return "error";
                }
            }
            result = service14.InsertDa026(_Da024Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("등록");
                _syslogDto.setMenunm("예약등록");
                _syslogDto.setSource("예약등록");
                _syslogDto.setMessage(_Da024Dto.getMisdate() + "/" + _Da024Dto.getMisnum() + "/" + _Da024Dto.getSeq());
                _syslogDto.setUserid(userformDto.getUserid());
                _syslogDto.setUsernm(userformDto.getUsername());
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }

        } catch (Exception e){
            log.info("index14/savecustwish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    //주문현황 리스트
    @GetMapping(value="/index14/list")
    public Object App14List_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("acode") String acode,
                                  @RequestParam("jcode") String jcode,
                                  @RequestParam("fixflag") String fixflag,
                                  @RequestParam("devflag") String devflag,
                                  @RequestParam("perid") String perid,
                                  @RequestParam("misgubun") String misgubun,
                                  @RequestParam("jpbgubn") String jpbgubn,
                                  @RequestParam("makflag") String makflag,
                                  @RequestParam("mflag") String mflag,
                                  @RequestParam("checkAS") String checkAS,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
        if(userformDto == null){
            log.info("App14List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);
        try {
            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            String ls_flag = mflag;
            frdate = year + month + day ;
            year = todate.substring(0,4) ;
            month = todate.substring(5,7) ;
            day   = todate.substring(8,10) ;
            todate = year + month + day ;
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            if(jcode == null || jcode.equals("")){
                jcode = "%";
            }
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setDevflag(devflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            _indexDa024Dto.setPcode(jcode);

            if(perid == null || perid.equals("")){
                perid = "%";
            }
            _indexDa024Dto.setMisgubun(misgubun);
            mflag = "%";
//            log.info("ls_flag=====>" + ls_flag);
//            log.info("ls_misgubun====>" + misgubun);
//            switch (ls_flag){
//                case "AA" :
//                    _indexDa024Dto.setMisgubun(misgubun);
//                    break;
//                case "BB":
//                    _indexDa024Dto.setMisgubun(ls_flag);
//                    break;
//                case "CC":
//                    _indexDa024Dto.setMisgubun(ls_flag);
//                    break;
//                default:
//                    break;
//            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setJfrdate("20000101");
            _indexDa024Dto.setJtodate(todate);
//            log.info("frdate =====>" + frdate);
//            log.info("todate =====>" + todate);
//            log.info("acode =====>" + acode);
//            log.info("jcode =====>" + jcode);
//            log.info("perid =====>" + perid);
//            log.info("fixflag =====>" + fixflag);
//            log.info("devflag =====>" + devflag);
//            log.info("misgubun =====>" + misgubun);
//            log.info("jpbgubn =====>" + jpbgubn);
//            log.info("makflag =====>" + misgubun);
//            log.info("mflag =====>" + mflag);

            if(checkAS.equals("AS")){
                //as접수 포함
                if(acode.equals("%")){
                    _indexDa024ListDto = service14.SelectDa024ListLikeAS(_indexDa024Dto);
                }else{
                    _indexDa024ListDto = service14.SelectDa024ListAS(_indexDa024Dto);
                }
            }else{
                //as접수 미포함
                if(acode.equals("%")){
                    _indexDa024ListDto = service14.SelectDa024ListLike(_indexDa024Dto);
                }else{
                    _indexDa024ListDto = service14.SelectDa024List(_indexDa024Dto);
                }
            }
//            log.info("misgubun2 =====>" + misgubun);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
    }

    //AS주문현황 리스트
    @GetMapping(value="/index14/listjupsu")
    public Object App14ListJupsu_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("asflag") String asflag,
                                  @RequestParam("asacorp") String asacorp,
                                  @RequestParam("condamdang") String condamdang,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("접수등록");
        CommDto.setMenuUrl("접수등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
        if(userformDto == null){
            log.info("App14ListJupsu_index Exception =====> relogin userformDto null");
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(asacorp);
            _indexDa024Dto.setFixflag(asflag);
            _indexDa024Dto.setJfrdate("20000101");
            _indexDa024Dto.setJtodate(todate);
            _indexDa024Dto.setUserid(condamdang);
            _indexDa024ListDto = service14.SelectDa024ListLikeJupsu(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
                                  @RequestParam("jpbgubn") String jpbgubn,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setDevflag(devflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            //영업사원과 본사가 같은업체 같은 품목으로 겹칠수있어 구분을 넣음.
            _indexDa024Dto.setMisgubun(misgubun);

            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJfrdate("20000101");
            _indexDa024Dto.setJtodate(todate);
            if(acode.equals("%")){
                _indexDa024Dto.setFrdate("20000101");
                _indexDa024Dto.setTodate("21000101");
                _indexDa024ListDto = service14.SelectDa024ListLikeJang(_indexDa024Dto);
            }else{
                _indexDa024ListDto = service14.SelectDa024ListJang(_indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jcode);
            _indexDa024Dto.setJpb_gubn(jpbgubn);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    mflag = "%";
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            //indexDa024Dto.setMisgubun(mflag);
            //log.info("jpbgubn =====>" + jpbgubn);
            _indexDa024ListDto = service14.SelectDa024ListDel(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listdel 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
    }


    //주문현황 리스트
    @GetMapping(value="/index14/loglist")
    public Object App14Loglist_index(@RequestParam("frdate") String frdate,
                                     @RequestParam("todate") String todate,
                                     @RequestParam("typeflag") String typeflag,
                                     @RequestParam("menuflag") String menuflag,
                                     @RequestParam("userflag") String userflag,
                                     @RequestParam("messagetxt") String messagetxt,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>로그현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        SyslogDto _syslogDto = new SyslogDto();
        List<SyslogDto> _syslogDtoListDto = new ArrayList<>();
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
            _syslogDto.setFrdate(frdate);
            _syslogDto.setTodate(todate);
            _syslogDto.setType(typeflag);
            _syslogDto.setMenunm(menuflag);
            _syslogDto.setUsernm(userflag);
            _syslogDto.setMessage(messagetxt);
            _syslogDtoListDto = service_auth.TB_GET_LOGLIST(_syslogDto);
            model.addAttribute("_syslogDtoListDto",_syslogDtoListDto);

        } catch (Exception e){
            log.info("index14/loglist 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _syslogDtoListDto;
    }

    //장바구니현황 리스트
    @GetMapping(value="/index14/listmakflag")
    public Object App14ListMakflag_index(@RequestParam("frdate") String frdate,
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
        CommDto.setMenuUrl("주문등록>장바구니현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jcode);
            _indexDa024Dto.setJpb_gubn(jpbgubn);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    mflag = "%";
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            //indexDa024Dto.setMisgubun(mflag);
            //log.info("jpbgubn =====>" + jpbgubn);
            _indexDa024ListDto = service14.SelectDa024ListMakflag(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
                    if(ls_tempItem.equals( ls_misdate + misnumarr.get(i) + seqarr.get(i)  + cltcdarr.get(i))){
                        continue;
                    }
                    itemString[ll_count] = ls_misdate + misnumarr.get(i) + seqarr.get(i)  + cltcdarr.get(i);  //
                    ll_count++;
                    ls_tempItem = ls_misdate  + misnumarr.get(i) + seqarr.get(i) + cltcdarr.get(i);  //
                    //log.info("itemString =====>" + ls_misdate + misnumarr.get(i) + seqarr.get(i) + cltcdarr.get(i));
                }
                hm.put("itemcdArr", itemString);
                indexDa024ListDto = service14.SelectDa024ListPrtGroup(hm);
            }

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return indexDa024ListDto;
    }

    //주문배송현황 리스트
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
                    String ls_misdate = "";
                    if(misdatearr.get(i).length() == 10){
                        String year = misdatearr.get(i).substring(0,4);
                        String month = misdatearr.get(i).substring(5,7);
                        String day = misdatearr.get(i).substring(8,10);
                        ls_misdate = year + month + day ;
                    }else{
                        ls_misdate = misdatearr.get(i) ;
                    }
                    if(ls_tempItem.equals( ls_misdate + misnumarr.get(i) +   cltcdarr.get(i))){
                        continue;
                    }
                    itemString[ll_count] = ls_misdate + misnumarr.get(i) +  cltcdarr.get(i);
                    ll_count++;
//                    log.info("cltcdarr=====>" +  cltcdarr.get(i));
                    ls_tempItem = ls_misdate +  misnumarr.get(i) +  cltcdarr.get(i);
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
                                  @RequestParam("sflag") String sflag,
                                  @RequestParam("jkey") String jkey,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jkey);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn("%");
            //출력일
            if(sflag.equals("0")){
                _indexDa024ListDto = service14.SelectDa024ListPerid(_indexDa024Dto);
            }else{
                _indexDa024ListDto = service14.SelectDa024ListPeridJumun(_indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
                                       @RequestParam("sflag") String sflag,
                                       @RequestParam("jkey") String jkey,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024PeridDto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn("%");
            _indexDa024Dto.setPcode(jkey);
            //출력일 기준
            if(sflag.equals("0")){
                _indexDa024ListDto = service14.SelectDa024ListPeridGroup(_indexDa024Dto);
            }else{
                _indexDa024ListDto = service14.SelectDa024ListPeridGroupJumun(_indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            _indexDa024ListDto = service14.SelectDa024ListCltcd(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
                                       @RequestParam("sflag") String sflag,
                                            @RequestParam("jkey") String jkey,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jkey);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            _indexDa024Dto.setPerid(perid);
//            switch (mflag){
//                case "AA" :
//                    _indexDa024Dto.setPerid(perid);
//                    break;
//                case "BB":
//                    _indexDa024Dto.setPerid(perid);
//                    break;
//                case "CC":
//                    _indexDa024Dto.setPerid(perid);
//                    break;
//                default:
//                    break;
//            }
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn("%");

//            log.info("acode =====>" +acode);
//            log.info("fixflag =====>" +fixflag);
//            log.info("makflag =====>" +makflag);
//            log.info("perid =====>" +perid);
//            log.info("mflag =====>" +mflag);
//            log.info("jpbgubn =====>" +jpbgubn);
            //출력일기준
            if(sflag.equals("0")){
                _indexDa024ListDto = service14.SelectDa024ListCltcdGroup(_indexDa024Dto); //출력일
            }else{
                _indexDa024ListDto = service14.SelectDa024ListCltcdGroupJumun(_indexDa024Dto); //주문일
            }
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
    }

    //as배송현황 리스트
    @GetMapping(value="/index14/listdevjupsu")
    public Object App14ListDevJupsu_index(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
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
                    String ls_misdate = "";
                    ls_misdate = misdatearr.get(i) ;
                    if(ls_tempItem.equals( ls_misdate + misnumarr.get(i) )){
                        continue;
                    }
                    itemString[ll_count] = ls_misdate + misnumarr.get(i) ;
                    ll_count++;
//                    log.info("ls_misdate=====>" + ls_misdate);
//                    log.info("misnumarr=====>" + misnumarr.get(i));
//                    log.info("cltcdarr=====>" +  cltcdarr.get(i));
                    ls_tempItem = ls_misdate + misnumarr.get(i) ;
//                    log.info("itemString =====>" + ls_misdate + misnumarr.get(i) + seqarr.get(i) + cltcdarr.get(i));
                }
                hm.put("itemcdArr", itemString);
                indexDa024ListDto = service14.SelectDa024ListDevJupsuGroup(hm);

            }

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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setPcode(jkey);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            //log.info("jpbgubn 111=====>" + jpbgubn);
            _indexDa024ListDto = service14.SelectDa024ListJpum(_indexDa024Dto);
            //log.info("jpbgubn 222=====>" + jpbgubn);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14List_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
                                           @RequestParam("sflag") String sflag,
                                      Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("주문등록>주문현황");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setPcode(jkey);
            _indexDa024Dto.setJpbgubn("%");
            //출력일기준
            if(sflag.equals("0")){
                _indexDa024ListDto = service14.SelectDa024ListJpumGroup(_indexDa024Dto);
            }else{
                _indexDa024ListDto = service14.SelectDa024ListJpumGroupJumun(_indexDa024Dto);
            }

            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListJkeyGroup_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            _indexDa024ListDto = service14.SelectDa024ListJpumArea(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception ex) {
            log.info("App14ListSido_index Exception =====>" + ex.toString());
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setPerid(perid);
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            _indexDa024ListDto = service14.SelectDa024ListJpumAreaGugun(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listgugun 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jcode);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun(misgubun);
                   //mflag = "%";
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            //indexDa024Dto.setMisgubun(mflag);
//            log.info("frdate =====>" + _indexDa024Dto.getFrdate());
//            log.info("todate =====>" + _indexDa024Dto.getTodate());
//            log.info("acode =====>" + _indexDa024Dto.getCltcd());
//            log.info("fixflag =====>" + _indexDa024Dto.getFixflag());
//            log.info("makflag =====>" + _indexDa024Dto.getMakflag());
//            log.info("jcode =====>" + _indexDa024Dto.getPcode());
//            log.info("jpbgubn =====>" + _indexDa024Dto.getJpbgubn());
            _indexDa024Dto.setJfrdate("20000101");
            _indexDa024Dto.setJtodate(getToDate());
            if(acode.equals("%")){
                _indexDa024ListDto = service14.SelectDa026ListLike(_indexDa024Dto);
            }else{
                _indexDa024ListDto = service14.SelectDa026List(_indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listwish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jcode);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun(misgubun);
                    //mflag = "%";
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setMisgubun(mflag);
            //log.info("jpbgubn Exception =====>" + indexDa024Dto.getJpbgubn());
            if(acode.equals("%")){
                _indexDa024ListDto = service14.SelectDa026ListLike(_indexDa024Dto);
            }else{
                _indexDa024ListDto = service14.SelectDa026List(_indexDa024Dto);
            }
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listwishjang 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            _indexDa024Dto.setPcode(jcode);
            _indexDa024Dto.setJpbgubn("%");
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    //mflag = "%";
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    _indexDa024Dto.setMisgubun("%");
                    break;
                default:
                    break;
            }
            //indexDa024Dto.setMisgubun(mflag);
//            log.info("Misgubun Exception =====>" + indexDa024Dto.getMisgubun());
            _indexDa024ListDto = service14.SelectDa026ListDel(_indexDa024Dto);

            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listwishdel 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024ListDto = service14.SelectDa026ListPerid(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listwishperid 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            _indexDa024ListDto = service14.SelectDa026ListCltcd(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listwishcltcd 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        List<IndexDa024Dto> _indexDa024ListDto = new ArrayList<>();
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
            _indexDa024Dto.setFrdate(frdate);
            _indexDa024Dto.setTodate(todate);
            _indexDa024Dto.setCltcd(acode);
            _indexDa024Dto.setPcode(jkey);
            _indexDa024Dto.setFixflag(fixflag);
            _indexDa024Dto.setMakflag(makflag);
            if(perid == null || perid.equals("")){
                perid = "%";
            }
            mflag = "%";
            switch (mflag){
                case "AA" :
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "BB":
                    _indexDa024Dto.setPerid(perid);
                    break;
                case "CC":
                    _indexDa024Dto.setPerid(perid);
                    break;
                default:
                    break;
            }
            _indexDa024Dto.setMisgubun(mflag);
            _indexDa024Dto.setJpbgubn(jpbgubn);
            //log.info("jpbgubn 222=====>" + jpbgubn);
            _indexDa024ListDto = service14.SelectDa026ListJpum(_indexDa024Dto);
            model.addAttribute("indexDa024ListDto",_indexDa024ListDto);

        } catch (Exception e){
            log.info("index14/listwishjkey 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }

        return _indexDa024ListDto;
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
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            boolean result = false;
            HashMap hm = new HashMap();


            //당일 배송순번 채번
            String ls_devnum, ls_devdate;
            ls_devdate = getToDate();
            ls_devnum = GetTrackDevNum(getToDate());
            if(ls_devnum.equals("error")){
                return "error";
            }

            String[] itemString =new String[misdatearr.size()];
            String ls_tempItem = "";
            Integer ll_count = 0;
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    _indexDa024Dto.setMisdate(ls_misdate);
                    _indexDa024Dto.setMisnum(misnumarr.get(i));
                    _indexDa024Dto.setSeq(seqarr.get(i));
                    _indexDa024Dto.setCltcd(cltcdarr.get(i));
                    _indexDa024Dto.setMisgubun(gubunarr.get(i));
                    _indexDa024Dto.setFixdate(getToDate());
                    if(ls_tempItem.equals( "EEAN" + "ZZ" + ls_misdate + misnumarr.get(i) + seqarr.get(i)  + cltcdarr.get(i))){
                        continue;
                    }
                    itemString[ll_count] = "EEAN" + "ZZ" + ls_misdate + misnumarr.get(i) + seqarr.get(i)  + cltcdarr.get(i);  //
                    ll_count++;
                    ls_tempItem = "EEAN" + "ZZ" + ls_misdate  + misnumarr.get(i) + seqarr.get(i) + cltcdarr.get(i);  //
//                    if (!mflag.equals("AA")){
//                        _indexDa024Dto.setDevflag("1");
//                        result = service14.UpdateDA024Dev(_indexDa024Dto);
//                        if (!result){
//                            return "error";
//                        }
//                    }
                }
                //DD -->배송
                if (!mflag.equals("AA")){
                    hm.put("itemcdArr", itemString);
                    result = service14.UpdateDA024Dev(hm);
                    if (!result){
                        //return "error";
                    }
                }
                //DD -->확정
                if (mflag.equals("AA")){
                    hm.put("itemcdArr", itemString);
                    if (mflag.equals("AA")){
                        result = service14.UpdateDA024Fix(hm);
                        if (!result){
                            //return "error";
                        }
                    }
                }

            }

        }catch (Exception e){
            log.info("index16/save 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            SyslogDto _syslogDto = new SyslogDto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    _indexDa024Dto.setMisdate(ls_misdate);
                    _indexDa024Dto.setMisnum(misnumarr.get(i));
                    _indexDa024Dto.setSeq(seqarr.get(i));
                    _indexDa024Dto.setCltcd(cltcdarr.get(i));
                    _indexDa024Dto.setMisgubun(gubunarr.get(i));
                    _indexDa024Dto.setQty(qtyarr.get(i));
                    _indexDa024Dto.setUamt(uamtarr.get(i));
                    //_indexDa024Dto = service14.SelectDa024Detail(indexDa024Dto);
                    Integer _ll_uamt = _indexDa024Dto.getUamt();
                    Integer _ll_samt = 0;
                    Integer _ll_addamt = 0;
                    Integer _ll_amt = 0;
                    Integer _ll_qty = _indexDa024Dto.getQty();
                    if(_ll_uamt > 0){
                        _ll_samt = _ll_qty * _ll_uamt;
                        _ll_addamt = _ll_samt / 10 ;
                        _ll_amt = _ll_samt + _ll_addamt;
                        _indexDa024Dto.setSamt(_ll_samt);
                        _indexDa024Dto.setAddamt(_ll_addamt);
                        _indexDa024Dto.setAmt(_ll_amt);
                    }else{
                        _indexDa024Dto.setSamt(0);
                        _indexDa024Dto.setAddamt(0);
                        _indexDa024Dto.setAmt(0);
                    }

                    result = service14.UpdateDA024Amt(_indexDa024Dto);

                    if (!result){
                        return "error";
                    }else{
                        _syslogDto.setType("수정");
                        _syslogDto.setMenunm("주문현황");
                        _syslogDto.setSource("주문현황에서 수정");
                        _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                        _syslogDto.setUserid(userformDto.getUserid());
                        _syslogDto.setUsernm(userformDto.getUsername());
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }
                }

            }

        }catch (Exception e){
            log.info("index16/modify 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index16/modifyremk")
    public String index16ModifyRemk(@RequestParam(value = "mmisdate") String mmisdate
            ,@RequestParam( value =  "mmisnum") String mmisnum
            ,@RequestParam( value =  "mmisseq") String mmisseq
            ,@RequestParam( value =  "mcltcd") String mcltcd
            ,@RequestParam( value =  "mmisgubun") String mmisgubun
            ,@RequestParam( value =  "mremarkaa") String mremarkaa
            ,@RequestParam( value =  "mremarkbb") String mremarkbb
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            String year = mmisdate.substring(0,4);
            String month = mmisdate.substring(5,7);
            String day = mmisdate.substring(8,10);
            String ls_misdate = year + month + day ;
            _indexDa024Dto.setMisdate(ls_misdate);
            _indexDa024Dto.setMisnum(mmisnum);
            _indexDa024Dto.setCltcd(mcltcd);
            _indexDa024Dto.setMisgubun(mmisnum);
            _indexDa024Dto.setMisgubun(mmisgubun);
            _indexDa024Dto.setRemarkaa(mremarkaa);
            _indexDa024Dto.setRemarkbb(mremarkbb);
            result = service14.UpdateDA024Remk(_indexDa024Dto);

            if (!result){
                return "error";
            }
            return "success";

        }catch (Exception e){
            log.info("index16/modifyremk 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value="/index16/delarr")
    public String index16DelArr(@RequestParam(value = "misdatearr[]") List<String> misdatearr
            ,@RequestParam( value =  "misnumarr[]") List<String> misnumarr
            ,@RequestParam( value =  "seqarr[]") List<String> seqarr
            ,@RequestParam( value =  "cltcdarr[]") List<String> cltcdarr
            ,@RequestParam( value =  "gubunarr[]") List<String> gubunarr
            ,@RequestParam("mflag") String mflag
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            Index20Dto _indexDa020Dto = new Index20Dto();
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if( misdatearr.size() > 0){
                for(int i = 0; i < misdatearr.size(); i++){
                    String year = misdatearr.get(i).substring(0,4);
                    String month = misdatearr.get(i).substring(5,7);
                    String day = misdatearr.get(i).substring(8,10);
                    String ls_misdate = year + month + day ;
                    _indexDa024Dto.setMisdate(ls_misdate);
                    _indexDa024Dto.setMisnum(misnumarr.get(i));
                    _indexDa024Dto.setSeq(seqarr.get(i));
                    _indexDa024Dto.setCltcd(cltcdarr.get(i));
                    _indexDa024Dto.setMisgubun(gubunarr.get(i));
                    _indexDa024Dto.setDeluser(usernm);
                    _indexDa024Dto.setDeluserid(userid);
                    result = service14.DeleteDA024(_indexDa024Dto);
                    if (!result){
                        return "error";
                    }
                    result = service14.DeleteDA023(_indexDa024Dto);
                    if (!result){
                        //return "error";
                    }
                    result = service14.UpdateDA024Del(_indexDa024Dto);
                    if (!result){
                        //return "error";
                    }else{
                        _syslogDto.setType("삭제");
                        _syslogDto.setMenunm("주문현황");
                        _syslogDto.setSource("주문현황에서 삭제");
                        _syslogDto.setMessage(_indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum() + "/" + _indexDa024Dto.getSeq());
                        _syslogDto.setUserid(userid);
                        _syslogDto.setUsernm(usernm);
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }
                    //접수 주문 업데이트
                    _indexDa020Dto.setMisdate(ls_misdate);
                    _indexDa020Dto.setMisnum(misnumarr.get(i));
                    result = service01.UpdateJupsuMisnum(_indexDa020Dto);
                    if (!result){
                        //return "error";
                    }


                }

            }

        }catch (Exception e){
            log.info("index16/delarr 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
                    if(devnum03.get(i).substring(0,1).equals("D")){
                        indexDa023Dto.setDevnum(devnum03.get(i));
                        result = service14.UpdateDA023UnsongDevnum(indexDa023Dto);
                    }else{
                        indexDa023Dto.setMisdate(devnum03.get(i).substring(0,8));
                        indexDa023Dto.setMisnum(devnum03.get(i).substring(8,12));
                        ls_cltcd = ls_cltcd.substring(12, ls_cltcd.length());
                        indexDa023Dto.setCltcd(ls_cltcd);
                        result = service14.UpdateDA023Unsong(indexDa023Dto);
                    }
                    if (!result){
                         log.info("배송업로드 없는자료 =====>" +  indexDa023Dto.getMisdate() + '/'  + indexDa023Dto.getCltcd());
                        //log.info("배송업로드 없는자료 =====>" +  indexDa023Dto.getMisdate() + '/' + indexDa023Dto.getMisnum() + '/' + indexDa023Dto.getCltcd());
                        //return "error";
                    }

                }
                return "success";
            }

        }catch (Exception e){
            log.info("index16 savedev 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
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
                    // indexDa024Dto.setOmisdate(ls_omisdate);
                    //indexDa024Dto.setOmisnum(misnumarr.get(i));
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
                            indexDa024Dto.setOmisnum(ls_omisnum);  //신규매출번호
                            result = service14.InsertDa023Order(indexDa024Dto);
                            if (!result){
                                log.info("error =====>InsertDa023Order" );
                                return "error";
                            }
                        }else{
                        }
                        result = service14.InsertDa024Order(indexDa024Dto);
                        if (!result){
                            log.info("error =====>InsertDa024Order" );
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

        }catch (Exception e){
            log.info("savewish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
                    _indexDa024Dto.setMisdate(ls_misdate);
                    _indexDa024Dto.setMisnum(misnumarr.get(i));
                    _indexDa024Dto.setSeq(seqarr.get(i));
                    _indexDa024Dto.setCltcd(cltcdarr.get(i));
                    _indexDa024Dto.setMisgubun(gubunarr.get(i));
                    _indexDa024Dto.setQty(qtyarr.get(i));
                    _indexDa024Dto.setUamt(uamtarr.get(i));
                    //_indexDa024Dto = service14.SelectDa026Detail02(indexDa024Dto);
                    Integer _ll_uamt = _indexDa024Dto.getUamt();
                    Integer _ll_samt = 0;
                    Integer _ll_addamt = 0;
                    Integer _ll_amt = 0;
                    Integer _ll_qty = _indexDa024Dto.getQty();
                    if(_ll_uamt > 0){
                        _ll_samt = _ll_qty * _ll_uamt;
                        _ll_addamt = _ll_samt / 10 ;
                        _ll_amt = _ll_samt + _ll_addamt;
                        _indexDa024Dto.setSamt(_ll_samt);
                        _indexDa024Dto.setAddamt(_ll_addamt);
                        _indexDa024Dto.setAmt(_ll_amt);
                    }else{
                        _indexDa024Dto.setSamt(0);
                        _indexDa024Dto.setAddamt(0);
                        _indexDa024Dto.setAmt(0);
                    }
                    result = service14.UpdateDA026Amt(_indexDa024Dto);

                    if (!result){
                        return "error";
                    }
                }

            }

        }catch (Exception e){
            log.info("modifywish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
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
                    _indexDa024Dto.setMisdate(ls_misdate);
                    _indexDa024Dto.setMisnum(misnumarr.get(i));
                    _indexDa024Dto.setSeq(seqarr.get(i));
                    _indexDa024Dto.setCltcd(cltcdarr.get(i));
                    _indexDa024Dto.setMisgubun(gubunarr.get(i));
                    _indexDa024Dto.setDeluserid(userid);
                    _indexDa024Dto.setDeluser(usernm);
                    result = service14.DeleteDA026(_indexDa024Dto);
                    if (!result){
                        return "error";
                    }
                    result = service14.DeleteDA025(_indexDa024Dto);
                    if (!result){
                        //return "error";
                    }
                    result = service14.UpdateDA026Del(_indexDa024Dto);
                    if (!result){
                        //return "error";
                    }
                }

            }

        }catch (Exception e){
            log.info("delarrwish 오류 메시지: " + e.getMessage());
            e.printStackTrace();
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
            index01Dto.setCom_cls(com_cls);
            index01Dto.setCom_cnam(searchtxt);;
            index01ListDto = service01.GetComcodeDetailList(index01Dto);
            model.addAttribute("index01ListDto",index01ListDto);

        } catch (Exception e) {
            log.info("comcodedetaillist 오류 메시지: " + e.getMessage());
            e.printStackTrace();
        }

        return index01ListDto;
    }



    @RequestMapping(value="/jupsusave")
    public String index20JupsuSave(@RequestParam(value = "asaskey1") String asaskey1
            ,@RequestParam( value =  "asaskey2") String asaskey2
            ,@RequestParam( value =  "condamdang") String condamdang
            ,@RequestParam( value =  "asasgong") String asasgong
            ,@RequestParam( value =  "jacorp") String jacorp
            ,@RequestParam( value =  "jacorp1") String jacorp1
            ,@RequestParam( value =  "jacorp2") String jacorp2
            ,@RequestParam( value =  "jacorp3") String jacorp3
            ,@RequestParam( value =  "asasmodel") String asasmodel
            ,@RequestParam( value =  "asascolor") String asascolor
            ,@RequestParam( value =  "asasyumu") String asasyumu
            ,@RequestParam( value =  "asassuri") String asassuri
            ,@RequestParam( value =  "asasmemo") String asasmemo
            ,@RequestParam( value =  "asasmemo2") String asasmemo2
            ,@RequestParam( value =  "asasmemo3") String asasmemo3
            ,@RequestParam( value =  "asaname") String asaname
            ,@RequestParam( value =  "ascustnm") String ascustnm
            ,@RequestParam( value =  "asrnum") String asrnum
            ,@RequestParam( value =  "userid") String userid
            ,@RequestParam( value =  "usernm") String usernm
            ,@RequestParam( value =  "jchgoga")  Integer jchgoga
            , Model model
            , HttpServletRequest request){

        try {
            HttpSession session = request.getSession();
            SyslogDto _syslogDto = new SyslogDto();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("App01ComdodeDetailList_index Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            boolean result = false;
            Index20Dto _index20Dto = new Index20Dto();
            Index02Dto _index02Dto = new Index02Dto();
//            String year = mmisdate.substring(0,4);
//            String month = mmisdate.substring(5,7);
//            String day = mmisdate.substring(8,10);
//            String ls_misdate = year + month + day ;
            _index02Dto.setAcode(jacorp1 + jacorp2);
            _index02Dto = service02.GetCifListAcode(_index02Dto);  //거래처정보

            _index20Dto.setAs_key1(asaskey1);
            _index20Dto.setAs_key2(asaskey2);
            _index20Dto.setAs_damdang(condamdang);
            _index20Dto.setAs_gongjang(asasgong);
            _index20Dto.setAs_computer(ascustnm);
            _index20Dto.setAs_rnum(asrnum);
            _index20Dto.setAs_acorp(_index02Dto.getAcorp());
            _index20Dto.setAs_acorp1(jacorp1);
            _index20Dto.setAs_acorp2(jacorp2);
            _index20Dto.setAs_acorp3(jacorp3);
            _index20Dto.setAs_model(asasmodel);
            _index20Dto.setAs_color(asascolor);
            _index20Dto.setAs_yumu(asasyumu);
            _index20Dto.setAs_suri(asassuri);
            _index20Dto.setAs_memo(asasmemo);
            _index20Dto.setAs_memo2(asasmemo2);
            _index20Dto.setAs_memo3(asasmemo3);
            _index20Dto.setAs_aname(asaname);
            _index20Dto.setUserid(userid);
            _index20Dto.setUsernm(usernm);
            _index20Dto.setJchgoga(jchgoga);
//            log.info("jchgoga =====>" + jchgoga);
//            log.info("jchgoga2 =====>" + _index20Dto.getJchgoga());
//            log.info("asrnum =====>" + asrnum);
                if(asaskey2 == null || asaskey2.equals("")){
                    asaskey2 = GetMaxJupsu(_index20Dto);
                    _index20Dto.setAs_key2(asaskey2);
                    _index20Dto.setAs_devflag("0");
                    _index20Dto.setMisflag("0");
                    result = service01.InsertJupsu(_index20Dto);
                    if(result){
                        _syslogDto.setType("등록");
                        _syslogDto.setMenunm("AS접수등록");
                        _syslogDto.setSource(_index20Dto.getAs_acorp() + " AS등록");
                        _syslogDto.setMessage(_index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                        _syslogDto.setUserid(userformDto.getUserid());
                        _syslogDto.setUsernm(userformDto.getUsername());
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }

                }else{
                    result = service01.UpdateJupsu(_index20Dto);
                    if(result){
                        _syslogDto.setType("수정");
                        _syslogDto.setMenunm("AS접수등록");
                        _syslogDto.setSource(_index20Dto.getAs_acorp() + " AS수정");
                        _syslogDto.setMessage(_index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                        _syslogDto.setUserid(userid);
                        _syslogDto.setUsernm(usernm);
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }
                }
            if (!result){
                return "error";
            }
            return "success";

        }catch (Exception e){
//            model.addAttribute("index20JupsuSave errorMessage", e.getMessage());
            log.info("jupsusave 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }



    @RequestMapping(value="/jupsudel")
    public String index20JupsuDel(@RequestParam(value = "asaskey1") String asaskey1
            ,@RequestParam( value =  "asaskey2") String asaskey2
            ,@RequestParam( value =  "userid") String userid
            ,@RequestParam( value =  "usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("App01ComdodeDetailList_index Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            boolean result = false;
            Index20Dto _index20Dto = new Index20Dto();
            _index20Dto.setAs_key1(asaskey1);
            _index20Dto.setAs_key2(asaskey2);

            result = service01.DeleteJupsu(_index20Dto);
            if (!result){
                return "error";
            }else{
                _syslogDto.setType("삭제");
                _syslogDto.setMenunm("AS접수등록");
                _syslogDto.setSource("AS접수등록 삭제");
                _syslogDto.setMessage(_index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                _syslogDto.setUserid(userid);
                _syslogDto.setUsernm(usernm);
                result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                if (!result) {
                    //return "error";
                }
            }
            return "success";

        }catch (Exception e){
            log.info("jupsudel 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }


    @RequestMapping(value="/jupsudellist")
    public String index20JupsuDelList(@RequestParam(value = "asKey1Arr[]") List<String> asKey1Arr
            ,@RequestParam( value =  "asKey2Arr[]") List<String> asKey2Arr
            ,@RequestParam( value =  "userid") String userid
            ,@RequestParam( value =  "usernm") String usernm
            , Model model
            , HttpServletRequest request){
        try {
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("App01ComdodeDetailList_index Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            boolean result = false;
            Index20Dto _index20Dto = new Index20Dto();
            Index20Dto _index20DtoRe = new Index20Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            if( asKey1Arr.size() > 0){
                for(int i = 0; i < asKey1Arr.size(); i++){
                    _index20Dto.setAs_key1(asKey1Arr.get(i));
                    _index20Dto.setAs_key2(asKey2Arr.get(i));
                    _index20DtoRe = service01.GetAsJumsuDataMU(_index20Dto);
                    String ls_misdate = _index20DtoRe.getMisdate();
                    if(ls_misdate == null || ls_misdate.equals("")){
                        ls_misdate = "";
                    }
                    if(ls_misdate.length() > 0 ){
                        _indexDa024Dto.setMisdate(_index20DtoRe.getMisdate());
                        _indexDa024Dto.setMisnum(_index20DtoRe.getMisnum());
                        _indexDa024Dto.setCltcd(_index20DtoRe.getAcode());
                        _indexDa024Dto.setSeq("001");
                        _indexDa024Dto.setMisgubun("AA");
                        result = service14.DeleteDA024Jumsu(_indexDa024Dto);
                        if (!result){
                            //return "error";
                        }
                        result = service14.DeleteDA023(_indexDa024Dto);
                        if (!result){
                            //return "error";
                        }
                    }
                    result = service01.DeleteJupsu(_index20Dto);
                    if (!result){
                        return "error";
                    }else{
                        _syslogDto.setType("삭제");
                        _syslogDto.setMenunm("AS접수등록");
                        if(ls_misdate.length() > 0 ) {
                            _syslogDto.setSource("주문:" + _indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum());
                        }else{
                            _syslogDto.setSource("AS접수등록 삭제");
                        }
                        _syslogDto.setMessage("접수:" + _index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                        _syslogDto.setUserid(userid);
                        _syslogDto.setUsernm(usernm);
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }
                }

            }
            return "success";
        }catch (Exception e){
            log.info("jupsudellist 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }


    @RequestMapping(value="/jupsumodlist")
    public String index20JupsuModList(@RequestParam(value = "asKey1Arr[]") List<String> asKey1Arr
            ,@RequestParam( value =  "asKey2Arr[]") List<String> asKey2Arr
            ,@RequestParam( value =  "asmemoArr[]") List<String> asmemoArr
            ,@RequestParam( value =  "asmemoArr02[]") List<String> asmemoArr02
            ,@RequestParam( value =  "userid") String userid
            ,@RequestParam( value =  "usernm") String usernm
            , Model model
            , HttpServletRequest request){
        try {
            SyslogDto _syslogDto = new SyslogDto();
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("index20JupsuModList Exception =====> relogin userformDto null");
                return "relogin";
            }
            model.addAttribute("userformDto",userformDto);

            boolean result = false;
            Index20Dto _index20Dto = new Index20Dto();
            Index20Dto _index20DtoRe = new Index20Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            if( asKey1Arr.size() > 0){
                for(int i = 0; i < asKey1Arr.size(); i++){
                    _index20Dto.setAs_key1(asKey1Arr.get(i));
                    _index20Dto.setAs_key2(asKey2Arr.get(i));
                    _index20Dto.setAs_memo(asmemoArr.get(i));
                    _index20Dto.setAs_memo2(asmemoArr02.get(i));
                    result = service01.ModifyJupsuMemo(_index20Dto);
                    if (!result){
                        return "error";
                    }else{
                        _syslogDto.setType("메모수정");
                        _syslogDto.setMenunm("AS접수등록");
                        _syslogDto.setSource("AS접수등록 메모수정");
                        _syslogDto.setMessage("접수:" + _index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                        _syslogDto.setUserid(userid);
                        _syslogDto.setUsernm(usernm);
                        result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                        if (!result) {
                            //return "error";
                        }
                    }
                }

            }
            return "success";
        }catch (Exception e){
            log.info("jupsudellist 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }






    @RequestMapping(value="/index20/deliv")
    public String index21Deliv(@RequestParam(value = "asKey1Arr[]") List<String> asKey1Arr
            ,@RequestParam( value =  "asKey2Arr[]") List<String> asKey2Arr
            ,@RequestParam( value =  "ascltcdArr[]") List<String> ascltcdArr
            ,@RequestParam( value =  "asYumu[]") List<String> asYumu
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;


            //당일 배송순번 채번
            String ls_devnum, ls_devdate;
            ls_devdate = getToDate();
            ls_devnum = GetTrackDevNum(getToDate());
            if(ls_devnum.equals("error")){
                return "error";
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date      = new Date(System.currentTimeMillis());
            String ls_date2 = formatter.format(date);
            Index20Dto _index20Dto = new Index20Dto();
            Index20Dto _index20DtoRe = new Index20Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            String lsYumu = "";
            if( asKey1Arr.size() > 0){
                for(int i = 0; i < asKey1Arr.size(); i++){
                    String year = asKey1Arr.get(i).substring(0,4);
                    String month = asKey1Arr.get(i).substring(5,7);
                    String day = asKey1Arr.get(i).substring(8,10);
                           lsYumu = asYumu.get(i);
                    String ls_misdate = year + month + day ;
                    _index20Dto.setAs_key1(asKey1Arr.get(i));
                    _index20Dto.setAs_key2(asKey2Arr.get(i));
                    _index20Dto.setAs_devflag("1");
                    _index20Dto.setAs_date2(ls_date2);
//                    if(asflagArr.get(i).equals("0")){
//                        index20Dto.setAs_devflag("1");
//                    }else{
//                        index20Dto.setAs_devflag("0");
//                    }
                    _index20Dto.setAs_devcode("DJ" + ls_devdate + ls_devnum + ascltcdArr.get(i) );
                    result = service01.UpdateDevJupsu(_index20Dto);
//                    log.info("result  =====>" + result);
//                    log.info("setAs_date2  =====>" + ls_date2);
//                    log.info("getMisdate  =====>" + indexDa024Dto.getMisdate());
                    if (!result){
                       // return "error";
                    }
                    if(lsYumu.equals("유상")){
                        _index20DtoRe = service01.GetAsJumsuDataYU(_index20Dto);
                        String ls_misate = _index20DtoRe.getMisdate();
                        if(ls_misate == null){ls_misate = "";}
                        //접수등록한 건만 업데이트
                        if(ls_misate != null && ls_misate.length() > 0){
                            _indexDa024Dto.setMisdate(_index20DtoRe.getMisdate());
                            _indexDa024Dto.setMisnum(_index20DtoRe.getMisnum());
                            _indexDa024Dto.setCltcd(_index20DtoRe.getAcode());
                            _indexDa024Dto.setDevflag(_index20DtoRe.getAs_devflag());
                            _indexDa024Dto.setDevdatetime(_index20DtoRe.getAs_devdate());
                            _indexDa024Dto.setUnsongnum(_index20DtoRe.getUnsongnum());
                            _indexDa024Dto.setReservnum(_index20DtoRe.getReservnum());
                            _indexDa024Dto.setDevnum("DJ" + ls_devdate + ls_devnum + ascltcdArr.get(i) );
                            _indexDa024Dto.setMisgubun("AA");
                            result = service14.UpdateDa024JumsuDev(_indexDa024Dto);
                            if (!result){
                               // return "error";
                            }
                        }
                    }


                }
            }
            return "success";

        }catch (Exception e){
            log.info("deliv 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value="/index20/jumunsav")
    public String index21JumunSav(@RequestParam(value = "asKey1Arr[]") List<String> asKey1Arr
            ,@RequestParam( value =  "asKey2Arr[]") List<String> asKey2Arr
            ,@RequestParam( value =  "asYumu[]") List<String> asYumu
            ,@RequestParam( value =  "asAname[]") List<String> asAname
            ,@RequestParam("userid") String userid
            ,@RequestParam("usernm") String usernm
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            SyslogDto _syslogDto = new SyslogDto();
            Index20Dto _index20Dto = new Index20Dto();
            Index20Dto _index20DtoRe = new Index20Dto();
            IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
            IndexDa023Dto _indexDa023Dto = new IndexDa023Dto();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date      = new Date(System.currentTimeMillis());
            String ls_date2 = formatter.format(date);
            if( asKey1Arr.size() > 0){
                for(int i = 0; i < asKey1Arr.size(); i++){
                    if(asKey1Arr.get(i).length() == 10){
                        String year = asKey1Arr.get(i).substring(0,4);
                        String month = asKey1Arr.get(i).substring(5,7);
                        String day = asKey1Arr.get(i).substring(8,10);
                    }
//                    String ls_misdate = year + month + day ;  //요청에 의해 당일일자로 변경함.
                    String ls_misdate = getToDate() ;
                    String ls_misnum = "";
                    String ls_yumu = asYumu.get(i);
                    String ls_aname = asAname.get(i);
                    String ls_misdateCHK = "";

                    _index20Dto.setUserid(userid);
                    _index20Dto.setUsernm(usernm);
                    _index20Dto.setAs_key1(asKey1Arr.get(i));
                    _index20Dto.setAs_key2(asKey2Arr.get(i));
                    _index20Dto.setMisflag("1");
                    _index20Dto.setAs_date2(ls_date2);
                    if(ls_aname.equals("P") || ls_aname.equals("B")){
                        _index20Dto.setJpb_gubn(ls_aname);
                    }else{
                        _index20Dto.setJpb_gubn("Z");
                    }

                    if(ls_yumu.equals("무상")){
                        _index20DtoRe = service01.GetAsJumsuDataMU(_index20Dto);
                    }else{
                        _index20DtoRe = service01.GetAsJumsuDataYU(_index20Dto);
                    }
                    ls_misdateCHK = _index20DtoRe.getMisdate();
                    _indexDa023Dto.setCltcd(_index20DtoRe.getAcode());
                    _indexDa023Dto.setMisdate(ls_misdate);
                    _indexDa023Dto.setMisgubun("AA");
                    _indexDa024Dto.setUserid(userid);
                    _indexDa024Dto.setUsernm(usernm);
                    _indexDa024Dto.setDevflag(_index20DtoRe.getAs_devflag());
                    _indexDa024Dto.setUnsongnum(_index20DtoRe.getUnsongnum());

                    if(ls_misdateCHK.length() == 0 || ls_misdateCHK == null || ls_misdateCHK.equals("")){
                        ls_misnum = service14.SelectCheckMisnumMkflagJupsu(_indexDa023Dto);  //주문된 순번max 찾기
                        if(ls_misnum == null){
                            ls_misnum = "5001";
                        }else{
                            Integer ll_misnum = Integer.parseInt(ls_misnum) + 1;
                            ls_misnum = ll_misnum.toString();
                            if (ls_misnum.length() == 1){
                                ls_misnum = "000" + ls_misnum;
                            }else if(ls_misnum.length() == 2){
                                ls_misnum = "00" + ls_misnum;
                            }else if(ls_misnum.length() == 3){
                                ls_misnum = "0" + ls_misnum;
                            }
                        }
                    }else{
                        ls_misnum =  _index20DtoRe.getMisnum();
                    }
                    _indexDa023Dto.setMisnum(ls_misnum);
                    _indexDa023Dto.setCltcd(_index20DtoRe.getAcode());
                    _indexDa023Dto.setPerid("");
                    _indexDa023Dto.setContamt(0);
                    _indexDa023Dto.setAddamt(0);
                    _indexDa023Dto.setAddamt(0);
                    _indexDa023Dto.setMisamt(0);
                    _indexDa023Dto.setAmt(0);
                    _indexDa023Dto.setBillkind("1");     //0 미발행 1 발행 2 역발행 3 타사이트발행
                    _indexDa023Dto.setTaxcls("0");       //0 부가세별도 1 부가세포함
                    _indexDa023Dto.setTaxgubun("001");   //001 과세 002 비과세
                    _indexDa023Dto.setBigo("");
                    _indexDa023Dto.setRemark("");
                    _indexDa023Dto.setSpjangnum(_index20DtoRe.getAs_acorp());
                    _indexDa023Dto.setGubun("");


                    _indexDa024Dto.setMisdate(ls_misdate);
                    _indexDa024Dto.setMisnum(ls_misnum);
                    _indexDa024Dto.setMisgubun("AA");
                    _indexDa024Dto.setPcode("BAS00A00000000");  //_index20DtoRe.getJkey()
                    _indexDa024Dto.setPname("AS");
                    _indexDa024Dto.setPsize("");
                    _indexDa024Dto.setPbonsa(_index20DtoRe.getJbonsa_code2());
                    _indexDa024Dto.setPmodel("AS");  //_index20DtoRe.getJmodel_code2()
                    _indexDa024Dto.setPcolor(_index20DtoRe.getJcolor_code());
                    _indexDa024Dto.setCltcd(_index20DtoRe.getAcode());
                    _indexDa024Dto.setAcorp(_index20DtoRe.getAs_acorp());
                    _indexDa024Dto.setPernm(_index20DtoRe.getPernm());
                    _indexDa024Dto.setPerid(_index20DtoRe.getPerid());
                    _indexDa024Dto.setJpb_gubn(_index20DtoRe.getAs_aname());

                    _indexDa024Dto.setAddamt(0);
                    _indexDa024Dto.setSeq("001");
                    _indexDa024Dto.setQty(1);
                    _indexDa024Dto.setMakflag("1");
                    _indexDa024Dto.setMisdatetime(getToDateTime());
                    _index20DtoRe.setMisdate(ls_misdate);
                    _index20DtoRe.setMisnum(ls_misnum);
                    _index20DtoRe.setMisflag("1");


                    Integer ll_chulgoga = _index20DtoRe.getJchgoga0();
                    if(ll_chulgoga != 0 ) {
                        _indexDa024Dto.setUamt(ll_chulgoga);
                        _indexDa024Dto.setAddamt(ll_chulgoga / 10);
                        _indexDa024Dto.setAmt(ll_chulgoga + (ll_chulgoga / 10));
                        _indexDa024Dto.setSamt(ll_chulgoga);
                    }else{
                        _indexDa024Dto.setUamt(0);
                        _indexDa024Dto.setAddamt(0);
                        _indexDa024Dto.setAmt(0);
                        _indexDa024Dto.setSamt(0);
                    };
//                    log.info("misnum  =====>" + _indexDa024Dto.getMisnum());
//                    log.info("setUamt  =====>" + _indexDa024Dto.getUamt());
//                    log.info("setAddamt  =====>" + _indexDa024Dto.getAddamt());
//                    log.info("setAmt  =====>" + _indexDa024Dto.getAmt());
//                    log.info("setSamt  =====>" + _indexDa024Dto.getSamt());
                    _indexDa024Dto.setIndate(getToDate());
                    _indexDa024Dto.setInperid(userid);
                    _indexDa024Dto.setPunit("EA");
                    _indexDa024Dto.setRemarkaa("");
                    _indexDa024Dto.setRemarkbb("");
                    if(ls_misdateCHK.length() == 0 || ls_misdateCHK == null || ls_misdateCHK.equals("")){
                        result = service14.InsertDa023(_indexDa023Dto);
                        if (!result){
                            return "error";
                        }
                        result = service14.InsertDa024Jumsu(_indexDa024Dto);
                        if (!result){
                            return "error";
                        }else{
                            _syslogDto.setType("등록");
                            _syslogDto.setMenunm("AS접수등록");
                            _syslogDto.setSource("AS주문번호:" + _indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum());
                            _syslogDto.setMessage("AS접수번호:" + _index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                            _syslogDto.setUserid(userid);
                            _syslogDto.setUsernm(usernm);
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                    }else{
                        result = service14.UpdateDa024Jumsu(_indexDa024Dto);
                        if (!result){
                            return "error";
                        }else{
                            _syslogDto.setType("수정");
                            _syslogDto.setMenunm("AS접수등록");
                            _syslogDto.setSource("AS주문번호:" + _indexDa024Dto.getMisdate() + "/" + _indexDa024Dto.getMisnum());
                            _syslogDto.setMessage("AS접수번호:" + _index20Dto.getAs_key1() + "/" + _index20Dto.getAs_key2());
                            _syslogDto.setUserid(userid);
                            _syslogDto.setUsernm(usernm);
                            result = service_auth.TB_SYSLOG_INSERT(_syslogDto);
                            if (!result) {
                                //return "error";
                            }
                        }
                    }
                    result = service01.UpdateMisJupsu(_index20DtoRe);
//                    log.info("setAs_date2  =====>" + ls_date2);
//                    log.info("getMisdate  =====>" + indexDa024Dto.getMisdate());
                    if (!result){
                        return "error";
                    }
                }
            }
            return "success";

        }catch (Exception  e){
//            model.addAttribute("index21Deliv errorMessage", e.getMessage());
            log.info("jumunsav AS->주문처리 중 오류 메시지: " + e.getMessage());
            // 스택 트레이스를 출력하여 오류의 발생 위치를 확인
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value="/index20/savedev")
    public String index20SaveDev(@RequestParam(value = "devnum01[]") List<String> devnum01
            ,@RequestParam( value =  "devnum02[]") List<String> devnum02
            ,@RequestParam( value =  "devnum03[]") List<String> devnum03
            , Model model
            , HttpServletRequest request){

        try {
            boolean result = false;
            if( devnum01.size() > 0){
                for(int i = 0; i < devnum01.size(); i++){
                    String ls_unsongnum = "";
                    ls_unsongnum = devnum02.get(i);
                    if(ls_unsongnum == null || ls_unsongnum.equals("")){
                        continue;
                    }
                    if(devnum03.get(i) == null || devnum03.get(i).equals("")){
                        continue;
                    }
                    String ls_cltcd = "";
                    index20Dto.setReservnum(devnum01.get(i));
                    index20Dto.setUnsongnum(devnum02.get(i));
                    indexDa023Dto.setReservnum(devnum01.get(i));
                    indexDa023Dto.setUnsongnum(devnum02.get(i));
                    if(devnum03.get(i).substring(0,1).equals("D")){
                        index20Dto.setAs_devcode(devnum03.get(i));
                        result = service01.UpdateDevJupsuUnsongDevnum(index20Dto);
                        if (!result){
                            //return "error";
                        }
                        indexDa023Dto.setDevnum(devnum03.get(i));
                        result = service14.UpdateDA023UnsongDevnum(indexDa023Dto);
                        if (!result){
                            //return "error";
                        }
                    }else{
                        index20Dto.setMisdate(devnum03.get(i).substring(0,8));
                        index20Dto.setMisnum(devnum03.get(i).substring(8,12));
                        ls_cltcd = devnum03.get(i);
                        if(ls_cltcd.length() < 12){
                            continue;
                        }
                        ls_cltcd = ls_cltcd.substring(12, ls_cltcd.length());
                        index20Dto.setAs_acorp1(ls_cltcd);
                        result = service01.UpdateDevJupsuUnsong(index20Dto);
                        if (!result){
                            //return "error";
                        }
                    }


                }
                return "success";
            }

        }catch (Exception e){
            log.info("index20 savedev 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @Transactional
    public synchronized  String GetTrackNum(String agDate){
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        Integer ll_misnum = 0;
        String ls_misnum = "";
        Boolean lb_result = false;
        ll_misnum = service14.SelectTrackMisnum(agDate);
        try {
            if (ll_misnum == null){
                ll_misnum = 1;
                _indexDa024Dto.setMisdate(agDate);
                _indexDa024Dto.setMnum(ll_misnum);
                lb_result = service14.InsertTrackMisnum(_indexDa024Dto);
            }else{
                ll_misnum += 1;
                _indexDa024Dto.setMisdate(agDate);
                _indexDa024Dto.setMnum(ll_misnum);
                lb_result = service14.UpdateTrackMisnum(_indexDa024Dto);
            }
            if (!lb_result){
                log.info("GetTrackNum 오류 메시지: " + agDate + " / " + lb_result);
                return "error";
            }
        }catch (Exception e){
            log.info("GetTrackNum 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
        // mnum을 4자리 문자열로 변환
        ls_misnum = String.format("%04d", ll_misnum);
        return ls_misnum;
    }


    @Transactional
    public synchronized  String GetTrackDevNum(String agDate){
        IndexDa024Dto _indexDa024Dto = new IndexDa024Dto();
        Integer ll_misnum = 0;
        String ls_misnum = "";
        Boolean lb_result = false;
        ll_misnum = service14.SelectTrackDevnum(agDate);
        try {
            if (ll_misnum == null){
                ll_misnum = 1;
                _indexDa024Dto.setMisdate(agDate);
                _indexDa024Dto.setMnum(ll_misnum);
                lb_result = service14.InsertTrackDevnum(_indexDa024Dto);
            }else{
                ll_misnum += 1;
                _indexDa024Dto.setMisdate(agDate);
                _indexDa024Dto.setMnum(ll_misnum);
                lb_result = service14.UpdateTrackDevnum(_indexDa024Dto);
            }
            if (!lb_result){
                log.info("GetTrackDevNum 오류 메시지: " + agDate + " / " + lb_result);
                return "error";
            }
        }catch (Exception e){
            log.info("GetTrackNum 오류 메시지: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
        // mnum을 4자리 문자열로 변환
        ls_misnum = String.format("%04d", ll_misnum);
        return ls_misnum;
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

    public String GetMaxSeq(IndexDa023Dto asDto, String agDate){

        String ls_seq = service14.SelectMaxSeq(asDto);
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
//        log.info("ls_seq =====>" + ls_seq);
        return ls_seq;
    }
    public String GetMaxSeqWish(IndexDa023Dto asDto, String agDate){

        String ls_seq = service14.SelectMaxSeqWish(asDto);
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

    public String GetMaxJupsu(Index20Dto asDto){

        String ls_seq = service01.SelectMaxJupsu(asDto);
        if(asDto.getAs_rnum() == null || asDto.getAs_rnum().length() == 0){
            ls_seq = "9001";
        }else{
            if(ls_seq == null){
                Integer ll_misnum = Integer.parseInt(asDto.getAs_rnum()) + 1;
                ls_seq = ll_misnum.toString();
            }else{
                Integer ll_misnum = Integer.parseInt(ls_seq) + 1;
                ls_seq = ll_misnum.toString();
            }
        }
        return ls_seq;
    }

    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

    private String getToDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return date.toString();
    }
    public  String escapeBrackets(String input) {
        if (input == null) {
            return null;
        }
        // Replace [ with [[ and ] with ]]

        return input.replace("[", "[[").replace("]", "]]");
    }

}
