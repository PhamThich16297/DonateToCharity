package bean;

import java.sql.Date;

public class QuyenGop1Lan {
	DotQuyenGop dqg;
	TaiKhoan tk;
	float soTien;
	Date thoiGianQG;
	
	

	public QuyenGop1Lan() {}

	public QuyenGop1Lan(DotQuyenGop dqg, TaiKhoan tk, float soTien, Date thoiGianQG) {
		this.dqg = dqg;
		this.tk = tk;
		this.soTien = soTien;
		this.thoiGianQG = thoiGianQG;
	}

	public QuyenGop1Lan(DotQuyenGop dqg, TaiKhoan tk, float soTien) {
		this.dqg = dqg;
		this.tk = tk;
		this.soTien = soTien;
	}

	public DotQuyenGop getDqg() {
		return dqg;
	}

	public void setDqg(DotQuyenGop dqg) {
		this.dqg = dqg;
	}

	public TaiKhoan getTk() {
		return tk;
	}

	public void setTk(TaiKhoan tk) {
		this.tk = tk;
	}

	public float getSoTien() {
		return soTien;
	}

	public void setSoTien(float soTien) {
		this.soTien = soTien;
	}

	public Date getThoiGianQG() {
		return thoiGianQG;
	}

	public void setThoiGianQG(Date thoiGianQG) {
		this.thoiGianQG = thoiGianQG;
	}

	public static void main(String[] args) {
		DotQuyenGop dqg = new DotQuyenGop();
		TaiKhoan tk = new TaiKhoan();
		QuyenGop1Lan qg = new QuyenGop1Lan(dqg, tk, 35, new Date(System.currentTimeMillis()));

		System.out.println(qg.getThoiGianQG());
	}

}
