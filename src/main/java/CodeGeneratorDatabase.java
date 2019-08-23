public class CodeGeneratorDatabase {
    public static String dbURL = "192.168.10.200:3306/electronic";//localhost:3306/
    public static String dbUSER = "root";
    public static String dbPass = "123456";//mysql

    public static void setOnlineDB() {
        dbURL = "203.60.1.45:3306/global_3rd_db";//localhost:3306/
        dbUSER = "root";
        dbPass = "Taihui8888#";//mysql
    }

    public static void setOfflineDB() {
        dbURL = "172.16.9.83:3306/max";
        dbUSER = "root";
        dbPass = "h9HAFvuD3w#";//
    }
}
