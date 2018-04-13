package apreendendo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class appLeitura {

    @Test
    public void testAppLeitura() throws MalformedURLException {

        // capacidade (indicando plataforma, dispositivo e aplicação)
        DesiredCapabilities capacidade = new DesiredCapabilities();
        capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "lispda");

        // abre a conexão com o servidor (url) e executa um dispositivo (descrito na capacidade)
        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);

        // interagindo com os componentes
        //driver.findElement(By.id("")).click();;

        // validando o resultado apresentado
        //assertEquals("", driver.findElement(By.id("")).getText());

    }

}
