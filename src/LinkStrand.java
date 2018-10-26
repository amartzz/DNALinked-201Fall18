
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
	
	@Override
	public String toString() {
		StringBuilder dnaList= new StringBuilder();
		Node current=myFirst;
		while (current!=null) {
			dnaList.append(current.info);
			current=current.next;
			}
		return dnaList.toString();
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
		Node current= myFirst;
		StringBuilder firstOne = new StringBuilder(current.info);	
		firstOne.reverse();
		String thing= firstOne.toString();
		//need to get first one into node, then add to link strand
		LinkStrand reverseLS= new LinkStrand();
		current=current.next;
		//update size of link strand
			
		while (current!=null) {
			StringBuilder copy = new StringBuilder(current.info);
			copy.reverse();
			String rDna= copy.toString();
		//this line doesnt work, unclear why
		//	Node newReversed= new Node(copy);
	//rest of code should work once the copy is placed into newReversed
					
			current=current.next;
		}
			
		
		
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
			if (myIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent=myCurrent.next;
			}
		}
		//saves index from previous call
		myIndex= index;
           return myCurrent.info.charAt(myLocalIndex);
        }

	


}
