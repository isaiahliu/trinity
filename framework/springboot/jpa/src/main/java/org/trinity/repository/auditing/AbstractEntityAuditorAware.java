package org.trinity.repository.auditing;

import org.springframework.data.domain.AuditorAware;

public abstract class AbstractEntityAuditorAware implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return getAuditorName();
    }

    protected abstract String getAuditorName();
}
