package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    static
    {
        try
        {
            Class.forName(driver);
        }
        catch(Exception ex)
        {
            System.out.println("�޷��ҵ�������!");
        }
    }
    
    public static Connection getConnection() throws Exception
    {
        if(conn==null)
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_tips?userUnicode=true&characterEncoding=UTF-8","root","151094");
            return conn;
        }
        return conn;
    }
    
    public static void main(String[] avgs){
        try {
            Connection conn = DBHelper.getConnection();
            if(conn!=null){
                System.out.println("���ݿ���������!");
            }
            else
                System.out.println("���ݿ������쳣!");
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
