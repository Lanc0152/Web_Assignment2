/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import cst8218.sn041068644.bouncer.entity.Bouncer;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 *
 * @author sean lancaster 041068644
 *
 * This class implements restful api to conduct basic CRUD operations
 */
@RolesAllowed({"Admin","RESTGroup"})
@Stateless
@Path("cst8218.sn041068644.bouncer.entity.bouncer")
public class BouncerFacadeREST extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BouncerFacadeREST() {
        super(Bouncer.class);
    }
    //this is a post request on the root resource
    @RolesAllowed({"Admin","RESTGroup"})
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createPost(Bouncer entity, @Context UriInfo uriInfo) {
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(entity.getId())).build();

        if (entity.getId() == null) {
            super.create(entity);

            return Response.status(Response.Status.CREATED)
                    .location(location)
                    .entity(entity)
                    .build();
        } else {
            if (super.find(entity.getId()) != null) {
                entity.updates(super.find(entity.getId()));

                return Response.status(Response.Status.OK)
                        .location(location)
                        .entity(entity)
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .location(location)
                .entity(entity)
                .build();
    }

    //this is a post request on a specific id
    @RolesAllowed({"Admin","RESTGroup"})
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createPostId(Bouncer entity, @Context UriInfo uriInfo, @PathParam("id") Long id) {
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(entity.getId())).build();

        if (super.find(entity.getId()) != null && entity.getId().equals(id)) {
            super.edit(entity);
            return Response.status(Response.Status.OK) 
                    .location(location) 
                    .entity(entity) 
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .location(location)
                .entity(entity)
                .build();
    }
    //this is a put request on a specific id
    @RolesAllowed({"Admin","RESTGroup"})
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Bouncer entity, @Context UriInfo uriInfo) {
        URI location = URI.create(uriInfo.getRequestUri().getPath() + "/" + entity.getId());

        if (super.find(entity.getId()) != null && entity.getId() == id) {

            entity.updates(super.find(entity.getId()));

            return Response.status(Response.Status.OK).location(location).entity(entity).build();

        }

        return Response.status(Response.Status.BAD_REQUEST).location(location).entity(entity).build();

    }

    //this is a put request on the root resource
    @RolesAllowed({"Admin","RESTGroup"})
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response notAllowed(Bouncer entity, @Context UriInfo uriInfo) {
        URI location = URI.create(uriInfo.getRequestUri().getPath() + "/" + entity.getId());

        return Response.status(Response.Status.UNAUTHORIZED).location(location).entity(entity).build();
    }
//this is a delete request on a specific id
    @RolesAllowed({"Admin","RESTGroup"})
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
//this is a get request on a specific id
    @RolesAllowed({"Admin","RESTGroup"})
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bouncer find(@PathParam("id") Long id) {
        return super.find(id);
    }
//this is a get request on the root resource
    @RolesAllowed({"Admin","RESTGroup"})
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findAll() {
        return super.findAll();
    }
//this is a get request on a range of id's
    @RolesAllowed({"Admin","RESTGroup"})
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
//this is a get request to see how many id's are stored
    @RolesAllowed({"Admin","RESTGroup"})
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

}
