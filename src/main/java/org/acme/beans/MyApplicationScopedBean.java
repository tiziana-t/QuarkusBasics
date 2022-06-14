package org.acme.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyApplicationScopedBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationScopedBean.class);

    @PostConstruct
    void init(){
        LOGGER.info("MyApplicationScopedBean: Initialization");
    }
}
