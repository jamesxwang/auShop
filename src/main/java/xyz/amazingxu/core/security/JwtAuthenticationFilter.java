package xyz.amazingxu.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import xyz.amazingxu.core.security.model.SimpleAuthority;
import xyz.amazingxu.core.utils.SystemVars;
import xyz.amazingxu.core.utils.WebResults;
import xyz.amazingxu.core.utils.WebUtils;
import xyz.amazingxu.auShop.dto.UserContextDTO;
import xyz.amazingxu.auShop.service.IUserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * jwt的验证过滤器
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/4/26 16:05
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private IUserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, IUserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        WebUtils webUtils = new WebUtils();

        String header = request.getHeader("Authorization");
        if ((header != null) && header.startsWith(SystemVars.AUTHENTICATION_HEAD)) {
            //当token和原有的一致则不重新生成token，以减少开销
            String token = request.getHeader("Authorization");
            UsernamePasswordAuthenticationToken authentication = webUtils.getAuthentication(token);
            if (authentication != null) {
                //用户信息存储在session中
                SecurityContextHolder.getContext().setAuthentication(authentication);
                AntPathRequestMatcher matcher;
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    SimpleAuthority sa = (SimpleAuthority)ga;
                    String url = sa.getUrl();
                    matcher = new AntPathRequestMatcher(url);
                    if (matcher.matches(request)) {
                        if("0".equals(sa.getType())){
                            sendMessage(response,"抱歉,您无权访问资源"+request.getRequestURI().toString());
                            return;
                        }
                    }
                }
                chain.doFilter(request, response);
            }else{
                sendMessage(response,"身份验证失败!");
                return;
            }
        }else{
            chain.doFilter(request, response);
        }
    }


    private void sendMessage(HttpServletResponse response,String message) throws IOException{
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setDateHeader("Expires", 0);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        WebResults result = WebUtils.error(-1,message);
        response.getWriter().write(WebUtils.getString(result));
    }
}
