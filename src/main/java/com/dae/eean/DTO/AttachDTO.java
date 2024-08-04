package com.dae.eean.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDTO extends CommonDto{

    /** 파일 번호 (PK) */
    private Long idx;

    /** 게시글 번호 (FK) */
    private String boardIdx;

    /** 원본 파일명 */
    private String originalName;

    /** 저장 파일명 */
    private String saveName;

    /** 파일 크기 */
    private long size;

    /** 테이블 구분 */
    private String flag;


    /** 삭제여부 */
    private String deleteyn;

    /** 등록일자  */
    private String inserttime;

    /** 삭제일자  */
    private String deletetime;

    private String custcd;
    private String spjangcd;

}

