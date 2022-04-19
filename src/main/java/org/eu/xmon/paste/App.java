package org.eu.xmon.paste;

import io.jooby.Jooby;
import io.jooby.freemarker.FreemarkerModule;
import io.jooby.whoops.WhoopsModule;

import java.nio.file.Paths;

public class App extends Jooby {

  {
    install(new FreemarkerModule());
    install(new WhoopsModule());

    decorator(next -> ctx -> {
      long start = System.currentTimeMillis();

      Object response = next.apply(ctx);

      long end = System.currentTimeMillis();
      long took = end - start;

      System.out.println("[DEBUG] Took: " + took + "ms [ " + ctx.getContextPath() + " ]");

      return response;
    });
    mvc(new Controller());
    assets("/assets/*", Paths.get("static"));
  }

  public static void main(final String[] args) {
    runApp(args, App::new);
  }

}
