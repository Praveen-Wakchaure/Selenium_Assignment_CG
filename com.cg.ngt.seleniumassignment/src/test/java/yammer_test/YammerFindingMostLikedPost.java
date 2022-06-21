package yammer_test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YammerFindingMostLikedPost {

	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {

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
		Thread.sleep(7000);

		// Technical error in wait
		WebElement yesButton= driver.findElement(By.xpath("//input[@type='submit']"));
		yesButton.click();
		Thread.sleep(10000);

		//Click on OneTeamONeFamily
		driver.findElement(By.xpath("//div[contains(text(),'OneTeamOneFamily')]")).click();
		Thread.sleep(1000);
		
//		var limit = Math.max( document.body.scrollHeight, document.body.offsetHeight, 
//                document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight );

		// Set set= new TreeSet();
		List list2= new ArrayList();
		Map <Integer, WebElement> map = new HashMap<Integer, WebElement>();
		WebElement list=null;
		//int x=list.getSize();
		for(int j=2;j<5 ;j++) {
			
			//int k=4;
			list= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/ul/li["+j+"]/div/div/div/div/div/div/div[1]/div[5]/div[2]/div/div/div/div/div[2]/div/div/div/button/span/div"));
			                                   
			//k++;
			WebElement e = list;
			System.out.println("running "+ (j-1));
			Thread.sleep(3000);
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
			
				String a=list.getText().substring(0, 2).replaceAll(" ","");
				JavascriptExecutor executor= (JavascriptExecutor) driver;
//				executor.executeScript("window.scrollBy(0,2000)");
				
				Thread.sleep(3000);
				int num=Integer.parseInt(a);
				list2.add(num);
				map.put(num, e);
				Thread.sleep(2000);
		}
		Collections.sort(list2);
		System.out.println(list2);
		Object numberOfLike = (list2.get(list2.size()-1));
		
		int numberOfLikeOfMostLikedPost = (Integer) (numberOfLike);
		System.out.println("Number of like of most liked post is: "+(numberOfLikeOfMostLikedPost+1));
		//System.out.println(map.get(list2.get(list2.size()-1)));
		WebElement mostLikedLocator = map.get(list2.get(list2.size()-1));
		//System.out.println(mostLikedLocator);
		
	    //System.out.println("Most liked post location: "+mostLikedLocator.getLocation());
	    JavascriptExecutor executor= (JavascriptExecutor) driver;
	    
	    String mostLikedPostStringPath = mostLikedLocator.toString().substring(80, 153);
	    //System.out.println("mostLikedPostStringPath: "+mostLikedPostStringPath);
	    WebElement mostLikedPostLocator = driver.findElement(By.xpath(mostLikedPostStringPath));
	    //System.out.println("mostLikedPostLocator: "+mostLikedPostLocator);
	    
		File srcfile1 = ((TakesScreenshot)mostLikedPostLocator).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile1, new File("./screenshot/image2.png"));
		}
	}

