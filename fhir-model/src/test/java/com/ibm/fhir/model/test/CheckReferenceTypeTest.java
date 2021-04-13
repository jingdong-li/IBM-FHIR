/*
 * (C) Copyright IBM Corp. 2020, 2021
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.model.test;

import static com.ibm.fhir.model.type.String.string;
import static com.ibm.fhir.model.type.Uri.uri;
import static org.testng.Assert.fail;

import java.io.Reader;

import org.testng.annotations.Test;

import com.ibm.fhir.examples.ExamplesUtil;
import com.ibm.fhir.model.config.FHIRModelConfig;
import com.ibm.fhir.model.format.Format;
import com.ibm.fhir.model.parser.FHIRParser;
import com.ibm.fhir.model.resource.Observation;
import com.ibm.fhir.model.type.Reference;

public class CheckReferenceTypeTest {
    @Test
    public void testCheckReferenceType() throws Exception {
        boolean originalSetting = FHIRModelConfig.getCheckReferenceTypes();

        try (Reader reader = ExamplesUtil.resourceReader("json/ibm/minimal/Observation-1.json")) {
            FHIRModelConfig.setCheckReferenceTypes(true);
            Observation observation = FHIRParser.parser(Format.JSON).parse(reader);

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .type(uri("Patient"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .type(uri("Patient"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("urn:uuid:7113a0bb-d9e0-49df-9855-887409388c69"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Condition/1234"))
                        .build())
                    .build();
                fail();
            } catch (IllegalStateException e) {
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("1234"))
                        .build())
                    .build();
                fail();
            } catch (IllegalStateException e) {
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/12_34"))
                        .build())
                    .build();
                fail();
            } catch (IllegalStateException e) {
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .type(uri("Condition"))
                        .build())
                    .build();
                fail();
            } catch (IllegalStateException e) {
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .type(uri("Group"))
                        .build())
                    .build();
                fail();
            } catch (IllegalStateException e) {
            }

            // turn off reference type checking
            FHIRModelConfig.setCheckReferenceTypes(false);

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .type(uri("Patient"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // valid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("urn:uuid:7113a0bb-d9e0-49df-9855-887409388c69"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Condition/1234"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("1234"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/12_34"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .type(uri("Condition"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

            // invalid
            try {
                observation.toBuilder()
                    .subject(Reference.builder()
                        .reference(string("Patient/1234"))
                        .type(uri("Group"))
                        .build())
                    .build();
            } catch (IllegalStateException e) {
                fail();
            }

        } finally {
            // Restore the original config setting to be as unobtrusive as possible
            FHIRModelConfig.setCheckReferenceTypes(originalSetting);
        }
    }
}
