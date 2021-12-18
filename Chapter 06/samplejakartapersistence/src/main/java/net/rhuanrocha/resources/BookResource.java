package net.rhuanrocha.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.rhuanrocha.business.BookBusiness;
import net.rhuanrocha.entities.Book;


import java.net.URI;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookBusiness bookBusiness;

    @GET
    public Response find(@QueryParam("authorName") String authorName){
        if(authorName != null && !authorName.isEmpty()){
            return Response
                    .ok(bookBusiness.findByAuthorName(authorName))
                    .build();
        }
        return Response.ok(bookBusiness.findAll()).build();
    }

    @POST
    public Response save(Book book){
        book.setId(null);
        bookBusiness.save(book);
        return Response
                .created(URI.create(String.format("books/%d",book.getId())))
                .build();
    }
}
