package org.tair.ws.rest.services;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import org.tair.ws.rest.download.*;

public class DWApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();
   private Set<Class<?>> empty = new HashSet<Class<?>>();

   public DWApplication() {
      singletons.add(new UsersResource());
      singletons.add(new UsersResourceXMLJSON());
      singletons.add(new Index());
      singletons.add(new FileService());
      singletons.add(new AnyFileService());
   }

   @Override
   public Set<Class<?>> getClasses() {
      return empty;
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}
