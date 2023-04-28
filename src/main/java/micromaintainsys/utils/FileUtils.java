package micromaintainsys.utils;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {

    public static String getFilePath(String fileName) {
        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources" + File.separator + "data" + File.separator + fileName;
        return filePath;
    }
    public static Object carregaDados(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Método para salvar dados em um arquivo binário
    public static void salvaDados(Object obj, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(obj);
            System.out.println("Dados salvos com sucesso em: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}