package jaxrs.multipart.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

//<artifactId>jersey-media-multipart</artifactId> needed
//update JAXRSConfig needed
@SuppressWarnings("unused")
@Path("file")
public class JaxRsFileUpload {
	
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFile(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition info) throws IOException {
		
		String fileName = info.getFileName();
		
		//Scanner sc = new Scanner(is);
		//String str = "File uploaded: "+fileName+"\nFirst line of uploaded file: "+sc.nextLine();
		//sc.close();
		
		String str = "File uploaded: "+fileName;
		System.out.println(str);
		
		saveFile(is, "d:/aa/"+fileName);
		
		return Response.ok(str).build();
	}
	
	
	private void saveFile(InputStream is, String fileLocation) {
		
		try (FileOutputStream out = new FileOutputStream(new File(fileLocation))) {  
			//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileLocation)));
            int bufferSize = 0;  
            byte[] buffer = new byte[4096];  
            while ((bufferSize = is.read(buffer)) != -1) {  
                out.write(buffer, 0, bufferSize);  
            }  
            out.flush();
            
        } catch (IOException e) {
        	e.printStackTrace();
        }  
	}

}
