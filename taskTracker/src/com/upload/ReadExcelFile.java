package com.upload;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReadExcelFile {

	public static JSONObject getExcelDataAsJsonObject(String mpp_file_dir) {

		JSONObject sheetsJsonObject = new JSONObject();
		Workbook workbook = null;

		try {

			FileInputStream inputStream = new FileInputStream(new File(getFileName(mpp_file_dir)));// D:/bkp/ReadExcel/Book1.xlsx

			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			System.out.println(
					"ExcelUtils -> getExcelDataAsJsonObject() :: Exception thrown constructing XSSFWorkbook from provided excel file.  InvalidFormatException | IOException => "
							+ e);
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			JSONArray sheetArray = new JSONArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();

			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JSONArray jsonObject = new JSONArray();

				if (currentRow.getRowNum() != 0 && !checkIfRowIsEmpty(currentRow)) {

					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellType() == CellType.STRING) {
								jsonObject.add(currentRow.getCell(j).getStringCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.NUMERIC) {
								jsonObject.add(String.format("%.1f",currentRow.getCell(j).getNumericCellValue() * 100));
							} else if (currentRow.getCell(j).getCellType() == CellType.BOOLEAN) {
								jsonObject.add(currentRow.getCell(j).getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.BLANK) {
								jsonObject.add("");
							}
						}
					}

					sheetArray.add(jsonObject);

				} else if (currentRow.getRowNum() == 0) {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k).getStringCellValue());
					}
				}

			}

			sheetsJsonObject.put(workbook.getSheetName(i), sheetArray);

		}

		return sheetsJsonObject;

	}

	
	public static JSONObject getCompletedTaskAsJsonObject(String mpp_file_dir) {

		JSONObject sheetsJsonObject = new JSONObject();
		Workbook workbook = null;

		try {

			FileInputStream inputStream = new FileInputStream(new File(getFileName(mpp_file_dir)));// D:/bkp/ReadExcel/Book1.xlsx

			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			System.out.println(
					"ExcelUtils -> getExcelDataAsJsonObject() :: Exception thrown constructing XSSFWorkbook from provided excel file.  InvalidFormatException | IOException => "
							+ e);
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			JSONArray sheetArray = new JSONArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();

			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JSONArray jsonObject = new JSONArray();

				if (currentRow.getRowNum() != 0 && !checkIfRowIsEmpty(currentRow)) {
					double completed = 0;
					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellType() == CellType.STRING) {
								jsonObject.add(currentRow.getCell(j).getStringCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.NUMERIC) {
								completed = currentRow.getCell(j).getNumericCellValue() * 100;
								jsonObject.add(String.valueOf(currentRow.getCell(j).getNumericCellValue() * 100));
							} else if (currentRow.getCell(j).getCellType() == CellType.BOOLEAN) {
								jsonObject.add(currentRow.getCell(j).getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.BLANK) {
								jsonObject.add("");
							}
						}
					}
					if (completed == 100.0) {
						sheetArray.add(jsonObject);
					}

				} else if (currentRow.getRowNum() == 0) {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k).getStringCellValue());
					}
				}

			}

			sheetsJsonObject.put(workbook.getSheetName(i), sheetArray);

		}

		return sheetsJsonObject;

	}
	
	
	public static JSONObject getInProgressTaskAsJsonObject(String mpp_file_dir) throws ParseException {

		JSONObject sheetsJsonObject = new JSONObject();
		Workbook workbook = null;

		try {

			FileInputStream inputStream = new FileInputStream(new File(getFileName(mpp_file_dir)));

			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			System.out.println(
					"ExcelUtils -> getExcelDataAsJsonObject() :: Exception thrown constructing XSSFWorkbook from provided excel file.  InvalidFormatException | IOException => "
							+ e);
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			JSONArray sheetArray = new JSONArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();

			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JSONArray jsonObject = new JSONArray();

				if (currentRow.getRowNum() != 0 && !checkIfRowIsEmpty(currentRow)) {
					double completed = 0;
					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellType() == CellType.STRING) {
								jsonObject.add(currentRow.getCell(j).getStringCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.NUMERIC) {
								completed = currentRow.getCell(j).getNumericCellValue() * 100;
								jsonObject.add(String.format("%.1f", currentRow.getCell(j).getNumericCellValue() * 100));
							} else if (currentRow.getCell(j).getCellType() == CellType.BOOLEAN) {
								jsonObject.add(currentRow.getCell(j).getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.BLANK) {
								jsonObject.add("");
							}
						}
					}
					SimpleDateFormat formatter= new SimpleDateFormat("E MM/dd/yy");
					
					if (formatter.parse(currentRow.getCell(7).getStringCellValue()).after(new Date())&&formatter.parse(currentRow.getCell(5).getStringCellValue()).before(new Date())) {//Date.parse(aData[7])>=Date.parse(new Date()) && Date.parse(aData[5])<=Date.parse(new Date())
						sheetArray.add(jsonObject);
					}

				} else if (currentRow.getRowNum() == 0) {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k).getStringCellValue());
					}
				}

			}

			sheetsJsonObject.put(workbook.getSheetName(i), sheetArray);

		}

		return sheetsJsonObject;

	}
	
	public static JSONObject getRunningLateAsJsonObject(String mpp_file_dir) throws ParseException {

		JSONObject sheetsJsonObject = new JSONObject();
		Workbook workbook = null;

		try {

			FileInputStream inputStream = new FileInputStream(new File(getFileName(mpp_file_dir)));

			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			System.out.println(
					"ExcelUtils -> getExcelDataAsJsonObject() :: Exception thrown constructing XSSFWorkbook from provided excel file.  InvalidFormatException | IOException => "
							+ e);
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			JSONArray sheetArray = new JSONArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();

			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JSONArray jsonObject = new JSONArray();

				if (currentRow.getRowNum() != 0 && !checkIfRowIsEmpty(currentRow)) {
					double completed = 0;
					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellType() == CellType.STRING) {
								jsonObject.add(currentRow.getCell(j).getStringCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.NUMERIC) {
								completed = currentRow.getCell(j).getNumericCellValue() * 100;
								jsonObject.add(String.format("%.1f",currentRow.getCell(j).getNumericCellValue() * 100));
							} else if (currentRow.getCell(j).getCellType() == CellType.BOOLEAN) {
								jsonObject.add(currentRow.getCell(j).getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.BLANK) {
								jsonObject.add("");
							}
						}
					}
					SimpleDateFormat formatter= new SimpleDateFormat("E MM/dd/yy");
					
					if (formatter.parse(currentRow.getCell(7).getStringCellValue()).before(new Date())&&completed!=100.0) {
						sheetArray.add(jsonObject);
					}

				} else if (currentRow.getRowNum() == 0) {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k).getStringCellValue());
					}
				}

			}

			sheetsJsonObject.put(workbook.getSheetName(i), sheetArray);

		}

		return sheetsJsonObject;

	}
	
	public static JSONObject getNotStartedTaskAsJsonObject(String mpp_file_dir) throws ParseException {

		JSONObject sheetsJsonObject = new JSONObject();
		Workbook workbook = null;

		try {

			FileInputStream inputStream = new FileInputStream(new File(getFileName(mpp_file_dir)));

			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			System.out.println(
					"ExcelUtils -> getExcelDataAsJsonObject() :: Exception thrown constructing XSSFWorkbook from provided excel file.  InvalidFormatException | IOException => "
							+ e);
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			JSONArray sheetArray = new JSONArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();

			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JSONArray jsonObject = new JSONArray();

				if (currentRow.getRowNum() != 0 && !checkIfRowIsEmpty(currentRow)) {
					double completed = 0;
					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellType() == CellType.STRING) {
								jsonObject.add(currentRow.getCell(j).getStringCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.NUMERIC) {
								completed = currentRow.getCell(j).getNumericCellValue() * 100;
								jsonObject.add(String.valueOf(currentRow.getCell(j).getNumericCellValue() * 100));
							} else if (currentRow.getCell(j).getCellType() == CellType.BOOLEAN) {
								jsonObject.add(currentRow.getCell(j).getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellType() == CellType.BLANK) {
								jsonObject.add("");
							}
						}
					}
					SimpleDateFormat formatter= new SimpleDateFormat("E MM/dd/yy");
					
					if (formatter.parse(currentRow.getCell(5).getStringCellValue()).after(new Date())) {
						sheetArray.add(jsonObject);
					}

				} else if (currentRow.getRowNum() == 0) {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k).getStringCellValue());
					}
				}

			}

			sheetsJsonObject.put(workbook.getSheetName(i), sheetArray);

		}

		return sheetsJsonObject;

	}
	
	private static boolean checkIfRowIsEmpty(Row row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}
		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			Cell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && !"".equals(cell.toString())) {
				return false;
			}
		}
		return true;
	}

	public static String getFileName(String mpp_file_dir) throws IOException {
		File directory = new File(mpp_file_dir);

		File[] files = directory.listFiles();
		String pattern = "(d_Dev_DCI-MPP)_[a-z_\\-\\s0-9\\.]*.(xls|xlsx)";

		FileFilter filter = new RegexFileFilter(pattern);
		files = directory.listFiles(filter);
		System.out.println("reading file" + mpp_file_dir + files[0].getName());
		return mpp_file_dir + files[0].getName();

	}

	
}
