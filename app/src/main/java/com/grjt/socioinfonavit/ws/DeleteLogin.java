package com.grjt.socioinfonavit.ws;

import java.net.MalformedURLException;
import java.net.URL;

import static com.grjt.socioinfonavit.control.Global.system_WServices;

public class DeleteLogin {

    public URL[] delete(String type)
    {
        URL typeDescription;
        URL url;

        URL[] urls = null;

        try
        {
            typeDescription = new URL("http://" + type);
            url = new URL(system_WServices + "logout");
            urls = new URL[]{typeDescription, url};
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return urls;
    }

}
