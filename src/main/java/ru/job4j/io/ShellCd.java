package ru.job4j.io;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;

/**
 * контрольное задание IO/NIO Block
 * эмитация команды cd в Windows
 */
public class ShellCd {

    private Path way;
   private Console console;
    private String path;

    public ShellCd() throws IOException {
//        this.console = System.console();
//        this.path = console.readLine();
        cd();
       // pwd();
        //run();
    }

    /**
     *
     * @throws IOException
     */
    public void cd() throws IOException {
       try (var in = new BufferedReader(new InputStreamReader(System.in));) {
           String inner;
          do {
              inner = in.readLine();
              //var p = Paths.get(inner);
              File file = new File(inner);
             /* if (!file.exists()) {
                  System.out.println("Такой папки не существует, введите снова");
              } else*/ if (inner.equals("cd")) {
                  var v = file.listRoots();
                  for (var t : v) {
                      System.out.println(t);
                  }
                  var l =  v[0];
                  this.way = l.toPath();
                  System.out.println("Root: " + l);
                  System.out.println("Вы в каталоге : " + FileSystemView.getFileSystemView().getHomeDirectory());
              } else if (inner.equals("..")) {
                  String dir = System.getProperty("user.dir"); // отображает текущюю директорию, где мы
                  this.way = Paths.get(dir);
                  System.out.println("текущая директория : " + pwd());
              } else if (inner.equals("/d")) { // изменяет текущий каталог, шаг назад
                  String dir = System.getProperty("user.dir");
                  var p = Paths.get(dir);
                  this.way = p.getParent();
                  System.out.println("изменяет текущий каталог, шаг назад : " + pwd());
              } else {
                  this.way = Paths.get(inner); // просто показывает куда мы зашли
                  System.out.println("просто показывает куда мы зашли : " + pwd());
              }
          } while (!inner.equals("end"));
       } catch (IOException e) {
           e.printStackTrace();
       }

    }

    /**
     * Проверяет что путь переданный в метод .. является директорией/каталогом
     * открывает каталог
     *
     * @return
     */
    public String pwd() {
        return way.toFile().getAbsolutePath();
       /* if (Files.isDirectory(way)) {

        }*/
        //  var d =  Files.getFileAttributeView(way, BasicFileAttributes);
        // return way.toFile().getParent();
    }

   /* private Console run() {

       var s = console.readLine();
        cd(s);
        return console.printf(pwd());
    }*/

    public static void main(String[] args) throws IOException {
        /*ShellCd shell = new ShellCd();
        shell.cd("/path/to/file");
        shell.cd("/new/path/to/my/file");
        System.out.println("То что вышло : " + shell.pwd());

        ShellCd shell1 = new ShellCd();
        shell1.cd("user");
        shell1.cd("local");
        System.out.println("То что вышло : " + shell1.pwd());

        ShellCd shell2 = new ShellCd();
        shell2.cd("user");
        shell2.cd("..");
        System.out.println("То что вышло : " + shell2.pwd());

        ShellCd shell3 = new ShellCd();
        shell3.cd("user");
        shell3.cd("/d");
        System.out.println("То что вышло : " + shell3.pwd());*/
        ShellCd shellCd = new ShellCd();
      /*Console console = System.console();
        if (console != null) {
            String path = console.readLine("Vveditelfksklf : ");
            console.printf(path);

        }*/

 // shellCd.cd(path);
  //shellCd.pwd();
    }
}
