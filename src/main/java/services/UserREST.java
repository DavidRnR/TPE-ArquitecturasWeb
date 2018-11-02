package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import login.Secured;
import entities.User;


@Path("/user")
public class UserREST {
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUser(){
		return UserDAO.getInstance().findAll(EmfREST.createEntityManager());
	}
	
	@GET
	@Secured
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		User user = UserDAO.getInstance().findById(id,EmfREST.createEntityManager());
		if(user!=null)
			return user;
		else
			throw new notFound(id);
	}
	
	public class notFound extends WebApplicationException {
	     public notFound(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("User with id "+id+" not found").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
