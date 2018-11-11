/*
 * Copyright 2018 Heiko Scherrer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openwms.core.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * A ApplicationInitializer.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 */
public class ApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInitializer.class);

    /**
     * {@inheritDoc}
     * <p>
     * Depending on the underlying platform, different Spring profiles are
     * included.
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String activeProfile = System.getProperty("spring.profiles.active");
        if ("OSGI".equalsIgnoreCase(activeProfile)) {
            LOGGER.info("Running in OSGI environment");
        } else if ("noOSGI".equalsIgnoreCase(activeProfile)) {
            LOGGER.info("Running in a non-OSGI environment");
        } else {
            applicationContext.getEnvironment().setActiveProfiles("noOSGI");
            applicationContext.refresh();
            LOGGER.info("Switched to a non-OSGI environment");
        }
    }
}