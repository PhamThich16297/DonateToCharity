package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.DotQuyenGop;
import bean.QuyenGop1Lan;
import bean.TaiKhoan;
import context.DBContext;

public class QuyenGop1LanDAO {
	// Thêm QuyenGop1Lan vào CSDL
	public void insertQG1L(int maDot, String tenDangNhap, String soTienQG, Date thoiGianQG) {
		String query = "insert into QuyenGop1Lan\r\n" + "values (?,?,?,?)";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, maDot);
			ps.setString(2, tenDangNhap);
			ps.setString(3, soTienQG);
			ps.setDate(4, thoiGianQG);

			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// get tổng tiền đã quyên góp của 1 đợt quyên góp
	public float getTienDaQuyenGop(String maDQG) {
		String query = "select sum(SoTienQuyenGop)\r\n" + "from QuyenGop1Lan\r\n" + "where MaDot = ?";
		float tongTien = 0;
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, maDQG);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tongTien = rs.getFloat(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongTien;
	}

	// get tổng tiền đã quyên góp của Tất cả đợt quyên góp
	public float getTienDaQuyenGop() {
		String query = "select sum(SoTienQuyenGop)\r\n" + "from QuyenGop1Lan";
		float tongTien = 0;
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tongTien = rs.getFloat(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongTien;
	}

	// get Tổng lượt đã quyên góp của 1 đợt Quyên góp
	public int getLuotDaQuyenGop(String maDQG) {
		String query = "select count(*)\r\n" + "from QuyenGop1Lan\r\n" + "where MaDot = ?";
		int tongLuot = 0;
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, maDQG);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tongLuot = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongLuot;
	}

	// get Tổng lượt đã quyên góp của Tất cả đợt Quyên góp
	public int getTongLuotDaQuyenGop() {
		String query = "select count(*)\r\n" + "from QuyenGop1Lan";
		int tongLuot = 0;
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tongLuot = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongLuot;
	}

	// get Danh Sách các DQG đã quyên góp của Tài Khoản
	public List<QuyenGop1Lan> getListQuyenGop1Lan(String tenDangNhap) {
		String query = "SELECT * FROM QuyenGop1Lan\r\n" + "where TenDangNhap = ?\r\n"
				+ "order by ThoiGianQuyenGop desc, MaDot";
		List<QuyenGop1Lan> listQuyenGop1Lan = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDangNhap);
			ResultSet rs = ps.executeQuery();
			DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			while (rs.next()) {
				DotQuyenGop dqg = dqgDAO.getDQG(rs.getInt(1));
				TaiKhoan tk = tkDAO.getTaiKhoan(tenDangNhap);
				QuyenGop1Lan qg1l = new QuyenGop1Lan(dqg, tk, rs.getFloat(3), rs.getDate(4));
				listQuyenGop1Lan.add(qg1l);
			}
			return listQuyenGop1Lan;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// get Danh Sách User theo trang
	public List<QuyenGop1Lan> getListTKUserOfPage(int pageTK) {
		String query = "select TK.TenDangNhap,QG.MaDot, Sum(QG.SoTienQuyenGop)\r\n" + "from QuyenGop1Lan as QG\r\n"
				+ "right join TaiKhoan as TK\r\n" + "on QG.TenDangNhap = TK.TenDangNhap\r\n"
				+ "where TK.PhanQuyen = 0\r\n" + "group by TK.TenDangNhap,QG.MaDot\r\n" + "order by TK.TenDangNhap\r\n"
				+ "OFFSET ? ROWS\r\n" + "FETCH FIRST 5 ROWS ONLY";
		List<QuyenGop1Lan> listTKUserOfPage = new ArrayList<>();
		DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (pageTK - 1) * 5);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				DotQuyenGop dqg = new DotQuyenGop();
				if (rs.getString(2) == null) {
					dqg = null;
				} else {
					dqg = dqgDAO.getDQG(Integer.parseInt(rs.getString(2)));
				}
				TaiKhoan tk = tkDAO.getTaiKhoan(rs.getString(1));
				QuyenGop1Lan qg1l = new QuyenGop1Lan();
				if (rs.getString(3) == null) {
					qg1l = new QuyenGop1Lan(dqg, tk, 0);
				} else {
					qg1l = new QuyenGop1Lan(dqg, tk, rs.getFloat(3));
				}

