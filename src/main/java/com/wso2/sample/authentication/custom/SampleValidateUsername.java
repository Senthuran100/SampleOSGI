package com.wso2.sample.authentication.custom;

import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SampleValidateUsername implements SampleGetUsername{
    private static final Log LOGGER = LogFactory.getLog(SampleValidateUsername.class);
    public boolean SampleValidateFunction(ArrayList<String> users, Object... parameter){
        String username= null;
        if(users==null||username==null){
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Passed parameter to SampleValidateFunction method has null values");
            }
        }

        if (parameter.length == 1 && parameter[0] instanceof String) {
            username = (String) parameter[0];
        }
        if(users!= null) {
            if (users.contains(username)) {
                return true;
            }
        }
        return false;

    }
}
