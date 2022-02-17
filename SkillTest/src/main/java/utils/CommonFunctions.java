package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.google.common.collect.Table.Cell;

public class CommonFunctions {
	
	
	public HashMap<String, String> fetchXLDataNStoreInHashMap(String strParametersNValues, String strSheetName,
			String strDataRow, String strColumnsCommaSeparated) throws Exception {
		HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+File.separator+"input"+File.separator+"inputData1.xls"));
		HSSFSheet objSHInputSheet = objWB.getSheet(strSheetName);

		HashMap<String, String> hmParamsNValues = SplitNStoreParamsInHashMap(strParametersNValues);
		int intParamInputRow = Integer.parseInt((String) hmParamsNValues.get(strDataRow));

		if (strColumnsCommaSeparated.equalsIgnoreCase("All")) {
			String strAllColumns = "";
			int intLastCellNum = objSHInputSheet.getRow(0).getLastCellNum();
			for (int intCell = 0; intCell < intLastCellNum; intCell++) {
				if (strAllColumns == "")
					strAllColumns = objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
				else if(null != objSHInputSheet && null != objSHInputSheet.getRow(0) && null != objSHInputSheet.getRow(0).getCell(intCell))
					strAllColumns = strAllColumns + ","
							+ objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
			}
			strColumnsCommaSeparated = strAllColumns;
		}

		String[] arrColumnNames = strColumnsCommaSeparated.split(",");
		HashMap<String, String> hmInputDataSet = new HashMap<String, String>();
		String strCurrCellValue = "";
		for (String strColumnName : arrColumnNames) {
			strCurrCellValue = GetCellValueForRowNum(objSHInputSheet, strColumnName, intParamInputRow);
			
			hmInputDataSet.put(strColumnName, strCurrCellValue);
		}

		return hmInputDataSet;
	}


	public HashMap<String, String> SplitNStoreParamsInHashMap(String strParameters) {
		HashMap<String, String> objHashMap = new HashMap<String, String>();
		if (strParameters != "" && strParameters != null) {
		String[] arrKeysNValues = strParameters.split(";");

		for (int intArrElt = 0; intArrElt < arrKeysNValues.length; intArrElt++) {
		if (StringUtils.isNotBlank(arrKeysNValues[intArrElt])) {
		String[] arrCurrKeyNValue = arrKeysNValues[intArrElt].split("=>");
		if (StringUtils.isNotBlank(arrCurrKeyNValue[0]) && "InputDataRow".equalsIgnoreCase(arrCurrKeyNValue[0])) {
		objHashMap.put(arrCurrKeyNValue[0].trim(), arrCurrKeyNValue[1]);
		}
		else if(StringUtils.isNotBlank(arrCurrKeyNValue[0])){
		objHashMap.put(arrCurrKeyNValue[0].trim().toLowerCase(), arrCurrKeyNValue[1]);
		}

		}
		}
		}
		return objHashMap;
		}

	public String GetCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception {
		intRowNum = intRowNum - 1;
		int intXLColumn;
		intXLColumn = this.FindColumnNoInExcel(objSH, strColumnName, 1);

		String strAvailCellValue;
		HSSFCell objXLCell;
		try {
		objXLCell = objSH.getRow(intRowNum).getCell(intXLColumn);
		objXLCell.setCellType(HSSFCell.CELL_TYPE_STRING); // to convert cell type to string
		strAvailCellValue = objXLCell.getStringCellValue();
		return strAvailCellValue.trim();
		} catch (Exception e) {
		// _log.info("Exception " + e);
		return "";
		}
		}
	
	public int FindColumnNoInExcel(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception {
		intRowNum = intRowNum - 1;
		HSSFRow objRow = objSH.getRow(intRowNum);

		int intLastCellNum;
		String strAvailCellValue;

		try {
			intLastCellNum = objRow.getLastCellNum();
			for (int intCell = 0; intCell < intLastCellNum; intCell++) {
				strAvailCellValue = objSH.getRow(intRowNum).getCell(intCell).getStringCellValue();
				if (strAvailCellValue.equalsIgnoreCase(strColumnName)) {
					return intCell;
				}
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}
	

	
}
