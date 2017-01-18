package spider;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

import sitemap.URLCollection;

public class Spider {

    private static final int MAX_PAGES_TO_SEARCH = 10000;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();
	
    URLCollection urls;
    
    /**
     * Our main launching point for the Spider's functionality. Internally it creates spider legs
     * that make an HTTP request and parse the response (the web page).
     * 
     * @param url - The starting point of the spider
     * @throws MalformedURLException 
     */
    public void search(String url, WebSitemapGenerator webSitemapGenerator, URLCollection urls) throws MalformedURLException{
    	this.urls = urls;
    	
    	while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH){
    		String currentURL;
    		SpiderLeg leg = new SpiderLeg();
    		
    		if(this.pagesToVisit.isEmpty()){
    			currentURL = url;
    			this.pagesVisited.add(url);
    		} else {
    			currentURL = this.nextURL();
    		}
    		
    		if(currentURL == null){
    			return;
    		}
    		leg.crawl(currentURL);
    		
    		System.out.println(this.pagesVisited.size() + ", " + currentURL);
    		
    		sortURLs(currentURL, webSitemapGenerator);
    	    
    	    // Stop at 1000
    	    if(this.pagesVisited.size() == 10000){
    	    	return;
    	    }
    	    
    		this.pagesToVisit.addAll(leg.getLinks());
    	}
    }
    
    /**
     * Returns the next URL to visit (in the order that they were found). We also do a check to make
     * sure this method doesn't return a URL that has already been visited.
     * 
     * @return
     */
    String nextURL(){
    	String nextURL;
    	do {
    		if(pagesToVisit.size() == 0){
    			return null;
    		}
			nextURL = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextURL));
    	this.pagesVisited.add(nextURL);
    	return nextURL;
    }
    
    /**
     * Sorts the URLs into their proper Set
     * @throws MalformedURLException 
     * 
     */
    public void sortURLs(String url, WebSitemapGenerator webSitemapGenerator) throws MalformedURLException{
    	WebSitemapUrl webSitemapUrl= new WebSitemapUrl.Options(url).priority(0.8).build();
    	
    	if(this.pagesVisited.size() == 1){
    		// Should only happen with first page
    		webSitemapUrl = new WebSitemapUrl.Options(url).lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.HOURLY).build();
    		urls.addCategory();
    	} else if(url.startsWith("http://morninggossip.com/category")){
    		webSitemapUrl = new WebSitemapUrl.Options(url).lastMod(new Date()).priority(0.9).changeFreq(ChangeFreq.DAILY).build();
    		urls.addCategory();
    	} else if(url.startsWith("http://morninggossip.com/tag")){
    		urls.addTag();
    	} else if(url.startsWith("http://morninggossip.com/author")){
    		urls.addAuthor();
    	} else if(url.startsWith("http://morninggossip.com/2011")){
    		urls.addArticles11();
    	} else if(url.startsWith("http://morninggossip.com/2012")){
    		urls.addArticles12();
    	} else if(url.startsWith("http://morninggossip.com/2013")){
    		urls.addArticles13();
    	} else if(url.startsWith("http://morninggossip.com/2014")){
    		urls.addArticles14();
    	} else if(url.startsWith("http://morninggossip.com/2015")){
    		urls.addArticles15();
    	} else if(url.startsWith("http://morninggossip.com/2016")){
    		urls.addArticles16();
    	} 
    	
    	webSitemapGenerator.addUrl(webSitemapUrl);
    }
}
