package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 5.2. Архивировать проект [#471728]
 * 1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\
 * 2. В качестве ключа передается  расширения файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * 4. Запуск проекта. -d=c:\project\job4j\ -e=class -o=project.zip добвление в массив параметров String[] args
 * 5. Для работы с входными аргументами используйте класс ArgsName из прошлого задания.
 * Важно! Перед запуском проекта нужно делать валидацию аргументов.
 * Для поиска и фильтрации файлов нужно использовать класс Search из задания "Сканирование файловой системы".
 */
public class Zip {

    /**
     * Метод проводит архивацию проекта в архив ZIP используя входные парметы
     *
     * @param sources List<Path> - директория которую мы хотим архивировать - c:\project
     * @param target  File target - имя файла в который будем архивировать - project.zip
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
     /*   new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );*/
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Root folder is null or File extension not specified, or Target folder is null.");
        }
        ArgsName nemes = ArgsName.of(args);
        Predicate<Path> condition = path -> !path.toFile().getName().endsWith(nemes.get("e"));
        Zip zipToZip = new Zip();
        zipToZip.packFiles((Search.search(Paths.get(nemes.get("d")), condition)), new File(nemes.get("o")));
    }
}

