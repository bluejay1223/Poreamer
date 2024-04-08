package edu.ncsu.csc316.social.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Test class for reportmanager
 * 
 * @author taeyoonkim
 *
 */
public class ReportManagerTest {
	
	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testGetConnectionsByPerson() throws FileNotFoundException {
	    // Setup your SocialMediaManager with test data or mock it
	    
	    ReportManager reportManager = new ReportManager("input/person-1.txt", "input/connection-1.txt");
	    
	    String report = reportManager.getConnectionsByPerson();
	    //System.out.println(report);
	    assertNotNull("The report should not be null", report);
	    assertFalse("The report should not be empty", report.isEmpty());
	    
	    
	    assertEquals(report, "Connections for Phyliss Beahan (beahanp130) {\n"
	    		+ "   Justin Boyer (boyerj706) on LinkedIn since Sat Nov 20 19:11:47 EST 2021\n"
	    		+ "   Brooks Homenick (homenickb690) on Facebook since Sun Feb 11 05:45:35 EST 2018\n"
	    		+ "   Brooks Homenick (homenickb690) on LinkedIn since Sun Jun 23 13:13:15 EDT 2019\n"
	    		+ "   Leonard Runolfsdottir (runolfsdottirl591) on WhatsApp since Tue Dec 08 03:54:58 EST 2020\n"
	    		+ "   Royce Simonis (simonisr882) on Instagram since Sun May 10 21:10:38 EDT 2015\n"
	    		+ "}\n"
	    		+ "Connections for Justin Boyer (boyerj706) {\n"
	    		+ "   Phyliss Beahan (beahanp130) on LinkedIn since Sat Nov 20 19:11:47 EST 2021\n"
	    		+ "   Brooks Homenick (homenickb690) on Instagram since Wed Mar 11 06:10:33 EDT 2015\n"
	    		+ "   Cristobal Jones (jonesc957) on WeChat since Wed Nov 11 18:51:12 EST 2015\n"
	    		+ "   Napoleon Kertzmann (kertzmannn329) on Telegram since Wed Nov 16 03:42:00 EST 2016\n"
	    		+ "   Leonard Runolfsdottir (runolfsdottirl591) on WhatsApp since Mon Oct 26 22:11:06 EDT 2015\n"
	    		+ "   Leonard Runolfsdottir (runolfsdottirl591) on Discord since Sun Feb 04 09:23:24 EST 2018\n"
	    		+ "   Royce Simonis (simonisr882) on Twitch since Mon Aug 03 02:54:39 EDT 2020\n"
	    		+ "}\n"
	    		+ "Connections for Brooks Homenick (homenickb690) {\n"
	    		+ "   Phyliss Beahan (beahanp130) on Facebook since Sun Feb 11 05:45:35 EST 2018\n"
	    		+ "   Phyliss Beahan (beahanp130) on LinkedIn since Sun Jun 23 13:13:15 EDT 2019\n"
	    		+ "   Justin Boyer (boyerj706) on Instagram since Wed Mar 11 06:10:33 EDT 2015\n"
	    		+ "   Cristobal Jones (jonesc957) on Facebook since Wed Mar 11 00:26:46 EDT 2020\n"
	    		+ "   Napoleon Kertzmann (kertzmannn329) on Discord since Fri Jun 10 21:34:34 EDT 2016\n"
	    		+ "   Chang Murazik (murazikc970) on TikTok since Mon Apr 03 11:38:59 EDT 2023\n"
	    		+ "   Leonard Runolfsdottir (runolfsdottirl591) on Reddit since Sun Nov 10 23:02:36 EST 2019\n"
	    		+ "   Leonard Runolfsdottir (runolfsdottirl591) on Twitch since Sun Dec 19 07:42:48 EST 2021\n"
	    		+ "}\n"
	    		+ "Connections for Cristobal Jones (jonesc957) {\n"
	    		+ "   Justin Boyer (boyerj706) on WeChat since Wed Nov 11 18:51:12 EST 2015\n"
	    		+ "   Brooks Homenick (homenickb690) on Facebook since Wed Mar 11 00:26:46 EDT 2020\n"
	    		+ "   Chang Murazik (murazikc970) on LinkedIn since Fri Jul 02 22:41:29 EDT 2021\n"
	    		+ "}\n"
	    		+ "Connections for Napoleon Kertzmann (kertzmannn329) {\n"
	    		+ "   Justin Boyer (boyerj706) on Telegram since Wed Nov 16 03:42:00 EST 2016\n"
	    		+ "   Brooks Homenick (homenickb690) on Discord since Fri Jun 10 21:34:34 EDT 2016\n"
	    		+ "   Chang Murazik (murazikc970) on Telegram since Sun Nov 23 13:14:01 EST 2014\n"
	    		+ "   Royce Simonis (simonisr882) on Twitter since Wed Jul 28 22:55:33 EDT 2021\n"
	    		+ "}\n"
	    		+ "Connections for Chang Murazik (murazikc970) {\n"
	    		+ "   Brooks Homenick (homenickb690) on TikTok since Mon Apr 03 11:38:59 EDT 2023\n"
	    		+ "   Cristobal Jones (jonesc957) on LinkedIn since Fri Jul 02 22:41:29 EDT 2021\n"
	    		+ "   Napoleon Kertzmann (kertzmannn329) on Telegram since Sun Nov 23 13:14:01 EST 2014\n"
	    		+ "   Leonard Runolfsdottir (runolfsdottirl591) on Facebook since Fri Apr 18 20:45:17 EDT 2014\n"
	    		+ "}\n"
	    		+ "Connections for Leonard Runolfsdottir (runolfsdottirl591) {\n"
	    		+ "   Phyliss Beahan (beahanp130) on WhatsApp since Tue Dec 08 03:54:58 EST 2020\n"
	    		+ "   Justin Boyer (boyerj706) on WhatsApp since Mon Oct 26 22:11:06 EDT 2015\n"
	    		+ "   Justin Boyer (boyerj706) on Discord since Sun Feb 04 09:23:24 EST 2018\n"
	    		+ "   Brooks Homenick (homenickb690) on Reddit since Sun Nov 10 23:02:36 EST 2019\n"
	    		+ "   Brooks Homenick (homenickb690) on Twitch since Sun Dec 19 07:42:48 EST 2021\n"
	    		+ "   Chang Murazik (murazikc970) on Facebook since Fri Apr 18 20:45:17 EDT 2014\n"
	    		+ "}\n"
	    		+ "Connections for Royce Simonis (simonisr882) {\n"
	    		+ "   Phyliss Beahan (beahanp130) on Instagram since Sun May 10 21:10:38 EDT 2015\n"
	    		+ "   Justin Boyer (boyerj706) on Twitch since Mon Aug 03 02:54:39 EDT 2020\n"
	    		+ "   Napoleon Kertzmann (kertzmannn329) on Twitter since Wed Jul 28 22:55:33 EDT 2021\n"
	    		+ "}\n"
	    		+ "");
	}
	
	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testGetConnectionsByPlatform() throws FileNotFoundException {
	    // Setup your SocialMediaManager with test data or mock it
	    
	    ReportManager reportManager = new ReportManager("input/person-1.txt", "input/connection-1.txt");
	    
	    String report = reportManager.getConnectionsByPlatform();
	    assertNotNull("The report should not be null", report);
	    assertFalse("The report should not be empty", report.isEmpty());
	    
	}

}
