package com.demoqa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @Test
    public void issueNameValidationTest() {
        open("https://www.amazon.com/");

        $(byId("twotabsearchtextbox")).click();
        $(byId("twotabsearchtextbox")).sendKeys("crocs");
        $(byId("twotabsearchtextbox")).submit();

        $(By.linkText("Women's")).click();
        $(By.partialLinkText("Crocs")).shouldBe(Condition.visible);
        $(By.partialLinkText("Crocs")).shouldHave(text("Crocs"));
    }
}