package generateReport;

import auxiliar.constants.ProjectConfigConstants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XlsxTools {
    static String xlsxPath = ProjectConfigConstants.xlsxPath;
    static String xlsFile = ProjectConfigConstants.xlsFile;

    public static String createStringFromXlsxFile(String excelTab) {
        File[] arquivos = FileUtil.getFilesListByExtension(xlsxPath, "xlsx");
        String fileContent = "";

        for (File arquivo : arquivos) {

            try (InputStream is = new FileInputStream(arquivo)) {
                Workbook wb = new XSSFWorkbook(is);

                for (int planNum = 0; planNum < wb.getNumberOfSheets(); planNum++) {
                    Sheet plan = wb.getSheetAt(planNum);

                    if (plan.getSheetName().equalsIgnoreCase(excelTab)) {
                        DataFormatter df = new DataFormatter();

                        for (Row linha : plan) {
                            int lastColumn = Math.max(linha.getLastCellNum(), plan.getRow(0).getLastCellNum());

                            for (int colNum = 0; colNum < lastColumn; colNum++) {
                                Cell celula = linha.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                                if (celula.getColumnIndex() == 0) fileContent = fileContent + "| ";

                                String valorCelula = df.formatCellValue(celula)
                                        .replaceAll("\\n", "&") + " | ";
                                fileContent = fileContent + valorCelula;
                            }
                            fileContent = fileContent + "\n";
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String content = new String(fileContent.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        return content;
    }

    public static void convertCsvToXlsx(String csvFile) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFile));
                BufferedReader br = new BufferedReader(reader);
                Workbook wb = new XSSFWorkbook();
        ) {
            Sheet plan = wb.createSheet("Report");
            String line;
            int linhaNum = 0;

            CellStyle cabecalho = wb.createCellStyle();
            Font negrito = wb.createFont();
            negrito.setBold(true);
            negrito.setColor(IndexedColors.WHITE.getIndex());

            cabecalho.setFont(negrito);
            cabecalho.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            cabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle linhaImpar = wb.createCellStyle();
            linhaImpar.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            linhaImpar.setFillPattern(FillPatternType.SOLID_FOREGROUND);


            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";");
                Row col = plan.createRow(linhaNum++);

                for (int i = 0; i < columns.length; i++) {
                    Cell cel = col.createCell(i);
                    cel.setCellValue(columns[i]);

                    if (linhaNum == 1) {
                        cel.setCellStyle(cabecalho);
                    } else if (linhaNum % 2 == 1) {
                        cel.setCellStyle(linhaImpar);
                    }
                }
            }

            for (int y = 0; y < linhaNum; y++) {
                plan.autoSizeColumn(y);
            }

            try (FileOutputStream fo = new FileOutputStream(xlsFile)) {
                wb.write(fo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
