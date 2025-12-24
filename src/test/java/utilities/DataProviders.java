package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // ================= DataProvider 1 =================
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Path of Excel file
        String path = "./testData/Opencart_LoginData.xlsx";
        // Taking excel file from testData folder

        ExcelUtility xlutil = new ExcelUtility(path);
        // Creating object for ExcelUtility

        int totalrows = xlutil.getRowCount("Sheet1");
        // Getting total number of rows

        int totalcols = xlutil.getCellCount("Sheet1", 1);
        // Getting total number of columns

        String logindata[][] = new String[totalrows][totalcols];
        // Creating two dimensional array to store login data

        // Read data from excel and store in array
        for (int i = 1; i <= totalrows; i++) {     // i is rows
            for (int j = 0; j < totalcols; j++) {  // j is columns
                logindata[i - 1][j] =
                        xlutil.getCellData("Sheet1", i, j);
            }
        }

        return logindata;
        // Returning two dimensional array
    }
}
