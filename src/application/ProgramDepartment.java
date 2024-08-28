package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDepartment {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== TEST 1: department    insert ====");

        Department newdep = new Department(5, "Light");

        departmentDao.insert(newdep);
        System.out.println("Inserted!");


    }
}
