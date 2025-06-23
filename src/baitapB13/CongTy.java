package baitapB13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//File: CongTy.java
class CongTy {
 private String tenCT, maSoThue;
 private double doanhThuThang;
 private List<NhanSu> danhSachNhanSu = new ArrayList<>();

 public CongTy(String tenCT, String maSoThue, double doanhThuThang) {
     this.tenCT = tenCT;
     this.maSoThue = maSoThue;
     this.doanhThuThang = doanhThuThang;
 }

 public void themNhanSu(NhanSu ns) {
     danhSachNhanSu.add(ns);
 }

 public void xoaNhanSu(String maSo) {
     danhSachNhanSu.removeIf(ns -> ns.getMaSo().equals(maSo));
 }

 public List<NhanSu> getDanhSachNhanSu() {
     return danhSachNhanSu;
 }

 public double tinhTongLuong() {
     return danhSachNhanSu.stream().mapToDouble(NhanSu::tinhLuong).sum();
 }

 public double tinhLoiNhuan() {
     return doanhThuThang - tinhTongLuong();
 }

 public GiamDoc timGiamDocCoPhanCaoNhat() {
     return danhSachNhanSu.stream()
             .filter(ns -> ns instanceof GiamDoc)
             .map(ns -> (GiamDoc) ns)
             .max(Comparator.comparingDouble(GiamDoc::getCoPhan))
             .orElse(null);
 }

 public void truongPhongQuanLyNhieuNhat() {
     TruongPhong maxTP = null;
     int maxSoNhanVien = -1;

     for (NhanSu ns : danhSachNhanSu) {
         if (ns instanceof TruongPhong tp) {
             if (tp.soNhanVienDuoiQuyen() > maxSoNhanVien) {
                 maxSoNhanVien = tp.soNhanVienDuoiQuyen();
                 maxTP = tp;
             }
         }
     }

     if (maxTP != null) {
         System.out.println("Trưởng phòng quản lý nhiều nhân viên nhất: " + maxTP + ", Số lượng: " + maxSoNhanVien);
         System.out.println("Danh sách nhân viên dưới quyền:");
         for (NhanVien nv : maxTP.getNhanVienDuoiQuyen()) {
             System.out.println("- " + nv);
         }
     } else {
         System.out.println("Không có trưởng phòng nào trong danh sách.");
     }
 }

 public void nhanVienLuongCaoNhat() {
     NhanSu caoNhat = danhSachNhanSu.stream()
             .filter(ns -> ns instanceof NhanVien && !(ns instanceof TruongPhong) && !(ns instanceof GiamDoc))
             .max(Comparator.comparingDouble(NhanSu::tinhLuong))
             .orElse(null);

     if (caoNhat != null) {
         System.out.println("Nhân viên có lương cao nhất: " + caoNhat + ", Lương: " + caoNhat.tinhLuong());
     } else {
         System.out.println("Không có nhân viên thường nào trong danh sách.");
     }
 }

 public void sapXepTheoTen() {
     danhSachNhanSu.sort(Comparator.comparing(ns -> ns.getHoTen().toLowerCase()));
     System.out.println("Đã sắp xếp danh sách theo tên:");
     danhSachNhanSu.forEach(System.out::println);
 }

 public void sapXepTheoLuong() {
     danhSachNhanSu.sort(Comparator.comparingDouble(NhanSu::tinhLuong).reversed());
     System.out.println("Đã sắp xếp danh sách theo lương giảm dần:");
     danhSachNhanSu.forEach(System.out::println);
 }
} 