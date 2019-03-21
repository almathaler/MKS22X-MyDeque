public class MyDeque<E>{
  private E[] data;
  private int size, start, end;
  @SuppressWarnings("unchecked")
  public MyDeque(){
    data = (E[])new Object[10];
    size = 0;
  }
  public MyDeque(int initialCapacity){
    data = (E[])new Object[initialCapacity];
    size = 0;
  }
  public int size(){
    return size;
  }
  public String toString(){
    String toReturn = "{";
    if (start < end){
      for (int i = start; i<= end; i++){
        toReturn += (data[i] + " ");
      }
    }else{
      for (int i = start; i<data.length; i++){
        toReturn += (data[i] + " ");
      }
      for (int k = 0; k <= end; k++){
        toReturn += (data[k] + " ");
      }
    }
    return toReturn;
  }
  public void addFirst(E element){ }
  public void addLast(E element){ }
  public E removeFirst(){ }
  public E removeLast(){ }
  public E getFirst(){ }
  public E getLast(){ }
}
