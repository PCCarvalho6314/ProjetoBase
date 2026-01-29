package generateReport;

import auxiliar.constants.ProjectConfigConstants;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    static String screenshotPath = ProjectConfigConstants.screenshotPath;
    static String xlsFile = ProjectConfigConstants.xlsFile;
    static String zipFile = ProjectConfigConstants.zipFile;

    public static void generateZipReport() {
        FileOutputStream fos = null;
        File capturasFolder = new File(screenshotPath);
        File reportFile = new File(xlsFile);

        try {
            fos = new FileOutputStream(zipFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ZipOutputStream zos = new ZipOutputStream(fos);
        addDirectoryToZip(zos, capturasFolder, null);
        addFileToZip(zos, reportFile);
        try {
            zos.flush();
            fos.flush();
            zos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addFileToZip(ZipOutputStream zos, File zipFileName) {
        byte[] buffer = new byte[1024];

        try {
            FileInputStream fis = new FileInputStream(zipFileName);
            zos.putNextEntry(new ZipEntry(zipFileName.getPath().replace("src\\test\\resources\\report\\", "")));
            int lenght = fis.read(buffer);
            while (lenght > 0) {
                zos.write(buffer, 0, lenght);
                lenght = fis.read(buffer);
            }
            zos.closeEntry();
            fis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDirectoryToZip(ZipOutputStream zos, File zipFile, String folder) {
        if (zipFile == null || !zipFile.exists()) {
            return;
        }

        String zipFileName = zipFile.getName();
        if (folder != null && !folder.isEmpty()) {
            zipFileName = folder + "/" + zipFile.getName();
        }

        if (zipFile.isDirectory()) {
            for (File arquivo : zipFile.listFiles()) {
                addDirectoryToZip(zos, arquivo, zipFileName);
            }
        } else {
            addFileToZip(zos, zipFile);
        }
    }
}
