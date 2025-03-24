package com.DataProvider;

import org.testng.annotations.DataProvider;

import com.Utility.NewExcelLibrary;

public class DataProviders {
	NewExcelLibrary obj = new NewExcelLibrary();

	@DataProvider(name = "credentials")
	public Object[][] getCredentials() {
		int rows = obj.getRowCount("Credentials");

		int column = 2;

		int rowsToRead = 7;
		int startRow = 2;

		int availableRows = rows - startRow + 1;
		rowsToRead = Math.min(rowsToRead, availableRows);

		if (rowsToRead <= 0) {
			System.out.println("No rows available to read!");
			return new Object[0][0];
		}

		Object[][] data = new Object[rowsToRead][column];

		for (int i = 0; i < rowsToRead; i++) {
			for (int j = 0; j < column; j++) {

				String cellData = obj.getCellData("Credentials", j, startRow + i);

				data[i][j] = (cellData != null && !cellData.isEmpty()) ? cellData : "N/A";

				System.out.println("Row: " + (startRow + i) + ", Col: " + j + " -> " + data[i][j]);
			}
		}
		return data;
	}
}
