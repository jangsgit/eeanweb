package com.dae.eean.Service.App01;
import com.dae.eean.DTO.App01.IndexDa024Dto;
import com.dae.eean.Mapper.App01.Index14ExcelMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service("Index14ExcelService")
public class Index14ExcelService {

    private final Index14ExcelMapper mapper;

    public Index14ExcelService(Index14ExcelMapper mapper) {
        this.mapper = mapper;
    }

    public void downloadDa024Excel(IndexDa024Dto param, HttpServletResponse response) throws IOException {

        // 파일명
        String filename = "품목별매출현황_출고확정_[" + param.getJpbgubn() + "]_" +  param.getFrdate() + "_" + param.getTodate() + ".xlsx";
        String encoded = URLEncoder.encode(filename, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encoded);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");


        try (SXSSFWorkbook wb = new SXSSFWorkbook(200)) { // 메모리에 유지할 row 수
            wb.setCompressTempFiles(true);

            Sheet sheet = wb.createSheet("DA024");
            int[] rowIdx = {0};

            // 헤더 스타일
            CellStyle headerStyle = wb.createCellStyle();
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            // 숫자 스타일(필요시)
            CellStyle numStyle = wb.createCellStyle();
            numStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0"));

            // 헤더
            Row header = sheet.createRow(rowIdx[0]++);
            String[] cols = {
                   "거래처코드",  "매출일자", "번호", "SEQ",
                    "품목코드", "품명", "규격", "단위",
                    "수량", "단가", "공급가", "부가세",
                    "합계", "거래처명", "본사", "모델", "색상",
                    "구분", "확정", "담당", "확정일", "확정일시"
            };
            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(headerStyle);
            }

            // 데이터: ResultHandler로 한 줄씩 받아서 바로 row 생성
            mapper.selectDa024ForExcel(param, resultContext -> {
                IndexDa024Dto r = resultContext.getResultObject();

                Row row = sheet.createRow(rowIdx[0]++);
                int col = 0;

                row.createCell(col++).setCellValue(nvl(r.getCltcd()));
                row.createCell(col++).setCellValue(nvl(r.getMisdate()));
                row.createCell(col++).setCellValue(nvl(r.getMisnum()));
                row.createCell(col++).setCellValue(nvl(r.getSeq()));

                row.createCell(col++).setCellValue(nvl(r.getPcode()));
                row.createCell(col++).setCellValue(nvl(r.getPname()));
                row.createCell(col++).setCellValue(nvl(r.getPsize()));
                row.createCell(col++).setCellValue(nvl(r.getPunit()));

                // 숫자 필드(문자/숫자 타입은 DTO 정의에 맞춰 조정)
                setNumber(row, col++, r.getQty(), numStyle);
                setNumber(row, col++, r.getUamt(), numStyle);
                setNumber(row, col++, r.getSamt(), numStyle);
                setNumber(row, col++, r.getAddamt(), numStyle);
                setNumber(row, col++, r.getAmt(), numStyle);

                row.createCell(col++).setCellValue(nvl(r.getAcorp()));
                row.createCell(col++).setCellValue(nvl(r.getPbonsa()));
                row.createCell(col++).setCellValue(nvl(r.getPmodel()));
                row.createCell(col++).setCellValue(nvl(r.getPcolor()));

                row.createCell(col++).setCellValue(nvl(r.getMisgubun()));
                row.createCell(col++).setCellValue(nvl(r.getFixflag()));
                row.createCell(col++).setCellValue(nvl(r.getPernm()));
                row.createCell(col++).setCellValue(nvl(r.getFixdate()));
                row.createCell(col++).setCellValue(nvl(r.getFixdatetime()));

                // 너무 자주 autosize하면 느려서 금지(마지막에 일부만 하거나 생략)
            });

            // 필요하면 컬럼 폭 기본값만 설정(빠름)
            for (int i = 0; i < cols.length; i++) {
                sheet.setColumnWidth(i, 4000);
            }

            wb.write(response.getOutputStream());
            response.flushBuffer();
        }
    }

    private static String nvl(Object v) {
        return v == null ? "" : String.valueOf(v);
    }

    private static void setNumber(Row row, int col, Object value, CellStyle style) {
        Cell cell = row.createCell(col);
        if (value == null) {
            cell.setCellValue(0);
            return;
        }
        try {
            double d = Double.parseDouble(String.valueOf(value));
            cell.setCellValue(d);
            if (style != null) cell.setCellStyle(style);
        } catch (Exception e) {
            cell.setCellValue(String.valueOf(value));
        }
    }
}
