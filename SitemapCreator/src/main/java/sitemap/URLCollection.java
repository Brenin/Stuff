package sitemap;

public class URLCollection {

    private Integer tags = 0;
    private Integer categories = 0;
    private Integer authors = 0;
    private Integer articles16 = 0;
    private Integer articles15 = 0;
    private Integer articles14 = 0;
    private Integer articles13 = 0;
    private Integer articles12 = 0;
    private Integer articles11 = 0;
	
    public void addTag(){
    	tags++;
    }
    
    public void addCategory(){
    	categories++;
    }
    
    public void addAuthor(){
    	authors++;
    }
    
    public void addArticles11(){
    	articles11++;
    }
    
    public void addArticles12(){
    	articles12++;
    }
    
    public void addArticles13(){
    	articles13++;
    }
    
    public void addArticles14(){
    	articles14++;
    }
    
    public void addArticles15(){
    	articles15++;
    }
    
    public void addArticles16(){
    	articles16++;
    }
    
    public Integer getTags() {
		return tags;
	}
    
	public Integer getCategories() {
		return categories;
	}
	
	public Integer getAuthors() {
		return authors;
	}
	
	public Integer getArticles16() {
		return articles16;
	}
	
	public Integer getArticles15() {
		return articles15;
	}
	
	public Integer getArticles14() {
		return articles14;
	}
	
	public Integer getArticles13() {
		return articles13;
	}
	
	public Integer getArticles12() {
		return articles12;
	}
	
	public Integer getArticles11() {
		return articles11;
	}
}
