import java.util.NoSuchElementException;
import java.util.Arrays;
//work on resize and also removing
//resize is faulty check terminal
public class MyDeque<E>{
  private E[] data;
  private int size = 0;
  private  int start = -1;
  private int end = -1;
  @SuppressWarnings("unchecked")
  public static void main(String[] args){
    MyDeque<Integer> test = new MyDeque();
    for (int i = 0; i<15; i++){
      if (i%2 == 0){
        test.addFirst(i);
      }else{
        test.addLast(i);
      }
      System.out.println("Current myDeque: " + test);
      System.out.println("The array: " + Arrays.toString(test.data));
    }
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
      System.out.println("in first block");
      data[0] = element;
      start = 0;
      end = 0;
      size++;
    } else if ((start == end && size == 1) || ((start == end + 1 || start == end - 1) && size < data.length)){
      System.out.println("in second block");
      start = data.length - 1;
      data[start] = element;
      size++;
    } else if (start > (end + 1)){
      System.out.println("in third block");
      start--;
      data[start] = element;
      size++;
    } else if (start > 0 && start < end){
      System.out.println("in fourth block");
      start--;
      data[start] = element;
      size++;
    } else if ((start == end + 1 || start == end - 1) && size == data.length){
      System.out.println("in fifth block");
      System.out.println("resizing");
      resize(0); //problem here -- went to this even when there is space to move back.
      start = 0; //rework this if clause
      data[start] = element;
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
  //REASSIGN FOR LOOP VARIABLES
  private void resize(int keepEmpty){ //should modify data to be resized but still contain space for thing to be added
    int k = 0; //this is the index of the data
    E[] toAssign = (E[])new Object[(data.length * 2 + 1)];
    System.out.println("\ntoAssign length: " + toAssign.length);
    if (start > end){
      int startAmount = (data.length - (1+start));
      int endAmount = (start);
      System.out.println("start is greater than end so: ");
      for (int i = 0; i<startAmount; i++){ //this is the index in the new data
        if (i != keepEmpty){
          toAssign[i] = data[k];
          k++;
          System.out.println("at the " + k + "th index of the MyDeque, copying over into index " + i + " of toAssign");
        }else{
          System.out.println("didn't copy over because " + i + " equals keepEmpty (" + keepEmpty + ")");
        }
      }
      for (int j = startAmount; j <= (startAmount+endAmount); j++){
        if (j != keepEmpty){
          toAssign[j] = data[k];
          k++;
          System.out.println("at the " + k + "th index of the MyDeque, copying over into index " + j + " of toAssign");
        }else{
          System.out.println("didn't copy over because " + j + " equals keepEmpty (" + keepEmpty + ")");
        }
      }
    }else{ //start where ti should be
      System.out.println("start is less than end so");
      for (int l = start; l <= end; l++){
        if (l != keepEmpty){
          toAssign[l] = data[k];
          k++;
          System.out.println("at the " + k + "th index of the MyDeque, copying over into index " + l + " of toAssign");
        }else{
          System.out.println("didn't copy over because " + l + " equals keepEmpty (" + keepEmpty + ")");
        }
      }
    }
    data = toAssign;
  }
  public void addLast(E element){
    System.out.println("Adding Last " + element);
    if (element == null){
      throw new NullPointerException("no null vals");
    }
    if (size == 0 && start == -1 && end == -1){
      System.out.println("in first block");
      data[0] = element;
      start = 0;
      end = 0;
      size++;
    }else if ((start == end && size == 1) || end == start + 1 || end == start - 1){
      System.out.println("in second block");
      end++;
      data[end] = element;
      size++;
    }else if(end < (start - 1)){
      System.out.println("in third block");
      end++;
      data[end] = element;
      size++;
    }else if (end > start && end < (data.length-1)){
      System.out.println("in fourth block");
      end++;
      data[end] = element;
      size++;
    }else if ((end == start + 1 || end == start - 1) && size == data.length){ //resize
      System.out.println("in fifth block");
      int toAdd = data.length;
      System.out.println("resizing");
      resize(toAdd);
      end = toAdd; //toAdd should == size actually
      data[end] = element;
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
