/**
 * (C) Copyright IBM Corp. 2016,2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.client.test.mains;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.ibm.watsonhealth.fhir.core.MediaType;
import com.ibm.watsonhealth.fhir.model.format.Format;
import com.ibm.watsonhealth.fhir.model.generator.FHIRGenerator;
import com.ibm.watsonhealth.fhir.model.resource.Claim;
import com.ibm.watsonhealth.fhir.provider.FHIRProvider;

public class ReadClaimTestMain {
    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newBuilder()
                .register(new FHIRProvider())
                .build();
        WebTarget target = client.target("http://fhirtest.uhn.ca/baseDstu2");
        Response response = target.path("Claim/14105").request(MediaType.APPLICATION_FHIR_JSON).get();
        Claim claim = response.readEntity(Claim.class);
        FHIRGenerator.generator( Format.JSON, false).generate(claim, System.out);
        System.out.println("");
        FHIRGenerator.generator( Format.XML, false).generate(claim, System.out);
        
    }
}
