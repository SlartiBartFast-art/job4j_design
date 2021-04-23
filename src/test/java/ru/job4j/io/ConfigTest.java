package ru.job4j.io;

import org.junit.Test;
import java.io.IOException;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

//TODO Дополните тест проверками на чтение файла с комментарием,
// а также файла с нарушением шаблона ключ=значение (напр. ключ=)
// в этом случае нужно ожидать исключение IllegalArgumentException

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsEmpty() {
        String path = "testApp.properties";
        Config config = new Config(path);
        config.load();
        // exceptionRule.expect(IllegalArgumentException.class);
        //assertThat(config.value("hibernate.dialect"), is(nullValue()));
    }
}