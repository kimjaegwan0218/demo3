package com.example.demo3.filter;

import com.example.demo3.dto.MemberDTO;
import com.example.demo3.service.MemberService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        log.info("Login check filter............");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginInfo")==null){

            Cookie cookie = findCookie(req.getCookies(),"remember-me");

            if(cookie != null){
                log.info("cookie는 존재하는 상황");
                String uuid = cookie.getValue();

                try{
                    MemberDTO memberDTO = MemberService.instance.getByUUID(uuid);

                    log.info("쿠키긔 앖으로 조회한 사용자 정보: "+memberDTO);
                    session.setAttribute("loginInfo",memberDTO);
                }catch(Exception e){
                    e.printStackTrace();
                }
                chain.doFilter(request,response);
                return;
            }
            resp.sendRedirect("/login");
            return;
        }

        chain.doFilter(request,response);
    }

    private Cookie findCookie(Cookie[] cookies, String name){
        if(cookies ==null || cookies.length ==0){
            return null;
        }
        Optional<Cookie> result = Arrays.stream(cookies).filter(ck->ck.getName().equals(name)).findFirst();

        return result.isPresent()?result.get():null;
    }
}
