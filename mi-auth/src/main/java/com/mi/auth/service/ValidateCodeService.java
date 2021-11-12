package com.mi.auth.service;

import com.mi.auth.config.MiValidateCodeConfig;
import com.mi.auth.properties.MiAuthProperties;
import com.mi.constant.CaptchaConstant;
import com.mi.excepition.ValidateCodeException;
import com.mi.utils.RedisUtils;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeService
 * @Author yangli
 * @Date 2021/8/23 14:09
 * @Description: 验证码 service
 */
@Service
public class ValidateCodeService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MiAuthProperties miAuthProperties;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws ValidateCodeException
     * @throws IOException
     */
    public void create (HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
        // 客户端id
        String clientId = request.getParameter("client_id");
        // 客户端id 非空判断
        if (StringUtils.isBlank(clientId)) {
            throw new ValidateCodeException("验证码client_id不能为空");
        }
        // 获取验证码code
        MiValidateCodeConfig code = miAuthProperties.getCode();
        setHeader(response,code.getType());

        Captcha captcha = createCaptcha(code);
        redisUtils.set(CaptchaConstant.CODE_PREFIX + clientId, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 校验验证码
     * @param clientId
     * @param value
     * @throws ValidateCodeException
     */
    public void check(String clientId, String value) throws ValidateCodeException {
        Object codeInRedis = redisUtils.get(CaptchaConstant.CODE_PREFIX + clientId);
        if (StringUtils.isBlank(value)) {
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new ValidateCodeException("验证码不正确");
        }
    }


    private Captcha createCaptcha(MiValidateCodeConfig code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), CaptchaConstant.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, CaptchaConstant.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }

}
