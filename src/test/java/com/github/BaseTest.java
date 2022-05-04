package com.github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    @Test
    void wikisCheckInSelenide() {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue("Selenide").pressEnter();
        $$(".repo-list").first().$("a").click();
        $("#wiki-tab").click();

        $("#wiki-pages-box ul li button").scrollTo().click();
        $("#wiki-pages-box").$(byLinkText("SoftAssertions")).click();
        $("#wiki-wrapper h1").shouldHave(text("SoftAssertions"));

        $("#wiki-body h4 a#user-content-3-using-junit5-extend-test-class")
                .scrollTo()
                .shouldBe(visible)
                .ancestor("h4")
                .shouldHave(text("Junit5"));
    }
}