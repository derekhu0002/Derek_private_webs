package sample.hello.client;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import sample.hello.bean.Paper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class PaperTestCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c = Client.create();
		WebResource r = c.resource("http://localhost/Papers/rest/papers");
		System.out.println("PaperTestCase start... ...");
		
		String id = "1";
		String name = "1stpapaer";
		String category = "alltech";
		String content = "helloeveryone";
		int status = 0;
		
		status = postOnePaper(r,id,name,category,content);
		assert status == 200;

		Paper paper = getOnePaper(r,id);
		assert paper.getId().equals(id);
		assert paper.getName().equals(name);
		assert paper.getCategory().equals(category);
		assert paper.getContent().equals(content);
		
		name = "1modified1stpaper";
		putOnePaper(r,id,name,category,content);
		paper = getOnePaper(r,id);
		assert paper.getId().equals(id);
		assert paper.getName().equals(name);
		assert paper.getCategory().equals(category);
		assert paper.getContent().equals(content);
		
		category = "all-life";
		putOnePaper(r,id,name,category,content);
		paper = getOnePaper(r,id);
		assert paper.getId().equals(id);
		assert paper.getName().equals(name);
		assert paper.getCategory().equals(category);
		assert paper.getContent().equals(content);		
		
		content = "hey,i'm herettttt!";
		putOnePaper(r,id,name,category,content);
		paper = getOnePaper(r,id);
		assert paper.getId().equals(id);
		assert paper.getName().equals(name);
		assert paper.getCategory().equals(category);
		assert paper.getContent().equals(content);	
		
		status = deleteOnePaper(r,id);
		assert status == 204;
		
		status = postOnePaper(r,id,name,category,content);
		assert status == 200;
		
		status = deleteOnePaper(r,id);
		assert status == 204;
		
		id = "2";
		name = "derek";
		category = "allteck";
		content = "heyman,it's yours!";
		
		status = postOnePaper(r,id,name,category,content);
		assert status == 200;
		paper = getOnePaper(r,id);
		assert paper.getId().equals(id);
		assert paper.getName().equals(name);
		assert paper.getCategory().equals(category);
		assert paper.getContent().equals(content);	
		
		id = "3";
		name = "derek33333";
		category = "alllife3333333";
		content = "hehe koko3333333!";
		putOnePaper(r,id,name,category,content);
		paper = getOnePaper(r,id);
		assert paper.getId().equals(id);
		assert paper.getName().equals(name);
		assert paper.getCategory().equals(category);
		assert paper.getContent().equals(content);	
		
		List<Paper> papers = getContacts(r);
		assert papers.get(0).getId().equals("2");
		assert papers.get(0).getName().equals("derek");
		assert papers.get(0).getCategory().equals("allteck");
		
		assert papers.get(1).getId().equals("3");
		assert papers.get(1).getName().equals("derek33333");
		assert papers.get(1).getCategory().equals("alllife3333333");
		
		
		status = deleteOnePaper(r,"2");
		assert status == 204;
		status = deleteOnePaper(r,"3");
		assert status == 204;
		
		System.out.println("PaperTestCase finished!");
	}
	
	public static List<Paper> getContacts(WebResource r){
		GenericType<List<Paper>> genericType = new GenericType<List<Paper>>() {};
		List<Paper> papers = r.accept(MediaType.APPLICATION_XML).get(genericType);
		System.out.println("No. of Papers: " + papers.size());
		Paper paper = papers.get(0);
		System.out.println(paper.getId() + ": " + paper.getName());
		return papers;
	}
	
	public static int postOnePaper(WebResource r, String id, String name, String category, String content) {
		Form form = new Form();
		
		form.add("id", id);
		form.add("name", name);
		form.add("category", category);
		form.add("content", content);
		
		ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		
		System.out.println(response.getEntity(String.class));
		System.out.println(response.getStatus());
		return response.getStatus();
	}
	public static int putOnePaper(WebResource r, String id, String name, String category, String content) {
		Form form = new Form();
		
		form.add("id", id);
		form.add("name", name);
		form.add("category", category);
		form.add("content", content);
		
		ClientResponse response = r.path(id)
				                   .accept(MediaType.APPLICATION_XML)
								   .put(ClientResponse.class, form);

		System.out.println(response.getStatus());
		return response.getStatus();
	}

	public static Paper getOnePaper(WebResource r, String id) {
		GenericType<JAXBElement<Paper>> generic = new GenericType<JAXBElement<Paper>>() {};
		JAXBElement<Paper> jaxbContact = r.path(id).accept(MediaType.APPLICATION_XML).get(generic);
		Paper paper = jaxbContact.getValue();
		try {
			paper.setContent(URLDecoder.decode(paper.getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("derek--" + paper.getId() + ": " + paper.getName() + ": " + paper.getCategory() + ": " + paper.getContent());
		return paper;
	}

	public static int deleteOnePaper(WebResource r, String id) {
		ClientResponse response = r.path(id).delete(ClientResponse.class);
		System.out.println(response.getStatus());
		return response.getStatus();
	}
}
