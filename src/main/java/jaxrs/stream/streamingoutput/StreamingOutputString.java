package jaxrs.stream.streamingoutput;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

//Content-Disposition: inline								//to read in browser
//Content-Disposition: attachment							//to download
//Content-Disposition: attachment; filename="filename.ext"	//Otherwise file name is @Path's value

//https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/StreamingOutput.html
//-A type that may be used as a resource method return value or as the entity in a Response when the application wishes to stream the output.
// This is a lightweight alternative to a MessageBodyWriter.
@Path("streamingOutput")
public class StreamingOutputString {
	
	@GET
	@Path("string")
	//@Produces(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response streamingOut2() {
		
		StreamingOutput stream = new StreamingOutput() {
		    @Override
		    public void write(OutputStream os) throws IOException, WebApplicationException {
		    	
		    	Writer writer = new BufferedWriter(new OutputStreamWriter(os));
		    	writer.write("Writing String to StreamingOutput");
		    	writer.flush();
		    }
		};
		
		return Response.ok(stream)
			.header("Content-Disposition", "inline")
			.build();
	}
}
