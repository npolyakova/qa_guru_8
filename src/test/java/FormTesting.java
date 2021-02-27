import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static utils.RandomUtils.*;

public class FormTesting extends TestBase {

    @Test //Успешное заполнение формы
    void formFillingWithCorrectData(){
        Faker faker = new Faker();
        String[] state = getRandomStateAndCity();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = getRandomGender();
        String phone = getRandomPhone();
        String year = getRandomInt(1900, 2100) + "";
        String month = getRandomMonth() + "";

        String day;
        if (month.equals("February")){
            day = getRandomInt(1, 28) + "";
        }
        else {
            day = getRandomInt(1, 31) + "";
        }

        String hobby = getRandomHobby();
        String address = faker.address().fullAddress();
        String sub = getRandomSubject();

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__month").$(byText(day)).click();
        $("#subjectsInput").setValue(sub).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/IMG_0356.JPG"));
        $("#currentAddress").setValue(address).scrollIntoView(true);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state[0])).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(state[1])).click();

        $("#submit").click();

        $(".fade.modal.show").shouldBe(Condition.visible);
        $(".modal-header").shouldHave(Condition.text("Thanks for submitting the form"));
        $(".modal-body table thead").shouldHave(Condition.text("Label"), Condition.text("Values"));
        $(".modal-body tbody tr", 0).shouldHave(Condition.text("Student Name"), Condition.text(firstName+" "+lastName));
        $(".modal-body tbody tr", 1).shouldHave(Condition.text("Student Email"), Condition.text(email));
        $(".modal-body tbody tr", 2).shouldHave(Condition.text("Gender"), Condition.text(gender));
        $(".modal-body tbody tr", 3).shouldHave(Condition.text("Mobile"), Condition.text(phone));
        $(".modal-body tbody tr", 4).shouldHave(Condition.text("Date of Birth"), Condition.text(day + " " + month + "," + year));
        $(".modal-body tbody tr", 5).shouldHave(Condition.text("Subjects"), Condition.text(sub));
        $(".modal-body tbody tr", 6).shouldHave(Condition.text("Hobbies"), Condition.text(hobby));
        $(".modal-body tbody tr", 7).shouldHave(Condition.text("Picture"), Condition.text("IMG_0356.JPG"));
        $(".modal-body tbody tr", 8).shouldHave(Condition.text("Address"), Condition.text(address));
        $(".modal-body tbody tr", 9).shouldHave(Condition.text("State and City"), Condition.text(state[0]+" "+state[1]));

        $("#closeLargeModal").click();
        $(".fade.modal.show").shouldNotBe(Condition.visible);

    }
}
