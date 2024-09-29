package com.dae.eean.controller.appadmin;


import com.dae.eean.DTO.*;
import com.dae.eean.Exception.AttachFileException;
import com.dae.eean.Service.PopupService;
import com.dae.eean.Service.impl.AppUploadService;
import com.dae.eean.Service.impl.AppUploadServiceImpl;
import com.dae.eean.Service.master.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/appadmod", method = RequestMethod.POST)
public class AppIndexCrudController {
    private final AuthService service;
    private final PopupService popservice;
    List<UserFormDto> appUserFormListDto ;
    List<TBXLoginDTO> appUserLoginListDto ;
    UserFormDto appUserFormDto  = new UserFormDto();
    TBXuserMenuDTO xusermenuDto = new TBXuserMenuDTO();
    App05ElvlrtDto App05Dto = new App05ElvlrtDto();
    AttachDTO attachDTO = new AttachDTO();
    private final AppUploadServiceImpl appServiceImpl;
    private final AppUploadService appUploadService;
//    private final String uploadPath = Paths.get("D:", "EEAN", "upload","mnotice", getToDate()).toString();

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
            ,@RequestParam("perid") String perid
            ,@RequestParam("role") String role
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
            appUserFormDto.setPasswd2(userpw);
            appUserFormDto.setPerid(perid);
            appUserFormDto.setRole(role);
            appUserFormDto.setRnum("0");
            log.info("role   =====> " + role);
            if(role.equals("B") ){
                appUserFormDto.setCustnm("블리스");
            }if(role.equals("P") ){
                appUserFormDto.setCustnm("피오비노");
            }else{
                appUserFormDto.setCustnm("공통");
            }
            appUserFormDto.setFlag("CC");

            boolean result = service.UpdateUserInfoCC(appUserFormDto);
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


