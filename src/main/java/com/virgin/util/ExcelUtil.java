package com.virgin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static final String filePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources";

	public static String[][] readExcel(String fileName, String sheetName)
			throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workBook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			workBook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			workBook = new HSSFWorkbook(inputStream);
		}
		Sheet guru99Sheet = workBook.getSheet(sheetName);
		int rowCount = guru99Sheet.getLastRowNum()
				- guru99Sheet.getFirstRowNum();
		int cellCount = guru99Sheet.getRow(0).getLastCellNum();
		String[][] testData = new String[rowCount][cellCount];
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = guru99Sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (i != 0){
					if(row.getCell(j).getCellType()==1){
						testData[i - 1][j] = row.getCell(j).getStringCellValue();	
					}else if(row.getCell(j).getCellType()==0){
						testData[i - 1][j] = new Double(row.getCell(j).getNumericCellValue()).toString();
					}				
				}					
			}
		}
		return testData;
	}

	public void writeExcel(String fileName, String sheetName,
			String[] dataToWrite) throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workBook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			workBook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			workBook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowCount + 1);
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}

		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workBook.write(outputStream);
		outputStream.close();
	}

}