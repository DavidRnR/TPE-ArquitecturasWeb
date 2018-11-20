package services;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.node.ObjectNode;

import entities.User;
import entities.Work;
import services.UserREST.notFound;


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

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, Work work) {
		Work result = WorkDAO.getInstance().update(id, work);
		if(result!=null) return Response.status(201).entity(result).build();
		throw new notFound(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteWork(@PathParam("id") Integer id) {
		boolean result= WorkDAO.getInstance().delete(id);
		if(result) return Response.status(201).build();
		
		throw new notFound(id);
	}
	
	@GET
	@Path("/getWorksbyAutor/{dni}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Work> getWorksByAutor(@PathParam("dni") String msg) {
		long dni = Integer.valueOf(msg);
		return CacicService.getWorksByAuthor(dni);
	}
	
	@GET
	@Path("/getWorksReviewdByEvaluador/{dni}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Work> getWorksReviewdByEvaluador(@PathParam("dni") String msg, @QueryParam("start_date") String start, @QueryParam("end_date") String end) {
		long dni = Integer.valueOf(msg);
		Date startDate = new Date(start);
		System.out.println(start);
		Date endDate = new Date(end);
		return CacicService.getWorksByEvaluadorRangeDate(dni, startDate, endDate);
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
