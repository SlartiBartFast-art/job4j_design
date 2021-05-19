package ru.job4j.search;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**Поиск файлов по критерию [#471739]
 * проводит запись в файл
 */
public class SearchFileToFile {

    /**
     * Метод проводит запись путей файлов отвечающим условию предиката из class Finish
     * @param sourse list<Path> пути к малам отвечающим условию
     * @param target итоговый файл куда будет произведена запись
     */
    public void packFiles(List<Path> sourse, String target) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (Path path : sourse) {
                out.write(path.toFile().getPath() + System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
