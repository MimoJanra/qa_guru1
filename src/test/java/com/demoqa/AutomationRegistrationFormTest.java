package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AutomationRegistrationFormTest {

    String studentRegistrationForm = "/automation-practice-form";

    String fistName = "Gogi";
    String lastName = "Velikolepniy";
    String email = "gogi@behinavole.pro";
    String gender = "Other";
    String mobile = "1234567890";
    String birthDay = "11";
    String birthMonth = "February";
    String birthYear = "1901";
    String dateOfBirthFormatted = birthMonth + " " + birthDay + "th, " + birthYear;
    String subjects = "History";
    String hobbies = "Music";
    String address = "Khasa Kothi Circle, MI Rd";
    String state = "NCR";
    String city = "Gurgaon";

    @BeforeAll
    static void BeforeAll(){
        Configuration.baseUrl = "https://demoqa.com";
    }

    public static Stream<Arguments> browserResolution() {
        return Stream.of(
                arguments("1920x1080"),
                arguments("1366x768"),
                arguments("1536x864"),
                arguments("1440x900"),
                arguments("1600x900"),
                arguments("1290x1024"),
                arguments("768x1024"),
                arguments("1280x720")
        );
    }

    @ParameterizedTest
    @MethodSource("browserResolution")
    void fillSubmitCloseRegistrationForm(String resolution) {
        Configuration.browserSize = resolution;
        open(studentRegistrationForm);

        //close ads
        $("#close-fixedban").click();

        //Filling form
        $("#firstName").setValue(fistName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").scrollIntoView(true).click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $("[aria-label$='" + dateOfBirthFormatted + "']").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue(address);
        $("#state").scrollIntoView(true).click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        // Check form
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x(getCell("Student Name")).shouldHave(text(fistName + " " + lastName));
        $x(getCell("Student Email")).shouldHave(text(email));
        $x(getCell("Gender")).shouldHave(text(gender));
        $x(getCell("Mobile")).shouldHave(text(mobile));
        $x(getCell("Date of Birth")).shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
        $x(getCell("Subjects")).shouldHave(text(subjects));
        $x(getCell("Hobbies")).shouldHave(text(hobbies));
        $x(getCell("Picture")).shouldHave(text("1.png"));
        $x(getCell("Address")).shouldHave(text(address));
        $x(getCell("Mobile")).shouldHave(text(mobile));
        $x(getCell("State and City")).shouldHave(text(state + " " + city));

        $("#closeLargeModal").click();

        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
    }

    public static String getCell(String label) {
        return "//td[preceding-sibling::td='" + label + "']";
    }
}