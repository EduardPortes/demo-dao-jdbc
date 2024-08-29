package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.Scanner;

public class ProgramDepartment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== TEST 1: department    findById ====");

        Department dep = departmentDao.findById(2);
        System.out.println(dep);

        System.out.println("==== TEST 2: department    findAll ====");

        List<Department> list = departmentDao.findAll();

        for (Department obj : list){
            System.out.println(obj);
        }

        System.out.println("==== TEST 3: department    insert ====");

        Department newdep = new Department(5, "Light");

        departmentDao.insert(newdep);
        System.out.println("Inserted!");

        System.out.println("==== TEST 3: department    update ====");

        dep = new Department(6, "MakeUp");

        departmentDao.update(dep);

        System.out.println("Update completed");

        System.out.println("==== TEST 3: department    deleteById ====");

        System.out.print("Enter the ID: ");
        int id = sc.nextInt();

        departmentDao.deleteById(id);

        System.out.println("Delete completed");

        sc.close();
    }
}
