package jaxrs.multipart;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;

import abc.User;

//<artifactId>jersey-media-multipart</artifactId> and update of JAXRSConfig needed

//-MultiPart class (or it's subclasses) can be used as an entry point to using jersey-media-multipart module on the client side. 
// This class represents a MIME multipart message and is able to hold an arbitrary number of BodyParts.
//-Default media type is multipart/mixed for MultiPart entity and text/plain forBodyPart. 

@Path("multipart")
public class JaxRsMultipart {
	
	@GET
	@Path("MultiPart")
	@Produces("multipart/mixed")
	public Response postForm1() {
		
		MultiPart multiPartEntity = new MultiPart();
		multiPartEntity.bodyPart(new BodyPart().entity("hello"));
        multiPartEntity.bodyPart(new BodyPart(new User("John","Xmlsky"), MediaType.APPLICATION_XML_TYPE));
        multiPartEntity.bodyPart(new BodyPart(new User("John","Jonson"), MediaType.APPLICATION_JSON_TYPE));
		
		return Response.ok(multiPartEntity).build();
	}
	
	
	@POST
	@Path("MultiPart")
	@Consumes("multipart/mixed")
	public Response postForm2(MultiPart multiPartEntity) {
		
        List<BodyPart> bodyParts = multiPartEntity.getBodyParts();
        
        StringBuilder sb = new StringBuilder();
        
        for(BodyPart bp : bodyParts) {
        	switch (bp.getMediaType().toString()) {
				case "text/plain" : sb.append("\ntext/plain: "+bp.getEntityAs(String.class)); break;
				case "application/xml" : sb.append("\napplication/xml: "+bp.getEntityAs(User.class)); break;
				case "application/json" : sb.append("\napplication/json: "+bp.getEntityAs(User.class)); break;
				default: break;
			}
        }
		return Response.ok(sb.toString()).build();
	}
	
	
}
