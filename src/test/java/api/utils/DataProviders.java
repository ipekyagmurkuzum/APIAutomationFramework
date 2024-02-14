package api.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        ExcelUtility xl = new ExcelUtility(path);

        int rowCount = xl.getRowCount();
        int colCount = xl.getCellCount(1);

        Object apiData[][] = new String[rowCount][colCount];
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                apiData[i - 1][j] = xl.getCellData(i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "Usernames")
    public Object[] getUsernames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        ExcelUtility xl = new ExcelUtility(path);

        int rowCount = xl.getRowCount();

        Object apiData[] = new String[rowCount];
        for (int i = 1; i <= rowCount; i++) {
            apiData[i - 1] = xl.getCellData(i, 1);
        }
        return apiData;
    }

}
