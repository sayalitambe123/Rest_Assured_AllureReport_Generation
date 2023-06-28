package Common_API_Methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Common_Utility_Method {
	public static void EvidenceFileCreator(String filename, String requestBody, String ResponseBody, int StatusCode)
			throws IOException {

		File NewText = new File("E:\\course\\RESTassuared\\Evidence\\" + filename + ".txt");
		System.out.println("New Blank File Created : " + NewText.getName());

		FileWriter Data = new FileWriter(NewText);

		Data.write("Request Body is :\n" + requestBody + "\n\n");
		Data.write("Status Code is :\n" + StatusCode + "\n\n");
		Data.write("Response Body is :\n" + ResponseBody);

		Data.close();
		System.out.println("Data Written into the file : " + NewText.getName());
	}

	public static ArrayList<String> readDataExcel(String SheetName, String TestCaseName) throws IOException {
		ArrayList<String> ArrayData = new ArrayList<String>();
		// Step1 : Locate the excel file -> Create object of fileinput stream
		FileInputStream fis = new FileInputStream("E:\\course\\Data_Driven_Testing_Excel\\DataDriven.xlsx");

		// Step2 : Open Excel file : XSSFWorkbook
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// Step3 : Open desired sheet : XSSFSheet
		int sheetcount = workbook.getNumberOfSheets();

		for (int i = 0; i < sheetcount; i++) {
			String Name = workbook.getSheetName(i);

			// Step 4 : Access the desired sheet
			if (Name.equalsIgnoreCase(SheetName)) {

				// Use XSSFSheet to save the sheet into the variable
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Create Iterator to iterate through rows and find out in which column test
				// case names are found
				Iterator<Row> Rows = sheet.iterator();
				Row Firstrow = Rows.next();

				// Create the iterator to iterate through cells of first row to findout which
				// cell contain testcase name
				Iterator<Cell> CellsOfFirstRow = Firstrow.cellIterator();
				int k = 0;
				int tc_column = 0;
				while (CellsOfFirstRow.hasNext()) {
					Cell cellValue = CellsOfFirstRow.next();
					if (cellValue.getStringCellValue().equalsIgnoreCase("TestCase")) {
						tc_column = k;
						// System.out.println("Expected column for test case name : "+k);
						break;
					}
					k++;
				}
				// Verify the row where the desired test case is found and fetch the entire row
				while (Rows.hasNext()) {
					Row DataRow = Rows.next();
					String TCname = DataRow.getCell(tc_column).getStringCellValue();
					if (TCname.equalsIgnoreCase(TestCaseName)) {
						Iterator<Cell> CellValues = DataRow.cellIterator();
						while (CellValues.hasNext()) {
							String Data = CellValues.next().getStringCellValue();
							ArrayData.add(Data);
						}
						break;
					}
				}
			}
		}
		return ArrayData;
	}

}