				listTKUserOfPage.add(qg1l);
			}
			return listTKUserOfPage;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<QuyenGop1Lan> getListTKUser() {
		String query = "select TK.TenDangNhap,QG.MaDot, Sum(QG.SoTienQuyenGop)\r\n" + "from QuyenGop1Lan as QG\r\n"
				+ "right join TaiKhoan as TK\r\n" + "on QG.TenDangNhap = TK.TenDangNhap\r\n"
				+ "where TK.PhanQuyen = 0\r\n" + "group by TK.TenDangNhap,QG.MaDot\r\n" + "order by TK.TenDangNhap";
		List<QuyenGop1Lan> listTKUser = new ArrayList<>();
		DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				DotQuyenGop dqg = new DotQuyenGop();
				if (rs.getString(2) == null) {
					dqg = null;
				} else {
					dqg = dqgDAO.getDQG(Integer.parseInt(rs.getString(2)));
				}
				TaiKhoan tk = tkDAO.getTaiKhoan(rs.getString(1));
				QuyenGop1Lan qg1l = new QuyenGop1Lan();
				if (rs.getString(3) == null) {
					qg1l = new QuyenGop1Lan(dqg, tk, 0);
				} else {
					qg1l = new QuyenGop1Lan(dqg, tk, rs.getFloat(3));
				}

				listTKUser.add(qg1l);
			}
			return listTKUser;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int getCountPageTKUser() {
		String query = "select TK.TenDangNhap,QG.MaDot, Sum(QG.SoTienQuyenGop)\r\n" + "from QuyenGop1Lan as QG\r\n"
				+ "right join TaiKhoan as TK\r\n" + "on QG.TenDangNhap = TK.TenDangNhap\r\n"
				+ "where TK.PhanQuyen = 0\r\n" + "group by TK.TenDangNhap,QG.MaDot\r\n" + "order by TK.TenDangNhap";
		List<QuyenGop1Lan> listTKUserOfPage = new ArrayList<>();
		DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		int countPageTK = 0;
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DotQuyenGop dqg = dqgDAO.getDQG(Integer.parseInt(rs.getString(2)));
				TaiKhoan tk = tkDAO.getTaiKhoan(rs.getString(1));

				QuyenGop1Lan qg1l = new QuyenGop1Lan(dqg, tk, rs.getFloat(3));
				listTKUserOfPage.add(qg1l);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		int size = listTKUserOfPage.size();
		countPageTK = size / 5;
		if (size % 5 != 0) {
			countPageTK = countPageTK + 1;
		}
		return countPageTK;
	}

	// get List Tài Khoản đã hoạt động
	public List<QuyenGop1Lan> getListDHD(List<QuyenGop1Lan> listAll) {
		List<QuyenGop1Lan> listDHD = new ArrayList<>();
		for (QuyenGop1Lan quyenGop1Lan : listAll) {
			if (quyenGop1Lan.getTk().getTrangThai().equals("Đang hoạt động")) {
				listDHD.add(quyenGop1Lan);
			}
		}
		return listDHD;
	}

	// get List Tài Khoản đã hoạt động
	public List<QuyenGop1Lan> getListKHD(List<QuyenGop1Lan> listAll) {
		List<QuyenGop1Lan> listKHD = new ArrayList<>();
		for (QuyenGop1Lan quyenGop1Lan : listAll) {
			if (quyenGop1Lan.getTk().getTrangThai().equals("Không hoạt động")) {
				listKHD.add(quyenGop1Lan);
			}
		}
		return listKHD;
	}

	// get List Tài Khoản đã tham gia quyên góp
	public List<QuyenGop1Lan> getListDaThamGia(List<QuyenGop1Lan> listAll) {
		List<QuyenGop1Lan> listDaThamGia = new ArrayList<>();
		for (QuyenGop1Lan quyenGop1Lan : listAll) {
			if (quyenGop1Lan.getSoTien() > 0) {
				listDaThamGia.add(quyenGop1Lan);
			}
		}
		return listDaThamGia;
	}
	// get List Tài Khoản chưa tham gia quyên góp
	public List<QuyenGop1Lan> getListChuaThamGia(List<QuyenGop1Lan> listAll) {
		List<QuyenGop1Lan> listChuaThamGia = new ArrayList<>();
		for (QuyenGop1Lan quyenGop1Lan : listAll) {
			if (quyenGop1Lan.getSoTien() == 0) {
				listChuaThamGia.add(quyenGop1Lan);
			}
		}
		return listChuaThamGia;
	}

	public static void main(String[] args) {
		QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
//		qg1lDAO.insertQG1L(2, "thichka1", "1200000", new Date(System.currentTimeMillis()));
		System.out.println(qg1lDAO.getCountPageTKUser());

	}
}
