package jaxrs.multipart;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

import abc.User;

//<artifactId>jersey-media-multipart</artifactId> and update of JAXRSConfig needed

//-MultiPart class (or it's subclasses) can be used as an entry point to using jersey-media-multipart module on the client side. 
// This class represents a MIME multipart message and is able to hold an arbitrary number of BodyParts.
//-Default media type is multipart/mixed for MultiPart entity and text/plain forBodyPart. 

//-When working with forms (e.g. media type multipart/form-data) and various fields in them, there is a more convenient class to be used - FormDataMultiPart.
// It automatically sets the media type for the FormDataMultiPart entity to multipart/form-data and Content-Disposition header to FormDataBodyPart body parts.

//-@FormDataParam annotation - This annotation in conjunction with the media type multipart/form-data
// should be used for submitting and consuming forms that contain files, non-ASCII data, and binary data
//-The type of the annotated parameter can be one of the following (for more detailed description see javadoc to @FormDataParam):
// • FormDataBodyPart - The value of the parameter will be the first named body part or null if such a named body part is not present.
// • A List or Collection of FormDataBodyPart. The value of the parameter will one or more named body parts with the same name or null if such a named body part is not present.
// • FormDataContentDisposition - The value of the parameter will be the content disposition of the first named body part part or null if such a named body part is not present.
// • A List or Collection of FormDataContentDisposition. The value of the parameter will one or more content dispositions of the named body parts with the same name or null if such a named body part is not present.
// • A type for which a message body reader is available given the media type of the first named body part. The value of the parameter will be the result of reading using the message body reader given the type T, the media type of the named part, and the bytes of the named body part as input.
@SuppressWarnings("unused")
@Path("multipart")
public class JaxRsFormDataMultipart {
	

	@POST
	@Path("FormDataMultiPart")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postForm2(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition info,
			@DefaultValue("true") @FormDataParam("enabled") boolean enabled, @FormDataParam("user") User u) {

		
		String str = "Enabled: "+enabled+", file name: "+info.getFileName()+", user: "+u;
		
		return Response.ok(str).build();
	}
	
	
	
	@GET
	@Path("FormDataMultiPart")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response postForm3() {
		
		FormDataMultiPart multipart = new FormDataMultiPart();
		//FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", new File("d:/download/alice30.txt"));
	    FormDataBodyPart formDataBodyPart = new FormDataBodyPart("user", new User("John","Wayne"), MediaType.APPLICATION_JSON_TYPE);
	    String fileName = "test.txt";
        InputStream is = getClass().getResourceAsStream("/"+fileName);
        StreamDataBodyPart strmDataBodyPart = new StreamDataBodyPart("file", is, fileName, MediaType.APPLICATION_OCTET_STREAM_TYPE);
	    
	    multipart.field("enabled", "false");
	    //multipart.bodyPart(fileDataBodyPart);
	    multipart.bodyPart(formDataBodyPart);
	    multipart.bodyPart(strmDataBodyPart);
	    
		return Response.ok(multipart).build();
	}
	
}
