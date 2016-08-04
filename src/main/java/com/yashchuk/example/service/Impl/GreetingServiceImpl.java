package com.yashchuk.example.service.Impl;

import com.yashchuk.example.model.Greeting;
import com.yashchuk.example.service.GreetingService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrii Iashchuk
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    private static BigInteger nextId;
    private static Map<BigInteger,Greeting> greetingMap;

    /**
     * method save entity into map-repository
     */
    private static Greeting save(Greeting greeting){
        if(greetingMap == null) {
            greetingMap = new HashMap<BigInteger, Greeting>();
            nextId = BigInteger.ONE;
        }
        // If Update...
        if(greeting.getId() != null){
            Greeting oldGreeting = greetingMap.get(greeting.getId());
            if(oldGreeting == null){
                return null;
            }
            greetingMap.remove(greeting.getId());
            greetingMap.put(greeting.getId(), greeting);
            return greeting;
        }
        // If Create...
        greeting.setId(nextId);
        nextId = nextId.add(BigInteger.ONE);
        greetingMap.put(greeting.getId(),greeting);
        return greeting;


    }

    /**
     * method delete entity from map-repository
     */
    private static boolean remove(Long id){
        Greeting deleteGreeting = greetingMap.remove(id);
        return deleteGreeting != null;
    }

    static {
        Greeting g1 = new Greeting();
        g1.setText("Hello world!");
        save(g1);

        Greeting g2 = new Greeting();
        g2.setText("Hi!");
        save(g2);
    }



    @Override
    public Collection<Greeting> findAll() {
        return greetingMap.values();
    }

    @Override
    public Greeting findOne(Long id) {
        return greetingMap.get(id);
    }

    @Override
    public Greeting create(Greeting greeting) {
        return save(greeting);
    }

    @Override
    public Greeting update(Greeting greeting) {
        return save(greeting);
    }

    @Override
    public void delete(Long id) {
        remove(id);
    }
}
