package jaxrs.stuff;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@WebFilter(filterName = "TimeOfDayFilter", urlPatterns = {"/*"}, initParams = {  @WebInitParam(name = "mood", value = "awake")} , asyncSupported=true)
public class ServletFilterTest implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filters init()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filters doFilter()");
		//Abc2 newResponse = new Abc2((HttpServletResponse)response);
		HttpServletResponseWrapper sw = new HttpServletResponseWrapper((HttpServletResponse) response) {
	        public void setHeader(String name, String value) {
	            if (!name.equalsIgnoreCase("Authorization")) {
	                super.setHeader(name, value);
	            }
	        }
	    };
		chain.doFilter(request, sw);
	}

	@Override
	public void destroy() {
		System.out.println("Filters destroy()");
	}

	

	

}


class Abc extends HttpServletRequestWrapper {

	public Abc(HttpServletRequest request) {
		super(request);
	}
}
class Abc2 extends HttpServletResponseWrapper {

	public Abc2(HttpServletResponse response) {
		super(response);
		super.setHeader("Authorization", "abc");
		
		Collection<String> headerNames = super.getHeaderNames();
		for (String h :headerNames ) {
			System.out.println(h+": "+super.getHeader(h));
		}
	}

}
