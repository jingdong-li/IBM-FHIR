/*
 * (C) Copyright IBM Corp. 2019, 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.model.type.code;

import com.ibm.fhir.model.type.Code;
import com.ibm.fhir.model.type.Extension;
import com.ibm.fhir.model.type.String;

import java.util.Collection;
import java.util.Objects;

import javax.annotation.Generated;

@Generated("com.ibm.fhir.tools.CodeGenerator")
public class CompartmentCode extends Code {
    /**
     * Patient
     */
    public static final CompartmentCode PATIENT = CompartmentCode.builder().value(ValueSet.PATIENT).build();

    /**
     * Encounter
     */
    public static final CompartmentCode ENCOUNTER = CompartmentCode.builder().value(ValueSet.ENCOUNTER).build();

    /**
     * RelatedPerson
     */
    public static final CompartmentCode RELATED_PERSON = CompartmentCode.builder().value(ValueSet.RELATED_PERSON).build();

    /**
     * Practitioner
     */
    public static final CompartmentCode PRACTITIONER = CompartmentCode.builder().value(ValueSet.PRACTITIONER).build();

    /**
     * Device
     */
    public static final CompartmentCode DEVICE = CompartmentCode.builder().value(ValueSet.DEVICE).build();

    private volatile int hashCode;

    private CompartmentCode(Builder builder) {
        super(builder);
    }

    public static CompartmentCode of(ValueSet value) {
        switch (value) {
        case PATIENT:
            return PATIENT;
        case ENCOUNTER:
            return ENCOUNTER;
        case RELATED_PERSON:
            return RELATED_PERSON;
        case PRACTITIONER:
            return PRACTITIONER;
        case DEVICE:
            return DEVICE;
        default:
            throw new IllegalStateException(value.name());
        }
    }

    public static CompartmentCode of(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    public static String string(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    public static Code code(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CompartmentCode other = (CompartmentCode) obj;
        return Objects.equals(id, other.id) && Objects.equals(extension, other.extension) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(id, extension, value);
            hashCode = result;
        }
        return result;
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.id(id);
        builder.extension(extension);
        builder.value(value);
        return builder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Code.Builder {
        private Builder() {
            super();
        }

        @Override
        public Builder id(java.lang.String id) {
            return (Builder) super.id(id);
        }

        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        @Override
        public Builder value(java.lang.String value) {
            return (value != null) ? (Builder) super.value(ValueSet.from(value).value()) : this;
        }

        public Builder value(ValueSet value) {
            return (value != null) ? (Builder) super.value(value.value()) : this;
        }

        @Override
        public CompartmentCode build() {
            return new CompartmentCode(this);
        }
    }

    public enum ValueSet {
        /**
         * Patient
         */
        PATIENT("Patient"),

        /**
         * Encounter
         */
        ENCOUNTER("Encounter"),

        /**
         * RelatedPerson
         */
        RELATED_PERSON("RelatedPerson"),

        /**
         * Practitioner
         */
        PRACTITIONER("Practitioner"),

        /**
         * Device
         */
        DEVICE("Device");

        private final java.lang.String value;

        ValueSet(java.lang.String value) {
            this.value = value;
        }

        public java.lang.String value() {
            return value;
        }

        public static ValueSet from(java.lang.String value) {
            for (ValueSet c : ValueSet.values()) {
                if (c.value.equals(value)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }
}
