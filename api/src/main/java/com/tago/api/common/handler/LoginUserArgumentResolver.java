package com.tago.api.common.handler;

import com.tago.api.common.annotation.LoginUser;
import com.tago.api.common.exception.ResolveArgumentException;
import com.tago.domain.common.error.ErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean isLongType = Long.class.isAssignableFrom(parameter.getParameterType());
        return isLoginUserAnnotation && isLongType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validatePrincipalType(authentication);
        return authentication.getPrincipal();
    }

    private void validatePrincipalType(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof Long)) {
            throw new ResolveArgumentException(ErrorCode.PRINCIPAL_TYPE_NOT_MATCH);
        }
    }
}
