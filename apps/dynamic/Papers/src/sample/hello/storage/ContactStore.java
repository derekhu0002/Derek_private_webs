package sample.hello.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample.hello.bean.Paper;

public class ContactStore {
	private static Map<String,Paper> store;
	private static Connection connection = null;
	private ContactStore() {

	}
	
	public static void finalizepaper(){
		System.out.println("[derek]contextDestroyed!");
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void init(){
		store = new HashMap<String,Paper>();
		Paper c = new Paper();
	     try {
         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序
         //Class.forName("org.gjt.mm.mysql.Driver");
         System.out.println("Success loading Mysql Driver!");
         }
	     catch (Exception e) {
	    	 System.out.print("Error loading Mysql Driver!");
	    	 e.printStackTrace();
	      }
		//context.setAttribute("user1", user);
	     try {
	    	connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test","root","");
			
			System.out.println("Success connect Mysql server!");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from paper");
			
			while (rs.next()) {
				c.setId(rs.getString("id"));
				System.out.println(c.getId());
				
				c.setName(rs.getString("name"));
				System.out.println(c.getName());
				
				c.setCategory(rs.getString("category"));
				System.out.println(c.getCategory());
				
				c.setContent(rs.getString("content"));
				System.out.println(c.getContent());
				
				store.put(c.getId(), c);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void update(Paper paper){
		System.out.println("dere ContactStore update!");
		store.put(paper.getId(), paper);
		updateDB(paper);
	}
	
	public static void create(Paper paper){
		System.out.println("dere ContactStore create!");
		store.put(paper.getId(), paper);
		insertDB(paper);
	}
	
	public static void delete(String id){
		System.out.println("dere ContactStore delete!");
		store.remove(id);
		deleteDB(id);
	}
	
	public static void deleteALL(){
		System.out.println("dere ContactStore deleteALL!");
		List<Paper> contacts = new ArrayList<Paper>();
		contacts.addAll(store.values());
		
		for(int i = 0;i<contacts.size();i++){
			store.remove(contacts.get(i).getId());
			deleteDB(contacts.get(i).getId());
		}

	}
	
	public static Map<String,Paper> getStore(){
		return store;
	}
	
	private static int updateDB(Paper paper) {
	    int i = 0;
	    String sql = "update paper set name ='" + paper.getName() + "',category = '" + paper.getCategory() + "',content = '" + paper.getContent() + "' where id='" + paper.getId() + "'";
	    PreparedStatement pstmt;
	    //System.out.println(" dere -- update");
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	private static int insertDB(Paper paper) {
	    int i = 0;
	    String sql = "insert into paper (id,name,category,content) values(?,?,?,?)";
	    PreparedStatement pstmt;
	    //System.out.println("dere -- insert");
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        pstmt.setString(1, paper.getId());
	        pstmt.setString(2, paper.getName());
	        pstmt.setString(3, paper.getCategory());
	        pstmt.setString(4, paper.getContent());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	private static int deleteDB(String id) {
	    int i = 0;
	    String sql = "delete from paper where id='" + id + "'";
	    PreparedStatement pstmt;
	    System.out.println("dere -- delete");
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
}