    @RequestMapping(value="/id05mod")   //영업사원 상태 수정
    public String UserUpdate05(@RequestParam("actseqz") String seq
            ,@RequestParam("actuseridz") String userid
            ,@RequestParam("actuseynz") String useyn
            ,@RequestParam("actusernmz") String usernm
            ,@RequestParam("actuserpwz") String userpw
            ,@RequestParam("rnum") String rnum
            ,@RequestParam("custnm") String custnm
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
            appUserFormDto.setPasswd2(userpw);
            appUserFormDto.setRnum(rnum);
            appUserFormDto.setCustnm(custnm);
            appUserFormDto.setFlag("DD");

            boolean result = service.UpdateUserInfoCC(appUserFormDto);
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


    @RequestMapping(value="/id05sav")   //영업사원 상태 수정
    public String UserInsert05(@RequestParam("userid") String userid
            ,@RequestParam("passwd1") String passwd1
            ,@RequestParam("passwd2") String passwd2
            ,@RequestParam("custnm") String custnm
            ,@RequestParam("rnum") String rnum
            ,@RequestParam("username") String username
            ,@RequestParam("flag") String flag
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setSpjangcd("ZZ");
            appUserFormDto.setUserid(userid);
            appUserFormDto.setUseyn("Y");
            appUserFormDto.setUsername(username);
            appUserFormDto.setPasswd1(passwd1);
            appUserFormDto.setPasswd2(passwd2);
            appUserFormDto.setRnum(rnum);
            appUserFormDto.setCustnm(custnm);
            appUserFormDto.setFlag(flag);
            appUserFormDto.setCustcd("actcd");
            appUserFormDto.setPerid(userid);
            appUserFormDto.setPassword(passwd1);
            appUserFormDto.setRole("%");
            appUserFormDto.setSaupnum("");
            boolean result = service.TB_XUSERS_INSERT(appUserFormDto);
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


    @RequestMapping(value="/id01sav")   //영업사원 상태 수정
    public String UserInsert01(@RequestParam("userid") String userid
            ,@RequestParam("passwd1") String passwd1
            ,@RequestParam("passwd2") String passwd2
            ,@RequestParam("custnm") String custnm
            ,@RequestParam("username") String username
            ,@RequestParam("flag") String flag
            ,@RequestParam("perid") String perid
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            String ls_role = "";
            if(custnm.equals("P")){
                appUserFormDto.setRole(custnm);
                appUserFormDto.setCustnm("피오비노");
            }else if(custnm.equals("B")){
                appUserFormDto.setRole(custnm);
                appUserFormDto.setCustnm("블리스");
            }else{
                appUserFormDto.setRole("%");
                appUserFormDto.setCustnm("%");
            }
            appUserFormDto.setSpjangcd("ZZ");
            appUserFormDto.setUserid(userid);
            appUserFormDto.setUseyn("Y");
            appUserFormDto.setUsername(username);
            appUserFormDto.setPernm(username);
            appUserFormDto.setPasswd1(passwd1);
            appUserFormDto.setPasswd2(passwd2);
            appUserFormDto.setFlag(flag);
            appUserFormDto.setRnum("0");
            appUserFormDto.setCustcd("actcd");
            appUserFormDto.setPerid(perid);
            appUserFormDto.setSaupnum("");
            boolean result = service.TB_XUSERS_INSERT(appUserFormDto);
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


    @RequestMapping(value="/id05list")   //거래처 리스트 조회
    public Object UserList05(@RequestParam("actusernamez") String usernm
            , Model model, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = userformDto.getCustcd();
            String ls_spjangcd = userformDto.getSpjangcd();
            appUserFormDto.setCustcd(ls_custcd);
            appUserFormDto.setSpjangcd(ls_spjangcd);
            appUserFormDto.setUsername(usernm);
            appUserFormDto.setFlag("DD");
            appUserFormListDto = service.GetUserListDto(appUserFormDto);
            model.addAttribute("appUserFormListDto", appUserFormListDto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return appUserFormListDto;
    }


    @RequestMapping(value="/id06sav")   //공지사항등록
    public String mnoticeUpload ( @RequestPart(value = "key") Map<String, Object> param,
                                  @RequestPart(value = "file",required = false) List<MultipartFile> file
            , Model model
            , HttpServletRequest request){
        String ls_fileName = "";
        String ls_errmsg = "";
        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<AttachDTO> attachList = new ArrayList<>();
        App05ElvlrtDto _App05Dto = new App05ElvlrtDto();

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        String ls_custcd = "EEAN";
        String ls_spjangcd = "ZZ";

        param.forEach((key, values) -> {
            switch (key){
                case "actnseqz":
                    _App05Dto.setNseq(values.toString());
                    break;
                case "actninputdatez":
                    _App05Dto.setNinputdate(values.toString());
                    break;
                case "actngroupcdz":
                    _App05Dto.setNgourpcd(values.toString());
                    break;
                case "actnsubjectz":
                    _App05Dto.setNsubject(values.toString());
                    break;
                case "actnpernmz":
                    _App05Dto.setNpernm(values.toString());
                    break;
                case "actnmemoz":
                    _App05Dto.setNmemo(values.toString());
                    break;
                case "actnflagz":
                    _App05Dto.setNflag(values.toString());
                    break;
                case "actfrdate":
                    _App05Dto.setFrdate(values.toString());
                    break;
                case "acttodate":
                    _App05Dto.setTodate(values.toString());
                    break;
                default:
                    break;
            }
        });
        String nseq = _App05Dto.getNseq();
        _App05Dto.setCustcd(ls_custcd);
        _App05Dto.setSpjangcd(ls_spjangcd);
        _App05Dto.setNpernm(userformDto.getUsername());
        String ninputdate = _App05Dto.getNinputdate();
        String ls_yeare = ninputdate.substring(0,4);
        String ls_mm = ninputdate.substring(5,7);
        String ls_dd = ninputdate.substring(8,10);
        ninputdate =  ls_yeare + ls_mm + ls_dd;
        _App05Dto.setNinputdate(ninputdate);

        String ls_frdate  = _App05Dto.getFrdate();
        ls_yeare = ls_frdate.substring(0,4);
        ls_mm = ls_frdate.substring(5,7);
        ls_dd = ls_frdate.substring(8,10);
        ls_frdate =  ls_yeare + ls_mm + ls_dd;
        _App05Dto.setFrdate(ls_frdate);

        String ls_todate  = _App05Dto.getTodate();
        ls_yeare = ls_todate.substring(0,4);
        ls_mm = ls_todate.substring(5,7);
        ls_dd = ls_todate.substring(8,10);
        ls_todate =  ls_yeare + ls_mm + ls_dd;
        _App05Dto.setTodate(ls_todate);

        if(nseq == null || nseq.equals("")){
            _App05Dto.setNseq(CountSeq(ls_yeare + ls_mm));
        }else{
            _App05Dto.setNseq(nseq);
        }
        _App05Dto.setYyyymm(ls_yeare + ls_mm);
        if(nseq == null || nseq.equals("")){
            boolean result = popservice.InsertMNotice(_App05Dto);
            if(!result){
                return  "error";
            }
        }else{
            boolean result = popservice.UpdateMNotice(_App05Dto);
            if(!result){
                return  "error";
            }
        }
        model.addAttribute("userformDto",userformDto);
        String ls_nseq = _App05Dto.getNseq();
        String _uploadPath = Paths.get("D:", "EEAN", "upload","mnotice", ls_nseq).toString();
        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(_uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        try {

            for(MultipartFile multipartFile : file){
                log.info("================================================================");
                log.info("upload file name : " + multipartFile.getOriginalFilename());
                log.info("upload file name : " + multipartFile.getSize());
                ls_fileName = multipartFile.getOriginalFilename();


                /* 파일이 비어있으면 비어있는 리스트 반환 */
                if (multipartFile.getSize() < 1) {
                    ls_errmsg = "success";
                    return ls_errmsg;
                }
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                final String saveName = getRandomString() + "." + extension;

                /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                File target = new File(_uploadPath, saveName);

                log.info("uploadPath : " + _uploadPath);
                log.info("saveName : " + saveName);

                multipartFile.transferTo(target);
                String nseq1 = _App05Dto.getNseq();
                /* 파일 정보 저장 */
                AttachDTO attach = new AttachDTO();
                attach.setBoardIdx(nseq1);
                attach.setOriginalName(multipartFile.getOriginalFilename());
                attach.setSaveName(saveName);
                attach.setSize(multipartFile.getSize());
                attach.setFlag("NN");
                /* 파일 정보 추가 */
                attachList.add(attach);
            }
            boolean result  = appServiceImpl.registerMNotice(_App05Dto, attachList);
            if(!result){
                return  "error";
            }

        }catch (DataAccessException e){
            log.info("memberUpload DataAccessException ================================================================");
            log.info(e.toString());
            throw new AttachFileException("[" + ls_fileName + "] DataAccessException to save");
            //utils.showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다", "/App05/App05list/", Method.GET, model);
        } catch (Exception  e){
            /*log.info("memberUpload Exception ================================================================");
            log.info(e.toString());
            ls_errmsg = "[" + ls_fileName + "] failed to save";
            throw new AttachFileException("[" + ls_fileName + "] failed to save");*/
            //utils.showMessageWithRedirect("시스템에 문제가 발생하였습니다", "/app05/App05list/", Method.GET, model);
        }

        return "success";
//        utils.showMessageWithRedirect("게시글 등록이 완료되었습니다", "/app05/App05list/", Method.GET, model);
    }


    @RequestMapping(value="/id06del")
    public String mnoticeDelete(@RequestParam("actnseqz") String nseq
            ,@RequestParam("actnflagz") String nflag
            ,Model model, HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            String ls_custcd = "EEAN";
            String ls_spjangcd = "ZZ";
            App05Dto.setNseq(nseq);
            App05Dto.setNflag(nflag);

            boolean result = popservice.DeleteMNotice(App05Dto);
            if(!result){
                return  "error";
            }
            result = appServiceImpl.registerMNoticeDel(App05Dto);
            if(!result){
                //return  "error";
            }
            model.addAttribute("userformDto",userformDto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/filedel")
    public String mnoticeFileDelete(@RequestParam("actidxz") Long idx
            ,@RequestParam("actnseqz") String nseq
            ,@RequestParam("actnflagz") String nflag
            ,Model model, HttpServletRequest request){

        try {
            attachDTO.setIdx(idx);
            attachDTO.setBoardIdx(nseq);
            attachDTO.setFlag(nflag);

            boolean result = appServiceImpl.MNoticeFileDel(attachDTO);
            if(!result){
                return  "error";
            }
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/flist")
    public Object mnoticeFilelist(@RequestParam("actnseqz") String nseq
            ,@RequestParam("actnflagz") String nflag
            , Model model, HttpServletRequest request){
        List<AttachDTO>  attach =new ArrayList<>();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");

            App05Dto.setNseq(nseq);
            App05Dto.setNflag(nflag);
            attach = appServiceImpl.MNoticeFilelist(App05Dto);
            model.addAttribute("userformDto",userformDto);
            model.addAttribute("attachDto",attach);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return attach;
    }


    @RequestMapping(value="/uploadImage")   //공지사항 이미지 등록
    public void UploadImage ( @RequestPart(value = "file",required = false) List<MultipartFile> file
            , Model model
            , HttpServletRequest request
            , HttpServletResponse response) throws IOException {
        String ls_fileName = "";
        String ls_errmsg = "";
        String imageUrl = "";
        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<AttachDTO> attachList = new ArrayList<>();


        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        String ls_custcd = "EEAN";
        String ls_spjangcd = "ZZ";


        model.addAttribute("userformDto",userformDto);
        String ls_nseq = getToDate().substring(0, 6);
        ls_nseq = CountSeq(getToDate().substring(0, 5));;
        String _uploadPath = Paths.get("D:", "EEAN", "upload","mnoticeimg", ls_nseq  ).toString();
        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(_uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        try {

            for(MultipartFile multipartFile : file){
//                log.info("================================================================");
//                log.info("upload file img name : " + multipartFile.getOriginalFilename());
//                log.info("upload file img name : " + multipartFile.getSize());
                ls_fileName = multipartFile.getOriginalFilename();


                /* 파일이 비어있으면 비어있는 리스트 반환 */
                if (multipartFile.getSize() < 1) {
                    ls_errmsg = "success";
                    return ;
                }
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                final String saveName = ls_fileName;

                /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                File target = new File(_uploadPath, saveName);
                multipartFile.transferTo(target);
                _uploadPath = _uploadPath + "\\";
                // 이미지 URL 반환
                imageUrl = _uploadPath + saveName;
                // JSON 응답 생성
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("location", imageUrl);

                // 응답에 JSON 전송
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponse.toString());
            }

        }catch (DataAccessException e){
            log.info("UploadImage DataAccessException ================================================================");
            log.info(e.toString());
            throw new AttachFileException("[" + ls_fileName + "] DataAccessException to save");
            //utils.showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다", "/App05/App05list/", Method.GET, model);
        } catch (Exception  e){
            /*log.info("memberUpload Exception ================================================================");
            log.info(e.toString());
            ls_errmsg = "[" + ls_fileName + "] failed to save";
            throw new AttachFileException("[" + ls_fileName + "] failed to save");*/
            //utils.showMessageWithRedirect("시스템에 문제가 발생하였습니다", "/app05/App05list/", Method.GET, model);
        }
        return ;
//        utils.showMessageWithRedirect("게시글 등록이 완료되었습니다", "/app05/App05list/", Method.GET, model);
    }

    @RequestMapping(value="/bbslist")
    public Object mnoticeBBSlist(@RequestParam("ngroupcd") String ngroupcd,
                                 Model model, HttpServletRequest request){
        List<App05ElvlrtDto> App05ListDto;
        App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");

            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            _App05Dto.setYyyymm(indate.substring(0,6));
            _App05Dto.setNinputdate(indate);
            if(ngroupcd.equals("BB")){
                _App05Dto.setNgourpcd("02");       //영업점
            }else if(ngroupcd.equals("CC")){
                _App05Dto.setNgourpcd("01");        //영업사원-직원공지
            }else if(ngroupcd.equals("AA")){
                _App05Dto.setNgourpcd("01");       //본사-직원공지
            }else{
                _App05Dto.setNgourpcd("%");         // 전체공지
            }
            App05ListDto = popservice.GetTodayMNoticeList(_App05Dto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return App05ListDto;
    }


    @RequestMapping(value="/noticelist")
    public Object mnoticeNoticelist(@RequestParam("actcustnm") String actcustnm
            ,@RequestParam("flag") String flag
            , Model model, HttpServletRequest request){
        List<App05ElvlrtDto> App05ListDto;
        App05ElvlrtDto _App05Dto = new App05ElvlrtDto();
        try {
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            if(userformDto == null){
                log.info("mnoticeNoticelist Exception =====> relogin userformDto null");
                return "relogin";
            }
            _App05Dto.setNsubject(actcustnm);
            _App05Dto.setNgourpcd(flag);
            App05ListDto = popservice.GetMNoticeList(_App05Dto);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return App05ListDto;
    }


    public String CountSeq(String yyyymm){
        String ls_nseq = popservice.getMNoticeMaxSeq(yyyymm);
        int ll_nseq = 0;
        if(ls_nseq == null ){
            ls_nseq = yyyymm + "001";
        }else{
            ll_nseq = Integer.parseInt(ls_nseq);
            ls_nseq = Integer.toString(ll_nseq + 1 );
        }
        return ls_nseq;
    }
    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }
    /**
     * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
     * @return 랜덤 문자열
     */
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
