package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.KeyWord;


@Path("/keyword")
public class KeyWordREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<KeyWord> getAllKeyWords(){
		return KeyWordDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public KeyWord getKeyWordById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		KeyWord keyWord = KeyWordDAO.getInstance().findById(id);
		if( keyWord != null )
			return keyWord;
		else
			throw new notFound(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createKeyWord(KeyWord keyWord) {
		KeyWord result = KeyWordDAO.getInstance().persist(keyWord);
		if(result==null) {
			throw new ResourseNotCreated(keyWord.getId());
		}else {
			return Response.status(201).entity(keyWord).build();
		}
	}

	public class notFound extends WebApplicationException {
		public notFound(int id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("Keyword with id "+ id +" not found").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class ResourseNotCreated extends WebApplicationException {
		public ResourseNotCreated(int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("Resourse with id "+ id +" could not created").type(MediaType.TEXT_PLAIN).build());
		}
	}
}
