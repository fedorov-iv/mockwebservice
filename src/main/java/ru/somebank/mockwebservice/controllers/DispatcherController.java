package ru.somebank.mockwebservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.somebank.mockwebservice.models.Endpoint;
import ru.somebank.mockwebservice.services.EndpointService;

import javax.servlet.http.HttpServletRequest;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class DispatcherController {

    @Autowired
    private EndpointService endpointService;

    @RequestMapping(value="/**", method = {GET, POST, DELETE, PUT})
    public ResponseEntity<String> endpoints(HttpServletRequest request){

        Endpoint endpoint = endpointService.findBy(request.getRequestURI(), request.getMethod());

        HttpHeaders httpHeaders = new HttpHeaders();

        if(endpoint == null){
            httpHeaders.set("Content-Type", "text/html;charset=UTF-8");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body("Service not implemented yet");
        }
        endpoint.getHeaders().forEach(h -> httpHeaders.set(h.getName(), h.getValue()));
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(endpoint.getBody());
    }

}
