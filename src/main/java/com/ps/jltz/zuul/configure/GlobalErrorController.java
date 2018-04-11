package com.ps.jltz.zuul.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全局异常处理
 * @author 刘智斌
 * @version 0.1
 * @time 1/26/18
 * @since 0.1
 */
@RestController
@RequestMapping("/error")
public class GlobalErrorController extends AbstractErrorController{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorController.class);

    @Autowired
    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    public static final Map<String, String> ERROR_MAP = new LinkedHashMap<>();
    public static final Map<String, String> FORBIDDEN_MAP = new LinkedHashMap<>();
    static {
        ERROR_MAP.put("resCode", "500");
        ERROR_MAP.put("msg", "Gateway Error");

        FORBIDDEN_MAP.put("resCode", "403");
        FORBIDDEN_MAP.put("msg", "Forbidden Error");
    }
/*

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok(MAP);
    }

*/
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        LOGGER.error("错误状态码: " + status.value() + "\t请求路径: " + request.getRequestURI());
        if (status.is5xxServerError()) {
            return ResponseEntity.ok(ERROR_MAP);
        }
        return ResponseEntity.ok(FORBIDDEN_MAP);
    }

}
