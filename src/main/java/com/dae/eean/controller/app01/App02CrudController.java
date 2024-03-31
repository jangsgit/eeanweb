package com.dae.eean.controller.app01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index11Dto;
import com.dae.eean.DTO.CommonDto;
import com.dae.eean.DTO.UserFormDto;
import com.dae.eean.Service.App01.Index02Service;
import com.dae.eean.Service.App01.Index11Service;
import com.dae.eean.Service.master.AuthService;
import com.dae.eean.controller.EncryptionController;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app02m", method = RequestMethod.POST)
public class App02CrudController {
    private final AuthService authService;
    UserFormDto userformDto = new UserFormDto();

    private final Index11Service service11;
    CommonDto CommDto = new CommonDto();
    List<Index11Dto> index11List = new ArrayList<>();
    Index11Dto index11Dto = new Index11Dto();

    EncryptionController enc = new EncryptionController();
    protected Log log =  LogFactory.getLog(this.getClass());


    //AS 접수현황
    @GetMapping(value="/index11/list01")
    public Object App02List_index(@RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  @RequestParam("asflag") String asflag,
                                  @RequestParam("asacorp") String asacorp,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("AS 접수현황");
        CommDto.setMenuUrl("통계현황>AS 접수현황");
        CommDto.setMenuCode("index11");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        if(userformDto == null){
            log.info("App02List_index Exception =====> relogin userformDto null");
            return "relogin";
        }
        model.addAttribute("userformDto",userformDto);

        try {

            if(asacorp == null || asacorp.equals("")){
                asacorp = "%";
            }
            index11Dto.setAs_acorp(asacorp);
            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            if(asflag.equals("2")){
                index11List = service11.GetAsJupsuList03(index11Dto);
            }else if(asflag.equals("1")){
                index11List = service11.GetAsJupsuList02(index11Dto);
            }else{
                index11List = service11.GetAsJupsuList01(index11Dto);
            }
            model.addAttribute("index11List",index11List);

        } catch (Exception ex) {
            log.info("App11List_index Exception =====>" + ex.toString());
        }

        return index11List;
    }




}
