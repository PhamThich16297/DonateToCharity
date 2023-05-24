package bean;

import java.util.Date;

public class DotQuyenGop {
	private int maDot;
	private String tieuDe;
	private String urlBanner;
	private float tienQuyenGop;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private int tgConLai;
	private DVTN dvtn;
	private String noiDung;
	private float tienDaQuyenGop;
	private int luotQuyenGop;

	public DotQuyenGop() {
	}

	public DotQuyenGop(int maDot, String tieuDe, String urlBanner, float tienQuyenGop, Date ngayBatDau,
			Date ngayKetThuc, int tgConLai, DVTN dvtn, String noiDung, float tienDaQuyenGop, int luotQuyenGop) {
		this.maDot = maDot;
		this.tieuDe = tieuDe;
		this.urlBanner = urlBanner;
		this.tienQuyenGop = tienQuyenGop;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.tgConLai = tgConLai;
		this.dvtn = dvtn;
		this.noiDung = noiDung;
		this.tienDaQuyenGop = tienDaQuyenGop;
		this.luotQuyenGop = luotQuyenGop;
	}

	public DotQuyenGop(int maDot, String tieuDe, String urlBanner, float tienQuyenGop, Date ngayBatDau,
			Date ngayKetThuc, DVTN dvtn) {
		this.maDot = maDot;
		this.tieuDe = tieuDe;
		this.urlBanner = urlBanner;
		this.tienQuyenGop = tienQuyenGop;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.dvtn = dvtn;
	}

	public DotQuyenGop(int maDot, String tieuDe, String urlBanner, float tienQuyenGop, Date ngayBatDau,
			Date ngayKetThuc, int tgConLai, DVTN dvtn, String noiDung) {
		this.maDot = maDot;
		this.tieuDe = tieuDe;
		this.urlBanner = urlBanner;
		this.tienQuyenGop = tienQuyenGop;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.tgConLai = tgConLai;
		this.dvtn = dvtn;
		this.noiDung = noiDung;
	}

	public int getMaDot() {
		return maDot;
	}

	public void setMaDot(int maDot) {
		this.maDot = maDot;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getUrlBanner() {
		return urlBanner;
	}

	public void setUrlBanner(String urlBanner) {
		this.urlBanner = urlBanner;
	}

	public float getTienQuyenGop() {
		return tienQuyenGop;
	}

	public void setTienQuyenGop(float tienQuyenGop) {
		this.tienQuyenGop = tienQuyenGop;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public int getTgConLai() {
		return tgConLai;
	}

	public void setTgConLai(int tgConLai) {
		this.tgConLai = tgConLai;
	}

	public DVTN getDvtn() {
		return dvtn;
	}

	public void setDvtn(DVTN dvtn) {
		this.dvtn = dvtn;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public float getTienDaQuyenGop() {
		return tienDaQuyenGop;
	}

	public void setTienDaQuyenGop(float tienDaQuyenGop) {
		this.tienDaQuyenGop = tienDaQuyenGop;
	}

	public int getLuotQuyenGop() {
		return luotQuyenGop;
	}

	public void setLuotQuyenGop(int luotQuyenGop) {
		this.luotQuyenGop = luotQuyenGop;
	}

	@Override
	public String toString() {
		return "DotQuyenGop [maDot=" + maDot + ", tieuDe=" + tieuDe + ", urlBanner=" + urlBanner + ", tienQuyenGop="
				+ tienQuyenGop + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", tgConLai="
				+ tgConLai + ", dvtn=" + dvtn + "]";
	}

}
