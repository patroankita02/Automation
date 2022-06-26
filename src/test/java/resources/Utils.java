package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils
{
    static RequestSpecification req;
    public static RequestSpecification requestSpecification() throws IOException
    {
        if(req==null)
        {
            PrintStream log = new PrintStream(new FileOutputStream(("logging.txt")));
            RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalvalueFromprop("baseURL")).
                    addQueryParam("key","qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log)).
                    setContentType(ContentType.JSON).build();
            return  req;
        }
        return  req;

    }

    public static String getGlobalvalueFromprop(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\AnkitaWorkspace\\APIFramework\\src\\test\\java\\resources\\Global.properties");
        prop.load(fis);
        String value = prop.getProperty(key);
        return value;
    }

    public String getJsonPath(Response response, String key)
    {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
