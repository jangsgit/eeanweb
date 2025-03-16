package com.dae.eean.Service.App01;

import com.dae.eean.DTO.App01.*;
import com.dae.eean.Mapper.App01.Index14Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import com.dae.eean.Service.SqlRunner;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service("Index14Service")
public class Index14Service {
    @Autowired
    SqlRunner sqlRunner;

    @Autowired
    Index14Mapper  appMapper;


    protected Log log =  LogFactory.getLog(this.getClass());
    public String SelectMaxMisnum(IndexDa023Dto parm){
        return  appMapper.SelectMaxMisnum(parm);
    }
    public Integer SelectTrackMisnum(String parm){
        return  appMapper.SelectTrackMisnum(parm);
    }
    public Integer SelectTrackDevnum(String parm){
        return  appMapper.SelectTrackDevnum(parm);
    }
    public Boolean InsertTrackMisnum(IndexDa024Dto parm){
        return  appMapper.InsertTrackMisnum(parm);
    }
    public Boolean InsertTrackDevnum(IndexDa024Dto parm){
        return  appMapper.InsertTrackDevnum(parm);
    }
    public Boolean UpdateTrackMisnum(IndexDa024Dto parm){
        return  appMapper.UpdateTrackMisnum(parm);
    }
    public Boolean UpdateTrackDevnum(IndexDa024Dto parm){
        return  appMapper.UpdateTrackDevnum(parm);
    }

    public String SelectMaxMisnumWish(IndexDa023Dto parm){
        return  appMapper.SelectMaxMisnumWish(parm);
    }
    public String SelectCheckMisnum(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnum(parm);
    }
    public String SelectCheckMisnumJupsu(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumJupsu(parm);
    }

    public String SelectCheckMisnumMkflag(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumMkflag(parm);
    }
    public String SelectCheckMisnumMkflagJupsu(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumMkflagJupsu(parm);
    }


    public String SelectCheckMisnumOrd(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumOrd(parm);
    }

    public String SelectCheckMisnumWish(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumWish(parm);
    }

    public String SelectCheckMisnumWishMkflag(IndexDa023Dto parm){
        return  appMapper.SelectCheckMisnumWishMkflag(parm);
    }

    public IndexDa024Dto SelectCheckJpum024(IndexDa024Dto parm){
        return  appMapper.SelectCheckJpum024(parm);
    }
    public IndexDa024Dto SelectCheckJpum026(IndexDa024Dto parm){
        return  appMapper.SelectCheckJpum026(parm);
    }

