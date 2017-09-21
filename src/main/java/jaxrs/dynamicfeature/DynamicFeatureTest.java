package jaxrs.dynamicfeature;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import jaxrs.filters.ContainerRequestFilterTest;
import jaxrs.filters.ContainerRequestFilterTwo;
import jaxrs.filters.ContainerResponseFilterTest;
import jaxrs.interceptors.MyReaderInterceptor;
import jaxrs.interceptors.MyWriterInterceptor;

//Works only fo -> http://localhost:8080/TestWeb/res/test -> getTest()

//-A JAX-RS meta-provider for dynamic registration of post-matching providers during a JAX-RS application setup at deployment time.
//-It is used by JAX-RS runtime to register providers that shall be applied to a PARTICULAR resource class and method
// and overrides any annotation-based binding definitions defined on any registered resource filter or interceptor instance. 
//-Providers implementing this interface MAY be annotated with @Provider in order to be discovered by JAX-RS runtime when scanning for resources and providers.
//-This provider types is supported only as part of the Server API. 

//@Provider - Marks an implementation of an extension interface that should be discoverable by JAX-RS runtime during a provider scanning phase.
@Provider	//I changed Eclipse Error to Warning (Provider must implement at least one of the interfaces  ...)
public class DynamicFeatureTest implements DynamicFeature {
    
	private ContainerRequestFilterTest crq = new ContainerRequestFilterTest();
	private ContainerRequestFilterTwo crq2 = new ContainerRequestFilterTwo();
	private ContainerResponseFilterTest crp = new ContainerResponseFilterTest();
	
	private MyWriterInterceptor int1 = new MyWriterInterceptor();
	private MyReaderInterceptor int2 = new MyReaderInterceptor();
	
	public void configure(ResourceInfo ri, FeatureContext ctx) {
    	
    	//intercepts all resources within DynamicResources.class
    	if(ri.getResourceClass().getName().equals(DynamicResources.class.getName())) {
        	ctx.register(crq);
        	ctx.register(crq2);
            ctx.register(crp);
          	
            ctx.register(int1);
          	ctx.register(int2);
    	}
    	
    	//intercepts 2 methods from javax.custom.entity.format.CustomResources.class
        if(ri.getResourceMethod().getName().equals("customGet") || ri.getResourceMethod().getName().equals("customPost")){
          	ctx.register(int1);
          	ctx.register(int2);
        }
    }
}
