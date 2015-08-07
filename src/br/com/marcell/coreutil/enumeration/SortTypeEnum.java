package br.com.marcell.coreutil.enumeration;
public enum SortTypeEnum {  
   ASC(1), DESC(-1);  
     
   private int index = 1;  
     
      
   private SortTypeEnum(int index) {  
      this.index = index;  
   }  
     
   public int getIndex() {  
      return this.index;  
   }  
}  