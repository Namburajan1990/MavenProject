package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import configuration.Constantclass;

public class ExcelRead {
	
	public static Object[][] readvalues(String ExcelPath,String Sheetname) throws IOException
	{
		FileInputStream Fis = new FileInputStream(new File(ExcelPath));
		
		XSSFWorkbook Book = new XSSFWorkbook(Fis);
		
		XSSFSheet Sheet = Book.getSheet(Sheetname);
		
		int RowCount = Sheet.getLastRowNum()+1;
		int ColumnCount = Sheet.getRow(0).getLastCellNum();
		System.out.println(RowCount);
		System.out.println(ColumnCount);
		String Data[][]=new String[RowCount-1][ColumnCount];
		
		for(int i=0;i<RowCount-1;i++)
		{
			for(int j=0;j<ColumnCount;j++)
			{
				Data[i][j]=Sheet.getRow(i+1).getCell(j).getStringCellValue();
				
			}
			
		}
		return Data;
		
	}

public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String ExcelPath=Constantclass.ExcelPath+"Datadriver.xlsx";
		String SheetName = "Registeration";
		readvalues(ExcelPath, SheetName);

		
	}

}
