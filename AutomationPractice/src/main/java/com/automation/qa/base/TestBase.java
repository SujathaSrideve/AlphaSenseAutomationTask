package com.automation.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.automation.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	protected static File folder;

	
	
	public TestBase(){

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +
					"\\src\\main\\resources\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization(){
		DesiredCapabilities capability=null;
		String browserName = prop.getProperty("browser");
		ChromeOptions options = new ChromeOptions();
		
		//creating unique folder with UUID for saving downloaded files
			folder = new File(UUID.randomUUID().toString());
			folder.mkdir(); 
		
		//Chrome Options
			HashMap<String, Object> perfs = new HashMap<String, Object>();
				
		//popup disabled and capability set.
			perfs.put("profile.default_content_settings.popups", 0);
			perfs.put("download.default_directory", folder.getAbsolutePath());
			options.setExperimentalOption("prefs", perfs);
			//driver = new ChromeDriver(options);
		
			//Can be extended for opera, safari and other browsers.
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");	
			driver = new ChromeDriver(options); 
			capability= DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
		}
		else if(browserName.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver.exe");
			capability= DesiredCapabilities.firefox();
            capability.setBrowserName("firefox"); 
            capability.setPlatform(org.openqa.selenium.Platform.ANY);
			driver = new FirefoxDriver(); 
		}
		else if(browserName.equalsIgnoreCase("Edge")){
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/msedgedriver.exe");
			capability= DesiredCapabilities.edge();
            capability.setBrowserName("edge"); 
            capability.setPlatform(org.openqa.selenium.Platform.WIN10);
			driver = new EdgeDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}

}
