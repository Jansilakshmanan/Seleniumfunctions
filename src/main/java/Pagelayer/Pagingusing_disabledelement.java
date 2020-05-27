package Pagelayer;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pagingusing_disabledelement {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\jansi_webdrivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://datatables.net/examples/advanced_init/dt_events.html");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    @Test
    public void paging_functionality() {
        //gets all the elements and its text,size of those in page 1
        List<WebElement> lst = driver.findElements(By.xpath("//*[@class='sorting_1']"));
        List<String> lst_string = new ArrayList<>();
        for (WebElement e : lst) {
            lst_string .add(e.getText());

        }
        int size=lst.size();
        List<Integer> lst_size = new ArrayList<>();
        lst_size.add(size);

         //xpath for the webelement next
        WebElement next = driver.findElement(By.xpath("//*[@id='example_next']"));
        //while loop to click next button until it gets disabled
        //after clicking next,it gets the text and size of those in each page
        try {
            while (!(driver.findElement(By.xpath("//*[@id='example_next']")).getAttribute("class").equalsIgnoreCase("paginate_button next disabled"))) {
                driver.findElement(By.xpath("//*[@id='example_next']")).click();
                List<WebElement> lst_paging = driver.findElements(By.xpath("//*[@class='sorting_1']"));
                for (WebElement e : lst_paging) {
                    lst_string .add(e.getText());

                }
                lst_size.add((lst_paging.size()));
            }

                 //iterates and prints all the names from all the pages
                for (String s : lst_string) {

                    System.out.println(s);
                }
               //iterates and prints total webelements in the table from all the pages
                int temp=0;
                int total=0;
                for(int i=0;i< lst_size.size();i++)
                {
                     total=temp+lst_size.get(i);
                     temp=total;

                }
            System.out.println("Total count is: "+total);



        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @AfterClass
    public void teardown()
    {
        driver.quit();
    }
}
