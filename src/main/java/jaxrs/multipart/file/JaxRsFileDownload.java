package jaxrs.multipart.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.apache.poi.xwpf.converter.xhtml.DefaultContentHandlerFactory;
//import org.apache.poi.xwpf.converter.xhtml.IContentHandlerFactory;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
//import org.apache.poi.xwpf.converter.xhtml.internal.XHTMLMapper;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;

//Content-Disposition: inline								//to read in browser
//Content-Disposition: attachment							//to download
//Content-Disposition: attachment; filename="filename.ext"	//Otherwise file name is @Path's value
@SuppressWarnings("unused")
@Path("file")
public class JaxRsFileDownload {
	
	//Files are in src/main/resources (WWEB-INF/classes/ in .war)
	
	@GET
	@Path("readTxt")
	@Produces(MediaType.TEXT_PLAIN)
	public Response readTxt() {
		String name = "alice.txt";
		InputStream is = getClass().getResourceAsStream("/"+name);
		
		return Response.ok(is)
			.header("Content-Disposition", "inline")						//Displays in a browser
			//.header("Content-Disposition", "attachment; filename="+name)	//Downloads file
			.build();
	}
	
	@GET
	@Path("readTxt2")
	@Produces(MediaType.TEXT_PLAIN)
	public Response readTxt2() {
		String name = "alice.txt";
		InputStream is = getClass().getResourceAsStream("/"+name);
		
		return Response.ok(is)
			//.header("Content-Disposition", "inline")						//Displays in a browser
			.header("Content-Disposition", "attachment; filename="+name)	//Downloads file
			.build();
	}
	
	@GET
	@Path("readTxt3")
	@Produces(MediaType.TEXT_PLAIN)
	public Response readTxt3() {
		String name = "alice30.txt";
		
		File file = new File("d:/download/"+name);
		
		return Response.ok(file)
			.header("Content-Disposition", "inline")
			//.header("Content-Disposition", "attachment; filename="+name)
			.build();
		
	}
	
	
	@GET
	@Path("readPdf")
	@Produces("application/pdf")
	public Response readPdf() {
		String name = "pdfExample.pdf";
		InputStream is = this.getClass().getResourceAsStream("/"+name);
		
		return Response.ok(is)
			.header("Content-Disposition", "inline")						//Displays in a browser
			//.header("Content-Disposition", "attachment; filename="+name)	//Downloads file
			.build();
	}
	
	
	@GET
	@Path("readWord")
	@Produces("application/msword")
	public Response readWord() {
		String name = "wordExample.doc";
		
		InputStream is = JaxRsFileDownload.class.getResourceAsStream("/"+name);
		
		return Response.ok(is)
			.header("Content-Disposition", "attachment; filename="+name)
			.build();
	}
	
	
	@GET
	@Path("readWord2")
	@Produces("application/msword")
	public Response readWord2() {
		String name = "/wordExample.doc";
		
		String externalForm = getClass().getResource(name).toExternalForm();
		//URI uri = getClass().getResource(name).toURI();					//There is an exception to handle.
		//URI uri = new URI(externalForm);									//There is an exception to handle.
		File file = new File(URI.create(externalForm));						//No exception to handle this way.
		
		return Response.ok(file)
			.header("Content-Disposition", "inline")						//Does not work in Chroem/Firefox/Opera
			//.header("Content-Disposition", "attachment; filename="+name)
			.build();
		
	}
	
	
	//https://www.infoq.com/articles/convert-microsoft-word-to-html
	@GET
	@Path("readWord3")
	@Produces(MediaType.TEXT_HTML)
	public Response readWord3() throws Exception {
		String name = "/wordExample.doc";
		
		InputStream is = getClass().getResourceAsStream(name);
		
//		XWPFDocument document = new XWPFDocument(is);
//		// Create options. Options are used to control image rendering etc.,
//		XHTMLOptions options = XHTMLOptions.create();
//		// Create outputstream to store generated HTML source
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		IContentHandlerFactory factory = DefaultContentHandlerFactory.INSTANCE;
//		options.setIgnoreStylesIfUnused(false);
//		XHTMLMapper mapper = new XHTMLMapper (document, factory.create(out, null, options), options);
//		mapper.start();
		
//		String str = new String(out.toByteArray());
		
		String str = "Uncomment pom libraries to get it working.";
		return Response.ok(str)
			.header("Content-Disposition", "inline")						//Does not work in Chroem/Firefox/Opera
			//.header("Content-Disposition", "attachment; filename="+name)
			.build();
		
	}
}
