package uwo.ca.jersey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("aliens")
public class AlienResource {
	
	AlienDAO dao = new AlienDAO();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("getAliens")
	public List<Alien> getAliens() {
		
		return dao.getAliens();
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("getAlien/{id}")
	public Alien getAlien(@PathParam("id") int id) {
		
		return dao.getAlien(id);
	}
	
	@POST
	@Path("addAlien")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a1) {
		System.out.println(a1);
		dao.createAlien(a1);
		return a1;
	}
	
	@PUT
	@Path("updateAlien")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a1) {
		System.out.println(a1);
		dao.createAlien(a1);
		return a1;
	}
	

}
