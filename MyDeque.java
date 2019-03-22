import java.util.Arrays;
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
  //add to front, so push everything back one.
  public void addFirst(E element){
    if (element == null){
      throw new NullPointerException("no null values allowed");
    }
    if (start > 0 && (end < (start-1) || end > start)){ // if there is space in front of start
      data[start - 1] = element;
      start--; //cuz you're adding to front, move start closer to the front
      size++;
    }else if (start<end && end < (data.length - 1)){ //if they're in the right order and end doesn't occupy entire array
      data[data.length - 1] = element;
      start = data.length - 1; //move start to the back
      size++;
    }else if (size == data.length){ //at capacity
      resize(0); //make more spaces, and also indicate to keep first position free so u can add there
      data[0] = element; //don't have to modify start, start was and now still is poitning to 0
      size++;
    }
  }
  private void resize(){ //just makes data 2 times as big + 1
    data = Arrays.copyOf(data, (data.length * 2 + 1));
  }
  private void resize(int keepEmpty){ //should modify data to be resized but still contain space for thing to be added
    data1 = (E[])new Object[(data.length * 2 + 1)];
    for (int i = 0; i<keepEmpty; i++){
      data1[i] = data[i];
    }
    for (int k = (keepEmpty+1); k<(data.length); k++){
      data1[k] = data[k];
    }
    data = data1;
  }
  public void addLast(E element){
    if (element == null){
      throw new NullPointerException("no null vals");
    }
    if (end < (data.length-1) && (start >= (end+1) || end > start)){//if there is room after end
      data[end+1] = element;
      end++;
      size++;
    }else if(end == (data.length - 1) && start != 0){ //so start shouldn't be at 0 and there's space to move in front of start
      data[0] = element;
      end = 0;
      size++;
    }else if (size == data.length){
      int toAdd = data.length
      resize(toAdd);
      data[toAdd] = element;
      size++;
    }
  }
  public E removeFirst(){
    int toReturn;
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
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    int toReturn;
    if (size == 0){
      throw new NoSuchElementException("deque empty!");
    }
    toReturn = data[end];
    if (end > 0){
      data[end] = null;
      end--;
    }else if (data == 0){
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
