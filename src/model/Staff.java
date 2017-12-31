package model;

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Staff {

    private static String manv;
    private String ho;
    private String ten;
    private Date dob;
    private String sex;
    private String CMND;
    private String pw;
    private String status;
    public static Connection con = null;

    public Staff() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://localhost:1433;databaseName=QLNS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionURL);
            System.out.println("conneted");
        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap driver and connec: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi nap d and c 2:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("loi nap 3" + e.getMessage());
        }
    }

    // lay 1 nhan vien batky:
    public Staff(int MaNV) {
        this();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select MaNV, Ho, Ten,dob,CMND,PW, Status from Staff where MaNV=" + MaNV);
            System.out.println("ádasds");
            while (rs.next()) {
                manv = rs.getString("MaNV");
                ho = rs.getString("Ho");
                ten = rs.getString("Ten");
                dob = rs.getDate("dob");
                CMND = rs.getString("CMND");
                pw = rs.getString("PW");
                status = rs.getString("Status");
            }
        } catch (SQLException e) {
            System.out.println("lôi khi statement and result: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println("dong ket noi bi loi");
            }
        }
    }

    // đăng nhập
    public Staff DangNhap(int MaNV, String MatKhau) {
        Staff s = new Staff(MaNV);
        if (s.pw.equalsIgnoreCase(MatKhau)) {
            return s;
        } else {
            return null;
        }

    }

    // đổi mật khẩu
    public void DoiMatKhau(int MaNV, String MatKhauMoi) {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://localhost:1433;databaseName=QLNS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionURL);
            System.out.println("conneted");
            PreparedStatement ps = con.prepareStatement("update  Staff set PW=? where MaNV=" + MaNV);
            ps.setString(1, MatKhauMoi);// lấy ? the first
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap driver and connec: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi nap d and c 2:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("loi nap 3" + e.getMessage());
        }

    }

    @Override
    public String toString() {
        return "Staff{" + "manv=" + manv + ", ho=" + ho + ", ten=" + ten + ", dob=" + dob + ", sex=" + sex + ", CMND=" + CMND + ", pw=" + pw + ", status=" + status + '}';
    }

    //thêm nhân viên:
    public  void ThemNhanVien(String ho, String ten, String dob, String sex, String CMND, String pw, String status) {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://localhost:1433;databaseName=QLNS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionURL);
            System.out.println("conneted");
        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap driver and connec: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi nap d and c 2:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("loi nap 3" + e.getMessage());
        }
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = d.parse(dob);
            java.sql.Date sq = new java.sql.Date(parsed.getTime());
            PreparedStatement p;
            ResultSet rs;
            p = con.prepareStatement("insert into Staff (Ho,Ten,dob,sex,CMND,PW,Status)\n"
                    + "values (?,?,?,?,?,?,?)");
            p.setString(1, ho);
            p.setString(2, ten);
            p.setDate(3, sq);
            p.setString(4, sex);
            p.setString(5, CMND);
            p.setString(6, pw);
            p.setString(7, status);
            int r = p.executeUpdate();
        } catch (Exception e) {

            System.out.println("Lỗi chung: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Staff(String manv, String ho, String ten, Date dob, String sex, String CMND, String pw, String status) {
        this.manv = manv;
        this.ho = ho;
        this.ten = ten;
        this.dob = dob;
        this.sex = sex;
        this.CMND = CMND;
        this.pw = pw;
        this.status = status;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getManv() {
        return manv;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public Date getDob() {
        return dob;
    }

    public String getSex() {
        return sex;
    }

    public String getCMND() {
        return CMND;
    }

    public String getPw() {
        return pw;
    }

    public String getStatus() {
        return status;
    }

    public Connection getCon() {
        return con;
    }

    public static void main(String[] args) {

        Staff s2 = new Staff();
//        s2.DoiMatKhau(100, "1234567");
//        Staff s = new Staff(100);
//        String trs = s.toString();
//        System.out.println(trs);
        //ThemNhanVien(String ho, String ten, String dob, String sex, String CMND, String pw, String status) 
        //Staff.ThemNhanVien("Mai", "Ho", "30/03/1996", "Nam", "193312314", "1234", "nhan vien jv");
    }
}
