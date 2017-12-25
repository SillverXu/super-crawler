package com.bmsoft.xu.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomjsUtil {


    public static WebDriver getPhantomJs(){
        String osname = System.getProperties().getProperty("os.name");
        if(osname.equals("Linux")){
            System.setProperty("phantomjs.binary.path","/usr/bin/phantomjs");
        }else{
            System.setProperty("phantomjs.binary.path","D:\\phantomjs\\bin\\phantomjs.exe");
        }
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();
        desiredCapabilities.setCapability("phantomjs.page.settings.userAgent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        desiredCapabilities.setCapability("phantomjs.page.customHeaders.User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //todo 判断代理if()

        return new PhantomJSDriver(desiredCapabilities);
    }
}
