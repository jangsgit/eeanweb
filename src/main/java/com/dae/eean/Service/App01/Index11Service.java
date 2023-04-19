package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.Index02Dto;
import com.dae.eean.DTO.App01.Index11Dto;
import com.dae.eean.Mapper.App01.Index02Mapper;
import com.dae.eean.Mapper.App01.Index11Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index11Service")
public class Index11Service {
    @Autowired
    Index11Mapper appMapper;

    public List<Index11Dto> GetAsJupsuList01(Index11Dto parm){
                return  appMapper.GetAsJupsuList01(parm);
    }
    public List<Index11Dto> GetAsJupsuList02(Index11Dto parm){
        return  appMapper.GetAsJupsuList02(parm);
    }
    public List<Index11Dto> GetAsJupsuList03(Index11Dto parm) { return  appMapper.GetAsJupsuList03(parm);}



}
