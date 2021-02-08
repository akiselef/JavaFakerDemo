package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class JavaFakerDemoSelenide {

    @Test
    void practiceFormTest () {

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
//        String phoneNumber = faker.phoneNumber().cellPhone(); // генерит номер с числом знаков менее 10
        String currentAddress = faker.address().fullAddress();

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue("a@a.com");
        $(".custom-control-label").shouldHave(text("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("3");
        $(".react-datepicker__year-select").selectOptionByValue("2007");
        $x("//*[@class='react-datepicker__day react-datepicker__day--017']").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $x("//label[text()='Music']").click();
        $x("//input[@type='file']").uploadFile(new File("src/test/java/resources/download.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#submit").scrollTo();
        $x("//div[text()='Select State']").click();
        $("#react-select-3-option-2").click();
        $x("//div[text()='Select City']").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();
        Selenide.sleep(2000);


        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='a@a.com']").shouldBe(visible);
        $x("//td[text()='Mobile']").parent().shouldHave(text("1234567890"));
        $x("//td[text()='Male']").shouldBe(visible);
        $x("//td[text()='17 April,2007']").shouldBe(visible);
        $x("//td[text()='English']").shouldBe(visible);
        $x("//td[text()='Music']").shouldBe(visible);
        $x("//td[text()='download.jpg']").shouldBe(visible);
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='Haryana Panipat']").shouldBe(visible);
    }

}