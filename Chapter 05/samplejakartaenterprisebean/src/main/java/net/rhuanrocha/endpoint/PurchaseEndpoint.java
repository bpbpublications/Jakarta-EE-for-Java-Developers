package net.rhuanrocha.endpoint;

import net.rhuanrocha.entity.Product;
import net.rhuanrocha.entity.Purchase;
import net.rhuanrocha.stateless.ProductBusiness;
import net.rhuanrocha.stateless.PurchaseBusiness;
import net.rhuanrocha.stateful.ShoppingCartBusiness;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.Optional;

@Path("/purchases")
@SessionScoped
public class PurchaseEndpoint implements Serializable {

    @Inject
    private PurchaseBusiness purchaseBusiness;

    @Inject
    private ShoppingCartBusiness shoppingCartBusiness;

    @Inject
    private ProductBusiness productBusiness;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response
                .ok(purchaseBusiness.findAll())
                .build();
    }

    @PUT
    @Path("/shoppingcart")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addProduct(@FormParam("idProduct")Long idProduct){
        Optional<Product> product = productBusiness.findById(idProduct);
        if(!product.isPresent()){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        shoppingCartBusiness.add(product.get());
        return Response.ok().build();

    }


    @POST
    public Response finalizePurchase(){
        if(shoppingCartBusiness.getProducts().isEmpty()){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        Purchase purchase = shoppingCartBusiness.finalizeCart();
        return Response.created(URI.create(String.format("/purchases/%d",purchase.getId()))).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        Optional<Purchase> purchase = purchaseBusiness.findById(id);
        if(!purchase.isPresent()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .ok(purchase.get())
                .build();
    }


}
