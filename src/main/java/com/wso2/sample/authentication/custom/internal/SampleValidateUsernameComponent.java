package com.wso2.sample.authentication.custom.internal;


import com.wso2.sample.authentication.custom.SampleGetUsername;
import com.wso2.sample.authentication.custom.SampleValidateUsername;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.application.authentication.framework.JsFunctionRegistry;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;


@Component(
        name = "com.wso2.sample.authentication.custom.component",
        immediate = true
)
public class SampleValidateUsernameComponent {

    private static final Log LOGGER = LogFactory.getLog(SampleValidateUsernameComponent.class);

    @Activate
    protected void activate(ComponentContext context) {
//        System.out.println("Validate Username Component is initialising");
//        LOGGER.info("Validate Username Component is initialising");
//        BundleContext bundleContext = context.getBundleContext();
//        bundleContext.registerService(SampleGetUsername.class,
//              new  SampleValidateUsername(), null);
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("Validate Username Component is activated");
//        }
//        LOGGER.info("Validate Username Component is activated");
//        System.out.println("Validate Username Component is activated");

        try {
            SampleValidateUsername validateUsername = new SampleValidateUsername();
            JsFunctionRegistry jsFunctionRegistry = SampleValidateUsernameServiceHolder.getInstance()
                    .getJsFunctionRegistry();
            jsFunctionRegistry.register(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "SampleValidateFunction",
                    (SampleGetUsername) validateUsername::SampleValidateFunction);
            LOGGER.info("Validate Username Component is activated");
        }
        catch (Throwable e) {
            LOGGER.error("Error occurred during conditional authentication user functions bundle activation. ", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        JsFunctionRegistry jsFunctionRegistry = SampleValidateUsernameServiceHolder.getInstance()
                .getJsFunctionRegistry();
        if (jsFunctionRegistry != null) {
            jsFunctionRegistry.deRegister(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER,
                    "SampleGetUsername");
            jsFunctionRegistry.deRegister(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "getUserClaimValue");
        }
    }


    @Reference(
            name = "user.realmservice.default",
            service = RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService"
    )
    protected void setRealmService(RealmService realmService) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RealmService is set in the conditional authentication user functions bundle");
        }
        SampleValidateUsernameServiceHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RealmService is unset in the conditional authentication user functions bundle");
        }
        SampleValidateUsernameServiceHolder.getInstance().setRealmService(null);
    }

    @Reference(
            name = "registry.service",
            service = RegistryService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRegistryService"
    )
    protected void setRegistryService(RegistryService registryService) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RegistryService is set in the conditional authentication user functions bundle");
        }
        SampleValidateUsernameServiceHolder.getInstance().setRegistryService(registryService);
    }

    protected void unsetRegistryService(RegistryService registryService) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RegistryService is unset in the conditional authentication user functions bundle");
        }
        SampleValidateUsernameServiceHolder.getInstance().setRegistryService(null);
    }

    @Reference(
            service = JsFunctionRegistry.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetJsFunctionRegistry"
    )
    public void setJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {

        SampleValidateUsernameServiceHolder.getInstance().setJsFunctionRegistry(jsFunctionRegistry);
    }

    public void unsetJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {

        SampleValidateUsernameServiceHolder.getInstance().setJsFunctionRegistry(null);
    }

}