package cn.cy.moneysys.interceptor;

import cn.cy.moneysys.entity.User;
import cn.cy.moneysys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class rootInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            HttpSession session = request.getSession();
            String uid = (String)session.getAttribute("uid");
            User user = userService.selectUserById(uid);
            if(user != null){
                return true;
            }else{
                response.setStatus(403);
                return false;
            }
        }catch (Exception e){
            response.setStatus(403);
            return false;
        }
    }
}
