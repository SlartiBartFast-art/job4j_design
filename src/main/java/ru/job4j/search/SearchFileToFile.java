package ru.job4j.search;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**Поиск файлов по критерию [#471739]
 * проводит запись в файл
 */
public class SearchFileToFile {

    /**
     * Метод проводит запись путей файлов отвечающим условию предиката из class Finish
     * @param sourse list<Path> пути к малам отвечающим условию
     * @param target итоговый файл куда будет произведена запись
     */
    public void packFiles(List<Path> sourse, String target) { // List<Path> sourse, String target
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (Path path : sourse) {
                out.write(path.toFile().getPath()); // path.toFile().getPath() + System.lineSeparator())
                out.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
