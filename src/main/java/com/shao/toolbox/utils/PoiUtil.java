package com.shao.toolbox.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {

	/**
	 * 读取excel全部sheet的内容
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, List<List<String>>> readExcel(File file) throws FileNotFoundException, IOException {

		Map<String, List<List<String>>> resultMap = new HashMap<String, List<List<String>>>();

		// 获取Workbook对象
		Workbook wb = getWorkBook(file);

		try {
			// 获取sheet数量
			int numberOfSheets = wb.getNumberOfSheets();

			// 遍历sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = wb.getSheetAt(i);
				String sheetName = sheet.getSheetName();
				List<List<String>> sheetList = new ArrayList<>();

				// 获取行数
				int numberOfRows = sheet.getPhysicalNumberOfRows();

				// 遍历行
				for (int r = 0; r < numberOfRows; r++) {
					Row row = sheet.getRow(r);
					// 一行结束
					sheetList.add(readRow(row));
				}

				// 一个sheet结束
				resultMap.put(sheetName, sheetList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (wb != null) {
				wb.close();
			}
		}
		return resultMap;
	}

	/**
	 * 读取excel指定sheetname的内容
	 * 
	 * @param file
	 * @param sheetName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, List<List<String>>> readExcelBySheetName(File file, String sheetName)
			throws FileNotFoundException, IOException {

		Map<String, List<List<String>>> resultMap = new HashMap<String, List<List<String>>>();

		// 获取Workbook对象
		Workbook wb = getWorkBook(file);

		try {
			Sheet sheet = wb.getSheet(sheetName);
			List<List<String>> sheetList = new ArrayList<>();

			// 获取行数
			int numberOfRows = sheet.getPhysicalNumberOfRows();

			// 遍历行
			for (int r = 0; r < numberOfRows; r++) {
				Row row = sheet.getRow(r);
				// 一行结束
				sheetList.add(readRow(row));
			}

			// 一个sheet结束
			resultMap.put(sheetName, sheetList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (wb != null) {
				wb.close();
			}
		}
		return resultMap;
	}

	/**
	 * 读取excel指定sheetIndex的内容
	 * 
	 * @param file
	 * @param sheetIndex
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, List<List<String>>> readExcelBySheetIndex(File file, int index)
			throws FileNotFoundException, IOException {

		Map<String, List<List<String>>> resultMap = new HashMap<String, List<List<String>>>();

		// 获取Workbook对象
		Workbook wb = getWorkBook(file);

		try {
			Sheet sheet = wb.getSheetAt(index);
			List<List<String>> sheetList = new ArrayList<>();

			// 获取行数
			int numberOfRows = sheet.getPhysicalNumberOfRows();

			// 遍历行
			for (int r = 0; r < numberOfRows; r++) {
				Row row = sheet.getRow(r);
				// 一行结束
				sheetList.add(readRow(row));
			}

			// 一个sheet结束
			resultMap.put(sheet.getSheetName(), sheetList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (wb != null) {
				wb.close();
			}
		}
		return resultMap;
	}
	
	/**
	 * 获取Workbook对象
	 * @param file 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Workbook getWorkBook(File file) throws FileNotFoundException, IOException {
		// 获取文件名称
		String fileName = file.getName();

		// 初始化workbook对象
		if (fileName.substring(fileName.lastIndexOf(".")).equals(".xls")) {
			return new HSSFWorkbook(new FileInputStream(file));
		} else if (fileName.substring(fileName.lastIndexOf(".")).equals(".xlsx")) {
			return new XSSFWorkbook(new FileInputStream(file));
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	private static List<String> readRow(Row row) {
		List<String> rowList = new ArrayList<>();

		// 获取列数
		int numberOfCells = row.getPhysicalNumberOfCells();

		// 遍历列
		for (int c = 0; c < numberOfCells; c++) {
			Cell cell = row.getCell(c);
			CellType cellTypeEnum = cell.getCellTypeEnum();

			// 根据cell的类型获取值
			switch (cellTypeEnum.getCode()) {

			case Cell.CELL_TYPE_BLANK:
				rowList.add("");
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				rowList.add(String.valueOf(cell.getBooleanCellValue()));
				break;
			case Cell.CELL_TYPE_ERROR:
				rowList.add(String.valueOf(cell.getErrorCellValue()));
				break;
			case Cell.CELL_TYPE_FORMULA:
//				CellType typeEnum = cell.getCachedFormulaResultTypeEnum();
				// TODO 下次再写
				rowList.add("");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				rowList.add(String.valueOf(cell.getNumericCellValue()));
				break;
			case Cell.CELL_TYPE_STRING:
				rowList.add(cell.getStringCellValue());
			default:
				break;
			}
		}

		return rowList;
	}

}
