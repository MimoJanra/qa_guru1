package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationFormPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AutomationRegistrationFormTest {

    Faker faker = new Faker();
    String studentRegistrationForm = "/automation-practice-form",
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(lastName),
            gender = faker.demographic().sex(),
            mobile = faker.numerify("##########"),
            day = "11",
            month = "April",
            year = "1901",
            subjects = "History",
            hobbies = "Music",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Gurgaon";

    @BeforeAll
    static void BeforeAll() {
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
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        Configuration.browserSize = resolution;
        open(studentRegistrationForm);

        registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setBirthDate(day, month, year)
                .setSubjects(subjects)
                .setUserHobbies(hobbies)
                .upLoadPicture("img/1.png")
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        registrationFormPage.checkTableHeaderHasText("Thanks for submitting the form")
                .checkTableRowHasText("Student Name", firstName)
                .checkTableRowHasText("Student Email", email)
                .checkTableRowHasText("Gender", gender)
                .checkTableRowHasText("Mobile", mobile)
                .checkTableRowHasText("Date of Birth", day + " " + month + "," + year)
                .checkTableRowHasText("Subjects", String.join(", ", subjects))
                .checkTableRowHasText("Hobbies", String.join(", ", hobbies))
                .checkTableRowHasText("Picture", "1.png")
                .checkTableRowHasText("Address", address)
                .checkTableRowHasText("Mobile", mobile)
                .checkTableRowHasText("State and City", state + " " + city);

        registrationFormPage.closeModal();
    }
}