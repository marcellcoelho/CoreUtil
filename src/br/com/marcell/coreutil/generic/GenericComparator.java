package br.com.marcell.coreutil.generic;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.marcell.coreutil.enumeration.SortTypeEnum;
import br.com.marcell.coreutil.reflexao.Reflexao;

@SuppressWarnings({ "unchecked", "rawtypes" })  
public class GenericComparator<T extends GenericEntity> implements Comparator<T> {  
     
   private SortTypeEnum sortType   = null;  
   private String   methodName = null;  
     
   /** 
    * Constr�i um GenericComparator de acordo com o campo e o tipo  
    * @param sortField Campo para ordena��o 
    * @param sortType Tipo de ordena��o, ascendente (ASC) ou descendente (DESC) 
    */  
   public GenericComparator(String sortField, SortTypeEnum sortType) {  
      this.sortType   = sortType;  
      this.methodName = Reflexao.buildGetMethodName(sortField);
   }  
  
   @Override  
   public int compare(T o1, T o2) {  
      try {  
         Method method1 = o1.getClass().getMethod(this.methodName,new Class[]{});  
         Comparable comp1 = (Comparable) method1.invoke(o1, new Object[]{});  
           
         Method method2 = o1.getClass().getMethod(this.methodName, new Class[]{});  
         Comparable comp2 = (Comparable) method2.invoke(o2, new Object[]{});  
           
         return comp1.compareTo(comp2) * this.sortType.getIndex();  
      } catch (Exception e) {  
         e.printStackTrace();  
         throw new RuntimeException(e.getMessage());  
      }  
   }  
     
   /** 
    * Organiza um List<T> de acordo com o campo e o tipo (ASC ou DESC) 
    *  
    * @param <T> Classe dos objetos que ser�o ordenados 
    * @param list List<T> a ser ordenada 
    * @param sortField Campo para ordena��o 
    * @param sortType Tipo da ordena��o (ASC ou DESC) 
    */  
      
   public static <T extends GenericEntity> void sortList(List<T> list, String sortField,   
         SortTypeEnum sortType) {  
      GenericComparator<T> comparator = new GenericComparator<T>(sortField,   
            sortType);  
      Collections.sort(list, comparator);  
   }  
     
   /** 
    * Organiza um T[] de acorco com o campo e o tipo (ASC ou DESC) 
    *  
    * @param <T> Classe dos objetos que ser�o ordenados 
    * @param array  T[] a ser ordenado 
    * @param sortField Campo para ordena��o 
    * @param sortType Tipo da ordena��o (ASC ou DESC) 
    */  
       
   public static <T extends GenericEntity> void sortArray(T[] array, String sortField,   
         SortTypeEnum sortType) {  
      GenericComparator<T> comparator = new GenericComparator<T>(sortField,   
            sortType);  
      Arrays.sort(array, comparator);  
   }  
}  