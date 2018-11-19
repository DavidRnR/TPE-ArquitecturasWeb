package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Role;


@Path("/role")
public class RoleREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Role> getAllKeyWords(){
		return RoleDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Role getRoleById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Role role = RoleDAO.getInstance().findById(id);
		if( role != null )
			return role;
		else
			throw new notFound(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRole(Role role) {
		Role result = RoleDAO.getInstance().persist(role);
		if(result==null) {
			throw new ResourseNotCreated(role.getId());
		}else {
			return Response.status(201).entity(role).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRole(@PathParam("id") int id) {
		boolean result= RoleDAO.getInstance().delete(id);
		if(result) return Response.status(201).build();
		
		throw new notFound(id);
	}

	public class notFound extends WebApplicationException {
		public notFound(int id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("Role with id "+ id +" not found").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class ResourseNotCreated extends WebApplicationException {
		public ResourseNotCreated(int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("Resourse with id "+ id +" could not created").type(MediaType.TEXT_PLAIN).build());
		}
	}
}
