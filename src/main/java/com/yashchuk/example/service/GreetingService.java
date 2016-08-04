package com.yashchuk.example.service;

import com.yashchuk.example.model.Greeting;
import java.util.Collection;

/**
 * @author Andrii Iashchuk
 */
public interface GreetingService {
    Collection<Greeting> findAll();
    Greeting findOne(Long id);
    Greeting create(Greeting greeting);
    Greeting update(Greeting greeting);
    void delete(Long id);
}
