package bean;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private String tenCaNhan;
	private String email;
	private String diaChi;
	private String sdt;
	private String phanQuyen;
	private String trangThai;
	private float soTienHienCo;

	public TaiKhoan() {}
	
	

	public TaiKhoan(String tenDangNhap, String matKhau) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}



	public TaiKhoan(String tenDangNhap, String matKhau, String tenCaNhan, String email, String diaChi, String sdt,
			String phanQuyen, String trangThai, float soTienHienCo) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.tenCaNhan = tenCaNhan;
		this.email = email;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.phanQuyen = phanQuyen;
		this.trangThai = trangThai;
		this.soTienHienCo = soTienHienCo;
	}
	




	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTenCaNhan() {
		return tenCaNhan;
	}

	public void setTenCaNhan(String tenCaNhan) {
		this.tenCaNhan = tenCaNhan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(String phanQuyen) {
		this.phanQuyen = phanQuyen;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public float getSoTienHienCo() {
		return soTienHienCo;
	}

	public void setSoTienHienCo(float soTienHienCo) {
		this.soTienHienCo = soTienHienCo;
	}


	
}
