package com.dae.eean.Mapper;

import com.dae.eean.DTO.AttachDTO;

import java.util.List;

public interface MattachElvlrtMapper {

    public int InsertAttach(List<AttachDTO> attachList);

    public int deleteAttachDetail(AttachDTO perm);
    public AttachDTO selectAttachDeteil(AttachDTO perm);
    public int selectAttachTotalCount(String boardIdx);

}
