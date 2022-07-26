import java.util.*;

class node
{
	node root;
	node lp,hp;
    int rent,roomNum;
	String type;
	int numPeople;
	int allot=0;

	public node(int rent,int roomNum,String type,int numPeople , int allot)
	{
		lp =hp=null;
		this.rent=rent;
		this.roomNum=roomNum;
		this.numPeople=numPeople;
		this.type=type;
		this.allot=allot=0;
	}
}

public class Hotel {

	Scanner sc=new Scanner(System.in);
	Scanner sc1=new Scanner(System.in);
	Stack<node>s=new Stack<>();
	node root,temp;
	public Hotel()
	{
		root=null;
	}

	
	node RoomInfo()
	{
		System.out.println("Enter room number : ");
		int RoomNo=sc.nextInt();
		System.out.println("Enter Rent of room : ");
		int rent=sc.nextInt();
		System.out.println("Enter type of room :"); 
		String type1=sc1.nextLine();
		System.out.println("Enter number of people (max 4) :");
		int numPeople=sc.nextInt();
		temp=new node(rent,RoomNo,type1,numPeople,0);
		if(root==null)
		{
			root=temp;
		}
		else {
			node ptr=root;
			while(ptr!=null)
			{
				if(temp.rent>ptr.rent)
				{
					if(ptr.hp==null)
					{
						ptr.hp=temp;
						System.out.println(ptr.hp.rent + "  is attached to hp of  " + ptr.rent);
						break;
					}
					else {
						ptr=ptr.hp;;
					}


				}
				else
				{
					if(ptr.lp==null)
					{
						ptr.lp=temp;
						System.out.println(ptr.lp.rent + "  is attached to lp of  " + ptr.rent);
						break;
					}
					else {
						ptr=ptr.lp;
					}
				}
			}
		}
		return temp;

	}

	public void print(node ptr)
	{	if(ptr!=null && ptr.allot==0) {
		System.out.println(" Room Available ");
		System.out.println("Room No :  		 "+ptr.roomNum);
		System.out.println("Room Type :		 "+ptr.type);
		System.out.println("Room Rent :		 "+ptr.rent);
		System.out.println("Number of People:  "+ptr.numPeople);
	}
	else {
		System.out.println("Room Not Available");
	}
	}

	public void displayRoom(node ptr,int rent)
	{
		while(ptr!=null)
		{
			if( ptr.rent<=rent)
			{
				System.out.println("========================================");
				System.out.println("Room No :  		 "+ptr.roomNum);
				System.out.println("Room Type :		 "+ptr.type);
				System.out.println("Room Rent :		 "+ptr.rent);
				System.out.println("Number of People:  "+ptr.numPeople);
				if(ptr.lp!=null)
				{
					ptr=ptr.lp;	
				}
				else {
					ptr=ptr.hp;
				}
				
			}	
		}		
	}
	public node Search() {
		node ptr=root;

		System.out.println("Enter Rent(Budget) of room : ");
		int r=sc.nextInt();
		while(ptr!=null && ptr.allot==0) {
			if(ptr.rent==r) {
				return ptr;
			}
			else if(ptr.rent<r) {
				ptr=ptr.hp;
			}
			else if(ptr.rent>r) {
				ptr=ptr.lp;
			}
		}
		return null;


	}

	node parent()
	{
		node ptr=Search();
		node parent=null;
		int rent=ptr.rent;
		while(ptr.lp!=null)
		{
			print(ptr);
			parent=ptr;
			ptr=ptr.lp;
		}
		ptr=parent.hp;
		if(rent>ptr.rent)
		{
			ptr=ptr.hp;
		}
		return ptr;
	}
	
	node Update()
	{
		System.out.println("Enter Details of room to search ");
		node ptr2=Search();
		System.out.println("Enter the updated Rent: ");
		int updRent=sc.nextInt();
		System.out.println("Enter the updated Room number: ");
		int updNum=sc.nextInt();
		System.out.println("Enter the updated Type: ");
		String updType=sc1.nextLine();
		System.out.println("Enter the updated Number of peoplr allowed in room: ");
		int updPeo=sc.nextInt();

		ptr2.rent=updRent;
		ptr2.type=updType;
		ptr2.numPeople=updPeo;
		ptr2.roomNum=updNum;
		ptr2.allot=0;
		return ptr2;
	}
	node Display()
	{
		if(root==null)
		{
			return null;
		}
		node cur=root;
		while(cur!=null||s.size()>0)
		{
			while(cur!=null)
			{
				s.push(cur);
				cur=cur.lp;
			}
			cur=s.pop();
			System.out.println("\n============================================\n");
			System.out.println("Room No :  		 "+cur.roomNum);
			System.out.println("Room Type :		 "+cur.type);
			System.out.println("Room Rent :		 "+cur.rent);
			System.out.println("Number of People:"+cur.numPeople);

			cur=cur.hp;
		}
		return cur;
	}

	void Delete(int key)
	{
		node ptr,parent;
		ptr=root;
		parent=null;
		while(ptr!=null && ptr.rent!=key) {
			parent=ptr;
			if(key>ptr.rent) {
				ptr=ptr.hp;
			}
			else if(key<ptr.rent) {
				ptr=ptr.lp;
			}
		}
		if(ptr.lp==null && ptr.hp==null) {
			if(ptr!=root) {
				if(parent.hp==ptr) {
					parent.hp=null;
				}
				else {
					parent.lp=null;
				}
			}
			else  {
				root=null;
			}
		}


		else if(ptr.hp!=null && ptr.lp!=null) {
			node successor=null;
			if(root==null) {                                //condition to check if the tree is empty
				System.out.println("Room is not found");
				return;
			}
			ptr=root;
			while(ptr!=null||s.isEmpty()!=true) {
				while(ptr!=null) {
					s.push(ptr);
					ptr=ptr.hp;	
				}
				ptr=s.pop();
				if(ptr.rent==key) {
					successor=s.pop();
					break;
				}
				ptr= ptr.lp;
			}
			int room_rent=successor.rent;
			int room_no=successor.roomNum;
			int people=successor.numPeople;
			String room_type=successor.type;
			int allotment=successor.allot;
			Delete(successor.rent);
			ptr.rent=room_rent;
			ptr.numPeople=people;
			ptr.roomNum=room_no;
			ptr.type=room_type;
			ptr.allot=allotment;

		}
		else {
			node child=null;
			if(ptr.hp!=null) {
				child=ptr.hp;
			}
			else {
				child=ptr.lp;
			}
			if(ptr!=root) {
				if (ptr== parent.hp) {
					parent.hp = child;
				}
				else {
					parent.lp = child;
				}
			}
			else {
				root=child;
			}

		}

	}





}

