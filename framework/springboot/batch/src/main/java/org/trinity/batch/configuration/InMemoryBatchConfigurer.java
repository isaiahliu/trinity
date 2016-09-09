package org.trinity.batch.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;

public class InMemoryBatchConfigurer extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(final DataSource dataSource) {
    }

}
