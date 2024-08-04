package com.dae.eean.Service.impl;

import com.dae.eean.DTO.App05ElvlrtDto;
import com.dae.eean.DTO.AttachDTO;
import com.dae.eean.Mapper.NattachElvlrtMapper;
import com.dae.eean.Mapper.PopupMapper;
import com.dae.eean.util.FilsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class AppUploadServiceImpl implements  AppUploadService {

    @Autowired
    private PopupMapper AppPopMapper;

    @Autowired
    private NattachElvlrtMapper attachMapper;

    @Autowired
    private FilsUtils fileUtils;

    public boolean registerMNotice(App05ElvlrtDto params, List<AttachDTO> attachDto){
        int queryResult = 1;

        if (CollectionUtils.isEmpty(attachDto) == false) {
            queryResult = attachMapper.InsertAttach(attachDto);
            if(queryResult < 1){
                queryResult = 0;
            }
        }
       return (queryResult > 0);
    }
    public boolean registerMNoticeDel(App05ElvlrtDto parm){
        int queryResult = 1;
        queryResult = attachMapper.deleteAttach(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }
    public boolean MNoticeFileDel(AttachDTO parm){
        int queryResult = 1;
        queryResult = attachMapper.deleteAttachDetail(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }

    public List<AttachDTO> MNoticeFilelist(App05ElvlrtDto perm){
        List<AttachDTO> attachDto = attachMapper.selectAttachList(perm);
        return attachDto;
    }

    public AttachDTO selectAttachDeteil(AttachDTO parm){
        AttachDTO attachDto = attachMapper.selectAttachDeteil(parm);
        return attachDto;
    }

    @Override
    public boolean registerMNotice(App05ElvlrtDto params, MultipartFile[] files) {
        return false;
    }
}
