package sitemap;

import java.io.File;
import java.net.MalformedURLException;

import com.redfin.sitemapgenerator.WebSitemapGenerator;

import spider.Spider;

public class Main {
	public static void main(String[] args) throws MalformedURLException {
		
		URLCollection urls = new URLCollection();
		String site = "http://morninggossip.com/";
		File file = new File("C:\\Users\\erik.lundin\\Documents");
		
	    WebSitemapGenerator webSitemapGenerator = WebSitemapGenerator.builder(site, file).build();

		Spider spider = new Spider();
		spider.search(site, webSitemapGenerator, urls);
		
		System.out.println("////////////////////////");
		System.out.println("//--------------------//");
		System.out.println("//  Done crawling :)  //");
		System.out.println("//--------------------//");
		System.out.println("////////////////////////");

		System.out.println("Categories: " + urls.getCategories());
		System.out.println("Tags: " + urls.getTags());
		System.out.println("Author: " + urls.getAuthors());
		System.out.println("2011: " + urls.getArticles11());
		System.out.println("2012: " + urls.getArticles12());
		System.out.println("2013: " + urls.getArticles13());
		System.out.println("2014: " + urls.getArticles14());
		System.out.println("2015: " + urls.getArticles15());
		System.out.println("2016: " + urls.getArticles16());
		
		webSitemapGenerator.write();
		
		System.out.println("////////////////////////");
		System.out.println("//--------------------//");
		System.out.println("//  Done writing :)   //");
		System.out.println("//--------------------//");
		System.out.println("////////////////////////");
	}
}
