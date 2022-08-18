package guru.qa.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class KhanAcademyTests extends KnamAcademyBaseTest {

    static Stream<Arguments> signUpOptionsForDifferentRolesTest() {
        return Stream.of(
                Arguments.of(Roles.Parent, "First, create your parent account."),
                Arguments.of(Roles.Learner, "A world class education for anyone, anywhere. 100% free."),
                Arguments.of(Roles.Teacher, "Help every student succeed with personalized practice. 100% free.")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "In sign up for role \"{0}\" is showed \"{1}\" text")
    void signUpOptionsForDifferentRolesTest(Roles role, String expectedText) {
        open("signup/");
        $("#app-shell-root").$(byText(role.toString())).click();
        $("#app-shell-root section h1").shouldHave(text("Sign up"));
        $("#app-shell-root section h2").shouldHave(text(expectedText));
    }

    // Tест должен и будет падать для Индии, так как у Индии нет навигации в футере
    @EnumSource(Countries.class)
    @ParameterizedTest(name = "Site version for \"{0}\" has navigation in the footer")
    void switchCountryVersionFooterInPlaceTest(Countries country) {
        open("");
        $("#footer").$(byText(country.getNotation())).click();
        $("#footer div[role='navigation']").shouldBe(visible);
    }

    @ValueSource(strings = {"math", "biology"})
    @ParameterizedTest(name = "After entering \"{0}\" in the searchbar Search Result Popup should be visible")
    void searchBarHasSearchResultsPopupAfterEnteringDataTest(String data) {
        open("");
        $("a[data-test-id='navbar-search-button']").click();
        $("input[data-test-id='page-search-box']").sendKeys(data);
        $("div[data-test-id='search-results-popup']").shouldBe(visible);
    }

    // CscSource
    // Test Custom amounts correctly how percents
}

