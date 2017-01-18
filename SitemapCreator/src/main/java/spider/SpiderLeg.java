package spider;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {

    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	private List<String> links = new LinkedList<String>();
	private Document webPage;
	
    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a searchForWord after the successful crawl
     * 
     * @param url - The URL to visit
     * @return whether or not the crawl was successful
     */
	public boolean crawl(String url){
		try {
			Connection con = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = con.get();
			this.webPage = htmlDocument;
			
			if(con.response().statusCode() != 200){
				System.out.println(con.response().statusCode());
				return false;
			}
			
			if(!con.response().contentType().contains("text/html")){
                System.out.println("**Failure** Retrieved something other than HTML");
                return false;
            }
			
			Elements linksOnPage = webPage.select("a[href]");
//            System.out.println("Found (" + linksOnPage.size() + ") links");
            
            for(Element link : linksOnPage){
            	String path = link.absUrl("href");
            	if(path.startsWith("http://morninggossip.com/") && path.endsWith("/")){  
            		this.links.add(path);
            	}
            }
		} catch (IOException e) {
			System.out.println("Error in out HTTP request " + e);
		}
		return true;
	}
	
	public List<String> getLinks(){
		return links;
	}
}
