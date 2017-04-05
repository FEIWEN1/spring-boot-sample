package com.spring.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * ClassName:AccessFilter <br/>
 * Title:
 * <p>
 * 自定义过滤器的实现 
 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型 
 * pre：可以在请求被路由之前调用
 * routing：在路由请求时候被调用 
 * post：在routing和error过滤器之后被调用 
 * error：处理请求时发生错误时被调用
 * filterOrder：通过int值来定义过滤器的执行顺序 
 * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行
 * run：过滤器的具体逻辑
 * </p>
 * <br/>
 * Date: 2017年4月5日 下午4:26:23 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public class AccessFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		LOGGER.info(String.format("%s request to %s", request.getMethod(),
				request.getRequestURL().toString()));
		Object accessToken = request.getParameter("accessToken");
		if (accessToken == null) {
			LOGGER.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("Unauthorized");
			return null;
		}
		LOGGER.info("access token ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
