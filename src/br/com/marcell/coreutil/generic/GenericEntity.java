package br.com.marcell.coreutil.generic;

import java.io.Serializable;

public abstract class GenericEntity
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  public abstract Serializable getIdentificador();
}