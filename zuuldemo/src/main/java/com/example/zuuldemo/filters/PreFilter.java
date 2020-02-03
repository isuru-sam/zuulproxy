package com.example.zuuldemo.filters;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {
	  @Override
	  public String filterType() {
	    return "pre";
	  }
	 
	  @Override
	  public int filterOrder() {
	 //   return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1; 
		  return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return RequestContext.getCurrentContext().getRequest().getRequestURI().startsWith("/books");
	  }
	  /**
	   *     Map<String, List<String>> params = ctx.getRequestQueryParams();
    if (params == null) {
        params = Maps.newHashMap();
    }
    for (String key : checkMap.keySet()) {
        params.put(key, Lists.newArrayList(checkMap.get(key).toString()));
    }
    ctx.setRequestQueryParams(params);
	   */
	 
	  @Override
	  public Object run() {
		  RequestContext ctx = RequestContext.getCurrentContext();	    
		     StringBuffer strLog=new StringBuffer();
		     strLog.append("\n------ FILTRANDO ACCESO A PRIVADO - PREREWRITE FILTER  ------\n");	    
		     Map<String, List> qps = new HashMap<String, List>();
			 //copy request param map
			 Map<String, String[]> qpmap = ctx.getRequest().getParameterMap();
			 for (Map.Entry<String, String[]> entry : qpmap.entrySet()) {
			 String key = entry.getKey();
			 String[] values = entry.getValue();
			 qps.put(key, Arrays.asList(values));
			 }
			 
		     /*try {	    	
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
		    
		    // log.info(strLog.toString());
		     return null;
	  }
}
