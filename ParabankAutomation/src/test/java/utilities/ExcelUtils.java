package utilities;

import java.io.FileInputStream;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static XSSFWorkbook wb;
	static XSSFSheet ws;

	
	public static Object[][] getTestData(String filepath, String xlsheet) throws Exception{
		FileInputStream file = new FileInputStream(filepath);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(xlsheet);
		
		int rows = ws.getLastRowNum();
		int cols = ws.getRow(0).getLastCellNum();
		
		Object[][] data =new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j] = ws.getRow(i+1).getCell(j).toString();
			}
		}
		wb.close();
		return data;
	}
	
	public static Object[][] getLoginData(String filepath, String xlsheet) throws Exception{
		FileInputStream file = new FileInputStream(filepath);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(xlsheet);
		
		int rows = ws.getLastRowNum();
		int cols = ws.getRow(0).getLastCellNum();
		
		Object[][] data =new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j] = ws.getRow(i+1).getCell(j).toString();
			}
		}
		wb.close();
		return data;
	}

}