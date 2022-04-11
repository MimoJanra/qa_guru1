package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    private final CalendarComponent calendarComponent = new CalendarComponent();

    public SelenideElement tableTitle() {
        return $("#example-modal-sizes-title-lg");
    }

    public SelenideElement tableRow(String label) {
        return $x("//td[preceding-sibling::td='" + label + "']");
    }

    public SelenideElement modal() {
        return $("#modal-dialog modal-lg");
    }


    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGenderClick = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendar = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            userHobbies = $("#hobbiesWrapper"),
            upLoadPicture = $("#uploadPicture"),
            userAddress = $("#currentAddress"),
            setState = $("#react-select-3-input"),
            setCity = $("#react-select-4-input");

    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationFormPage setGender(String userGender) {
        userGenderClick.$(byText(userGender)).click();
        return this;
    }

    public RegistrationFormPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        calendar.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationFormPage setUserHobbies(String hobbies) {
        userHobbies.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationFormPage upLoadPicture(String namePicture) {
        upLoadPicture.uploadFromClasspath(namePicture);
        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        userAddress.setValue(address);
        return this;
    }

    public RegistrationFormPage setState(String state) {
        setState.setValue(state).pressEnter();
        return this;
    }

    public RegistrationFormPage setCity(String City) {
        setCity.setValue(City).pressEnter();
        return this;
    }

    public void submit() {
        $("#submit").click();
    }

    public RegistrationFormPage checkTableHeaderHasText(String text) {
        tableTitle().shouldHave(text(text));
        return this;
    }

    public RegistrationFormPage checkTableRowHasText(String label, String text) {
        tableRow(label).shouldHave(text(text));
        return this;
    }

    public void closeModal() {
        $("#closeLargeModal").click();
    }
}