import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;


@ExtendWith(MockitoExtension.class)

@DisplayName("Тестирование: MessageSenderImpl")
public class MessageSenderImplTest {
    private MessageSenderImpl messageSenderImpl;
    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;

    @BeforeEach
    void setUp() {

        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        System.out.println("Тест вызван");
    }

    @AfterEach
    void tearDown() {
        System.out.println();
        System.out.println("Тест окончен");
    }

    @Test
    @DisplayName("Тестирование отправляет только английский текст для USA (ожидаем - Welcome)")
    void send1() {
        Mockito.when(geoService.byIp(Mockito.eq(NEW_YORK_IP))).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        String actualResult = messageSenderImpl.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP));
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Тестирование отправляет только русский текст для RUSSIA (ожидаем - Добро пожаловать)")
    void send2() {
        Mockito.when(geoService.byIp(Mockito.eq(MOSCOW_IP))).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        String actualResult = messageSenderImpl.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP));
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Тестирование отправляет только английский текст для id \"96.\" (ожидаем - Welcome)")
    void send3() {
        Mockito.when(geoService.byIp(Mockito.eq("96."))).thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        String actualResult = messageSenderImpl.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, "96."));
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Тестирование отправляет только русский текст для id \"172.\" (ожидаем - Добро пожаловать)")
    void send4() {
        Mockito.when(geoService.byIp(Mockito.eq("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        String actualResult = messageSenderImpl.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, "172."));
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
