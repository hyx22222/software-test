package com.company;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {

    static class person{
        String name;
        String url;
        String password;
        person(){
            name = null;
            url = null;
            password = null;
        }
    }

    public static void main(String[] args) throws IOException {

        String ID = null;
        String password_s = null;
        List<person> personList = new ArrayList<>();

        File file = new File("E:\\作业\\软件测试\\input.xlsx");
        InputStream inputStream = new FileInputStream(file.getAbsolutePath());
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        char[] temp2 = new char[6];

        for (int i=sheet.getFirstRowNum();i<sheet.getPhysicalNumberOfRows();i++){
            row = sheet.getRow(i);
            if (row != null){
                person p = new person();
                p.name = clear(row.getCell(0).toString());
                p.url = row.getCell(1).toString();
                    char[] temp = p.name.toCharArray();
                    for(int j=0;j<6;j++){
                        temp2[j]=temp[j+4];
                    }
                    p.password =String.valueOf(temp2);
                    personList.add(p);
            }
        }

        System.setProperty(  "webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe" );
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe")).usingAnyFreePort().build();
        service.start();
        WebDriver driver = new RemoteWebDriver(service.getUrl(),DesiredCapabilities.chrome());


        for (int i=0;i<personList.size();i++){
            driver.get("https://psych.liebes.top/st");
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement button = driver.findElement(By.id("submitButton"));
            username.sendKeys(personList.get(i).name);
            password.sendKeys(personList.get(i).password);
            button.click();
            WebElement myUrl_p = driver.findElement(By.tagName("p"));
            if ( personList.get(i).url.contains(myUrl_p.getText())){
                System.out.println(personList.get(i).name+"   OK");
            }else{
                System.out.println(personList.get(i).name+"   ERROR! "+" xslx中的： "+personList.get(i).url+" …… "+" 网页上的： "+myUrl_p.getText());
            }
        }
    }

    static String clear(String s){
        s = s.replace(".","");
        s = s.replace("E9","");
        if (s.length()<10){
            s=s+"0";
        }
        return s;
    }
}
