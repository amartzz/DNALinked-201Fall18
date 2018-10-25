
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
	public IDnaStrand reverse() {
		
		//copy.reverse();
		//StringStrand ss = new StringStrand(copy.toString());
		//just dont know how to reverse the order of nodes
		Node current= myFirst;
		//ListNode reverseList = new ListNode(dna);
		/*while (current!=null) {
			StringBuilder copy = new StringBuilder(current.info);
			copy.reverse();
			copy.toString();
			Node newReversed= new Node(copy);
			current.append(copy);
			//reverseList=reverseList.next;			
			current=current.next;
		}
			*/
		
		
		return null;
		
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}
//to be completed
	@Override
	public char charAt(int index) {
		
		int count = 0;
		int dex = 0;
		Node list = myFirst;
		while (count != index) {
			count++;
			dex++;
			if (dex >= list.info.length()) {
				dex = 0;
				list = list.next;
			}
		}
		//saves index from previous call
		myIndex= index;
           return list.info.charAt(dex);
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

}
