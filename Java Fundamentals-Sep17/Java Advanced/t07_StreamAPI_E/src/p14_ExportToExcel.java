import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.*;

import java.io.*;
import java.util.ArrayList;

public class p14_ExportToExcel {

    private static final String FILE_NAME = "StudentsExportToExcel.xlsx";

    public static void main(String[] args) throws IOException {

        ArrayList<Object[]> allLines = new ArrayList<>();
        allLines.add(new Object[]
                {"FN", "First name", "Last Name", "Email", "Age", "Group", "Grade1", "Grade2", "Grade3", "Grade4", "Phones"});
        new BufferedReader(new FileReader("StudentsData.txt")).lines()
                .map(x -> x.split("\\t"))
                .filter(x -> !x[0].equals("FN"))
                .forEach(allLines::add);

        // Create workbook and worksheet object
        int rowStart = 2;
        int columnStart = 0;
        int totalRows = allLines.size();
        int totalCols = allLines.get(0).length ;
        int rowEnd = totalRows + rowStart - 1;
        int colEnd = totalCols + columnStart - 1;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("SoftUniOOPCourseResults");
        CellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(HorizontalAlignment.RIGHT);
        XSSFDataFormat intFormat = workbook.createDataFormat();
        style1.setDataFormat(intFormat.getFormat("0"));
        CellStyle style2 = workbook.createCellStyle();
        XSSFDataFormat strFormat = workbook.createDataFormat();
        style2.setDataFormat(strFormat.getFormat("General"));
        style2.setAlignment(HorizontalAlignment.LEFT);
        XSSFRow row;
        XSSFCell cell;
        // Create an object of type XSSFTable
        XSSFTable myTable = sheet.createTable();
        // Get CTTable object
        CTTable cttable = myTable.getCTTable();
        cttable.setTotalsRowShown(false);
        // Define the required style1 for the table
        CTTableStyleInfo table_style = cttable.addNewTableStyleInfo();
        table_style.setName("TableStyleLight14");
        // Set table style option
        table_style.setShowColumnStripes(false);
        table_style.setShowRowStripes(true);
        // define the data range including headers
        AreaReference my_data_range = new AreaReference(new CellReference(rowStart,columnStart),
                new CellReference(rowEnd , colEnd));
        // Set range to the table
        cttable.setRef(my_data_range.formatAsString());
        cttable.setDisplayName("Students");
        cttable.setName("Students");
        cttable.setId(1L);
        CTTableColumns columns = cttable.addNewTableColumns();
        CTAutoFilter autoFilter = cttable.addNewAutoFilter();
        columns.setCount(totalCols);
        // Define Header Information for the table
        for (int i = columnStart; i <= colEnd; i++) {
            CTTableColumn column = columns.addNewTableColumn();
            column.setName(allLines.get(0)[i].toString());
            column.setId(i + 1);

        }
        sheet.setAutoFilter(new CellRangeAddress(rowStart, rowStart, columnStart, colEnd));

        // Add remaining Table data
        row = sheet.createRow((short) 0);
        cell = row.createCell((short) 0);
        sheet.addMergedRegion(new CellRangeAddress(0, rowStart - 1, columnStart, colEnd));
        cell.setCellValue("SoftUni OOP Course Results");
        CellStyle bolded = workbook.createCellStyle();
        bolded.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont myFont = workbook.createFont();
        myFont.setBold(true);
        myFont.setFontHeightInPoints((short)30);
        bolded.setFont(myFont);
        cell.setCellStyle(bolded);

        int rowNum = rowStart;
        for (Object[] datatype:
        allLines){
            XSSFRow inRow = sheet.createRow(rowNum++);
            int colNum = columnStart;
            for(Object field : datatype){
                XSSFCell inCell = inRow.createCell(colNum++);
                if(isInt(field)) {
                    inCell.setCellStyle(style1);
                    inCell.setCellType(CellType.NUMERIC);
                    inCell.setCellValue((Double)field);
                } else {
                    inCell.setCellStyle(style2);
                    inCell.setCellType(CellType.STRING);
                    inCell.setCellValue((String)field);
                }
            }
        }
        for (int i = 1; i <= 11; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }
    public static boolean isInt(Object o)
    {
        try
        {
            int i = (Integer)o;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        } catch (ClassCastException cce){
            return false;
        }
        return true;
    }
}