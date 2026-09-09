data class Student (
    val studentID : String,
    val fullName : String,
    val age : Int,
    val major : String,
    val GPA : Double
)

// 1. Add Student + Validation GPA (0.0 - 10.0 hoặc 0.0 - 4.0)
fun addStudent(students : MutableList<Student>){
    print("Nhap vao so sinh vien can them vao : ")
    val n = readln().toInt()
    for (i in 1..n) {
        println("\n--- Nhap sinh vien thu $i ---")
        print("Nhap vao ID sinh vien : ")
        val studentID = readln()
        print("Nhap vao ten sinh vien : ")
        val fullName = readln()
        print("Nhap vao tuoi cua sinh vien : ")
        val age = readln().toInt()
        print("Nhap vao thong tin chuyen nganh : ")
        val major = readln()

        // Validation GPA
        var GPA: Double
        while (true) {
            print("Nhap vao diem GPA cua sinh vien (0.0 - 10.0): ")
            GPA = readln().toDouble()
            if (GPA in 0.0..10.0) {
                break
            } else {
                println("Loi: Diem GPA phai nam trong khoang 0.0 đến 10.0! Vui long nhap lai.")
            }
        }

        val student = Student(studentID, fullName, age, major, GPA)
        students.add(student)
    }
    println("Da nhap thanh cong $n sinh vien!")
}

// 2. Display All Students
fun displayAllStudent(students : MutableList<Student>){
    if (students.isEmpty()){
        println("Danh sach sinh vien dang trong!")
        return
    }
    while(true){
        println("\n========== Hien thi sinh vien ==========")
        println("1. Toan bo sinh vien")
        println("2. 3 sinh vien co GPA cao nhat")
        println("0. Exit")
        print("Chon: ")
        val choice = readln().toInt()
        when(choice){
            1 -> {
                println("====================== Danh sach sinh vien ===========================")
                for (student in students){
                    println("ID: ${student.studentID} | Ten: ${student.fullName} | Tuoi: ${student.age} | Nganh: ${student.major} | GPA: ${student.GPA}")
                }
            }
            2 -> {
                println("====================== Top 3 sinh vien GPA cao nhat ===========================")
                val top3 = students.sortedByDescending { it.GPA }.take(3)
                top3.forEach { println("ID: ${it.studentID} | Ten: ${it.fullName} | Tuoi: ${it.age} | Nganh: ${it.major} | GPA: ${it.GPA}") }
            }
            0 -> return
            else -> println("Lua chon khong hop le!")
        }
    }
}

// 3. Search Student
fun searchStudent(students : MutableList<Student>){
    while(true){
        println("\n======= Tim Kiem sinh vien ======")
        println("1. Tim sinh vien lon tuoi nhat")
        println("2. Tim kiem theo khoang GPA")
        println("3. Tim kiem theo nganh")
        println("4. Tim kiem theo mot phan ten")
        println("5. Tim kiem theo ma sinh vien")
        println("6. Danh sach nhan hoc bong (GPA >= 3.2 hoac GPA >= 8.0)")
        println("0. Exit")
        print("Chon: ")
        val choice = readln().toInt()
        when (choice) {
            1 -> {
                val tuoiStudent = students.maxByOrNull { it.age }
                if (tuoiStudent != null) {
                    println("Sinh vien co tuoi lon nhat la: ${tuoiStudent.fullName} - Tuoi: ${tuoiStudent.age} - GPA: ${tuoiStudent.GPA}")
                }
            }
            2 -> {
                print("Nhap vao GPA thap nhat : ")
                val min = readln().toDouble()
                print("Nhap vao GPA cao nhat : ")
                val max = readln().toDouble()
                val result = students.filter { it.GPA in min..max }
                if (result.isEmpty()) println("Khong tim thay sinh vien phu hop!")
                else result.forEach { println("${it.fullName} - GPA: ${it.GPA}") }
            }
            3 -> {
                print("Nhap vao nganh muon tim kiem : ")
                val major = readln()
                val result = students.filter { it.major.equals(major, ignoreCase = true) }
                if (result.isEmpty()) println("Khong co sinh vien thuoc nganh $major")
                else result.forEach { println("${it.fullName} - Nganh: ${it.major} - GPA: ${it.GPA}") }
            }
            4 -> {
                print("Nhap vao ten muon tim : ")
                val keyword = readln()
                val result = students.filter { it.fullName.contains(keyword, ignoreCase = true) }
                if (result.isEmpty()) println("Khong tim thay sinh vien nao!")
                else result.forEach { println("${it.fullName} - ID: ${it.studentID}") }
            }
            5 -> {
                print("Nhap vao ma sinh vien muon tim: ")
                val id = readln()
                val student = students.find { it.studentID.lowercase() == id.lowercase() }
                if (student == null)
                    println("Khong co sinh vien giong ma nay!")
                else
                    println("Tim thay: ${student.fullName} - ID: ${student.studentID} - GPA: ${student.GPA}")
            }
            6 -> {
                println("--- DANH SACH NHAN HOC BONG ---")
                // Lọc theo thang điểm (hỗ trợ cả hệ 4 và hệ 10)
                val hocBong = students.filter { it.GPA >= 8.0 || (it.GPA >= 3.2 && it.GPA <= 4.0) }
                if (hocBong.isEmpty()) println("Khong co sinh vien nao du dieu kien hoc bong!")
                else hocBong.forEach { println("ID: ${it.studentID} | Ten: ${it.fullName} | GPA: ${it.GPA}") }
            }
            0 -> return
            else -> println("Lua chon khong hop le!")
        }
    }
}

