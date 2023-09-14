package com.tago.domain.course.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class CourseNotFoundException extends BaseBusinessException {
    public CourseNotFoundException() {
        super(ErrorCode.COURSE_NOT_MATCHING);
    }
}
