package baitapB13;

import java.util.ArrayList;
import java.util.List;

class TruongPhong extends NhanVien {
    private List<NhanVien> nhanVienDuoiQuyen = new ArrayList<>();

    public TruongPhong(String maSo, String hoTen, String soDienThoai, int soNgayLam) {
        super(maSo, hoTen, soDienThoai, soNgayLam);
        super.luong1Ngay = 200;
    }

    public void themNhanVien(NhanVien nv) {
        nhanVienDuoiQuyen.add(nv);
        nv.setTruongPhongQuanLy(this);
    }

    public void xoaNhanVien(NhanVien nv) {
        nhanVienDuoiQuyen.remove(nv);
        nv.setTruongPhongQuanLy(null);
    }

    public int soNhanVienDuoiQuyen() {
        return nhanVienDuoiQuyen.size();
    }

    @Override
    public double tinhLuong() {
        return luong1Ngay * soNgayLam + 100 * soNhanVienDuoiQuyen();
    }

    public List<NhanVien> getNhanVienDuoiQuyen() {
        return nhanVienDuoiQuyen;
    }
}