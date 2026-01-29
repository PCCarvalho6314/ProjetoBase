package generateReport;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static void deleteDirectory(String filePath) {
        File file = FileUtils.getFile(filePath);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            throw new RuntimeException(e + " Feche os arquivos para ques os mesmos posssam ser deletados");
        }
    }

    public static void deleteFiles(String filePath, String extension) {
        File files = new File(filePath);

        if (files.exists() && files.isDirectory()) {
            File[] arquivos = files.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile() && arquivo.getName().endsWith(extension)) {
                        arquivo.delete();
                    }
                }
            }
        }
    }

    public static void deleteFolder(String folderPath) {
        File pasta = new File(folderPath);
        File[] arquivos = pasta.listFiles();

        for (File arquivo : arquivos) {
            if (arquivo.isFile()) {
                arquivo.delete();
            }
        }
        pasta.delete();
    }

    public static void createFolder(String folderPath) {
        File pasta = new File(folderPath);
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }

    public static void deleteFileWithContent(String path, String content) {
        File arquivo = new File(path);

        try {
            List<String> linhas = Files.readAllLines(Paths.get(arquivo.toURI()), Charset.defaultCharset());

            for (String linha : linhas) {
                if (linha.contains(content)) {
                    arquivo.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File[] getFilesListByExtension(String path, String extension) {
        File pastaArquivos = new File(path);
        File[] arquivos = pastaArquivos.listFiles((pasta, nome) ->
                nome.toLowerCase().endsWith(extension));

        return arquivos;
    }

    public static void replaceStringToFileContent(String basePath, String featuresPath) {
        String baseFeature = "";
        String excelTab = basePath
                .replace("src\\test\\resources\\features_base\\", "")
                .replace(".base", "") + "Erros";
        String stringToReplace = "#@" + excelTab;

        try {
            baseFeature = new String(Files.readAllBytes(Paths.get(basePath)), StandardCharsets.UTF_8);
            String excelTabContent = XlsxTools.createStringFromXlsxFile(excelTab);
            String featureFile = baseFeature.replace(stringToReplace, excelTabContent);
            Files.write(Paths.get(featuresPath), featureFile.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
