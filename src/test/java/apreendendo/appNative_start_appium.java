package apreendendo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class appNative_start_appium {
    AndroidDriver<WebElement> driver;
    AppiumDriverLocalService appiumServico;
    String appiumServicoUrl;
    DesiredCapabilities capacidade;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        appiumServico = AppiumDriverLocalService.buildDefaultService();
        appiumServico.start();
        appiumServicoUrl = appiumServico.getUrl().toString();
        System.out.println("Endereco do servidor Appium : - " + appiumServicoUrl);

        // capacidade (indicando plataforma, dispositivo e aplicação)
        capacidade = new DesiredCapabilities();

        capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "k10 Power");
        capacidade.setCapability("appPackage", "br.com.xxx.android.xxxxx.launcher");
        capacidade.setCapability("appActivity", "br.com.xxx.android.xxxxx.launcher.LauncherActivity");
        capacidade.setCapability("noReset", "true");
        capacidade.setCapability("fullReset", "false");
        capacidade.setCapability("dontStopAppOnReset", "false");

        // abre a conexão com o servidor (url) e executa um dispositivo (descrito na capacidade)
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void testAppLeitura() throws InterruptedException {

        WebDriverWait aguardarElemento = new WebDriverWait(driver, 10);

        // interagindo com os componente
        driver.findElement(By.id("br.com.xxx.android.xxxxx.launcher:id/imgIcon")).click();

        aguardarElemento.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("br.com.xxx.android.xxxxx.lis:id/btnLogin")));

        // validando o resultado apresentado
        String mensagemAtual = driver.findElement(By.id("br.com.xxx.android.xxxxx.lis:id/btnLogin")).getText();
        assertEquals("ENTRAR", mensagemAtual);

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.findElement(By.id("br.com.xxx.android.xxxxx.lis:id/btnLogin")).click();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
