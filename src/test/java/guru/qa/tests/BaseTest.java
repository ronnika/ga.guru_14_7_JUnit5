package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://www.khanacademy.org";
    }
}
