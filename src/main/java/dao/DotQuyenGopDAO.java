package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bean.DVTN;
import bean.DotQuyenGop;
import context.DBContext;

public class DotQuyenGopDAO {
	// get All danh sach
	public List<DotQuyenGop> getListAllDQG() {

		String query = "select dqg.MaDot, dqg.TieuDe, dqg.URLHinhAnhBanner,dqg.TienQuyenGop, dqg.NgayBatDau, dqg.NgayKetThuc,\r\n"
				+ "DATEDIFF(day, CURRENT_TIMESTAMP, dqg.NgayKetThuc) as TGConLai,dvtn.URLHinhAnhLogo, dvtn.TenDVTN, dqg.NoiDung\r\n"
				+ "from DotQuyenGop as dqg\r\n" + "join DVTN as dvtn\r\n" + "on dqg.MaDVTN = dvtn.MaDVTN";
		List<DotQuyenGop> list = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
			while (rs.next()) {

				DVTN dvtn = new DVTN(rs.getString(9), rs.getString(8));

				DotQuyenGop dqg = new DotQuyenGop(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
						rs.getDate(5), rs.getDate(6), rs.getInt(7), dvtn, rs.getString(10),
						qg1lDAO.getTienDaQuyenGop(String.valueOf(rs.getInt(1))),
						qg1lDAO.getLuotDaQuyenGop(String.valueOf(rs.getInt(1))));

				list.add(dqg);

			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	// get danh sach dang dien ra
	public List<DotQuyenGop> getListDangDienRaDQG() {

		String query = "select dqg.MaDot, dqg.TieuDe, dqg.URLHinhAnhBanner,dqg.TienQuyenGop, dqg.NgayBatDau, dqg.NgayKetThuc,\r\n"
				+ "DATEDIFF(day, CURRENT_TIMESTAMP, dqg.NgayKetThuc) as TGConLai,\r\n"
				+ "dvtn.URLHinhAnhLogo, dvtn.TenDVTN, dqg.NoiDung\r\n" + "from DotQuyenGop as dqg\r\n"
				+ "join DVTN as dvtn\r\n"
				+ "on dqg.MaDVTN = dvtn.MaDVTN and DATEDIFF(day, CURRENT_TIMESTAMP, dqg.NgayKetThuc) > 0";

		List<DotQuyenGop> list = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
			while (rs.next()) {

				DVTN dvtn = new DVTN(rs.getString(9), rs.getString(8));

				DotQuyenGop dqg = new DotQuyenGop(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
						rs.getDate(5), rs.getDate(6), rs.getInt(7), dvtn, rs.getString(10),
						qg1lDAO.getTienDaQuyenGop(String.valueOf(rs.getInt(1))),
						qg1lDAO.getLuotDaQuyenGop(String.valueOf(rs.getInt(1))));
				list.add(dqg);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// get danh sach da ket thuc
	public List<DotQuyenGop> getListKetThucDQG() {
		String query = "select dqg.MaDot, dqg.TieuDe, dqg.URLHinhAnhBanner,\r\n"
				+ "dqg.TienQuyenGop, dqg.NgayBatDau, dqg.NgayKetThuc,\r\n"
				+ "DATEDIFF(day, CURRENT_TIMESTAMP, dqg.NgayKetThuc) as TGConLai,\r\n"
				+ "dvtn.URLHinhAnhLogo, dvtn.TenDVTN, dqg.NoiDung\r\n" + "from DotQuyenGop as dqg\r\n"
				+ "join DVTN as dvtn\r\n"
				+ "on dqg.MaDVTN = dvtn.MaDVTN and DATEDIFF(day, CURRENT_TIMESTAMP, dqg.NgayKetThuc) <= 0";

		List<DotQuyenGop> list = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				DVTN dvtn = new DVTN(rs.getString(9), rs.getString(8));

				DotQuyenGop dqg = new DotQuyenGop(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
						rs.getDate(5), rs.getDate(6), rs.getInt(7), dvtn, rs.getString(10));
				list.add(dqg);
			}

			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Sap xep DS DotQuyenGop tang theo ID
	public List<DotQuyenGop> getListDQGTangID(List<DotQuyenGop> list) {

		Collections.sort(list, new Comparator<DotQuyenGop>() {
			@Override
			public int compare(DotQuyenGop o1, DotQuyenGop o2) {
				if (o1.getMaDot() > o2.getMaDot()) {
					return 1;
				} else if (o1.getMaDot() < o2.getMaDot()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	// Sap xep DS DotQuyenGop giam theo ID
	public List<DotQuyenGop> getListDQGGiamID(List<DotQuyenGop> list) {

		Collections.sort(list, new Comparator<DotQuyenGop>() {
			@Override
			public int compare(DotQuyenGop o1, DotQuyenGop o2) {
				if (o1.getMaDot() < o2.getMaDot()) {
					return 1;
				} else if (o1.getMaDot() > o2.getMaDot()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	// Sap xep DS DotQuyenGop tang theo Tien
	public List<DotQuyenGop> getListDQGTangTheoTien(List<DotQuyenGop> list) {

		Collections.sort(list, new Comparator<DotQuyenGop>() {
			@Override
			public int compare(DotQuyenGop o1, DotQuyenGop o2) {
				if (o1.getTienQuyenGop() > o2.getTienQuyenGop()) {
					return 1;
				} else if (o1.getTienQuyenGop() < o2.getTienQuyenGop()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	// Sap xep DS DotQuyenGop giam theo Tien
	public List<DotQuyenGop> getListDQGGiamTheoTien(List<DotQuyenGop> list) {

		Collections.sort(list, new Comparator<DotQuyenGop>() {
			@Override
			public int compare(DotQuyenGop o1, DotQuyenGop o2) {
				if (o1.getTienQuyenGop() < o2.getTienQuyenGop()) {
					return 1;
				} else if (o1.getTienQuyenGop() > o2.getTienQuyenGop()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	// Them DQG
	public void insertDQG(String tieuDe, String ngayBD, String ngayKT, String tienQG, String imgBanner, String maDVTN,
			String noiDung) {
		String query = "insert into DotQuyenGop\r\n" + "values (?,?,?,?,?,?,?)";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tieuDe);
			ps.setString(2, ngayBD);
			ps.setString(3, ngayKT);
			ps.setString(4, tienQG);
			ps.setString(5, imgBanner);
			ps.setString(6, maDVTN);
			ps.setString(7, noiDung);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// xoa DQG theo id
	public void deleteDQG(String id) {
		String query = "delete from DotQuyenGop\r\n" + "where MaDot = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Cập nhật DQG theo id
	public void updateDQG(String id, String tieuDe, String ngayBD, String ngayKT, String tienQG, String urlBanner,
			String maDVTN, String noiDung) {
		String query = "update DotQuyenGop\r\n" + "set TieuDe = ?,\r\n" + "NgayBatDau = ?,\r\n" + "NgayKetThuc = ?,\r\n"
				+ "TienQuyenGop = ?,\r\n" + "URLHinhAnhBanner = ?,\r\n" + "MaDVTN = ?,\r\n" + "NoiDung = ?\r\n"
				+ "where MaDot = ?";
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tieuDe);
			ps.setString(2, ngayBD);
			ps.setString(3, ngayKT);
			ps.setString(4, tienQG);
			ps.setString(5, urlBanner);
			ps.setString(6, maDVTN);
			ps.setString(7, noiDung);
			ps.setString(8, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// lấy số trang, mỗi trang có 5 DQG
	public int getCountPage() {
		List<DotQuyenGop> listDQG = new DotQuyenGopDAO().getListAllDQG();
		int size = listDQG.size();
		int countPage = size / 5;
		if (size % 5 != 0) {
			countPage = countPage + 1;
		}
		return countPage;
	}

	// Lấy danh sách DQG trong 1 trang
	public List<DotQuyenGop> getListOfPage(int page) {
		String query = "select dqg.MaDot, dqg.TieuDe, dqg.URLHinhAnhBanner,dqg.TienQuyenGop, dqg.NgayBatDau, dqg.NgayKetThuc,\r\n"
				+ "DATEDIFF(day, CURRENT_TIMESTAMP, dqg.NgayKetThuc) as TGConLai,\r\n"
				+ "dvtn.URLHinhAnhLogo, dvtn.TenDVTN, dqg.NoiDung\r\n" + "from DotQuyenGop as dqg\r\n"
				+ "join DVTN as dvtn\r\n" + "on dqg.MaDVTN = dvtn.MaDVTN\r\n" + "order by MaDot\r\n"
				+ "OFFSET ? ROWS\r\n" + "FETCH FIRST 5 ROWS ONLY";
		List<DotQuyenGop> listOfPgae = new ArrayList<>();
		try {
			Connection conn = new DBContext().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (page - 1) * 5);
			ResultSet rs = ps.executeQuery();
			QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
			while (rs.next()) {
				DVTN dvtn = new DVTN(rs.getString(9), rs.getString(8));

				DotQuyenGop dqg = new DotQuyenGop(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
						rs.getDate(5), rs.getDate(6), rs.getInt(7), dvtn, rs.getString(10),
						qg1lDAO.getTienDaQuyenGop(String.valueOf(rs.getInt(1))),
						qg1lDAO.getLuotDaQuyenGop(String.valueOf(rs.getInt(1))));

				listOfPgae.add(dqg);
			}
			return listOfPgae;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// get DQG theo id
	public DotQuyenGop getDQG(int maDot) {
		DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
		List<DotQuyenGop> listDQG = dqgDAO.getListAllDQG();
		for (DotQuyenGop dotQuyenGop : listDQG) {
			if (maDot == dotQuyenGop.getMaDot()) {
				return dotQuyenGop;
			}
		}
		return null;
	}
	
	// get danh sách sắp xếp theo lượt quyên góp tăng
	public List<DotQuyenGop> getListDQGTangLuot(List<DotQuyenGop> list) {

		Collections.sort(list, new Comparator<DotQuyenGop>() {
			@Override
			public int compare(DotQuyenGop o1, DotQuyenGop o2) {
				if (o1.getLuotQuyenGop() > o2.getLuotQuyenGop()) {
					return 1;
				} else if (o1.getLuotQuyenGop() < o2.getLuotQuyenGop()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}
	
	// get danh sách sắp xếp theo lượt quyên góp giảm
		public List<DotQuyenGop> getListDQGGiamLuot(List<DotQuyenGop> list) {

			Collections.sort(list, new Comparator<DotQuyenGop>() {
				@Override
				public int compare(DotQuyenGop o1, DotQuyenGop o2) {
					if (o1.getLuotQuyenGop() < o2.getLuotQuyenGop()) {
						return 1;
					} else if (o1.getLuotQuyenGop() > o2.getLuotQuyenGop()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			return list;
		}
	
	public static void main(String[] args) {
//		DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
//		dqgDAO.updateDQG("42", "ffff", "2022-07-26", "2022-08-07", "1200000", "images/bannertrangchu.png", "1");
	}
}
