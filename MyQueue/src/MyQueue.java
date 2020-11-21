
public class MyQueue {
	MyStack s1;
	MyStack s2;

	public MyQueue(int capacity) {
	s1=new MyStack(capacity);
	s2=new MyStack(capacity);
	}
	
	public boolean enqueue(int data){
		 return s1.push(data);
	}
	public int dequeue() {
		while(true) {
			int temp=s1.pop();
			if(temp==Integer.MAX_VALUE)
				break;
			s2.push(temp);	
		}
		int back=s2.pop();
		while(true) {
			int temp=s2.pop();
			if(temp==Integer.MAX_VALUE)
				break;
			s1.push(temp);
		}
		return back;
	}
	public static void main(String args[]) 
    { 
		MyQueue q1 = new MyQueue(3);
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		System.out.println(q1.enqueue(4));
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
    } 
}
