package services;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import entities.Category;
import entities.KeyWord;
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
	public Response createWork(ObjectNode workJson) throws ParseException, JsonProcessingException, IOException {
		Work work = new Work();
		work.setName(workJson.get("name").asText());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate localDate = LocalDate.parse(workJson.get("created").asText(), formatter);
		Date workFinalDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		work.setCreated(workFinalDate);
		
		Category cat = CategoryDAO.getInstance().findById(workJson.get("category_id").asInt());
		work.setCategory(cat);
		
		List<Integer> keywordsId = new ObjectMapper().convertValue(workJson.get("keyWords"), ArrayList.class);
		List<KeyWord> keyWords = new ArrayList<KeyWord>();
		
		for (Integer id : keywordsId) {
			keyWords.add(KeyWordDAO.getInstance().findById(id));
		}
		work.setKeyWords(keyWords);
		
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
		Work result = WorkDAO.getInstance().updateREST(id, work);
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
	@Path("/getWorksbyAutor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Work> getWorksByAutor(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		return CacicService.getWorksByAuthor(id);
	}
	
	@GET
	@Path("/getWorksReviewedByEvaluador/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Work> getWorksReviewdByEvaluador(@PathParam("id") String msg, @QueryParam("start_date") String start, @QueryParam("end_date") String end) {
		int id = Integer.valueOf(msg);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate localDate = LocalDate.parse(start, formatter);
		Date startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		LocalDate localDateEnd = LocalDate.parse(end, formatter);
		Date endDate = Date.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return CacicService.getWorksByEvaluadorRangeDate(id, startDate, endDate);
	}
	
	@PUT
	@Path("/setWorkAsReviewed/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setWorkAsReviewed(@PathParam("id") String msg) throws ParseException, JsonProcessingException, IOException {
		int id = Integer.valueOf(msg);
		Work work = WorkDAO.getInstance().findById(id);
		work.setReviewed(new Date());
		Work result = WorkDAO.getInstance().updateREST(id, work);
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
