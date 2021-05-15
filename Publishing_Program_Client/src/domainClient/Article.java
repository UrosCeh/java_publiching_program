/*
 * To change this license title, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainClient;

/**
 *
 * @author hatch
 */
public class Article {
    private int articleID;
    private String title;
    private String body;
    private String writer;

    public Article(int articleID, String title, String body) {
        this.articleID = articleID;
        this.title = title;
        this.body = body;
    }

    public Article() {
    }

    
    
    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return articleID + " " + title + " " + body;
    }
    
    
    
}
