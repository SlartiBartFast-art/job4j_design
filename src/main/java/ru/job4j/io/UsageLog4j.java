package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
       /* LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");*/
        String u = "Ivan Ivanov";
        int a = 33;
        boolean b = true;
        byte v = 2;
        short l = 547; //staff number
        long g = 12L; //parking space
        float f = 2.17F; //temperature
        double n = 11000.0;
        char s = '\u0067';

        LOG.debug(
                "u : {}, a : {}, b : {}, v : {}, l : {}, g : {}, f : {}, n : {}, s : {}", u, a, b, v, l, g, f, n, s);

//        LOG.debug("debug message - v : {}, l : {}", v, l);
//        LOG.info("info message - g : {}, f : {}", g, f);
//        LOG.warn("warn message");
//        LOG.error("error message - u : {}, a : {}, b : {}", u, a, b);
    }
}
