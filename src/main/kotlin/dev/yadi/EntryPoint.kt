package dev.yadi

import dev.yadi.model.request.Greetings
import javax.ws.rs.core.MediaType
import dev.yadi.model.response.Health
import javax.ws.rs.*
import javax.ws.rs.core.Response

@Path("/")
class EntryPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/health")
    fun health(): Health {
        return Health("/health", "successfully reached health checkpoint")
    }

    /**
     * Greetings
     * Sends greetings based on the prefix and name
     * @param senderDetails
     * @return
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/greetings")
    fun greetings(senderDetails: Greetings): Response {
        val resBody = "Greetings there ${senderDetails.prefix}.${senderDetails.lastName}\n"
        return Response.ok(resBody).status(201).build()
    }

    /**
     * Greetings from sender to name
     *
     * @param name
     * @param senderDetails
     * @return
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/greetings/{name}")
    fun greetingsFromSenderToName(@PathParam("name") name: String, senderDetails: Greetings): Response{
        val resBody = "Greetings $name - From ${senderDetails.prefix}.${senderDetails.lastName}\n"
        return Response.ok(resBody).status(201).build()
    }
}