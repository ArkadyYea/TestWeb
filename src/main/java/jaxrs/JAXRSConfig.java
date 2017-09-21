package jaxrs;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import jaxrs.filters.ContainerRequestFilterTest;
import jaxrs.filters.ContainerResponseFilterTest;
import jaxrs.multipart.file.JaxRsFileUpload;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

@SuppressWarnings("unused")
@ApplicationPath("res")
public class JAXRSConfig extends Application {

	public JAXRSConfig() {}

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();

        //Adding additional features. Here working with multipart/file
        resources.add(MultiPartFeature.class);
        
        //Filter declared with @Provider or here work for ALL resources! If you want only for particular ones use DynamicFeature
        //Container filters need to use the @Provider annotation
        //Or you need to make sure the class is declared in the JAX-RS Application subclass if you dont want to rely on automatic discovery.
//        resources.add(ContainerRequestFilterTest.class);
//        resources.add(ContainerResponseFilterTest.class);
        
        
        return resources;
    }
	
	
	//-If we override getClasses() or getSingletons() of Application class (fe to use multipart) we HAVE TO add ALL classes manually in these methods
	// or use getProperties() with properties.put("jersey.config.server.provider.packages", "jaxrs, security, more packages");
	// and put all the packages there (coma or semicolon separated). By default, Jersey will recursively scan the sub-packages as well. 
	 
    //-If package is not listed, it is NOT REACHABLE (404)
	@Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "jaxrs, security");
        return properties;
    }
}
