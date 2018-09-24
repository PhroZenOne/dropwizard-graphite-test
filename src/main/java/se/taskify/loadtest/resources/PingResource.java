package se.taskify.loadtest.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

    private volatile int counter = 0;

    @GET
    @Timed
    public int root() throws InterruptedException {
        Thread.sleep(1000);
        return counter++;
    }
}
