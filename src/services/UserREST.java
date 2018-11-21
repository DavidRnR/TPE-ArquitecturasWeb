package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.node.ObjectNode;

import entities.User;
import entities.Work;
import services.RoleREST.notFound;


@Path("/user")
public class UserREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllKeyWords(){
		return UserDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getWorkById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		User user = UserDAO.getInstance().findById(id);
		if( user != null )
			return user;
		else
			throw new notFound(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWork(User user) {
		User result = UserDAO.getInstance().persist(user);
		if(result==null) {
			throw new ResourseNotCreated(user.getId());
		}else {
			return Response.status(201).entity(user).build();
		}
	}

	@POST
	@Path("/assingWorkRev/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assingWorkRev(@PathParam("id") String msg, ObjectNode json) {
		int id = Integer.valueOf(msg);
		User user = UserDAO.getInstance().findById(id);

		if(user == null) {
			throw new ResourseNotCreated(id);
		}else {
			Work work = WorkDAO.getInstance().findById(json.get("work_id").asInt());

			if(user.addWorkRev(work)) {
				User result = UserDAO.getInstance().update(user.getId(), user);	

				if(result == null) {
					throw new ResourseNotCreated(id);
				}
				else {
					return Response.status(201).entity(result).build();
				}
			}
			else {
				throw new WorkNotAssigned(id, json.get("work_id").asInt());
			}

		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, User user) {
		User result = UserDAO.getInstance().updateREST(id, user);
		if(result!=null) return Response.status(201).entity(result).build();
		throw new notFound(id);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") Integer id) {
		boolean result= UserDAO.getInstance().delete(id);
		if(result) return Response.status(201).build();

		throw new notFound(id);
	}

	public class notFound extends WebApplicationException {
		public notFound(int id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("User with id "+ id +" not found").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class ResourseNotCreated extends WebApplicationException {
		public ResourseNotCreated(int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("Resourse with id "+ id +" could not created").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class WorkNotAssigned extends WebApplicationException {
		public WorkNotAssigned(int idUser, int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("Work with id "+ id +" could not assigned to be review to user with id "+ idUser ).type(MediaType.TEXT_PLAIN).build());
		}
	}
}