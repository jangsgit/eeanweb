package com.dae.eean.Mapper;

import com.dae.eean.DTO.App05ElvlrtDto;
import com.dae.eean.DTO.AttachDTO;

import java.util.List;

public interface NattachElvlrtMapper {
    public int InsertAttach(List<AttachDTO> attachList);

    public int deleteAttach(App05ElvlrtDto perm);
    public int deleteAttachDetail(AttachDTO perm);
    public AttachDTO selectAttachDeteil(AttachDTO perm);

    public List<AttachDTO> selectAttachList(App05ElvlrtDto perm);

    public int selectAttachTotalCount(String boardIdx);
}
