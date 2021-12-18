package net.rhuanrocha.endpoint;

import net.rhuanrocha.entity.Product;
import net.rhuanrocha.stateless.ProductBusiness;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/products")
public class ProductEndpoint {

    @Inject
    private ProductBusiness productBusiness;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.ok(productBusiness.findAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Product product){
        product.setId(null);//To guarantee a new item will be created on the database
        productBusiness.save(product);
        return Response
                .created(URI.create(String.format("/products/%d",product.getId())))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        Optional<Product> product = productBusiness.findById(id);
        if(!product.isPresent()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.ok(product.get()).build();
    }
}
