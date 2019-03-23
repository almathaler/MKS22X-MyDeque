import java.util.NoSuchElementException;
import java.util.Arrays;
public class MyDeque<E>{
  private E[] data;
  private int size = 0;
  private  int start = -1;
  private int end = -1;
  @SuppressWarnings("unchecked")
  public static void main(String[] args){
    MyDeque<Integer> test = new MyDeque();
    System.out.println("Current myDeque: " + test);
    test.addFirst(2);
    System.out.println("Current myDeque: " + test);
    System.out.println("The array: " + Arrays.toString(test.data));
    test.addFirst(3);
    System.out.println("Current myDeque: " + test);
    System.out.println("The array: " + Arrays.toString(test.data));
    test.addFirst(4);
    System.out.println("Current myDeque: " + test);
    System.out.println("The array: " + Arrays.toString(test.data));
  }
  public MyDeque(){
    data = (E[])new Object[10];
    //size = 0;
  }
  public MyDeque(int initialCapacity){
    data = (E[])new Object[initialCapacity];
    //size = 0;
  }
  public int size(){
    return size;
  }
  public String toString(){
    System.out.println("start: " + start);
    System.out.println("end: " + end);
    System.out.println("size: " + size);
    String toReturn = "{";
    if (size == 0){
      return "{ }";
    }
    if (start == end){
      System.out.println("start == end");
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
    System.out.println("Adding " + element);
    if (element == null){
      throw new NullPointerException("no null values allowed");
    }
    if (size == 0 && start == -1 && end == -1){
      data[0] = element;
      start = 0;
      end = 0;
      size++;
    } else if (start == end && size == 1){
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
    } else if (start == end + 1 || start == end - 1){
      resize(0);
      start = 0;
      end = size;
      size++;
    }
  }
  private void resize(){ //just makes data 2 times as big + 1
    int k = 0;
    E[] toAssign = (E[])new Object[(data.length * 2 + 1)];
    if (start > end){
      for (int i = start; i<(data.length - 1); i++){
        toAssign[k] = data[i];
        k++;
      }
      for (int j = 0; j <= end; j++){
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
  private void resize(int keepEmpty){ //should modify data to be resized but still contain space for thing to be added
    int k = 0;
    E[] toAssign = (E[])new Object[(data.length * 2 + 1)];
    if (start > end){
      for (int i = start; i<(data.length - 1); i++){
        if (i != keepEmpty){
          toAssign[k] = data[i];
        }
        k++;
      }
      for (int j = 0; j <= end; j++){
        if (j != keepEmpty){
          toAssign[k] = data[j];
        }
        k++;
      }
    }else{ //start where ti should be
      for (int l = start; l <= end; l++){
        if (l != keepEmpty){
          toAssign[k] = data[l];
        }
        k++;
      }
    }
    data = toAssign;
  }
  public void addLast(E element){
    if (element == null){
      throw new NullPointerException("no null vals");
    }
    if (size == 0){
      data[0] = element;
      start = 0;
      end = 0;
    }else if (end < (data.length-1) && (start >= (end) || end > start)){//if there is room after end
      data[end+1] = element;
      if (size != 0){
        end++;
      }
      size++;
    }else if(end == (data.length - 1) && start != 0){ //so start shouldn't be at 0 and there's space to move in front of start
      data[0] = element;
      end = 0;
      size++;
    }else if (size == data.length){
      int toAdd = data.length;
      resize(toAdd);
      data[toAdd] = element;
      size++;
    }
  }
  public E removeFirst(){
    E toReturn;
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    toReturn = data[start];
    if (start < (data.length-1)){ //if there is space to move start up 1
      //toReturn = data[start];
      data[start] = null;
      start++;
    }else if (start == data.length-1){ //return start back to 0
      data[start] = null;
      start = 0;
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
    }else if (end == 0){
      data[end] = null;
      end = (data.length-1);
    }
    return toReturn;
  }
  public E getFirst(){
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    return data[start];
  }
  public E getLast(){
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    return data[end];
  }
}
