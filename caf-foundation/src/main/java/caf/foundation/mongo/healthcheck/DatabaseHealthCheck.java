package caf.foundation.mongo.healthcheck;

import javax.inject.Inject;

import org.mongodb.morphia.Datastore;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {
    private final Datastore datastore;

    @Inject
    public DatabaseHealthCheck(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    protected Result check() throws Exception {
        if (datastore.getDB().collectionExists("employee")) {
        	 return Result.healthy();
        } else {
            return Result.unhealthy("Cannot connect to " + datastore.getDB().getName());
        }
    }
}
