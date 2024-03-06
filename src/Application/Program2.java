package Application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		DepartmentDao departmentDao=DaoFactory.createdDepartmentDao();
		
		System.out.println("=== TEST 1: Department findById =======");
		Department findBy=departmentDao.FindById(1);
		System.out.println(findBy);
		
		
		System.out.println("\n=== TEST 2: Department findAll =======");
		List<Department> list=departmentDao.FindyAll();
		for(Department x:list) {
			System.out.println(x);
		}
		
		
		System.out.println("\n=== TEST 3: Deparment Insert =======");
		Department dep=new Department(null, "Design");
		departmentDao.insert(dep);
		System.out.println("Department Inserted! Id="+dep.getId());
		
		System.out.println("\n==== TEST 4: Department Update =====");
		Department UpdateDep=departmentDao.FindById(1);
		UpdateDep.setName("Food");
		departmentDao.update(UpdateDep);
		System.out.println("Update Completed!");

		
		System.out.println("\n==== TEST 5: Department Delete =====");
		System.out.println("Enter Id for delete test:");
		int test=sc.nextInt();
		departmentDao.deleteById(test);
		System.out.println("Delete Completed");
		
		
		
		
		
		
	}

}
