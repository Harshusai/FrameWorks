package utilities;
// Utility package for reusable classes

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;       
    // Used to read Excel file

    public FileOutputStream fo;      
    // Used to write Excel file

    public XSSFWorkbook workbook;   
    // Represents Excel workbook

    public XSSFSheet sheet;         
    // Represents Excel sheet

    public XSSFRow row;             
    // Represents Excel row

    public XSSFCell cell;           
    // Represents Excel cell

    public CellStyle style;         
    // Used for styling cells (optional)

    String path;                    
    // Stores Excel file path

    // Constructor to initialize file path
    public ExcelUtility(String path) {
        this.path = path;
    }

    // ================= GET ROW COUNT =================
    public int getRowCount(String sheetName) throws IOException {

        fi = new FileInputStream(path);          
        // Open Excel file

        workbook = new XSSFWorkbook(fi);        
        // Load workbook

        sheet = workbook.getSheet(sheetName);   
        // Get sheet by name

        int rowcount = sheet.getLastRowNum();   
        // Get last row number

        workbook.close();                       
        fi.close();                             

        return rowcount;                        
        // Return row count
    }

    // ================= GET CELL COUNT =================
    public int getCellCount(String sheetName, int rownum) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);             
        // Get specific row

        int cellcount = row.getLastCellNum();  
        // Get total cells in row

        workbook.close();
        fi.close();

        return cellcount;
    }

    // ================= GET CELL DATA =================
    public String getCellData(String sheetName, int rownum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);             
        // Get row

        cell = row.getCell(column);             
        // Get cell

        String data;
        try {
            data = cell.toString();             
            // Convert cell value to String
        } catch (Exception e) {
            data = "";                         
            // If cell empty, return blank
        }

        workbook.close();
        fi.close();

        return data;
    }

    // ================= SET CELL DATA (FULL LOGIC) =================
    public void setCellData(String sheetName, int rownum, int column, String data)
            throws IOException {

        File xlfile = new File(path);
        // Create File object

        // ---------- If file does NOT exist ----------
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();     
            // Create new workbook

            fo = new FileOutputStream(path);
            // Open file in write mode

            workbook.write(fo);                
            // Write empty workbook

            fo.close();
        }

        fi = new FileInputStream(path);
        // Read Excel file

        workbook = new XSSFWorkbook(fi);
        // Load workbook

        // ---------- If sheet does NOT exist ----------
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);  
            // Create new sheet
        }

        sheet = workbook.getSheet(sheetName);
        // Get sheet

        // ---------- If row does NOT exist ----------
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);           
            // Create new row
        }

        row = sheet.getRow(rownum);
        // Get row

        // ---------- If cell does NOT exist ----------
        cell = row.createCell(column);         
        // Create cell

        cell.setCellValue(data);               
        // Set value in cell

        fo = new FileOutputStream(path);
        // Open file in write mode

        workbook.write(fo);                    
        // Write data into Excel

        workbook.close();
        fi.close();
        fo.close();
    }
}
