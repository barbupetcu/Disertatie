package com.facultate.disertatie.projection;

import org.springframework.beans.factory.annotation.Value;

public interface TeamUsers{
	@Value("#{target.id}")
    Long getId();
    String getName();
    String getLastName();
}