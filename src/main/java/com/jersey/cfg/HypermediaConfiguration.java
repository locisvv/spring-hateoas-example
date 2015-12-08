package com.jersey.cfg;

/**
 * Created by vsabadosh on 21/11/15.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
class HypermediaConfiguration {
}

