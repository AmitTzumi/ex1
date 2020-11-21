
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
		if(s1.pop()==Integer.MAX_VALUE)
			return Integer.MAX_VALUE;	
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
}
