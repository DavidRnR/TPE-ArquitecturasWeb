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

import entities.Work;


@Path("/work")
public class WorkREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Work> getAllKeyWords(){
		return WorkDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Work getWorkById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Work work = WorkDAO.getInstance().findById(id);
		if( work != null )
			return work;
		else
			throw new notFound(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWork(Work work) {
		Work result = WorkDAO.getInstance().persist(work);
		if(result==null) {
			throw new ResourseNotCreated(work.getId());
		}else {
			return Response.status(201).entity(work).build();
		}
	}

	public class notFound extends WebApplicationException {
		public notFound(long id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("Work with id "+ id +" not found").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class ResourseNotCreated extends WebApplicationException {
		public ResourseNotCreated(long id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("Resourse with id "+ id +" could not created").type(MediaType.TEXT_PLAIN).build());
		}
	}
}
