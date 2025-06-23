package baitapB13;

abstract class NhanSu {
    protected String maSo, hoTen, soDienThoai;
    protected int soNgayLam;
    protected double luong1Ngay;

    public NhanSu(String maSo, String hoTen, String soDienThoai, int soNgayLam, double luong1Ngay) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.soNgayLam = soNgayLam;
        this.luong1Ngay = luong1Ngay;
    }

    public abstract double tinhLuong();

    public String getHoTen() {
        return hoTen;
    }

    public double getLuong1Ngay() {
        return luong1Ngay;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public String getMaSo() {
        return maSo;
    }

    public String toString() {
        return String.format("[%s] %s - %s", maSo, hoTen, getClass().getSimpleName());
    }
}