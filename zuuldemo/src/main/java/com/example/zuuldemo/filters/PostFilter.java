package com.example.zuuldemo.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import ch.qos.logback.core.net.SyslogOutputStream;

public class PostFilter  extends ZuulFilter {
	  @Override
	  public String filterType() {
	    return "post";
	  }
	 
	  @Override
	  public int filterOrder() {
	    //return FilterConstants.SEND_RESPONSE_FILTER_ORDER ;
		  return 1 ;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
	 
	  @Override
	  public Object run() {
	   System.out.println("Inside Response Filter");
	   RequestContext ctx = RequestContext.getCurrentContext();
	  if(true) {
		  ZuulException zuulException = new ZuulException("User message", 500, "Error Details message");
	        throw new ZuulRuntimeException(zuulException);
	  }
	  try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
		   final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
		  System.out.println(responseData);
		   ctx.setResponseBody(responseData);
		} catch (IOException e) {
		 //  logger.warn("Error reading body",e);
            ReflectionUtils.rethrowRuntimeException(e);

		}
	  
/*	  if (HttpStatus.INTERNAL_SERVER_ERROR.value() == ctx.getResponse().getStatus()) {
          try {
              response.sendError(404, "Error Error"); //trying to change the response will need to throw a JSON body.
          } catch (final IOException e) {
              e.printStackTrace();
          } ;
	  */
	  
	/*   
	   try {	    	
  		 String url=UriComponentsBuilder.fromHttpUrl("http://localhost:4200/customer").build().toUriString();
  		 String usuario=ctx.getRequest().getHeader("usuario")==null?"":ctx.getRequest().getHeader("usuario");
  		 String password=ctx.getRequest().getHeader("clave")==null?"":ctx.getRequest().getHeader("clave");
  		 
  	     if (! usuario.equals(""))
  	     {
  	    	if (!usuario.equals("profesorp") || !password.equals("profe"))
  	    	{
	    	    	String msgError="Usuario y/o contraseña invalidos";
	    	    	strLog.append("\n"+msgError+"\n");	  
	    	    	ctx.setResponseBody(msgError);
	    	    	ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
	    	    	ctx.setSendZuulResponse(false); 
	    	    	log.info(strLog.toString());	    	    	
	    	    	return null;
  	    	}
  	    	
  	     }	    	 
  		 ctx.setRouteHost(new URL(url));
  	        ctx.set("requestURI", url);

		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	  List<String> t=new ArrayList<>();
	  t.add("4");
	   String redirectURL="http://localhost:4200/customer?x=5";
	  
	 
	   String[] s = {"Rr"};
		 ctx.getRequest().getParameterMap().put("x", s);
	
	   
	   
	   ctx.setSendZuulResponse(false);
       ctx.put("FORWARD_TO_KEY", redirectURL);
       ctx.setResponseStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT);
       try {
		ctx.getResponse().sendRedirect(redirectURL);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
	  }
}
