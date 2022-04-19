package org.eu.xmon.paste;

import io.jooby.ModelAndView;
import io.jooby.annotations.*;

import java.util.concurrent.CompletableFuture;

@Path("/")
public class Controller {

  @GET
  public CompletableFuture<ModelAndView> sayHi() {
    final long start = System.currentTimeMillis();
    return CompletableFuture
            .supplyAsync(() -> new ModelAndView("index.ftl")
                    .put("title", "Strona Główna")
                    .put("genereted", (System.currentTimeMillis() - start))
            );

  }
}
