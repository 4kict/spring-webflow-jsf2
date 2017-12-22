package spring.workshop.webflow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.config.AbstractFacesFlowConfiguration;
import org.springframework.faces.webflow.FlowFacesContextLifecycleListener;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

/**
 * Spring WebFlow configuration.
 */
@Configuration
public class WebFlowConfiguration extends AbstractFacesFlowConfiguration {

    @Autowired
    FlowDefinitionRegistry flowDefinitionRegistry;

//    @Bean
//    public FlowDefinitionRegistry flowRegistry() {
//        return getFlowDefinitionRegistryBuilder ()
//                .setBasePath ( "/WEB-INF/flows" )
//                .addFlowLocationPattern ( "/**/*-flow.xml" )
//                .setFlowBuilderServices ( flowBuilderServices () )
//                .build ();
//    }

    @Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder ( flowDefinitionRegistry )
                .addFlowExecutionListener ( new FlowFacesContextLifecycleListener () )
                .addFlowExecutionListener ( new SecurityFlowExecutionListener () )
                .build ();
    }

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                .setDevelopmentMode ( true )
                .build();
    }

}
