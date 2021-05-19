package ru.job4j.io;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
public class Temp1 {
    @Test
    public void runFlag() {
        var args = new String[]{"-exe", "something"};

        Properties prop = ArgsLambda.build()
                .load(args)   // load(...) like first option.
                .add("-exe")  // add flag
                .run();       // start processing args

        assertEquals("-exe", prop.getProperty("-exe"));
    }

    @Test
    public void runNormalKey() {
        var args = new String[]{"url", "w.leningrad.ru"};
        var log = new ArrayList<String>();

        Properties prop = ArgsLambda.build()
                .load(args)
                .add("url", arg -> arg.contains(".org"))  // add normal-key
                .print(log::add)              // print option (save all warnings to log)
                .run();

        var expected = "WARNING - ArgsLambda: This key and value fail validate:"
                + System.lineSeparator()
                + "key:   " + "url" + System.lineSeparator()
                + "value: " + "w.leningrad.ru";

        assertEquals(expected, log.get(0));
    }

    @Test
    public void runMultiKey() {
        var args = new String[]{"-exe", "1", "kb", "s"};

        Map<String, List<String>> prop = ArgsLambda.build()  // add multi-key
                .add("-exe", List.of(
                        arg -> arg.equals("1"),    // first param validate
                        arg -> arg.equals("kb"),   // second param validate
                        arg -> arg.equals("s")))   // third param validate
                .load(args)                        // load like last option
                .runToMap();                       // start and return like Map<String, String>

        var values = prop.get("-exe");
        assertEquals("1", values.get(0));
        assertEquals("kb", values.get(1));
        assertEquals("s", values.get(2));
    }

    /* ######## Specific cases ######## */
    // overload params && update properties.

    @Test
    public void runOverloadKeyParams() {
        var args = new String[]{"-exe", "10", "mb"};

        Properties prop = ArgsLambda.build()
                .add("-exe", List.of(
                        arg -> arg.equals("1"),
                        arg -> arg.equals("kb"),
                        arg -> arg.equals("s")))
                .continuable()            // Very important option if you want to overload key param!
                .loadAndRun(args);        // * if we have args for second way to validate,
        // it's mean that first way fail and must have option {.continuable()}

        // add overload to multi-key validate, - new variant that you wait from args
        Properties alternativeValidate = ArgsLambda.build()
                .add("-exe", List.of(
                        arg -> arg.equals("10"),
                        arg -> arg.equals("mb")))
                .loadAndRun(args);                 // loadAndRun(...) in use.

        ArgsLambda.build().update(prop, alternativeValidate);   // update first prop with new prop from second prop

        /* How it work
            first properties with option {.continuable()} will save:
            key = "-exe" --> value = null
            second properties will accept args like right key and param:
            key = "-exe" --> value = "10-mb"
            finally we update first prop with new key-value pair.
            update mean: add all pairs that doesn't contains or value = null
         */

        var values = prop.getProperty("-exe").split("-");
        assertEquals("10", values[0]);
        assertEquals("mb", values[1]);
    }

}
