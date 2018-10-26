
public class LinkStrand implements IDnaStrand
{

	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst,myLast; //first node, last node
	private long mySize; //number of chars stored in all nodes
	private int myAppends; //number of times append has been called
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;


	// constructors
	public LinkStrand(){
		this("");
	}
	/**
	 * Create a strand representing s. No error checking is done to see if s
	 * represents valid genomic/DNA data.
	 * 
	 * @param s
	 *            is the source of cgat data for this strand
	 */
	public LinkStrand(String s) {
		initialize(s);
	}

	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst= new Node(source);
		myLast= myFirst;
		myAppends=0;
		mySize=source.length();
		myIndex=0;
		myCurrent=myFirst;
		myLocalIndex=0;
		

	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		//update mySize
		mySize= mySize+dna.length();
		//put string into node
		Node newNode = new Node(dna);
		myLast.next= newNode;
		myLast=myLast.next;
		myAppends++;
		
		return this;
	}
	
	
	
	
//create new linkstrand
//reverse copy, add in front of the first one
//copy--place into node
	//say the thing after new copy node (.next) = myFirst
	// myFirstreversed= new copy node you just added
	//create new linkstrand
	// <- make sure reversed
	@Override
	public IDnaStrand reverse() {
			
		
		
		//Node firstcopNode= new Node(copString);
		
		Node current= myFirst;
		//Node head=myFirst;
		Node prev= null;
		Node next= null;
		//LinkStrand reverseLS= new LinkStrand(myFirst.info);
		
		
		while (current.next!=null) {
			next= current.next;
			StringBuilder cop = new StringBuilder(next.info);
			cop=cop.reverse();
			String copString= cop.toString();
			Node rNode= new Node(copString);
			next=rNode;		
			
			
			
			current.next=prev;
			
			
			prev=current;
			current=next;
			
			}
		LinkStrand reverseLS= new LinkStrand(current.info);		
		return reverseLS;
		
	}
	


	@Override
	public int getAppendCount() {
		return myAppends;
	}
//to be completed
	//check for bigger and negative indexes
	
	//if index given is bigger than size of link strand, reinitialize index and lcoal index to zero, myCurrent=myFirst
	@Override
	public char charAt(int index) {
		if (index>= mySize || index<0) {
			throw new IndexOutOfBoundsException();
		}
		if (myIndex >index){
			//start over, needs to go backwards
			myCurrent=myFirst;
			myIndex=0;
			myLocalIndex=0;
			
		}
		
		while (myIndex!= index) {
			myIndex++;
			myLocalIndex++;
			
			//mycurrent.info.length is the total length of node
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent=myCurrent.next;
			}
		}
		//saves index from previous call
		myIndex= index;
           return myCurrent.info.charAt(myLocalIndex);
        }
	@Override
	public String toString() {
		StringBuilder aList= new StringBuilder();
		Node current=myFirst;
		while (current!=null) {
			aList.append(current.info);
			current=current.next;
			}
		return aList.toString();
	}	
	


}
