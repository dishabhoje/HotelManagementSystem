import java.util.Date;
public class Guest extends Hotel {
	node RoomDetails()
	{
		node ptr2=Search();
		int flag=0;
		if(ptr2!=null)
		{
			//System.out.println("\n============================================\n");
			displayRoom(ptr2, ptr2.rent);
			
			do {
				node ptr3=Search();
				System.out.println("Book Room press 1/0");
				int ch=sc.nextInt();
				if(ch==0 || ch==1)
				{
					flag=0;
				}
				else {
					System.out.println("Invalid input");
					flag=1;
				}
				if(ptr3!=null && ptr3.allot==0 && ch==1)
				{
					ptr3.allot=ch ;
					System.out.println("Room Booked");
				}
				else {
					System.out.println("Room Not Available");
				}
				
				}while(flag==1);
		}
		else {
			System.out.println("Room Not Available");
		}
		
		return ptr2;
	}

	void Checkout()
	{
		System.out.println("Number of days you stayed : ");
		int n=sc.nextInt();
		System.out.println("-------------Final Payment------------");
		node ptr=RoomDetails();
		double gst=0,cgst=0,amt=0;
		cgst=(ptr.rent)*(2/100);
		gst=(ptr.rent)*(10/100);
		amt=(ptr.rent*n)+gst+cgst;
		System.out.println("==================================================");
		System.out.println("Final Amount  : "+amt);
		System.out.println("\n\nThank you for choosing us ...\n\nSee you Soon....");

	}

	void Order()
	{
		String Menu[]= {"Noodle","Pizza","Burger","Pasta","Lunch","Dinner","Desert"};
		int prize[]= {100,500,250,250,500,600,100};
		int k=0,ono=0;
		int order[]=new int[10];
		do {
			System.out.println("Order no"+"\tMenu\t\tPrize");
			System.out.println("----------------------------------------------");
			for(int i=0;i<Menu.length;i++)
			{
				System.out.println((i+1)+"\t\t"+Menu[i]+"\t\t"+prize[i]);
			}
			System.out.println("Please Enter order : ");
			ono=sc.nextInt();
			order[k]=ono;
			k++;
			System.out.println("Do you want to order again (press 1): ");

		}while(sc.nextInt()==1);

		double total=0,cgst=0,gst=0,amt=0;
		System.out.println("====================================================");
		System.out.println("Your order numbers are : ");

		for(int i=0;i<k;i++)
		{
			System.out.println(order[i]);
			total=total+(prize[order[i]-1]);
		}
		gst=total*0.06;
		cgst=total*0.06;
		amt=gst+cgst+total;
		System.out.println("------------------Bill------------------");
		System.out.println(" Date : "+new Date());
		for(int i=0;i<k;i++)
		{
			System.out.println(Menu[order[i]-1]+"\t\t: "+prize[order[i]-1]);
		}
		System.out.println("\n\nTotal \t\t: "+total);
		System.out.println("CGST  \t\t: "+cgst);
		System.out.println("GST   \t\t: "+gst);
		System.out.println("=========================================");
		System.out.println("Final Amount  \t: "+amt);
		System.out.println("\n\tThank you ...Hope you enjoy food...");
	}



}