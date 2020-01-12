package edu.uclm.esi.apalabreitor.apalabreitor.model;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Funcional {
  private WebDriver driver;
  private WebDriver driverJuan;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:/Users/Adrian/Downloads/chromedriver.exe");
    driver = new ChromeDriver();
    driverJuan = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driverJuan.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  private void login(WebDriver driverRec, String userName, String pwd) {
	  	//login(driverRec, userName,pwd);
	    driverRec.get("http://localhost:8080/");
	    driverRec.findElement(By.id("loginUserName")).click();
	    driverRec.findElement(By.id("loginUserName")).clear();
	    driverRec.findElement(By.id("loginUserName")).sendKeys(userName);
	    driverRec.findElement(By.id("password")).clear();
	    driverRec.findElement(By.id("password")).sendKeys(pwd);
	    driverRec.findElement(By.id("login-submit")).click();
  }

  @Test
  public void testLoginAdrian() throws Exception {
	 login(driver, "pepe","pepe");
	 WebDriverWait wait = new WebDriverWait(driver,10);
	 //WebElement message= driver.findElement(By.id("messageSalaDeEspera"));
	 //wait.until(ExpectedConditions.textToBePresentInElement(message, "Bienvenid@"));
	// WebElement btnManu = driver.findElement(By.id("login-submit"));
	 //btnManu.click();
	 driver.findElement(By.xpath("(//input[@id='login-submit'])[2]")).click();
	 
	 Thread.sleep(1000);
	 login(driverJuan, "ana","ana");
	 wait = new WebDriverWait(driverJuan,10);
	 //message= driverJuan.findElement(By.id("messageSalaDeEspera"));
	 //wait.until(ExpectedConditions.textToBePresentInElement(message, "Bienvenid@"));
	 //WebElement btnJuan = driverJuan.findElement(By.id("login-submit2"));
	 driverJuan.findElement(By.xpath("(//input[@id='login-submit'])[3]")).click();
	 
	 	driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DP'])[6]/following::td[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ana'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='C'])[1]/following::td[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ana'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[4]/following::td[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ana'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[1]/following::label[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ana'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("//div[@id='botonera']/div/div/div/ul/li/button/img")).click();
	    
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[2]/following::td[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='pepe'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='D'])[2]/following::td[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='pepe'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("//div[@id='botonera']/div/div/div/ul/li/button/img")).click();
	    
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DL'])[7]/following::td[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DP'])[8]/following::label[4]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ana'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='TL'])[18]/following::td[5]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='O'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("//div[@id='botonera']/div/div/div/ul/li/button/img")).click();
	    
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DL'])[5]/following::td[5]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='pepe'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='M'])[2]/following::td[4]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='G'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[4]/following::label[4]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='G'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DL'])[10]/following::td[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='G'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DP'])[10]/following::td[2]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='pepe'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='TL'])[22]/following::td[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("//div[@id='botonera']/div/div/div/ul/li/button/img")).click();
	    
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='M'])[2]/following::td[3]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ana'])[1]/following::li[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='M'])[3]/following::label[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='X'])[2]/following::li[1]")).click();
	    driver.findElement(By.xpath("//div[@id='botonera']/div/div/div/ul/li/button/img")).click();
	    
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[5]/following::td[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='R'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='R'])[2]/following::label[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='A'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='R'])[2]/following::td[1]")).click();
	    driverJuan.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='pepe'])[1]/following::li[1]")).click();
	    driverJuan.findElement(By.xpath("//div[@id='botonera']/div/div/div/ul/li/button/img")).click();

	 
	 
	/* poner(driver,8,8,1); poner(driver,8,9,1); poner(driver,8,10,1);
	 poner(driver,8,11,1); poner(driver,8,12,1); poner(driver,8,13,1);
	 poner(driver,8,14,1);
	 driver.findElement(By.id("btnJugar")).click();
	*/
  }
  
 /* private void poner(WebDriver driver, int fila, int columna, int letra) {
	  WebElement casilla = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr["+fila+"]/td["+columna+"]"));
	  WebElement boton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/button["+letra+"]"));
	  casilla.click();
	  boton.click();
	 
  }*/

  @After
  public void tearDown() throws Exception {
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