// 4. Calculate & Statistics
fun calculateAverageGPA(students: MutableList<Student>){
    while(true){
        println("\n========= Thong ke & Sap xep ========")
        println("1. Dem so sinh vien gioi (GPA >= 8.0 / 3.2) va trung binh (GPA < 5.0 / 2.0)")
        println("2. Tinh GPA trung binh theo nganh")
        println("3. Sap xep sinh vien theo GPA giam dan")
        println("4. Sap xep sinh vien theo ten (ABC)")
        println("5. Sap xep sinh vien theo tuoi")
        println("0. Exit")
        print("Chon: ")
        val choice = readln().toInt()
        when(choice){
            1 -> {
                val giois = students.count { it.GPA >= 8.0 || (it.GPA >= 3.2 && it.GPA <= 4.0) }
                val yeus = students.count { it.GPA < 5.0 && it.GPA < 2.0 }
                println("So sinh vien GPA cao (Gioi/Xuat sac): $giois")
                println("So sinh vien GPA thap (< 5.0 / < 2.0): $yeus")
            }
            2 -> {
                print("Nhap nganh can tinh GPA trung binh: ")
                val major = readln()
                val studentInMajor = students.filter { it.major.equals(major, ignoreCase = true) }
                if(studentInMajor.isEmpty()){
                    println("Khong co sinh vien nao trong nganh $major")
                } else {
                    val avg = studentInMajor.map { it.GPA }.average()
                    println("Diem GPA trung binh cua nganh $major la: %.2f".format(avg))
                }
            }
            3 -> {
                val sort = students.sortedByDescending { it.GPA }
                println("--- Danh sach sap xep theo GPA giam dan ---")
                sort.forEach { println("Ten: ${it.fullName} - GPA: ${it.GPA}") }
            }
            4 -> {
                // Sắp xếp theo Tên chính (lấy từ cuối cùng trong chuỗi Họ tên)
                val sort = students.sortedBy { it.fullName.trim().split(" ").last() }
                println("--- Danh sach sap xep theo Ten (ABC) ---")
                sort.forEach { println("Ten: ${it.fullName} | ID: ${it.studentID}") }
            }
            5 -> {
                val sort = students.sortedBy { it.age }
                println("--- Danh sach sap xep theo Tuoi ---")
                sort.forEach { println("Ten: ${it.fullName} - Tuoi: ${it.age}") }
            }
            0 -> return
            else -> println("Khong hop le!")
        }
    }
}

// 5. Find Student with Highest GPA
fun findStudent(students : MutableList<Student>){
    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong!")
        return
    }
    val topStudent = students.maxByOrNull { it.GPA }
    println("\nSinh vien co diem GPA cao nhat la: ${topStudent?.fullName} - GPA: ${topStudent?.GPA}")
}

// 6. Remove Student
fun removeStudent(students : MutableList<Student>){
    print("\nNhap vao ma sinh vien can xoa: ")
    val id = readln()
    val removed = students.removeIf { it.studentID.equals(id, ignoreCase = true) }
    if (removed) {
        println("Da xoa thanh cong sinh vien co ma $id!")
    } else {
        println("Khong tim thay sinh vien co ma $id!")
    }
}

fun main() {
    val students = mutableListOf(
        Student("SV001", "Tran Thi Hanh", 20, "CNTT", 8.5),
        Student("SV002", "Tran Thi Giang Huong", 21, "CNTT", 9.2),
        Student("SV003", "Nguyen Anh Long", 22, "SPCN", 7.4),
        Student("SV004", "Pham Hong Quoc", 20, "CNTT", 4.8),
        Student("SV005", "Huynh Van Hau", 20, "SPCN", 6.9)
    )
    while(true){
        println("\n============ STUDENTS MANAGEMENT =========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search students")
        println("4. Calculate average GPA & Sort")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("0. Exit")
        println("==========================================")
        print("Choose: ")

        val choice = readln().toIntOrNull() ?: -1
        when(choice){
            1 -> addStudent(students)
            2 -> displayAllStudent(students)
            3 -> searchStudent(students)
            4 -> calculateAverageGPA(students)
            5 -> findStudent(students)
            6 -> removeStudent(students)
            0 -> {
                println("Thoat chuong trinh. Tam biet!")
                return
            }
            else -> println("Lua chon khong hop le!")
        }
    }
}