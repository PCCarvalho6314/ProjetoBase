import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateLoginDataExcel {
    public static void main(String[] args) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("LoginData");

            // Cabeçalhos
            var row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("email");
            row0.createCell(1).setCellValue("senha");
            row0.createCell(2).setCellValue("nome");
            row0.createCell(3).setCellValue("perfil");

            // Dados de exemplo
            var row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("user1@example.com");
            row1.createCell(1).setCellValue("senha123");
            row1.createCell(2).setCellValue("Usuário Teste 1");
            row1.createCell(3).setCellValue("admin");

            var row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("user2@example.com");
            row2.createCell(1).setCellValue("senha456");
            row2.createCell(2).setCellValue("Usuário Teste 2");
            row2.createCell(3).setCellValue("usuario");

            var row3 = sheet.createRow(3);
            row3.createCell(0).setCellValue("invalid@example.com");
            row3.createCell(1).setCellValue("senhaerrada");
            row3.createCell(2).setCellValue("Usuário Inválido");
            row3.createCell(3).setCellValue("usuario");

            FileOutputStream fos = new FileOutputStream("src/test/resources/data/LoginData.xlsx");
            workbook.write(fos);
            fos.close();
            workbook.close();
            
            System.out.println("Arquivo LoginData.xlsx criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
