/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author Frank
 */
public class Company {
    private JavaDeveloper javaDeveloper;
    private Client client;
    
    public Company(JavaDeveloper jd){
        javaDeveloper = jd;
        this.client = ClientBuilder.newClient();
    }
    
    public String send(final Contractor contractor) {    
        final WebTarget wegTarget = this.client
                .target("http://localhost:8888/{contractor}")                                        
                .resolveTemplate("contractor", contractor.getName()); 
    final Response response = wegTarget                                  
            .request("application/json")                                  
            .post(Entity.json(this.javaDeveloper), Response.class); 
    if (response.getStatus() != 200) {        
        throw new RuntimeException("HTTP Response: " + response.getStatus());    }    
    return response.readEntity(String.class); }
    
}
