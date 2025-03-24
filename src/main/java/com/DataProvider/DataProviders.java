package com.DataProvider;

import org.testng.annotations.DataProvider;

import com.Utility.NewExcelLibrary;

public class DataProviders 
{
NewExcelLibrary obj = new NewExcelLibrary();
	
	@DataProvider(name = "credentials")
	public Object[][] getCredentials() 
	{
		// Totals rows count
		int rows = obj.getRowCount("Credentials");
		// Total Columns
		int column = obj.getColumnCount("Credentials");
		int rowsToRead = 3;
		  int startRow = 2;
		  int endRow = Math.min(startRow + rowsToRead - 1, rows);
		  Object[][] data = new Object[rowsToRead][column];
		//int actRows = rows - 1;

		

		  for (int i = 0; i < rowsToRead; i++) {
		        for (int j = 0; j < column; j++) {
		            // Read only 3 rows starting from row 2
		            data[i][j] = obj.getCellData("Credentials", j, startRow + i);
		        }
		    }
		return data;
	}
}
