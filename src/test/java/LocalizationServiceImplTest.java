import org.junit.jupiter.api.*;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.*;

@DisplayName("Тестирование: LocalizationServiceImpl")

public class LocalizationServiceImplTest {
    private LocalizationServiceImpl localizationServiceImpl;


    @BeforeEach
    void setUp() {

        localizationServiceImpl = new LocalizationServiceImpl();

        System.out.println("Тест вызван");
    }


    @AfterEach
    void tearDown() {
        System.out.println("Тест окончен");
    }

    @Test
    @DisplayName("Тестирование сообщения для страны RUSSIA (ожидаем - Добро пожаловать)")
    void locale1() {
        Assertions.assertEquals("Добро пожаловать", localizationServiceImpl.locale(RUSSIA));
    }

    @Test
    @DisplayName("Тестирование сообщения для страны USA (ожидаем - Welcome)")
    void locale2() {
        Assertions.assertEquals("Welcome", localizationServiceImpl.locale(USA));
    }

    @Test
    @DisplayName("Тестирование сообщения для страны BRAZIL (ожидаем - Welcome)")
    void locale3() {
        Assertions.assertEquals("Welcome", localizationServiceImpl.locale(BRAZIL));
    }

    @Test
    @DisplayName("Тестирование сообщения для страны USA (не ожидаем - Добро пожаловать)")
    void locale4() {
        Assertions.assertNotSame("Добро пожаловать", localizationServiceImpl.locale(USA));
    }

    @Test
    @DisplayName("Тестирование сообщения для страны RUSSIA (не ожидаем - Welcome)")
    void locale5() {
        Assertions.assertNotSame("Welcome", localizationServiceImpl.locale(RUSSIA));
    }
}
