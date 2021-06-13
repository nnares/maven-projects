package com.nish.camel.bean1;

import java.util.HashMap;
import java.util.Map;

public class SqlParamMap {

    // (name, email, address, telephone)
    // insert.sql=INSERT INTO contact (name, email, address, telephone) VALUES (:#Name, :#Email, :#Address, :#Telephone)
    public Map<String, Object> getParameterMap(Contact contact) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("Name", contact.getName());
        parameterMap.put("Email", contact.getEmail());
        parameterMap.put("Address", contact.getAddress() );
        parameterMap.put("Telephone", contact.getPhone());
        return parameterMap;
    }

}
