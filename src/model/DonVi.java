package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DonVi {

    private String madv, tendv, tel, email;
    public static Connection con = null;

    public DonVi() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://localhost:1433;databaseName=QLNS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap driver and connec: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi nap d and c 2:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("loi nap 3" + e.getMessage());
        }
    }

    // lấy madv and ten dv
    public static Vector<String> getDataMethod() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://localhost:1433;databaseName=QLNS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionURL);

        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap driver and connec: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi nap d and c 2:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("loi nap 3" + e.getMessage());
        }
        Vector<String> ds = new Vector<>();
        try {
            Statement st = con.createStatement();
            String sql = "select * from department";
            ResultSet rs = st.executeQuery(sql);
            ds.add("Chọn:");
            while (rs.next()) {
                int manv = rs.getInt("MaDV");
                String ten = rs.getString("TenDV");
                ds.add(manv + " " + ten);
            }
        } catch (Exception e) {

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DonVi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }

    @Override
    public String toString() {
        return "DonVi{" + "madv=" + madv + ", tendv=" + tendv + ", tel=" + tel + ", email=" + email + '}';
    }

    // lấy bg:
    public static Vector<Vector> LayDanhSach(int MaDV) {
        Vector<Vector> ds = new Vector<Vector>(10, 5);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://localhost:1433;databaseName=QLNS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap driver and connec: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Loi nap d and c 2:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("loi nap 3" + e.getMessage());
        }

        try {
            PreparedStatement ps = con.prepareStatement("select DSNV.MaDV,Staff.MaNV, Staff.Ho, Staff.Ten,DSNV.chucvu, department.TenDV from Staff,DSNV,department\n"
                    + "where \n"
                    + "Staff.MaNV=DSNV.MaNV and\n"
                    + "DSNV.MaDV = department.MaDV and\n"
                    + "(DSNV.MaDV=?)");
            ps.setInt(1, MaDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("MaDV"));
                v.add(rs.getInt("MaNV"));
                v.add(rs.getString("Ho"));
                v.add(rs.getString("Ten"));
                v.add(rs.getString("chucvu"));
                v.add(rs.getString("TenDV"));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Lỗi ở nơi đâu: " + e.getMessage());
        }

        return ds;
    }

    public DonVi(String madv, String tendv, String tel, String email) {
        this.madv = madv;
        this.tendv = tendv;
        this.tel = tel;
        this.email = email;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMadv() {
        return madv;
    }

    public String getTendv() {
        return tendv;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public static void main(String[] args) {
        System.out.println("đá");
        Vector<Vector> v = new Vector<Vector>();
        v = DonVi.LayDanhSach(10);
        System.out.println("sdas");
        for (int i = 0; i < v.size(); i++) {
            System.out.println("b");
            System.out.println(v.get(i).toString());
        }
    }
}
