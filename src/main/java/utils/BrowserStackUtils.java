package utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BrowserStackUtils
{
    public void postFailedTestStatus(TestContext context, ITestResult result)
    {
        URI uri = getBrowserStackAutomateSessionUriFromContext(context);
        HttpPut putRequest = new HttpPut(uri);
        String testcaseName = createTestCaseName(context, result);

        ArrayList<NameValuePair> browserStackBuildData = new ArrayList<NameValuePair>();
        browserStackBuildData.add((new BasicNameValuePair("status", "failed")));
        browserStackBuildData.add((new BasicNameValuePair("reason", result.getThrowable().getMessage())));
        browserStackBuildData.add((new BasicNameValuePair("name", testcaseName)));

        try
        {
            putRequest.setEntity(new UrlEncodedFormEntity(browserStackBuildData));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        try
        {
            HttpClientBuilder.create().build().execute(putRequest);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void postPassedTestStatus(TestContext context, ITestResult result)
    {
        URI uri = getBrowserStackAutomateSessionUriFromContext(context);
        HttpPut putRequest = new HttpPut(uri);
        String testcaseName = createTestCaseName(context, result);

        ArrayList<NameValuePair> browserStackBuildData = new ArrayList<NameValuePair>();
        browserStackBuildData.add((new BasicNameValuePair("status", "passed")));
        browserStackBuildData.add((new BasicNameValuePair("name", testcaseName)));

        try
        {
            putRequest.setEntity(new UrlEncodedFormEntity(browserStackBuildData));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        try
        {
            HttpClientBuilder.create().build().execute(putRequest);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private URI getBrowserStackAutomateSessionUriFromContext(TestContext context)
    {
        URI uri = null;
        try
        {
            uri = new URI("https://" + context.getConfig().getBrowserStackUsername() + ":" +
                    context.getConfig().getBrowserStackKey() + "@api.browserstack.com/automate/sessions/"
                    + context.getBrowserStackSessionDetails().getSessionId() + ".json");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        return uri;
    }

    private String createTestCaseName(TestContext context, ITestResult result)
    {
        Date date = new Date();
        DateFormat dateFormat_timestamp = new SimpleDateFormat("dd-MM-YYYY-HHmmss");

        return context.getBrowserStackSessionDetails().getTestCaseName() + "-"
                + dateFormat_timestamp.format(date);
    }
}