package com.herokuapp.booker.cucumber;

import com.herokuapp.booker.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Jay
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/java/resources/feature/",
        glue = "com/herokuapp/booker"
)
public class BookingTestRunner extends TestBase {

}
