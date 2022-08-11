package com.ufcg.psoft.mercadofacil.integration.basics;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/basics",
        plugin = {"pretty", "html:target/cucumber/basics"},
        extraGlue = "com.ufcg.psoft.mercadofacil.integration.commons")
public class CucumberIntegrationTest {
}