    public String SelectMaxSeq(IndexDa023Dto parm){
        return  appMapper.SelectMaxSeq(parm);
    }
    public String SelectMaxSeqWish(IndexDa023Dto parm){
        return  appMapper.SelectMaxSeqWish(parm);
    }
    public List<IndexDa024Dto> SelectDa024List(IndexDa024Dto parm){
        return  appMapper.SelectDa024List(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListAS(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListAS(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListDel(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListDel(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListMakflag(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListMakflag(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListLikeAS(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListLikeAS(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListLike(IndexDa024Dto parm){

//        MapSqlParameterSource paramMap = new MapSqlParameterSource();
//        paramMap.addValue("cltcd", parm.getCltcd());
//        paramMap.addValue("perid", parm.getPerid());
//        paramMap.addValue("frdate", parm.getFrdate());
//        paramMap.addValue("todate", parm.getTodate());
//        paramMap.addValue("fixflag", parm.getFixflag());
//        paramMap.addValue("devflag", parm.getDevflag());
//        paramMap.addValue("misgubun", parm.getMisgubun());
//        paramMap.addValue("makflag", parm.getMakflag());
//        paramMap.addValue("jpbgubn", parm.getJpbgubn());
//        paramMap.addValue("jfrdate", parm.getJfrdate());
//        paramMap.addValue("jtodate", parm.getJtodate());
//
//        String sql = " select B.cltcd as cltcd, Left(B.misdate,4) + '-' + substring(B.misdate, 5,2)+ '-' + substring(B.misdate, 7,2) as misdate, B.misnum, B.seq, " +
//                         " B.pcode, B.pname, B.psize,    B.punit,  B.qty,  B.uamt, B.samt, B.addamt, isnull(C.remarkaa,'') as remarkaa, isnull(C.remarkbb,'') as remarkbb, isnull(B.makflag, '0') as makflag, " +
//                         " B.amt, A.acorp, pbonsa, pmodel, pcolor , D.inname, B.misgubun, isnull(B.fixflag,'0') as fixflag,isnull(B.devflag,'0') as devflag,   D.inname as pernm, C.unsongnum as unsongnum,   " +
//                         " B.fixdate,  CONVERT(CHAR(19),B.fixdatetime,20) as fixdatetime ,  0 as jqty " +
//                    "  from  TB_DA024 B WITH (NOLOCK) , " +
//                    "        cif A WITH (NOLOCK) Left outer JOIN INSA D ON A.abonsadam1 = D.insano, " +
//                    "        TB_DA023 C WITH (NOLOCK), " +
//                    "        jcode3 E WITH (NOLOCK)    " +
//                    "  where B.misdate + B.misnum = C.misdate + C.misnum AND B.misgubun = C.misgubun " +
//                    "        AND isnull(C.delflag, '') <> 'Y'  " +
//                    "        AND B.cltcd = A.acorp1 + CAST(A.acorp2 as varchar)      " +
//                    "        AND C.cltcd = A.acorp1 + CAST(A.acorp2 as varchar)      " +
//                    "        AND B.pcode = E.jkey                                    " +
//                    "        AND A.acorp1 + CAST(A.acorp2 as varchar) Like concat('%',:cltcd,'%')   " +
//                    "        AND isnull(A.abonsadam1,'')  Like concat('%',:perid,'%')   " +
//                    "        AND B.misdate between :frdate and :todate                   " +
//                    "        AND Isnull(B.fixflag, '0') Like concat('%',:fixflag,'%')    " +
//                    "        AND Isnull(B.devflag, '0') Like concat('%',:devflag,'%')     " +
//                    "        AND Isnull(C.misgubun, '') Like concat('%',:misgubun,'%')     " +
//                    "        AND Isnull(B.makflag, '0') Like concat('%',:makflag,'%')      " +
//                    "        and isnull(E.jpb_gubn,'') Like concat('%',:jpbgubn,'%')        " +
//                    "        ORDER BY  B.misdate desc, B.misnum, B.seq  ";

        //log.info("sql =====>" + sql);
//        return this.sqlRunner.getListRows(sql, paramMap);
//        {
//            call SP_SelectDa024ListLike(#{cltcd}, #{perid},#{frdate},#{todate},#{fixflag},#{devflag},#{misgubun},#{makflag},#{jpbgubn},#{jfrdate},#{jtodate})
//        }
        return  appMapper.SelectDa024ListLike(parm);
    }

//    public List<IndexDa024Dto> SelectDa024ListPrt(IndexDa024Dto parm){
//        return  appMapper.SelectDa024ListPrt(parm);
//    }
    public List<IndexDa024Dto> SelectDa024ListJang(IndexDa024Dto parm){
    return  appMapper.SelectDa024ListJang(parm);
}

    public List<IndexDa024Dto> SelectDa024ListLikeJang(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListLikeJang(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListLikeJupsu(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListLikeJupsu(parm);
    }

    public List<IndexDa024Dto> SelectDa024ListPrt(IndexDa024Dto parm) {
        return  appMapper.SelectDa024ListPrt(parm);
    };
    public List<IndexDa024Dto> SelectDa024ListPrtGroup(HashMap<String,String> parm) {
        return  appMapper.SelectDa024ListPrtGroup(parm);
    };
    public List<IndexDa024Dto> SelectDa024ListDevGroup(HashMap<String,String> parm) {
        return  appMapper.SelectDa024ListDevGroup(parm);
    };
    public List<IndexDa024Dto> SelectDa024ListDevJupsuGroup(HashMap<String,String> parm) {
        return  appMapper.SelectDa024ListDevJupsuGroup(parm);
    };

    public List<IndexDa024Dto> SelectDa026List(IndexDa024Dto parm){
        return  appMapper.SelectDa026List(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListDel(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListDel(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListLike(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListLike(parm);
    }

    public List<IndexDa024Dto> SelectDa024ListPerid(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListPerid(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListPeridJumun(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListPeridJumun(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListPeridGroup(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListPeridGroup(parm);
    }

    public List<IndexDa024Dto> SelectDa024ListPeridGroupJumun(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListPeridGroupJumun(parm);
    }

    public List<IndexDa024Dto> SelectDa024ListJpumArea(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumArea(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpumAreaGugun(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumAreaGugun(parm);
    }

    public List<IndexDa024Dto> SelectDa026ListPerid(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListPerid(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListCltcd(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListCltcd(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListCltcdGroup(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListCltcdGroup(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListCltcdGroupJumun(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListCltcdGroupJumun(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListCltcd(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListCltcd(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpum(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpum(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpumGroup(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumGroup(parm);
    }
    public List<IndexDa024Dto> SelectDa024ListJpumGroupJumun(IndexDa024Dto parm){
        return  appMapper.SelectDa024ListJpumGroupJumun(parm);
    }
    public List<IndexDa024Dto> SelectDa026ListJpum(IndexDa024Dto parm){
        return  appMapper.SelectDa026ListJpum(parm);
    }
    public IndexDa024Dto SelectDa026Detail(IndexDa024Dto parm){
        return  appMapper.SelectDa026Detail(parm);
    }
    public IndexDa024Dto SelectDa026Detail02(IndexDa024Dto parm){
        return  appMapper.SelectDa026Detail02(parm);
    }

    public IndexDa024Dto SelectDa024Detail(IndexDa024Dto parm){
        return  appMapper.SelectDa024Detail(parm);
    }

    public String SelectedFixDA024(IndexDa024Dto parm){
        return  appMapper.SelectedFixDA024(parm);
    }


    public Boolean InsertDa023(IndexDa023Dto parm){ return  appMapper.InsertDa023(parm);}
    public Boolean InsertDa024(IndexDa024Dto parm){ return  appMapper.InsertDa024(parm);}
    public Boolean InsertDa024Jumsu(IndexDa024Dto parm){ return  appMapper.InsertDa024Jumsu(parm);}
    public Boolean UpdateDa024Jumsu(IndexDa024Dto parm){ return  appMapper.UpdateDa024Jumsu(parm);}

    public Boolean InsertDa025(IndexDa023Dto parm){ return  appMapper.InsertDa025(parm);}
    public Boolean InsertDa026(IndexDa024Dto parm){ return  appMapper.InsertDa026(parm);}
    public Boolean InsertDa023Order(IndexDa024Dto parm){ return  appMapper.InsertDa023Order(parm);}
    public Boolean InsertDa024Order(IndexDa024Dto parm){ return  appMapper.InsertDa024Order(parm);}

    public Boolean InsertIa011(IndexIa011Dto parm){ return  appMapper.InsertIa011(parm);}
    public Boolean InsertIa012(IndexIa012Dto parm){ return  appMapper.InsertIa012(parm);}
    public Boolean DeleteDA023(IndexDa024Dto parm){ return  appMapper.DeleteDA023(parm);}
    public Boolean DeleteDA024(IndexDa024Dto parm){ return  appMapper.DeleteDA024(parm);}
    public Boolean DeleteDA024Jumsu(IndexDa024Dto parm){ return  appMapper.DeleteDA024Jumsu(parm);}

    public Boolean DeleteDA023Ord(IndexDa024Dto parm){ return  appMapper.DeleteDA023Ord(parm);}
    public Boolean DeleteDA024Ord(IndexDa024Dto parm){ return  appMapper.DeleteDA024Ord(parm);}
    public Boolean DeleteDA024Mkflag(IndexDa024Dto parm){ return  appMapper.DeleteDA024Mkflag(parm);}
    public Boolean DeleteDA026Mkflag(IndexDa024Dto parm){ return  appMapper.DeleteDA026Mkflag(parm);}



    public Boolean DeleteDA025(IndexDa024Dto parm){ return  appMapper.DeleteDA025(parm);}
    public Boolean DeleteDA026(IndexDa024Dto parm){ return  appMapper.DeleteDA026(parm);}
    public Boolean UpdateDA023Unsong(IndexDa023Dto parm){ return  appMapper.UpdateDA023Unsong(parm);}
    public Boolean UpdateDA023UnsongDevnum(IndexDa023Dto parm){ return  appMapper.UpdateDA023UnsongDevnum(parm);}
    public Boolean UpdateDA024(IndexDa024Dto parm){ return  appMapper.UpdateDA024(parm);}
    public Boolean UpdateDA024Del(IndexDa024Dto parm){ return  appMapper.UpdateDA024Del(parm);}

    public Boolean UpdateDA024Dev(HashMap<String,String> parm){ return  appMapper.UpdateDA024Dev(parm);}

    public Boolean UpdateDA024Devnum(IndexDa024Dto parm){ return  appMapper.UpdateDA024Devnum(parm);}

    public Boolean UpdateDA024Fix(HashMap<String,String> parm){ return  appMapper.UpdateDA024Fix(parm);}

    public Boolean UpdateDA026(IndexDa024Dto parm){ return  appMapper.UpdateDA026(parm);}
    public Boolean UpdateDA026Del(IndexDa024Dto parm){ return  appMapper.UpdateDA026Del(parm);}

    public Boolean UpdateDA024rkaa(IndexDa024Dto parm){ return  appMapper.UpdateDA024rkaa(parm);}
    public Boolean UpdateDA024rkbb(IndexDa024Dto parm){ return  appMapper.UpdateDA024rkbb(parm);}
    public Boolean UpdateDA026rkaa(IndexDa024Dto parm){ return  appMapper.UpdateDA026rkaa(parm);}
    public Boolean UpdateDA026rkbb(IndexDa024Dto parm){ return  appMapper.UpdateDA026rkbb(parm);}
    public Boolean UpdateDA024Qty(IndexDa024Dto parm){ return  appMapper.UpdateDA024Qty(parm);}
    public Boolean UpdateDA024Makfix(IndexDa024Dto parm){ return  appMapper.UpdateDA024Makfix(parm);}
    public Boolean UpdateDA024QtySame(IndexDa024Dto parm){ return  appMapper.UpdateDA024QtySame(parm);}
    public Boolean UpdateDA026QtySame(IndexDa024Dto parm){ return  appMapper.UpdateDA026QtySame(parm);}
    public Boolean UpdateDA026Qty(IndexDa024Dto parm){ return  appMapper.UpdateDA026Qty(parm);}
    public Boolean UpdateDA024Amt(IndexDa024Dto parm){ return  appMapper.UpdateDA024Amt(parm);}
    public Boolean UpdateDA024Remk(IndexDa024Dto parm){ return  appMapper.UpdateDA024Remk(parm);}
    public Boolean UpdateDA026Amt(IndexDa024Dto parm){ return  appMapper.UpdateDA026Amt(parm);}



}
