package sample.hello.resources;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import sample.hello.bean.Paper;
import sample.hello.storage.ContactStore;
import sample.hello.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class ContactResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String paper;
	
	public ContactResource(UriInfo uriInfo, Request request, String paper) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.paper = paper;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Paper getContact() {
		System.out.println("derek test --" + paper);
		Paper cont = ContactStore.getStore().get(paper);
		if(cont==null)
			throw new NotFoundException("No such Contact.");
		
		System.out.println("derek test --" + cont.getId());
		System.out.println("derek test --" + cont.getName());
		System.out.println("derek test --" + cont.getCategory());
		System.out.println("derek test --" + cont.getContent());
		return cont;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putContact(JAXBElement<Paper> jaxbContact) {
		Paper c = jaxbContact.getValue();
		return putAndGetResponse(c);
	}
	
	@PUT
	public Response putContact(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Paper c = new Paper(params.get("id"), params.get("name"), params.get("category"), params.get("content"));
		return putAndGetResponse(c); 
	}
	
	private Response putAndGetResponse(Paper c) {
		Response res;
		if(ContactStore.getStore().containsKey(c.getId())) {
			res = Response.noContent().build();
			ContactStore.update(c);
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			ContactStore.create(c);
		}
		
		return res;
	}
	
	@DELETE
	public void deleteContact() {
		ContactStore.delete(paper);
	}
}
