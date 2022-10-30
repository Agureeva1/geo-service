import org.junit.jupiter.api.*;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;

@DisplayName("Тестирование: GeoServiceImpl")
public class GeoServiceImplTest {

    private GeoServiceImpl geoServiceImpl;


    @BeforeEach
    void setUp() {

        geoServiceImpl = new GeoServiceImpl();
        System.out.println("Тест вызван");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Тест окончен");
    }

    @Test
    @DisplayName("Тестирование локации по ip 172.0.32.11 (ожидаем - RUSSIA)")
    void byIp1() {
        Location rus = geoServiceImpl.byIp(MOSCOW_IP);
        Assertions.assertEquals(RUSSIA, rus.getCountry());
    }

    @Test
    @DisplayName("Тестирование локации по ip 96.44.183.149 (ожидаем - USA)")
    void byIp2() {

        Assertions.assertEquals(USA, (geoServiceImpl.byIp(NEW_YORK_IP)).getCountry());
    }

    @Test
    @DisplayName("Тестирование локации по ip 96. (ожидаем - USA)")
    void byIp3() {
        Assertions.assertEquals(USA, (geoServiceImpl.byIp("96.")).getCountry());
    }

    @Test
    @DisplayName("Тестирование локации по ip 172. (ожидаем - RUSSIA)")
    void byIp4() {
        Location rus = geoServiceImpl.byIp("172.");
        Assertions.assertEquals(RUSSIA, rus.getCountry());
    }


}
