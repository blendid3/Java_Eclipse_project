
public class Test_shiyan {
	public static void  main() {
		try {
		
			Class a=int.class;
			   Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}
	}

}
