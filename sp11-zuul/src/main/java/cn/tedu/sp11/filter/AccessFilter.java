package cn.tedu.sp11.filter;

import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {

    /**
     * 设置过滤器类型: pre , routing , post , error
     * @return
     */
    @Override
    public String filterType() {
//        return "pre";
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 设置过滤器添加的位置顺序号
     * @return
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 判断对当前请求,是否要执行过滤代码
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String o = (String)ctx.get(FilterConstants.SERVICE_ID_KEY);
        return "item-service".equals(o);
    }

    /**
     * 过滤代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getParameter("token");

        if(StringUtils.isBlank(token)){
            ctx.setSendZuulResponse(false);
            String json = JsonResult.err().code(JsonResult.NOT_LOGIN).msg("没有登录").toString();
            ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
            ctx.setResponseBody(json);
        }

        return null;
    }
}
