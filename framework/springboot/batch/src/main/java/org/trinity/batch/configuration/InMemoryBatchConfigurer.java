package org.trinity.batch.configuration;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class InMemoryBatchConfigurer extends DefaultBatchConfigurer {

    public InMemoryBatchConfigurer(final ResourceLoader resourceLoader) {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:batch;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        final String schemaLocation = "classpath:org/springframework/batch/core/schema-h2.sql";
        populator.addScript(resourceLoader.getResource(schemaLocation));
        populator.setContinueOnError(true);
        DatabasePopulatorUtils.execute(populator, dataSource);

        setDataSource(dataSource);
    }

    @Override
    public void setDataSource(final DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
