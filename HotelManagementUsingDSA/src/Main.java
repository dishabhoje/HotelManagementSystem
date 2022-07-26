
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		Guest h=new Guest();
		int choice=0,choice1=0;
		String c1;
		Statement stmt = null;
		String query;
		String USER = "root";
		String PASS = "123456789";
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/hotelmanagement";
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(Exception e) {

		}

		do {
			System.out.println("\t\t1]Hotel Management \n\t\t2]Customer");
			System.out.println("Choose correct option : ");
			int op=sc.nextInt();

			switch(op)
			{
			case 1->{
				do {
					System.out.println("\n\t\t(I)nsertion of Room Info\n\t\t(U)pdate Details\n\t\t(S)earch\n\t\t(Di)splay Details\n\t\tDelete (R)oom\n(P)arent ");
					System.out.println("Please Enter the valid Command : ");
					c1=sc.next();
					switch(c1)
					{
					case "I"->{
						node ptr=h.RoomInfo();
						PreparedStatement pt;
						try {
							Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
							pt = conn.prepareStatement("insert into Hotel values (?,?,?,?,?)");
							pt.setInt(1, ptr.rent);
							pt.setInt(2, ptr.roomNum);
							pt.setString(3, ptr.type);
							pt.setInt(4,ptr.numPeople);
							pt.setInt(5, ptr.allot);
							int i=pt.executeUpdate();
							if(i>0)
							{
								System.out.println("Record inserted");
							}
						} catch (SQLException e) {
							System.out.println("Failed to insert record");
							e.printStackTrace();
						}  //rent,RoomNo,type1,numPeople,0
						
						
					}
					//update madhe rent of room to be updated ch code lihaych rahilay
					case "U"->{
						node ptr=h.Update();
						PreparedStatement pt;
						try {
							Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
							pt=conn.prepareStatement("update hotel set rent=?, roomNum=?,type=?,numPeople=?,allot=? where rent=?");
							pt.setInt(1, ptr.rent);
							pt.setInt(2, ptr.roomNum);
							pt.setString(3, ptr.type);
							pt.setInt(4,ptr.numPeople);
							pt.setInt(5, ptr.allot);
							int i=pt.executeUpdate();
							if(i>0)
							{
								System.out.println("Record inserted");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					case "S"->{
						node ptr=h.Search();
						h.print(ptr);
					}
					case "Di"->{
						//node ptr=h.Display();
						PreparedStatement pt;
						try {
							Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
							pt=conn.prepareStatement("select * from Hotel");
							ResultSet rs=pt.executeQuery();
							while(rs.next())
							{
								int rno=rs.getInt("roomNum");
								String type=rs.getString("type");
								int rent=rs.getInt("rent");
								int numPeople=rs.getInt("numPeople");
								System.out.println("\n============================================\n");
								System.out.println("Room No :  		 "+rno);
								System.out.println("Room Type :		 "+type);
								System.out.println("Room Rent :		 "+rent);
								System.out.println("Number of People:"+numPeople);
							}
							rs.close();
							stmt.close();
							conn.close();
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						
					}
					case "R"->{
						System.out.println("Enter Rent of room to be deleted : ");
						int key=sc.nextInt();
						h.Delete(key);
						System.out.println("Room removed from service Successfully...");
					}
					case "P"->{
						h.parent();
					}
					}
					System.out.println("Do you want to continue with Hotel Changes : press 1 ");
					choice1=sc.nextInt();
				}while(choice1==1);
			}

			case 2->{
				do {
					System.out.println("\n\t\tWelcome to Infinex Hotel ");
					System.out.println("\n\t\tRoom (D)etails\n\t\tFood (O)rder\n\t\t(C)heckout");
					System.out.println("Please Enter the valid Command : ");
					String c=sc.next();
					switch(c)
					{
					case "D"->{
						h.RoomDetails();
					}
					case "O"->{
						h.Order();
					}
					case "C"->{
						h.Checkout();
					}
					}
					System.out.println("Do you want to continue searching room: press1");
				}while(sc.nextInt()==1);

			}
			default ->{
				System.out.println("*** Invalid Input ***");
			}
			}

			System.out.println("Do you want to Continue : press 1");
			choice=sc.nextInt();
		}while(choice==1);
		System.out.println("Thank You.....!");

	}


}