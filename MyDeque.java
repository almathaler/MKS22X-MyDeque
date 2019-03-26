import java.util.NoSuchElementException;
import java.util.Arrays;
public class MyDeque<E>{
  private E[] data;
  private int size = 0;
  private  int start = -1;
  private int end = -1;
  @SuppressWarnings("unchecked")
  public MyDeque(){
    data = (E[])new Object[10];
  }
  public MyDeque(int initialCapacity){
    data = (E[])new Object[initialCapacity];
  }
  public int size(){
    return size;
  }
  public String toString(){
    String toReturn = "{";
    if (size == 0){
      return "{}";
    }
    if (start == end){
      toReturn += data[0];
    }else if(start < end){
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
    toReturn += "}";
    return toReturn;
  }
  //add to front, so push everything back one.
  public void addFirst(E element){
    if (element == null){
      throw new NullPointerException("no null values allowed");
    }
    if (size == 0){
      data[0] = element;
      start = 0;
      end = 0;
      size++;
    } else if (start == 0 && size < data.length){
      start = data.length - 1;
      data[start] = element;
      size++;
    } else if (start > (end + 1)){
      start--;
      data[start] = element;
      size++;
    } else if (start > 0 && start < end){
      start--;
      data[start] = element;
      size++;
    } else if ((start == end + 1 || start == end - 1) && size == data.length){
      resize(); //problem here -- went to this even when there is space to move back.
      start = 0;
      end = size-1;
      addFirst(element);
    }
  }
  private void resize(){ //just makes data 2 times as big + 1
    int k = 0; //index of toAssign
    E[] toAssign = (E[])new Object[(data.length * 2 + 1)];
    if (start > end){
      for (int i = start; i<(data.length); i++){
        toAssign[k] = data[i];
        k++;
      }
      for (int j = 0; j < start; j++){ //keep in mind that start == end-1
        toAssign[k] = data[j];
        k++;
      }
    }else{ //start where ti should be
      for (int l = start; l <= end; l++){
        toAssign[k] = data[l];
        k++;
      }
    }
    data = toAssign;
  }
  public void addLast(E element){
    if (element == null){
      throw new NullPointerException("no null vals");
    }
    //&& start == -1 && end == -1
    if (size == 0){
      data[0] = element;
      start = 0;
      end = 0;
      size++;
    }else if (start == end && size == 1){
      end++;
      data[end] = element;
      size++;
    }else if(end < (start - 1)){
      end++;
      data[end] = element;
      size++;
    }else if (end > start && end < (data.length-1)){
      end++;
      data[end] = element;
      size++;
    }else if (((end == start + 1 || end == start - 1) && size == data.length) || size == data.length){ //resize
      resize();
      start = 0;
      end = size-1;
      addLast(element);
    }
  }
  public E removeFirst(){
    E toReturn;
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    toReturn = data[start];
    if (start < (data.length-1)){ //if there is space to move start up 1
      data[start] = null;
      start++;
      size--;
    }else if (start == data.length-1){ //return start back to 0
      data[start] = null;
      start = 0;
      size--;
    }
    return toReturn;
  }
  public E removeLast(){
    E toReturn;
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    toReturn = data[end];
    if (end > 0){
      data[end] = null;
      end--;
      size--;
    }else if (end == 0){
      data[end] = null;
      end = (data.length-1);
      size--;
    }
    return toReturn;
  }
  public E getFirst(){
    if (size == 0){
      throw new NoSuchElementException("[get] deque empty!");
    }
    return data[start];
  }
  public E getLast(){
    if (size == 0){
      throw new NoSuchElementException("[get] deque empty!");
    }
    return data[end];
  }
}
