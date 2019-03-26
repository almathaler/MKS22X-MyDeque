
public class Calculator{
    public static void main(String[] args){
      String s = "11 3 - 4 + 2.5 *";
      //eval(s);
      System.out.println("11 3 - 4 + 2.5 * + is:  " + eval(s));
    }
    /*Evaluate a postfix expression stored in s.
     *Assume valid postfix notation, separated by spaces.
     */
    public static double eval(String s){
      MyDeque<String> queue = new MyDeque<String>(s.length());
      String[] postfix = s.split(" ");
      for (String st : postfix){
        queue.addLast(st);
      }
      //System.out.println("the queue: " + queue.toString());
      int size = queue.size();
      //int size = postfix.length;
      MyDeque<Double> stack = new MyDeque<Double>(size); //for the numbers
      boolean working = true;
      //int i = 0
      while (working){
        //System.out.println("getFirst: " + queue.getFirst());
        String addS = queue.removeFirst();
        //System.out.println("addS: " + addS + " at index: " + i);
        try{
          Double toAdd = Double.parseDouble(addS);
          stack.addLast(toAdd);
        } catch (NumberFormatException e){
          queue.addFirst(addS);
          //System.out.println("addS wasn't a number, added it back to queue");
        }
        //System.out.println("queue now: " + queue.toString() + " stack: " + stack.toString());
        //REMEMBER EVEN THO UR GETTING FROM LAST TO FRONT, U DO THE
        //OPERATIONS FROM FRONT TO BACK! SO THAT'S WHY IT IS
      //B TO A
        if (queue.getFirst().equals("+") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b+a;
          queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (queue.getFirst().equals("-") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b-a;
          queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (queue.getFirst().equals("*") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b*a;
          queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (queue.getFirst().equals("/") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b/a;
          queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }else if (queue.getFirst().equals("%") && stack.size() >= 2){
          Double a = stack.removeLast();
          Double b = stack.removeLast();
          Double toAdd = b%a;
          queue.removeFirst(); // the opperator
          stack.addLast(toAdd);
        }
        if (queue.size() == 0){
          working = false;
        }
      }
      return stack.getLast();
    }
}
