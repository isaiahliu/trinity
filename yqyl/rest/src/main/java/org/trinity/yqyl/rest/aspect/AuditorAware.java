package org.trinity.yqyl.rest.aspect;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.trinity.repository.auditing.AbstractEntityAuditorAware;

@Component
@EnableJpaAuditing
public class AuditorAware extends AbstractEntityAuditorAware {
    @Override
    protected String getAuditorName() {
        return "test";
    }

}
