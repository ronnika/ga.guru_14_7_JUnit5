package guru.qa.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class KhanAcademyTests extends BaseTest {

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
        open("/signup");
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

    @CsvSource(value = {
            "100,$2.50",
            "400,$10"
    })
    @ParameterizedTest(name = "Transaction fee for donation amount of \"{0}\" US dollars should be calculated and displayed correctly")
    void donateOtherAmountShouldChangeTransactionAmount(String amount, String percent) {
        open("/donate");
        $("input[value='month']").parent().click();
        $(byText("Other")).click();
        $$("input[name='amount']").last().parent().lastChild().setValue(amount);
        $("#cc-label").shouldHave(text(percent));
        $("input[value='once']").parent().click();
        $("#cc-label").shouldHave(text(percent));
    }
}

