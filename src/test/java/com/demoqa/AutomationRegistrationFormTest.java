package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillSubmitCloseRegistrationForm() {
        open(studentRegistrationForm);

        //close ads
        $("#close-fixedban").click();

        //Filling form
        $("#firstName").setValue(fistName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);
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
        $x(getValueCell("Student Name")).shouldHave(text(fistName + " " + lastName));
        $x(getValueCell("Student Email")).shouldHave(text(email));
        $x(getValueCell("Gender")).shouldHave(text(gender));
        $x(getValueCell("Mobile")).shouldHave(text(mobile));
        $x(getValueCell("Date of Birth")).shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
        $x(getValueCell("Subjects")).shouldHave(text(subjects));
        $x(getValueCell("Hobbies")).shouldHave(text(hobbies));
        $x(getValueCell("Picture")).shouldHave(text("1.png"));
        $x(getValueCell("Address")).shouldHave(text(address));
        $x(getValueCell("Mobile")).shouldHave(text(mobile));
        $x(getValueCell("State and City")).shouldHave(text(state + " " + city));

        $("#closeLargeModal").click();

        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
    }

    public static String getValueCell(String label) {
        return "//td[preceding-sibling::td='" + label + "']";
    }
}