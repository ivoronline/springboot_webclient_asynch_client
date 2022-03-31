package com.ivoronline.springboot_webclient_asynch_client.services;

import com.ivoronline.springboot_webclient_asynch_client.entities.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonService {

  //===============================================================
  // GET TEXT MONO
  //===============================================================
  public void getTextMono() {

    //LOG
    System.out.println("START: getTextMono()");

    //CREATE STREAM
    Mono<String> stringMono = WebClient
      .create("http://localhost:8080")
      .get()
      .uri("/GetTextMono")
      .retrieve()
      .bodyToMono(String.class);

    //SUBSCRIBE
    stringMono.subscribe(PersonService::textHelper);

    //LOG
    System.out.println("END:   getTextMono()");

  }

  //===============================================================
  // MONO HELPER
  //===============================================================
  public static void textHelper(String string) {
    System.out.println("textHelper: " + string);
  }
  //===============================================================
  // GET PERSON MONO
  //===============================================================
  public void getPersonMono() {

    //LOG
    System.out.println("START: getPersonMono()");

    //CREATE STREAM
    Mono<Person> personMono = WebClient
      .create("http://localhost:8080")
      .get()
      .uri("/GetPersonMono")
      .retrieve()
      .bodyToMono(Person.class);

    //SUBSCRIBE
    personMono.subscribe(PersonService::monoHelper);

    //LOG
    System.out.println("END:   getPersonMono()");

  }

  //===============================================================
  // MONO HELPER
  //===============================================================
  public static void monoHelper(Person person) {
    System.out.println("monoHelper: " + person.name);
  }

  //===============================================================
  // GET PERSONS FLUX
  //===============================================================
  public void getPersonsFlux() {

    //LOG
    System.out.println("START: getPersonsFlux()");

    //GET PERSON FROM SERVER
    Flux<Person> personsFlux = WebClient
      .create("http://localhost:8080")
      .get()
      .uri("/GetPersonsFlux")
      .retrieve()
      .bodyToFlux(Person.class);

    //SUBSCRIBE
    personsFlux.subscribe(PersonService::fluxHelper);

    //LOG
    System.out.println("END:   getPersonsFlux()");

  }

  //===============================================================
  // FLUX HELPER
  //===============================================================
  public static void fluxHelper(Person person) {
    System.out.println("fluxHelper: " + person.name);
  }

}


