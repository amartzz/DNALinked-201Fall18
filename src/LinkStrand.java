
public class LinkStrand implements IDnaStrand
{
/**
 * node class, allows us to use nodes within linkedlist
 */
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	/**
	 * intialize variables
	 * myFirst is first node
	 * myLast is last node
	 * mySize is number of chars in all nodes
	 * myAppends # of times append called
	 * myIndex is last index called in my char at
	 * myLocalIndex is the index with a node
	 * myCurrent is the current node
	 */
	private Node myFirst,myLast; //first node, last node
	private long mySize; //number of chars stored in all nodes
	private int myAppends; //number of times append has been called
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;

	/**
	 * class constructor
	 * @param empty
	 */
	
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
	/**
	 * @param empty
	 * @return int mySize which is size of LinkedList
	 */
	@Override
	public long size() {
		return mySize;
	}
	/**
	 * add values to initialized values
	 * does not return anything
	 */
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
	/**
	 * returns LinkStrand that contains source
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	/**
	 * creates one new node, updates instance variables to maintain class invariants
	 * @param String dna
	 * @return updates LinkStrand with new node
	 */
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

	/**
	 * empty param
	 * reverses the content within each node, reverses order of nodes
	 * @return a new reversed linked list
	 */
	@Override
	public IDnaStrand reverse() {
		StringBuilder first = new StringBuilder(myFirst.info);
		first=first.reverse();
		String firstString= first.toString();
		Node last= new Node(firstString);
		Node next= myFirst.next;
		Node prev= last;
		
		
		while (next!=null) {
			StringBuilder cop = new StringBuilder(next.info);
			cop=cop.reverse();
			String copString= cop.toString();
			last= new Node(copString);
			last.next=prev;
			prev=last;
			next=next.next;
			
			}
		LinkStrand reverseLS= new LinkStrand();	
		while(last!= null) {
			reverseLS.append(last.info);
			last=last.next;
		}
		return reverseLS;
		
	}
	

/**
 * empty param
 * @returns the number of times appends has been called
 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	/**
	 * @param int index, index to search at
	 * check for negative or out of bounds index
	 * check if index requested is greater than myIndex, need to start at 0
	 * @return character
	 */
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
	/**
	 * empty param
	 * writes toString method for StringBuilder objects
	 * @return String
	 */
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
