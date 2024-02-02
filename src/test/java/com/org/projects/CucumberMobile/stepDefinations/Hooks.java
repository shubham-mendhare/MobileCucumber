package com.org.projects.CucumberMobile.stepDefinations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.org.projects.CucumberMobile.utils.TestContextSetup;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Hooks {
TestContextSetup testContextSetup;
public static AppiumDriverLocalService service;
public static Properties prop;
	
	public Hooks(TestContextSetup testContextSetup)
	{
		
		this.testContextSetup = testContextSetup;

	}
	
	@BeforeAll
	public static void setUp() {
		InitconfigFile();
	    if(service == null) {
	    	System.out.println(TestContextSetup.ANSI_GREEN + "************Inside @BeforeAll Tag****************" + TestContextSetup.ANSI_RESET);
	    	System.out.println(TestContextSetup.ANSI_YELLOW + "************Initializing Appium server…****************" + TestContextSetup.ANSI_RESET);
	        service = new AppiumServiceBuilder()
	            .withAppiumJS(new File("C:\\Users\\shubh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	            .withArgument(() -> "--use-plugins", "element-wait,device-farm,appium-dashboard,gestures")
	            .withArgument(() -> "-ka", "800")
	            .withArgument(() -> "--plugin-device-farm-platform", "android")
	         // .withArgument(() -> "--allow-insecure","adb_shell")
	            .withArgument(() -> "--log-level","error")
	            .withTimeout(Duration.ofSeconds(120))
	            .withIPAddress(Hooks.prop.getProperty("AppiumURL"))
	            .usingPort(Integer.parseInt(Hooks.prop.getProperty("AppiumPort")))
	            .build();
	        service.start();
	        System.out.println(TestContextSetup.ANSI_YELLOW + "************Appium server started.****************" + TestContextSetup.ANSI_RESET);
	    }
	}

	
	
	@After
	public void AfterScenario() throws IOException, URISyntaxException
	{
		System.out.println(TestContextSetup.ANSI_GREEN + "************Inside @After tag****************" + TestContextSetup.ANSI_RESET);
		testContextSetup.testBase.DriverManager().quit();
		System.out.println(TestContextSetup.ANSI_YELLOW + "************driver quite executed.****************" + TestContextSetup.ANSI_RESET);
		
	}
	
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException, URISyntaxException
	{
		System.out.println(TestContextSetup.ANSI_GREEN + "************Inside @AfterStep tag****************" + TestContextSetup.ANSI_RESET);
		WebDriver driver =testContextSetup.testBase.DriverManager();
		if(scenario.isFailed())
		{
		//screenshot
		File sourcePath= 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "image");
		}
		
	}
	@AfterAll
    public static void tearDown() {
		System.out.println(TestContextSetup.ANSI_GREEN + "************Inside @AfterAll tag****************" + TestContextSetup.ANSI_RESET);
        // Stop Appium server
        if (service != null) {
        	System.out.println(TestContextSetup.ANSI_YELLOW + "************Terminating appium server....****************" + TestContextSetup.ANSI_RESET);
            service.stop();
            service = null;
            System.out.println(TestContextSetup.ANSI_YELLOW + "************Appium server terminated.****************" + TestContextSetup.ANSI_RESET);
          //  RunBatchScript();
            
            if(prop.getProperty("email_Module").equalsIgnoreCase("true")) {
        		String ReportPath= getLatestFilePathFromDir(System.getProperty("user.dir") + "//test-output//SparkReport//");	
        		EmailReport(prop.get("recipient").toString(), prop.get("sender").toString(), prop.get("username").toString(), 
        		prop.get("password").toString(), prop.get("subject").toString(), prop.get("message").toString().replace("<br>", "\n"), ReportPath, 
        		prop.get("smpthost").toString(), prop.get("smptport").toString());
        		}
        }
    }
	
	public static void RunBatchScript() {
		 ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", System.getProperty("user.dir")+"//src//test//resources//"+"CloseApp.bat");
	        processBuilder.redirectErrorStream(true);

	        try {
	            Process process = processBuilder.start();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
	            process.waitFor();
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void InitconfigFile(){
    	prop = new Properties();
    	try {
    		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//data.properties");
		prop.load(fis);
    	}
    	catch(Exception e) {
    		System.out.println("File not found");
    	}
    		
    }
	
	public static void EmailReport(String recipient, String sender, String usrname, String  usrpassword, String subj
    		, String msg, String AttachmentPath, String smpthost, String smptport ) {

        // Recipient's email ID needs to be mentioned.
        String to = recipient;

        // Sender's email ID needs to be mentioned
        String from = sender;
        final String username = usrname;//change accordingly
        final String password = usrpassword;//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = smpthost;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", smptport);

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subj);

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(msg);

            // Create a multipart message
            MimeMultipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = AttachmentPath;
            FileDataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
	

	    public static String getLatestFilePathFromDir(String dirPath) {
	        File dir = new File(dirPath);
	        File[] files = dir.listFiles();
	        if (files == null || files.length == 0) {
	            return null;
	        }

	        Arrays.sort(files, new Comparator<File>() {
	            public int compare(File f1, File f2) {
	                return Long.compare(f2.lastModified(), f1.lastModified());
	            }
	        });

	        // If the latest file is a directory, get the latest file from it
	        if (files[0].isDirectory()) {
	            return getLatestFilePathFromDir(files[0].getAbsolutePath());
	        }

	        // returns the path of the newest file
	        return files[0].getAbsolutePath();
	    }
}
	

