package com.udla.Controllers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.udla.Models.Contacto;
import com.udla.Utils.Utils;

@Path("/v1/api")
public class ApiController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(Utils.listaContactos).build();
    }

    @GET
    @Path("{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getbyid(@PathParam(value = "cedula") String cedula) {
        Contacto contacto = Utils.listaContactos.stream().filter(x->x.getCedula().equals(cedula)).findAny().orElse(null);
        if (contacto!=null){
            return Response.ok(contacto).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarContacto(Contacto contacto){

        Utils.listaContactos.add(contacto);

        return Response.ok(contacto).build();
    }

    @PUT
    @Path("{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarContacto(@PathParam(value = "cedula") String cedula, Contacto contacto){
        Contacto contactoObtenido = Utils.listaContactos.stream().filter(x->x.getCedula().equals(cedula)).findAny().orElse(null);
        if (contactoObtenido!=null){
            contactoObtenido.setNombre(contacto.getNombre());
            contactoObtenido.setDireccion(contacto.getDireccion());
            contactoObtenido.setTelefono(contacto.getTelefono());
            return Response.ok(contactoObtenido).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE
    @Path("{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarContacto(@PathParam(value = "cedula") String cedula){
        Contacto contactoObtenido = Utils.listaContactos.stream().filter(x->x.getCedula().equals(cedula)).findAny().orElse(null);
        if (contactoObtenido!=null){
            Utils.listaContactos.remove(contactoObtenido);
            return Response.status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

}