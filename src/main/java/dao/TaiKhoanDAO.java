package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bean.TaiKhoan;
import context.DBContext;

public class TaiKhoanDAO {
	// get Danh sách tất cả tài khoản
	public List<TaiKhoan> getListAllTK() {

		String query = "select * from TaiKhoan";
		List<TaiKhoan> list = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));

				list.add(tk);

			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	// get Danh sach theo trang
	public List<TaiKhoan> getListTKOfPage(int pageTK) {
		String query = "select * from TaiKhoan\r\n" + "order by TenDangNhap\r\n" + "OFFSET ? ROWS\r\n"
				+ "FETCH FIRST 5 ROWS ONLY";
		List<TaiKhoan> listTKOfPage = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (pageTK - 1) * 5);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));

				listTKOfPage.add(tk);
			}
			return listTKOfPage;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	

	// get số trang của Danh sách Tài Khoản
	public int getCountPageTK() {
		List<TaiKhoan> listTK = new TaiKhoanDAO().getListAllTK();
		int size = listTK.size();
		int countPageTK = size / 5;
		if (size % 5 != 0) {
			countPageTK = countPageTK + 1;
		}
		return countPageTK;
	}

	// get số trang của Danh sách Tài Khoản User
	public int getCountPageTKUser() {
		List<TaiKhoan> listTK = new TaiKhoanDAO().getListAllTK();
		int size = listTK.size();
		int countPageTK = size / 5;
		if (size % 5 != 0) {
			countPageTK = countPageTK + 1;
		}
		return countPageTK;
	}

	// get Danh Sách Admin
	public List<TaiKhoan> getListAdmin() {
		String query = "select * from TaiKhoan\r\n" + "where PhanQuyen = 1";
		List<TaiKhoan> listAdmin = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listAdmin.add(tk);
			}
			return listAdmin;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// get Danh Sách User
	public List<TaiKhoan> getListUser() {
		String query = "select * from TaiKhoan\r\n" + "where PhanQuyen = 0";
		List<TaiKhoan> listUser = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listUser.add(tk);
			}
			return listUser;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Sắp xếp danh sách theo Tên Đăng Nhập A-Z
	public List<TaiKhoan> getListTKAZ(List<TaiKhoan> listTK) {

		Collections.sort(listTK, new Comparator<TaiKhoan>() {
			@Override
			public int compare(TaiKhoan o1, TaiKhoan o2) {
				if ((o1.getTenDangNhap()).compareTo(o2.getTenDangNhap()) > 0) {
					return 1;
				} else if ((o1.getTenDangNhap()).compareTo(o2.getTenDangNhap()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return listTK;
	}

	// Sắp xếp danh sách theo Tên Đăng Nhập Z-A
	public List<TaiKhoan> getListTKZA(List<TaiKhoan> listTK) {

		Collections.sort(listTK, new Comparator<TaiKhoan>() {
			@Override
			public int compare(TaiKhoan o1, TaiKhoan o2) {
				if ((o1.getTenDangNhap()).compareTo(o2.getTenDangNhap()) < 0) {
					return 1;
				} else if ((o1.getTenDangNhap()).compareTo(o2.getTenDangNhap()) > 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return listTK;
	}

	// Sắp xếp danh sách theo SDT Tăng
	public List<TaiKhoan> getListTKTangSDT(List<TaiKhoan> listTK) {

		Collections.sort(listTK, new Comparator<TaiKhoan>() {
			@Override
			public int compare(TaiKhoan o1, TaiKhoan o2) {
				if ((o1.getSdt()).compareTo(o2.getSdt()) > 0) {
					return 1;
				} else if ((o1.getSdt()).compareTo(o2.getSdt()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return listTK;
	}

	// Sắp xếp danh sách theo SDT Giảm
	public List<TaiKhoan> getListTKGiamSDT(List<TaiKhoan> listTK) {

		Collections.sort(listTK, new Comparator<TaiKhoan>() {
			@Override
			public int compare(TaiKhoan o1, TaiKhoan o2) {
				if ((o1.getSdt()).compareTo(o2.getSdt()) < 0) {
					return 1;
				} else if ((o1.getSdt()).compareTo(o2.getSdt()) > 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return listTK;
	}

	// Xóa tài khoản theo tên đăng nhập
	// ==> Bản chất xóa: update trạng thái = Không hoạt động
	public void deleteTK(String tenDangNhap) {
		String query = "UPDATE TaiKhoan\r\n" + "SET TrangThai = N'Không hoạt động'\r\n" + "where TenDangNhap = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDangNhap);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Tìm tất cả trong DS theo tên đăng nhập với từ khóa người dùng nhập
	public List<TaiKhoan> getListAllTenDangNhap(String search) {
		String query = "select * from TaiKhoan\r\n" + "where TenDangNhap like ?";
		List<TaiKhoan> listAllTenDangNhap = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listAllTenDangNhap.add(tk);
			}
			return listAllTenDangNhap;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Tìm Admin theo tên đăng nhập với từ khóa người dùng nhập
	public List<TaiKhoan> getListAdminTenDangNhap(String search) {
		String query = "select * from TaiKhoan\r\n" + "where TenDangNhap like ? and PhanQuyen = 1";
		List<TaiKhoan> listAdminTenDangNhap = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listAdminTenDangNhap.add(tk);
			}
			return listAdminTenDangNhap;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Tìm User khách hàng theo tên đăng nhập với từ khóa người dùng nhập
	public List<TaiKhoan> getListUserTenDangNhap(String search) {
		String query = "select * from TaiKhoan\r\n" + "where TenDangNhap like ? and PhanQuyen = 0";
		List<TaiKhoan> listUserTenDangNhap = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listUserTenDangNhap.add(tk);
			}
			return listUserTenDangNhap;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Tìm tất cả trong DS theo SDT với từ khóa người dùng nhập
	public List<TaiKhoan> getListAllSdt(String search) {
		String query = "select * from TaiKhoan\r\n" + "where SDT like ?";
		List<TaiKhoan> listAllSdt = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listAllSdt.add(tk);
			}
			return listAllSdt;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Tìm Admin theo SDT với từ khóa người dùng nhập
	public List<TaiKhoan> getListAdminSdt(String search) {
		String query = "select * from TaiKhoan\r\n" + "where SDT like ? and PhanQuyen = 1";
		List<TaiKhoan> listAdminSdt = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listAdminSdt.add(tk);
			}
			return listAdminSdt;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Tìm User khách hàng theo SDT với từ khóa người dùng nhập
	public List<TaiKhoan> getListUserSdt(String search) {
		String query = "select * from TaiKhoan\r\n" + "where SDT like ? and PhanQuyen = 0";
		List<TaiKhoan> listUserSdt = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				listUserSdt.add(tk);
			}
			return listUserSdt;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Thêm Tài Khoản
	public void addTK(String tenDangNhap, String matKhau, String tenCaNhan, String email, String diaChi, String sdt,
			String phanQuyen) {
		String query = "insert into TaiKhoan\r\n" + "values (?,?,?,?,?,?,?,'0',N'Đang hoạt động')";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDangNhap);
			ps.setString(2, matKhau);
			ps.setString(3, tenCaNhan);
			ps.setString(4, email);
			ps.setString(5, diaChi);
			ps.setString(6, sdt);
			ps.setString(7, phanQuyen);

			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Cập nhật Tài Khoản
	public void updateTK(String tenDangNhap, String tenCaNhan, String email, String diaChi, String sdt,
			String phanQuyen, String trangThai) {
		String query = "UPDATE TaiKhoan\r\n" + "SET\r\n" + "TenCaNhan = ?,\r\n" + "Email = ?,\r\n" + "DiaChi = ?,\r\n"
				+ "SDT = ?,\r\n" + "PhanQuyen = ?,\r\n" + "TrangThai = ?\r\n" + "where TenDangNhap = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenCaNhan);
			ps.setString(2, email);
			ps.setString(3, diaChi);
			ps.setString(4, sdt);
			ps.setString(5, phanQuyen);
			ps.setString(6, trangThai);
			ps.setString(7, tenDangNhap);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Kiểm tra đăng nhập
	public TaiKhoan checkDangNhap(String tenDangNhap, String matKhau, String phanQuyen) {
		String query = "select * from TaiKhoan where TenDangNhap = ? and MatKhau = ? and PhanQuyen = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDangNhap);
			ps.setString(2, matKhau);
			ps.setString(3, phanQuyen);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				return tk;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// Thay đổi Mật Khẩu cho Tài Khoản
	public void updatePassTK(String tenDangNhap, String matKhau) {
		String query = "UPDATE TaiKhoan\r\n" + "SET MatKhau = ?\r\n" + "where TenDangNhap = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, matKhau);
			ps.setString(2, tenDangNhap);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Cập nhật lại số Tiền hiện có cho Tài Khoản
	public void updateSoTienHienCo(String tenDangNhap, float soTienHienCo) {
		String query = "UPDATE TaiKhoan\r\n" + "SET SoTienHienCo = ?\r\n" + "where TenDangNhap = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, soTienHienCo);
			ps.setString(2, tenDangNhap);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Kiểm tra Tài Khoản có tên đăng nhập tồn tại không
	public boolean booleanTKvsTenDangNhap(String tenDangNhap) {
		boolean b = false;
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		List<TaiKhoan> listTK = tkDAO.getListAllTK();
		for (TaiKhoan taiKhoan : listTK) {
			if (tenDangNhap.equals(taiKhoan.getTenDangNhap())) {
				b = true;
			}
		}
		return b;
	}

	// Kiểm tra Tài Khoản có email tồn tại không
	public boolean booleanTKvsEmail(String email) {
		boolean b = false;
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		List<TaiKhoan> listTK = tkDAO.getListAllTK();
		for (TaiKhoan taiKhoan : listTK) {
			if (email.equals(taiKhoan.getEmail())) {
				b = true;
			}
		}
		return b;
	}

	// Get Tài Khoản trong DS có tên đăng nhập và email người dùng nhập
	public TaiKhoan getTaiKhoan(String tenDangNhap, String email) {
		String query = "select * from TaiKhoan where TenDangNhap = ? and Email = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDangNhap);
			ps.setString(2, email);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				return tk;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// get Tài Khoản theo Tên đăng nhập
	public TaiKhoan getTaiKhoan(String tenDangNhap) {
		String query = "select * from TaiKhoan where TenDangNhap = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tenDangNhap);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getFloat(8));
				return tk;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// get Danh Sách tài khoản người dùng đang hoạt động
	public List<TaiKhoan> getListTK_DangHD() {
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		List<TaiKhoan> listUser = tkDAO.getListUser();
		List<TaiKhoan> listTK_DangHD = new ArrayList<>();
		for (TaiKhoan taiKhoan : listUser) {
			if (taiKhoan.getTrangThai().equals("Đang hoạt động")) {
				listTK_DangHD.add(taiKhoan);
			}
		}
		return listTK_DangHD;
	}

	// get Danh Sách tài khoản người dùng đang hoạt động
	public List<TaiKhoan> getListTK_KhongHD() {
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		List<TaiKhoan> listUser = tkDAO.getListUser();
		List<TaiKhoan> listTK_KhongHD = new ArrayList<>();
		for (TaiKhoan taiKhoan : listUser) {
			if (taiKhoan.getTrangThai().equals("Không hoạt động")) {
				listTK_KhongHD.add(taiKhoan);
			}
		}
		return listTK_KhongHD;
	}

	public static void main(String[] args) {
		TaiKhoanDAO tkDao = new TaiKhoanDAO();
		tkDao.addTK("test2", "555555", "tt", "te2", "tt", "12345645", "0");
//		String a = "h2";
//		tkDao.deleteTK(a);
//		List<TaiKhoan> listTK = tkDao.getListAllTK();
//		List<TaiKhoan> listSX = tkDao.getListTKAZ(listTK);
//		for (TaiKhoan taiKhoan : listSX) {
//			System.out.println(taiKhoan.getTenDangNhap());
//		}

	}
}
