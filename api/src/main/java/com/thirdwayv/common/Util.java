package com.thirdwayv.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 * @author Ahmed Basuny on 07_06_2020
 * to handle the utils of the project like handling customer error.
 */

@Service
public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public ResponseEntity<?> returnError(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorEntity(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
