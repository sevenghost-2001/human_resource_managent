package baitapB13;

class GiamDoc extends NhanVien {
    private double coPhan;

    public GiamDoc(String maSo, String hoTen, String soDienThoai, int soNgayLam, double coPhan) {
        super(maSo, hoTen, soDienThoai, soNgayLam);
        super.luong1Ngay = 300;
        this.coPhan = Math.min(coPhan, 100);
    }

    public double getCoPhan() {
        return coPhan;
    }

    public double tinhThuNhap(double loiNhuanCongTy) {
        return tinhLuong() + (coPhan / 100) * loiNhuanCongTy;
    }
}