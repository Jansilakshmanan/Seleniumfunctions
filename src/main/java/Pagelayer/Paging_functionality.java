package Pagelayer;

import BaseLayer.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Paging_functionality extends TestBase {
    //Web elements using Page factory
    @FindBy(xpath = "//*[@class='gh-tb ui-autocomplete-input']")
    WebElement searchbar;
    @FindBy(xpath = "//*[@id='gh-cat']")
    WebElement categorydrpdown;
    @FindBy(xpath = "//*[@class='ui-menu-item ghAC_visible']")
    List<WebElement> lstcategories;
    @FindBy(xpath = "//*[@class='s-item__link']")
    List<WebElement> lstproducts;

    Scrolldownfunctionality sf=new Scrolldownfunctionality();

    public Paging_functionality()
    {
        super();
        PageFactory.initElements(driver,this);
    }
    @BeforeMethod
    public void setup()
    {
        initialization();
    }
    @Test

    public void getlistitems() throws InterruptedException {

       sf.searchincategories();
       //get all the pagination links in a list
        List<WebElement>lstpaginationitems=driver.findElements(By.xpath("//*[@class='pagination__item']"));
        //create a arraylist where the count of each page will be added to the list
        List<Integer> lstcount=new ArrayList<Integer>();
        //iterate the list of page links and get the weblements in each page and print the total elements in that page
        for(int i=0;i<4;i++)
        {
            driver.findElements(By.xpath("//*[@class='pagination__item']")).get(i).click();
            List<WebElement> lst_links= driver.findElements(By.xpath("//*[@class='s-item__link']"));
            System.out.println("Total no of items in page"+(i+1)+": " +lst_links.size());
            Thread.sleep(3000);
            lstcount.add(lst_links.size());
        }

        System.out.println("Total items"+lstcount);
        int temp=0;
        int total=0;
        //iterate the arraylist and sum the count of each page to get the total count
        for(int i=0;i<lstcount.size();i++)
        {
            total=temp+lstcount.get(i);
            temp=total;

        }
        System.out.println("Total no of items till page 4: "+total);

    }
}
