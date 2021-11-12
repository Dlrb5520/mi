package com.mi.auth.controller;

import com.mi.auth.service.ValidateCodeService;
import com.mi.entity.common.MiResponse;
import com.mi.excepition.MiException;
import com.mi.excepition.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @ClassName SecurityController
 * @Author yangli
 * @Date 2021/7/14 21:45
 * @Description:
 */
@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    /**
     * 当前登录用户
     * @param principal
     * @return
     */
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * 退出登录
     * @param request
     * @return
     * @throws MiException
     */
    @DeleteMapping("signout")
    public MiResponse signout(HttpServletRequest request) throws MiException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        MiResponse miResponse = new MiResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new MiException("退出登录失败");
        }
        return miResponse.message("退出登录成功");
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            validateCodeService.create(request,response);
        } catch (ValidateCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
