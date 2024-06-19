package de.fhws.fiw.fds.client_server.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.api.queries.QueryByModuleName;
import de.fhws.fiw.fds.client_server.server.api.queries.QueryByAllNameAndCountry;
import de.fhws.fiw.fds.client_server.server.api.states.partneruniversities.*;
import de.fhws.fiw.fds.client_server.server.api.states.uni_modules.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("partnerunis")
public class PartnerUniversityJerseyService extends AbstractJerseyService {

    public PartnerUniversityJerseyService() {
        super();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPartnerUniversities(
            @DefaultValue("") @QueryParam("name") final String name,
            @DefaultValue("") @QueryParam("country") final String country,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size,
            @DefaultValue("") @QueryParam("orderByName") final String order) {

        try {
            return new GetAllPartnerUniversities(
                    this.serviceContext,
                    new QueryByAllNameAndCountry<>(name, country, order, offset, size)
            ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }


    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSinglePartnerUni(@PathParam("id") final long id) {
        try {
            return new GetSingleUni(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response
                    .status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build()
            );
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSingleUni(final PartnerUniversity uniModel) {
        try {
            return new PostNewUni(this.serviceContext, uniModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSingleUni(@PathParam("id") final long id, final PartnerUniversity uniModel) {
        try {
            return new PutSingleUni(this.serviceContext, id, uniModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSingleUni(@PathParam("id") final long id) {
        try {
            return new DeleteSingleUni(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{uniId: \\d+}/modules/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getModulesOfUni(@PathParam("uniId") final long uniId,
                                    @DefaultValue("") @QueryParam("moduleName") final String moduleName,
                                    @DefaultValue("0") @QueryParam("offset") int offset,
                                    @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllModulesOfUni(this.serviceContext, uniId, new QueryByModuleName<>(uniId, moduleName, offset, size)).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{uniId: \\d+}/modules/{moduleId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getModuleByIdOfUni(@PathParam("uniId") final long uniId,
                                            @PathParam("moduleId") final long moduleId) {
        try {
            return new GetSingleModuleOfUni(this.serviceContext, uniId, moduleId).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{uniId: \\d+}/modules")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewModuleOfUni(@PathParam("uniId") final long uniId, final Module module) {
        try {
            return new PostNewModuleOfUni(this.serviceContext, uniId, module).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{uniId: \\d+}/modules/{moduleId: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateNewModuleOfUni(@PathParam("uniId") final long uniId,
                                              @PathParam("moduleId") final long moduleId, final Module module) {
        try {
            return new PutSingleModuleOfUni(this.serviceContext, uniId, moduleId, module).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{uniId: \\d+}/modules/{moduleId: \\d+}")
    public Response deleteModuleOfUni(@PathParam("uniId") final long uniId,
                                      @PathParam("moduleId") final long moduleId) {
        try {
            return new DeleteSingleModuleOfUni(this.serviceContext, moduleId, uniId).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }
}

