package com.ufcg.psoft.mercadofacil.integration.extra;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/extra",
        plugin = {"pretty", "html:target/cucumber/extra"},
        extraGlue = "com.ufcg.psoft.mercadofacil.commons")
public class ExtraIntegrationTest {
}
