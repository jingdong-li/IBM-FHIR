/*
 * (C) Copyright IBM Corp. 2016, 2021
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.persistence.context;

import java.util.logging.Logger;

import com.ibm.fhir.config.FHIRConfigHelper;
import com.ibm.fhir.config.FHIRConfiguration;
import com.ibm.fhir.core.FHIRConstants;
import com.ibm.fhir.persistence.context.impl.FHIRHistoryContextImpl;
import com.ibm.fhir.persistence.context.impl.FHIRPersistenceContextImpl;
import com.ibm.fhir.persistence.interceptor.FHIRPersistenceEvent;
import com.ibm.fhir.search.SearchConstants;
import com.ibm.fhir.search.context.FHIRSearchContext;

/**
 * This is a factory used to create instances of the FHIRPersistenceContext interface.
 */
public class FHIRPersistenceContextFactory {
    private static Logger log = Logger.getLogger(FHIRPersistenceContextFactory.class.getName());

    private FHIRPersistenceContextFactory() {
        // No Operation
    }

    /**
     * Returns a FHIRPersistenceContext that contains a FHIRPersistenceEvent instance.
     * @param event the FHIRPersistenceEvent instance to be contained in the FHIRPersistenceContext instance
     */
    public static FHIRPersistenceContext createPersistenceContext(FHIRPersistenceEvent event) {
        return new FHIRPersistenceContextImpl(event);
    }

    /**
     * Returns a FHIRPersistenceContext that contains a FHIRPersistenceEvent instance.
     * @param event the FHIRPersistenceEvent instance to be contained in the FHIRPersistenceContext instance
     * @param includeDeleted flag to tell the persistence layer to include deleted resources in the operation results.
     */
    public static FHIRPersistenceContext createPersistenceContext(FHIRPersistenceEvent event, boolean includeDeleted) {
        return new FHIRPersistenceContextImpl(event, includeDeleted);
    }

    /**
     * Returns a FHIRPersistenceContext that contains a FHIRPersistenceEvent and a FHIRHistoryContext.
     * @param event the FHIRPersistenceEvent instance to be contained in the FHIRPersistenceContext instance
     * @param historyContext the FHIRHistoryContext instance to be contained in the FHIRPersistenceContext instance
     */
    public static FHIRPersistenceContext createPersistenceContext(FHIRPersistenceEvent event, FHIRHistoryContext historyContext) {
        return new FHIRPersistenceContextImpl(event, historyContext);
    }

    /**
     * Returns a FHIRPersistenceContext that contains a FHIRPersistenceEvent and a FHIRSearchContext.
     * @param event the FHIRPersistenceEvent instance to be contained in the FHIRPersistenceContext instance
     * @param searchContext the FHIRSearchContext instance to be contained in the FHIRPersistenceContext instance
     */
    public static FHIRPersistenceContext createPersistenceContext(FHIRPersistenceEvent event, FHIRSearchContext searchContext) {
        return new FHIRPersistenceContextImpl(event, searchContext);
    }

    /**
     * Returns a FHIRHistoryContext instance with default values.
     */
    public static FHIRHistoryContext createHistoryContext() {
        int pageSize = FHIRConfigHelper.getIntProperty(FHIRConfiguration.PROPERTY_DEFAULT_PAGE_SIZE, FHIRConstants.FHIR_PAGE_SIZE_DEFAULT);
        if (pageSize > SearchConstants.MAX_PAGE_SIZE) {
            log.warning(String.format("Server configuration %s = %d exceeds maximum allowed page size %d; using %d",
                FHIRConfiguration.PROPERTY_DEFAULT_PAGE_SIZE, pageSize, SearchConstants.MAX_PAGE_SIZE, SearchConstants.MAX_PAGE_SIZE));
            pageSize = SearchConstants.MAX_PAGE_SIZE;
        }
        
        FHIRHistoryContext ctx = new FHIRHistoryContextImpl();
        ctx.setPageSize(pageSize);
        return ctx;
    }

    /**
     * Returns a FHIRPersistenceContext that contains a FHIRPersistenceEvent instance.
     * @param event the FHIRPersistenceEvent instance to be contained in the FHIRPersistenceContext instance
     * @param includeDeleted flag to tell the persistence layer to include deleted resources in the operation results
     * @param searchContext the FHIRSearchContext instance to be contained in the FHIRPersistenceContext instance
     */
    public static FHIRPersistenceContext createPersistenceContext(FHIRPersistenceEvent event, boolean includeDeleted, FHIRSearchContext searchContext) {
        return new FHIRPersistenceContextImpl(event, includeDeleted, searchContext);
    }
}
