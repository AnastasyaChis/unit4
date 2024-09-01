package ru.netology;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class AppCardDeliveryTest {

    private String generateData (long addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldSuccessfullyTest(){
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Томск");
        String planningData = generateData(4,"dd.MM.yyyy");
        $("[data-test-id= 'date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
        $("[data-test-id= 'date'] input").setValue(planningData);
        $("[data-test-id= 'name'] input").setValue("Суворов Геннадий");
        $("[data-test-id= 'phone'] input").setValue("+79147364475");
        $("[data-test-id= 'agreement']").click();
        $("[class='button__content']").click();
        $("[class='notification__content']")
                .shouldBe(Condition.visible,Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningData));






    }







}

