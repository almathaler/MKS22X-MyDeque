
public class Calculator{
    public static void main(String[] args){
      String s = "1 2 3 4 5 + * - -";
      System.out.println("1 2 3 4 5 + * - -  is:  " + eval(s));
    }
    /*Evaluate a postfix expression stored in s.
     *Assume valid postfix notation, separated by spaces.
     */
    public static double eval(String s){
      MyDeque<String> queue = new MyDeque<String>(s.length()); //create a new queue to hold the tokens
      //write the string into the queue
      String[] postfix = s.split(" ");
      //for (String st : postfix){
      //  queue.addLast(st);
      //}
      //create a stack that will hold the numbers ( say u have {1 2 3 4 - + *}, stack will store 1 2 3 4 and
      //then when u hit an opperator, will use 3 and 4)
      //int size = queue.size();
      MyDeque<Double> stack = new MyDeque<Double>(postfix.length); //for the numbers
      //boolean working = true;
      //stops working when queue size is 0 / you have gone through all the elements
      for (int i = 0; i<postfix.length; i++){
        //String addS = queue.removeFirst();
        //see if the element is a number
        String addS = postfix[i];
        try{
          //if yes then add it to the stack
          Double toAdd = Double.parseDouble(addS);
          stack.addLast(toAdd);
        } catch (NumberFormatException e){
          //else keep it in the queue
          //queue.addFirst(addS);
          //don't do anything... keep that value in the array
        }
        //here are the operations
        //if you've hit an opperator and there are 2 or more #s in the stack
        //if (queue.getFirst().equals("+") && stack.size() >= 2){
        if (postfix[i].equals("+") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          //the order is b then a, since although we are picking up from the top of the stack we still need to
          //order apperation as leftmost number (operation) right digit
          Double toAdd = b+a;
          //queue.removeFirst(); // the opperator -- don't have to remove anything cuz index goes further
          stack.addLast(toAdd);
        }else if (postfix[i].equals("-") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b-a;
          //queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (postfix[i].equals("*") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b*a;
          //queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (postfix[i].equals("/") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b/a;
          //queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (postfix[i].equals("%") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b%a;
          //queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }
        //if (queue.size() == 0){
        //  working = false;
        //}
      }
      return stack.getLast();
    }
}
