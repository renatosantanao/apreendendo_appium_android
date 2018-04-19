package apreendendo;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class appNative {
    AndroidDriver<WebElement> driver;
    DesiredCapabilities capacidade;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        // capacidade (indicando plataforma, dispositivo e aplicação)
        capacidade = new DesiredCapabilities();
        capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "k10 Power");
        capacidade.setCapability("appPackage", "br.com.xxx.android.xxxx.launcher");
        capacidade.setCapability("appActivity", "br.com.xxx.android.xxxx.launcher.LauncherActivity");
        capacidade.setCapability("noReset", "true");
        capacidade.setCapability("fullReset", "false");
        capacidade.setCapability("dontStopAppOnReset", "false");

        // abre a conexão com o servidor (url) e executa um dispositivo (descrito na capacidade)
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void testAppLeitura() throws InterruptedException {

        // interagindo com os componente
        driver.findElement(By.id("br.com.xxx.android.xxxxx.launcher:id/imgIcon")).click();
        Thread.sleep(3000);

        // validando o resultado apresentado
        String mensagemAtual = driver.findElement(By.id("br.com.xxx.android.xxxxx.lis:id/btnLogin")).getText();
        assertEquals("ENTRAR",mensagemAtual);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
