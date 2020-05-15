package Pagelayer;

import BaseLayer.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Scrolldownfunctionality extends TestBase {

    //Web elements using Page factory
    @FindBy(xpath = "//*[@class='gh-tb ui-autocomplete-input']")
    WebElement searchbar;
    @FindBy(xpath = "//*[@id='gh-cat']")
    WebElement categorydrpdown;


    JavascriptExecutor   js = (JavascriptExecutor)driver;

    public Scrolldownfunctionality() {
        //initializing base class constructor
        super();
        //initializing elements using constructor
        PageFactory.initElements(driver, this);
    }

@BeforeMethod
    public void setup() {
        initialization();
    }


    public void searchincategories() {
        //importing select class to select from dropdown
        Select s = new Select(categorydrpdown);
        s.selectByVisibleText("All Categories");
        searchbar.sendKeys("watches");
        //Getting all webelements in a list and iterating
        List<WebElement> lstcategories = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']"));
        for (int i = 0; i < lstcategories.size(); i++) {
            try {
                //since,the DOM structure will be changed,
                //webelements are again iterated inside the for loop to avoid stalelement exception
                WebElement element = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i);

                //iteartes each element in the list and checks its text with i/p text,if so element is clicked
                if ((element.getText()).equalsIgnoreCase("watches for kids")) {

                    element.click();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception-Element# " + i + " not found");
            }
        }
    }
   // @Test(priority = 1)
    public void scrolldowntowebelementwithaction()
    {
        searchincategories();
        List<WebElement> lstitems=driver.findElements(By.xpath("//*[@class='s-item__link']"));
        int totalnoofprod=lstitems.size();
        Actions action=new Actions(driver);
        WebElement e=driver.findElement(By.partialLinkText("Fashion Cute "));
        action.moveToElement(e).build().perform();
        System.out.println("Last item is"+e.getText());

    }
    public void scrolldownwithcoordinates(int X,int Y)
    {
        String script=String.format("window.scrollBy(%d,%d)",X,Y);

            js.executeScript(script);

    }
   // @Test(priority = 2)
    public void scrolldownwithjs()
    {
        searchincategories();
        List<WebElement> lstitems=driver.findElements(By.xpath("//*[@class='s-item__link']"));
       int  totalnoofproducts=lstitems.size();
        for(int i=0;i<lstitems.size();i++)
        {
            WebElement e=lstitems.get(i);
           int X= e.getLocation().x;
           int Y=e.getLocation().y;
            scrolldownwithcoordinates( X, Y);
            if(i==(totalnoofproducts-1))
            {
                 System.out.println("last item via js"+e.getText());

            }
        }
    }

    public void scrollup_downpage() throws InterruptedException {

        String script="window.scrollBy(0,1500)";
        js.executeScript(script);
        Thread.sleep(5000);
       String script1="window.scrollBy(0,-1500)";
        js.executeScript(script1);

    }

    public void horizontal_vertical_scrolling() throws InterruptedException {
        String script="window.scrollBy(1500,0)";
        js.executeScript(script);
        Thread.sleep(5000);
        String script1="window.scrollBy(-1500,0)";
        js.executeScript(script1);

    }
    public void scrolltobottomofpage()
    {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    @Test
    public void scrollfunction() throws InterruptedException {
        scrolldowntowebelementwithaction();
       Thread.sleep(5000);
        scrollup_downpage();
        Thread.sleep(5000);
        horizontal_vertical_scrolling();
        Thread.sleep(5000);
       scrolltobottomofpage();
        Thread.sleep(5000);

    }
}
