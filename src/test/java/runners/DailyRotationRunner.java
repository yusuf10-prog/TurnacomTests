package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@daily_rotation",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class DailyRotationRunner {
    static {
        // Günün etiketini belirle
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String tag = switch (today) {
            case MONDAY -> "@monday";
            case TUESDAY -> "@tuesday";
            case WEDNESDAY -> "@wednesday";
            case THURSDAY -> "@thursday";
            case FRIDAY -> "@friday";
            case SATURDAY -> "@saturday";
            case SUNDAY -> "@sunday";
        };
        
        // Günün etiketini sistem property'sine ekle
        System.setProperty("cucumber.filter.tags", "@daily_rotation and " + tag);
    }
}
