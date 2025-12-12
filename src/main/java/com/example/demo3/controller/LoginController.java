package com.example.demo3.controller;

import com.example.demo3.dto.MemberDTO;
import com.example.demo3.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        log.info("login get.................");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        log.info("login post.................");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String auto = req.getParameter("auto");
        boolean remeberMe = auto != null && auto.equals("on");

        log.info("--------------------------");
        log.info(remeberMe);

        try{
            MemberDTO memberDTO = MemberService.instance.login(mid,mpw);

            if(remeberMe){
                String uuid = UUID.randomUUID().toString();

                MemberService.instance.updateUuid(mid,uuid);
                memberDTO.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me",uuid);
                rememberCookie.setMaxAge(60*60*24*7);
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);
            }
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo",memberDTO);
            resp.sendRedirect("/todo/list");
        }catch(Exception e){
            resp.sendRedirect("/login?result=error");
        }

    }
}
