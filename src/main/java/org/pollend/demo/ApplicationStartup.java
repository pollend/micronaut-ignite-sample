package org.pollend.demo;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceReadyEvent;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import javax.inject.Singleton;

@Singleton
public class ApplicationStartup implements ApplicationEventListener<ServiceReadyEvent> {
    private final Ignite ignite;

    public ApplicationStartup(Ignite ignite) {
        this.ignite = ignite;
    }

    @Override
    public void onApplicationEvent(ServiceReadyEvent event) {
        IgniteCache<Long, Pet> petIgniteCache = ignite.cache("pet-store");
        petIgniteCache.query(new SqlFieldsQuery("INSERT INTO Pet(id,breed,name) VALUES (?,?,?)").setArgs(0, "Labrador", "Jessie"));
        petIgniteCache.query(new SqlFieldsQuery("INSERT INTO Pet(id,breed,name) VALUES (?,?,?)").setArgs(1, "Labrador", "Bo"));
        petIgniteCache.query(new SqlFieldsQuery("INSERT INTO Pet(id,breed,name) VALUES (?,?,?)").setArgs(2, "Beagle", "Bengie"));
    }
}
