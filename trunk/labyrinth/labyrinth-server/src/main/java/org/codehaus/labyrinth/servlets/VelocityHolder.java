package org.codehaus.labyrinth.servlets;

import org.apache.velocity.app.VelocityEngine;

/**
 * @author Ben Walding
 *
 */
public class VelocityHolder
{
  private static final VelocityHolder instance = new VelocityHolder();
  private VelocityHolder()
  {
  }

  public static VelocityHolder instance()
  {
    return instance;
  }
  
  
  private VelocityEngine ve = new VelocityEngine();
  public VelocityEngine getEngine(String key) {
      return ve;
  }
}
