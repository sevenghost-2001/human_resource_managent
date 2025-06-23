package baitapB13;

class NhanVien extends NhanSu {
    private TruongPhong truongPhongQuanLy;

    public NhanVien(String maSo, String hoTen, String soDienThoai, int soNgayLam) {
        super(maSo, hoTen, soDienThoai, soNgayLam, 100);
    }

    public void setTruongPhongQuanLy(TruongPhong tp) {
        this.truongPhongQuanLy = tp;
    }

    public TruongPhong getTruongPhongQuanLy() {
        return truongPhongQuanLy;
    }

    @Override
    public double tinhLuong() {
        return luong1Ngay * soNgayLam;
    }
}