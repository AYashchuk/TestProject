package com.yashchuk.example.web.api;

import com.yashchuk.example.model.Greeting;
import com.yashchuk.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * @author Andrii Iashchuk
 */
@RestController
public class GreetingRestController {

    @Autowired
    private GreetingService greetingService;


    /**
     * Method get collection of all greeting objects
     */
    @RequestMapping(
                    value = "/api/greetings",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getAllGreetings(){
        Collection<Greeting> greetings = greetingService.findAll();
        return  new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
    }


    /**
     * get greeting object by his id
     */
    @RequestMapping(
                    value = "/api/greetings/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id){
        Greeting greeting = greetingService.findOne(id);

        if(greeting == null){
            return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }


    /**
     * receive and put in repository greeting object
     */
    @RequestMapping(
                    value = "/api/greetings",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting){
        Greeting saveGreeting = greetingService.create(greeting);
        return new ResponseEntity<Greeting>(saveGreeting, HttpStatus.CREATED);
    }


    /**
     *  method update greeting entity
     */
    @RequestMapping(
                    value = "/api/greetings/{id}",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting){

        Greeting updateGreeting = greetingService.update(greeting);
        if(updateGreeting == null){
            return new ResponseEntity<Greeting>(updateGreeting, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<Greeting>(updateGreeting, HttpStatus.OK);
    }



    @RequestMapping(
                    value = "/api/greetings/{id}",
                    method = RequestMethod.DELETE,
                    consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting){
        greetingService.delete(id);
        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
    }
}
