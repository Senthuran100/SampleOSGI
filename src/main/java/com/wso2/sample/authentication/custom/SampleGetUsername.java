package com.wso2.sample.authentication.custom;

import java.util.ArrayList;

@FunctionalInterface
public interface SampleGetUsername {
    boolean SampleValidateFunction(ArrayList<String> users, Object... parameters);
}
