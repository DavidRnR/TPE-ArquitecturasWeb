package login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import entities.User;
import services.EmfREST;
import services.UserDAO;

@Path("/autenticacion")
public class AuthService {
  	    @POST
	    @Produces("application/json")
	    @Consumes("application/json")
	    public Response autenticarUser(Credencial credentials){
  	      String email = credentials.getUserEmail();
	      String password = credentials.getPassword();
	      
	      try {
     
	            auth(email, password);
         
	            String token = emitToken(email);
         
	            return Response.ok(token).build();

	        } catch (Exception e) {
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        }

  	    }
  	    
	    private void auth (String email, String password) throws Exception{
	    	  User user = new User();
			     user.setEmail(email);
			     user.setPassword(password);
			     user = UserDAO.getInstance().login(user, EmfREST.createEntityManager());
			     if(user == null){
			    	 throw new RuntimeException();
			     }

	    }
	    
	    private String emitToken(String email){
	        String token = TokenHelper.generarToken(email);
	    	TokenHelper.setToken(token, email);
	    	return token;

	    }	

}