package yammer_test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class YammerFindingMostActiveUser {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions option= new ChromeOptions();
		option.addArguments("--disable-notifications");

		driver= new ChromeDriver(option);
		driver.get("https://web.yammer.com/main/capgemini.com/");
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.name("loginfmt")).sendKeys("praveen-uttam.wakchaure@capgemini.com");
		driver.findElement(By.id("idSIButton9")).click();
		Thread.sleep(5000);

		// Technical error in wait
		WebElement yesButton= driver.findElement(By.xpath("//input[@type='submit']"));
		yesButton.click();
		Thread.sleep(10000);

		//Click on OneTeamONeFamily
		driver.findElement(By.xpath("//div[contains(text(),'OneTeamOneFamily')]")).click();
		Thread.sleep(5000);

		WebElement usersLocator;
		WebElement dateLocator;
		String user;

		//		JavascriptExecutor executor= (JavascriptExecutor) driver;
		//		System.out.println("Maximum scroll size: "+executor.executeScript("Math.max( document.body.scrollHeight, document.body.offsetHeight,document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight)"));
		//		int verticalWindowSize = driver.manage().window().getSize().height;

		ArrayList<String> userNames = new ArrayList<String>();

		try {
			for(int j=1;j<20;j++) {
				usersLocator= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/ul/li["+j+"]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/div/div[2]/div[1]/div/span/div/div[1]/div/div/a/span/span"));
				user = usersLocator.getText().replace(",","");
				userNames.add(user);
				dateLocator = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/ul/li["+j+"]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/div/div[2]/div[2]/ul/li[1]/a/time"));
				System.out.println("post no."+j +" -> "+user+": "+dateLocator.getText());
				JavascriptExecutor executor= (JavascriptExecutor) driver;
				executor.executeScript("window.scrollBy(0,2000)");
				Thread.sleep(2000);
				//System.out.println("running "+j);
			}
		}
		catch (Exception e) {
			System.out.println("Some exception occur...");
		}

		finally {

			//			for(String s: userNames) {
			//			System.out.println(s);}
			System.out.println("=====User name and Post Count=====");
			Set<String> unique = new HashSet<String>(userNames);
			for (String key : unique) {
				System.out.println(key + ": " + Collections.frequency(userNames, key ));
			}
			
		}			
	}
}
