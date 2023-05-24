package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.DVTN;
import context.DBContext;

public class DVTNDAO {
	// Lấy danh sách Đơn Vị Tiếp Nhận
	public List<DVTN> getListDVTN() {
		String query = "select * from DVTN";
		List<DVTN> listDVTN = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DVTN dvtn = new DVTN(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				listDVTN.add(dvtn);
			}
			return listDVTN;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Thêm Đơn vị Tiếp Nhận vào DB
	public void insertDVTN(String tenDVTN, String thongTin, String imgLogo) {
		String query = "insert into DVTN\r\n" + "values (?,?,?)";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDVTN);
			ps.setString(2, thongTin);
			ps.setString(3, imgLogo);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Lấy Số Trang trong Xem Danh Sách DVTN
	public int getCountPage() {
		List<DVTN> listDVTN = new DVTNDAO().getListDVTN();
		int size = listDVTN.size();
		int countPage = size / 5;
		if (size % 5 != 0) {
			countPage = countPage + 1;
		}
		return countPage;
	}

	// Lấy danh sách DVTN trong 1 trang
	public List<DVTN> getListOfPage(int page) {
		String query = "select * from DVTN\r\n" + "order by MaDVTN\r\n" + "OFFSET ? ROWS\r\n"
				+ "FETCH FIRST 5 ROWS ONLY";
		List<DVTN> listDVTNOfPgae = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (page - 1) * 5);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DVTN dvtn = new DVTN(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

				listDVTNOfPgae.add(dvtn);
			}
			return listDVTNOfPgae;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Cập nhật DVTN
	public void updateDVTN(String id, String tenDVTN, String imgLogo, String thongTin) {
		String query = "update DVTN\r\n" + "set TenDVTN = ?,\r\n" + "ThongTin = ?,\r\n" + "URLHinhAnhLogo = ?\r\n"
				+ "where MaDVTN = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDVTN);
			ps.setString(2, thongTin);
			ps.setString(3, imgLogo);
			ps.setString(4, id);

			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Tìm List DVTN theo tên DVTN
	public List<DVTN> searchDVTN(String search) {
		String query = "select * from DVTN\r\n" + "where TenDVTN like ?";
		List<DVTN> listDVTN = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DVTN dvtn = new DVTN(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				listDVTN.add(dvtn);
			}
			return listDVTN;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
