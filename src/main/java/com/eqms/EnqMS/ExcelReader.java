package com.eqms.EnqMS;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    /**
     * Reads data from an Excel sheet and returns it as a list of maps.
     *
     * @param filePath  Path to the Excel file.
     * @param sheetName Name of the sheet to read.
     * @return List of maps where each map represents a row in the Excel sheet.
     * @throws IOException If there's an issue reading the Excel file.
     */
    public static List<Map<String, String>> readExcel(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Map<String, String>> testCases = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        int numOfColumns = headerRow.getPhysicalNumberOfCells();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row currentRow = sheet.getRow(i);
            if (currentRow == null) continue;

            Map<String, String> testCase = new HashMap<>();
            for (int j = 0; j < numOfColumns; j++) {
                Cell headerCell = headerRow.getCell(j);
                Cell dataCell = currentRow.getCell(j);

                String header = headerCell != null ? headerCell.getStringCellValue() : "Column" + j;
                String value = "";

                if (dataCell != null) {
                    switch (dataCell.getCellType()) {
                        case STRING:
                            value = dataCell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(dataCell)) {
                                value = dataCell.getDateCellValue().toString();
                            } else {
                                value = String.valueOf((int) dataCell.getNumericCellValue()); // Cast to integer
                            }
                            break;
                        case BOOLEAN:
                            value = String.valueOf(dataCell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            value = dataCell.getCellFormula();
                            break;
                        default:
                            value = "";
                            break;
                    }
                }
                testCase.put(header, value);
            }
            testCases.add(testCase);
        }

        workbook.close();
        fis.close();

        return testCases;
    }
}

