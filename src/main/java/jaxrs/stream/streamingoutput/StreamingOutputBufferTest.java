package jaxrs.stream.streamingoutput;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

//https://stackoverflow.com/questions/29637151/jersey-streamingoutput-as-response-entity
//When jersey.config.server.contentLength.buffer in JAXRSConfig is set to 0 (no buffering), every line is automatically flushed
//use curl http://localhost:8080/TestWeb/res/streamingOutput/buffer
@Path("streamingOutput")
public class StreamingOutputBufferTest {
	
    @GET
    @Path("buffer")
    //@Produces("application/octet-stream")
    @Produces("text/plain")
    public Response getStream() {
        return Response.ok(new FeedReturnStreamingOutput()).build();
    }

    public static class FeedReturnStreamingOutput implements StreamingOutput {

        @Override
        public void write(OutputStream output)
                throws IOException, WebApplicationException {
            try {
                for (int i = 0; i < 10; i++) {
                    output.write(String.format("Hello %d\n", i).getBytes());
                    output.flush();
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException e) {  throw new RuntimeException(e); }
        }
    }
}
