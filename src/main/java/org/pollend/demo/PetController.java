package org.pollend.demo;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.ignite.annotation.IgniteRef;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.util.ArrayList;
import java.util.List;

@Controller("/pets")
public class PetController {
    private final IgniteCache<Long, Pet> store;

    public PetController(@IgniteRef(value = "pet-store") IgniteCache<Long, Pet> store) {
        this.store = store;
    }

    @Get()
    public MutableHttpResponse<List<Pet>> index() {
        FieldsQueryCursor<List<?>> cursor = store.query(
            new SqlFieldsQuery("SELECT id, breed, name FROM Pet"));
        List<Pet> response = new ArrayList<>(cursor.getColumnsCount());
        for (List<?> row : cursor.getAll()) {
            Pet p = new Pet();

            p.id = (long) row.get(0);
            p.breed = (String) row.get(1);
            p.name = (String) row.get(2);
            response.add(p);
        }
        return HttpResponse.ok(response);
    }

    @Get("/{id}")
    public MutableHttpResponse<Pet> get(@PathVariable Long id) {
        FieldsQueryCursor<List<?>> cursor = store.query(
            new SqlFieldsQuery("SELECT id, breed, name FROM Pet WHERE id = ?")
                .setArgs(id));
        for (List<?> row : cursor.getAll()) {
            Pet p = new Pet();
            p.id = (long) row.get(0);
            p.breed = (String) row.get(1);
            p.name = (String) row.get(2);
            return HttpResponse.ok(p);
        }
        return HttpResponse.badRequest();
    }
}
