package com.common.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;

public class GetLocalStorage {

    public String getObjectBundle(String object) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) DriverUtils.getDriver());
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        LocalStorage storage = webStorage.getLocalStorage();
        String txt = storage.getItem("okta-token-storage");
        JsonElement jelement = new JsonParser().parse(txt);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("idToken");
        jobject = jobject.getAsJsonObject("claims");
        return jobject.get(object).getAsString();
    }
}
