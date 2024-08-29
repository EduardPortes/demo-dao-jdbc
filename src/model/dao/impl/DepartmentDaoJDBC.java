package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.List;


public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department "
                    + "(Id, Name) "
                    + "VALUES "
                    + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected erro! no rows affected");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(st);
        }



    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM department "
                    + "WHERE department.Id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return dep;
            } else {
                System.out.println("No department with the ID inserted");
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();

        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));

        return dep;
    }

}
