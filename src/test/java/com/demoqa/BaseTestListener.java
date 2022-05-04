package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BaseTestListener {

    @Test
    public void issueNameValidationSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://www.amazon.com/");

        $(byId("twotabsearchtextbox")).click();
        $(byId("twotabsearchtextbox")).sendKeys("crocs");
        $(byId("twotabsearchtextbox")).submit();

        $(By.linkText("Women's")).click();
        $(By.partialLinkText("Crocs")).shouldBe(Condition.visible);
        $(By.partialLinkText("Crocs")).shouldHave(text("Crocs"));
    }
}