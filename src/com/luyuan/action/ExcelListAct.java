package com.luyuan.action;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 使用poi导出Excel的基本格式
 */

public class ExcelListAct extends ListAct {

	/***************Excel******************/
	//标题行格式
	protected HSSFCellStyle getTitleStytle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont(); 
        font.setFontHeightInPoints((short)11); 
        font.setFontName("宋体"); 
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置粗体

		HSSFCellStyle style = workbook.createCellStyle(); 
        style.setFont(font); 
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        style.setFillBackgroundColor((short)255);
        return style;
	}
	
	//内容行格式
	protected HSSFCellStyle getContentStytle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont(); 
        font.setFontHeightInPoints((short)11); 
        font.setFontName("宋体"); 

		HSSFCellStyle style = workbook.createCellStyle(); 
        style.setFont(font); 
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        return style;
	}
	
	//设置一行中所有单元格的格式
	protected void setRowStyle(HSSFRow row, HSSFCellStyle style) {
		for(int i = 0; row.getCell(i) != null; i++)
			row.getCell(i).setCellStyle(style);
	}
	
	//设置列宽
	protected void setColumnWidth(HSSFSheet sheet, List<Integer> columnWidthList) {
		//根据自己调整的Excel列宽的像素值乘以37
		for(int i = 0; i < columnWidthList.size(); i++)
			sheet.setColumnWidth(i, columnWidthList.get(i) * 37);		
	}
}
