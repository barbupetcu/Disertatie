package com.facultate.disertatie.projection;

import org.springframework.beans.factory.annotation.Value;

public interface DisabledUsers{
	@Value("#{target.id}")
    Long getId();
    
    DisabledPerso getPerso();
    
    public interface DisabledPerso{
    	String getName();
    	String getLastName();
    }
}