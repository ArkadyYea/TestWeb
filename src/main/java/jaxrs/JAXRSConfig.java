package jaxrs;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import jaxrs.multipart.file.JaxRsFileUpload;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

@SuppressWarnings("unused")
@ApplicationPath("res")
public class JAXRSConfig extends Application {

	public JAXRSConfig() {}

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(MultiPartFeature.class);
        
        //Woring with multipart/file
        //Need to add this OR properties below! Otherwise Exception: No injection source found for a parameter of type public javax.ws.rs.core.Response ...
        //No, need this below to work (if package is not listed, it is NOT REACHABLE (404))
        //resources.add(JaxRsFileUpload.class);

        // Add additional features such as support for Multipart.
        resources.add(MultiPartFeature.class);

        return resources;
    }
	
	//If we set jaxrs, fe, it works for jaxrs.file too
	//We can weparate packages with ','.
	@Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "jaxrs, security");
        return properties;
    }
}
