/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package test.org.jboss.forge.furnace.classpath;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.arquillian.archive.AddonArchive;
import org.jboss.forge.furnace.util.OperatingSystemUtils;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assume.assumeTrue;

@RunWith(Arquillian.class)
public class CORBALookupTest
{
   @Deployment
   public static AddonArchive getDeployment()
   {
      AddonArchive archive = ShrinkWrap.create(AddonArchive.class)
               .addAsLocalServices(CORBALookupTest.class);

      return archive;
   }

   @Test
   public void testGetJDKProvidedCORBA()
   {
      assumeTrue(OperatingSystemUtils.isJava8());
      try
      {
         getClass().getClassLoader().loadClass("javax.rmi.CORBA.Tie");
         getClass().getClassLoader().loadClass("javax.rmi.CORBA.ClassDesc");
         getClass().getClassLoader().loadClass("javax.rmi.CORBA.PortableRemoteObjectDelegate");
         getClass().getClassLoader().loadClass("org.omg.CORBA.Any");
      }
      catch (Exception e)
      {
         Assert.fail("Could not load required CORBA class." + e.getMessage());
      }
   }
}
