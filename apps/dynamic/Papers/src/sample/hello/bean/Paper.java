package sample.hello.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paper {
	private String id;
	private String name;
	private String category;
	private String content;
	
	public Paper(){}
	
	public Paper(String id,String name,String category,String content){
		this.id = id;
		this.name = name;
		this.category = category;
		this.content = content;
	}
	@XmlElement(name="id")
	public String getId(){
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name="name")
	public String getName(){
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="category")
	public String getCategory(){
		return category;
	}	
	public void setCategory(String category) {
		this.category = category;
	}
	@XmlElement(name="content")
	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
