package com.dae.eean.Service.impl;

import com.dae.eean.DTO.App05ElvlrtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public interface AppUploadService {

    @Autowired
    public boolean registerMNotice(App05ElvlrtDto params, MultipartFile[] files);
}
