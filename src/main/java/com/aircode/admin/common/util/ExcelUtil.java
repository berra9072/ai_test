package com.aircode.admin.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUtil {

	/**
	 * Excel 다운로드
	 *
	 * @param sheetName
	 * @param contentsList
	 * @param width
	 * @param size
	 * @param res
	 * @param fileName
	 */
	public static void excelDown(String sheetName, List<HashMap<String,Object>> contentsList, int width, int size,
			HttpServletResponse res, String fileName, String[] headerData) {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREAN);

		String fName = fileName;
		// Workbook 생성
		Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 이상

		// *** Sheet-------------------------------------------------
		// Sheet 생성
		Sheet sheet1 = xlsxWb.createSheet(sheetName);
		sheet1.setDefaultColumnWidth((short) width);

		Row row = null;

		row = sheet1.createRow(0);
		int j = 1;

		CellStyle styleTop = getStyle(xlsxWb, CellStyle.ALIGN_CENTER, "top");
		CellStyle styleContent = getStyle(xlsxWb, CellStyle.ALIGN_CENTER, "content");
		CellStyle tmpStyle = styleTop;
		createCellHeader(xlsxWb, row, headerData, tmpStyle);

		row = sheet1.createRow(1);

		if (contentsList.size() > 0) {
			for (int i = 0; i < contentsList.size(); i++) {
					tmpStyle = styleContent;
					createCell(xlsxWb, row, contentsList.get(i), tmpStyle);
				/*
				 * if (((i + 1) % size) == 0) { j++; t = 0;
				 *
				 * row = sheet1.createRow(j); }
				 */
					j++;
					row = sheet1.createRow(j);
			}
		}

		try {
			res.setContentType("application/vnd.ms-excel; charset=utf-8");
			fName = new String(fName.getBytes("KSC5601"), "8859_1");
			res.setHeader("Content-Disposition", "attachment; filename=" + fName.replaceAll("\n|\r", " ") + "_"
					+ df.format(new Date()).replaceAll("\n|\r", " ") + ".xlsx;");
			OutputStream out = res.getOutputStream();
			xlsxWb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("[COMMON][CommonUtil.excelDown] - Excel Down Error", e);
		}

	}

	/**
	 * Cell 서식 정의
	 *
	 * @param wb
	 * @param align
	 * @param back
	 * @return
	 */
	private static CellStyle getStyle(Workbook wb, int align, String back) {

		CellStyle style = wb.createCellStyle();

		style.setAlignment((short) align);

		if (back.equals("top")) {
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		} else if (back.equals("title")) {
			style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);

		return style;
	}

	/**
	 * Excel Cell Create
	 *
	 * @param wb
	 * @param row
	 * @param column
	 * @param str
	 * @param style
	 */
	private static void createCell(Workbook wb, Row row, HashMap<String,Object> map, CellStyle style) {

		Cell cell = null;
		int i = 0;

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			cell = row.createCell(i);
			cell.setCellValue(entry.getValue().toString());
			cell.setCellStyle(style);
			i++;
	    }


		if (style == null)
			return;


	}

	/**
	 * Excel Cell Create
	 *
	 * @param wb
	 * @param row
	 * @param column
	 * @param str
	 * @param style
	 */
	private static void createCellHeader(Workbook wb, Row row, String[] data, CellStyle style) {

		Cell cell = null;

		for (int i=0;i<data.length;i++) {
			cell = row.createCell(i);
			cell.setCellValue(data[i]);
			cell.setCellStyle(style);
	    }


		if (style == null)
			return;


	}
}
