package com.tago.domain.place.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class PlaceNotFoundException extends BaseBusinessException{
    public PlaceNotFoundException(){
        super(ErrorCode.PLACE_NOT_FOUND);
    }
}
