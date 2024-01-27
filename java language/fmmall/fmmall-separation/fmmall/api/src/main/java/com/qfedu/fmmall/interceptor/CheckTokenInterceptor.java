package com.qfedu.fmmall.interceptor;

import com.google.gson.Gson;
import com.qfedu.fmmall.utils.TokenUtils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        //Release pre-inspection request.
        if ("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }

        String token = request.getHeader("token");
        if (token == null) {
            //If the token is empty, please log in first.
            doResponse(response, new ResultVO(ResStatus.AUTH_FAIL_NOT, "请先登录！", ""));
        } else {
            try {
                TokenUtils.parseClaim(token);
                return true;
            } catch (ExpiredJwtException e) {
                doResponse(response, new ResultVO(ResStatus.AUTH_FAIL_OVERDUE, "登录过期，请重新登录！", ""));
            } catch (SignatureException e) {
                doResponse(response, new ResultVO(ResStatus.AUTH_FAIL_ERROR, "token不合法，已记录ip地址！", ""));
            } catch (Exception e) {
                doResponse(response, new ResultVO(ResStatus.AUTH_FAIL_NOT, "请先登录！", ""));
            }
        }
        return false;
    }

    private void doResponse(HttpServletResponse response, ResultVO resultVO) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String json = new Gson().toJson(resultVO);
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
