package baitapB13;

import java.util.Scanner;

//File: Main.java
//File: Main.java
import java.util.*;

public class Main {
public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);
   CongTy congTy = new CongTy("ABC Corp", "123456789", 1000000);

   while (true) {
       System.out.println("\n===== MENU QUẢN LÝ NHÂN SỰ =====");
       System.out.println("1. Thêm nhân sự");
       System.out.println("2. Xuất danh sách nhân sự");
       System.out.println("3. Xóa nhân sự theo mã số");
       System.out.println("4. Phân bổ nhân viên vào trưởng phòng");
       System.out.println("5. Tìm trưởng phòng có nhiều nhân viên nhất");
       System.out.println("6. Tìm nhân viên có lương cao nhất");
       System.out.println("7. Sắp xếp nhân sự theo tên");
       System.out.println("8. Sắp xếp nhân sự theo thu nhập");
       System.out.println("9. Xuất tổng lương toàn công ty");
       System.out.println("10. Tìm giám đốc có cổ phần cao nhất");
       System.out.println("11. Thoát");
       System.out.print("Chọn chức năng (1-11): ");

       String choice = sc.nextLine();
       switch (choice) {
           case "1":
               themNhanSu(sc, congTy);
               break;
           case "2":
               congTy.getDanhSachNhanSu().forEach(System.out::println);
               break;
           case "3":
               System.out.print("Nhập mã số nhân sự cần xóa: ");
               String maXoa = sc.nextLine();
               congTy.xoaNhanSu(maXoa);
               break;
           case "4":
               phanBoNhanVien(sc, congTy);
               break;
           case "5":
               congTy.truongPhongQuanLyNhieuNhat();
               break;
           case "6":
               congTy.nhanVienLuongCaoNhat();
               break;
           case "7":
               congTy.sapXepTheoTen();
               break;
           case "8":
               congTy.sapXepTheoLuong();
               break;
           case "9":
               System.out.println("Tổng lương công ty: " + congTy.tinhTongLuong());
               break;
           case "10":
               GiamDoc gd = congTy.timGiamDocCoPhanCaoNhat();
               if (gd != null)
                   System.out.println("Giám đốc có cổ phần cao nhất: " + gd);
               else
                   System.out.println("Không có giám đốc nào trong danh sách.");
               break;
           case "11":
               System.out.println("Thoát chương trình.");
               return;
           default:
               System.out.println("Vui lòng chọn từ 1 đến 11.");
       }
   }
}

public static void themNhanSu(Scanner sc, CongTy congTy) {
   System.out.println("\nChọn loại nhân sự:");
   System.out.println("1. Nhân viên");
   System.out.println("2. Trưởng phòng");
   System.out.println("3. Giám đốc");
   System.out.print("Lựa chọn: ");
   String loai = sc.nextLine();

   System.out.print("Mã số: ");
   String maSo = sc.nextLine();

   String hoTen;
   while (true) {
       System.out.print("Họ tên: ");
       hoTen = sc.nextLine();
       if (hoTen.matches(".*\\d.*")) {
           System.out.println("Họ tên không được chứa số. Vui lòng nhập lại.");
       } else break;
   }

   String soDienThoai;
   while (true) {
       System.out.print("Số điện thoại: ");
       soDienThoai = sc.nextLine();
       if (!soDienThoai.matches("\\d+")) {
           System.out.println("Số điện thoại chỉ được chứa chữ số. Vui lòng nhập lại.");
       } else break;
   }

   int soNgayLam;
   while (true) {
       try {
           System.out.print("Số ngày làm việc: ");
           soNgayLam = Integer.parseInt(sc.nextLine());
           if (soNgayLam < 0) {
               System.out.println("Số ngày làm phải >= 0. Vui lòng nhập lại.");
           } else break;
       } catch (NumberFormatException e) {
           System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
       }
   }

   switch (loai) {
       case "1":
           congTy.themNhanSu(new NhanVien(maSo, hoTen, soDienThoai, soNgayLam));
           break;
       case "2":
           congTy.themNhanSu(new TruongPhong(maSo, hoTen, soDienThoai, soNgayLam));
           break;
       case "3":
           double coPhan;
           while (true) {
               try {
                   System.out.print("Cổ phần (%): ");
                   coPhan = Double.parseDouble(sc.nextLine());
                   if (coPhan < 0 || coPhan > 100) {
                       System.out.println("Cổ phần phải trong khoảng 0-100%. Vui lòng nhập lại.");
                   } else break;
               } catch (NumberFormatException e) {
                   System.out.println("Vui lòng nhập số thực hợp lệ.");
               }
           }
           congTy.themNhanSu(new GiamDoc(maSo, hoTen, soDienThoai, soNgayLam, coPhan));
           break;
       default:
           System.out.println("Lựa chọn không hợp lệ.");
   }
}

public static void phanBoNhanVien(Scanner sc, CongTy congTy) {
   System.out.print("Nhập mã trưởng phòng: ");
   String maTP = sc.nextLine();
   TruongPhong tp = null;
   for (NhanSu ns : congTy.getDanhSachNhanSu()) {
       if (ns instanceof TruongPhong && ns.getMaSo().equals(maTP)) {
           tp = (TruongPhong) ns;
           break;
       }
   }
   if (tp == null) {
       System.out.println("Không tìm thấy trưởng phòng với mã: " + maTP);
       return;
   }

   System.out.print("Nhập mã nhân viên cần phân bổ: ");
   String maNV = sc.nextLine();
   for (NhanSu ns : congTy.getDanhSachNhanSu()) {
       if (ns instanceof NhanVien && !(ns instanceof TruongPhong) && !(ns instanceof GiamDoc)
           && ns.getMaSo().equals(maNV)) {
           tp.themNhanVien((NhanVien) ns);
           System.out.println("Đã phân bổ nhân viên " + ns.getHoTen() + " cho trưởng phòng " + tp.getHoTen());
           return;
       }
   }
   System.out.println("Không tìm thấy nhân viên phù hợp để phân bổ.");
}
}
