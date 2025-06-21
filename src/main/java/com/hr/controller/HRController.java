package com.hr.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hr.entity.CreatePost;
import com.hr.entity.Employee;
import com.hr.entity.UserCompose;
import com.hr.repository.ComposeRepository;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.service.HrService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HRController 
{
	
	@Autowired
	private HrService hrService;
	
	@Autowired
	private CreatePostRepository createPostRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ComposeRepository composeRepository;
	
	
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	
	@GetMapping("/forgot")
	public String forgetPassword(HttpSession session)
	{
		
//		Object id4 = session.getAttribute("id");
//
//	    if (id4 == null) {
//	        return "redirect:/login?sessionExpired=true";
//	    }
		
		return "forget-password";
	}
	
	@GetMapping("/dashboards")
	public String dashBoardBase(@RequestParam("Username") String username, @RequestParam("Password") String Password ,Model model,HttpSession session )
    {
		
		
		

		try
		{
         String empId=username.trim().substring(3);
         
		
         
		Employee emp = employeeRepository.findByIdAndPassword(Integer.parseInt(empId), Password);
		
		
		
	
         
         		if(emp!=null)
		{
         			
         			
			
			session.setAttribute("id", Integer.parseInt(empId)); //For the MyProfile we set id and get into the myprofile controller . 
			session.setAttribute("name",emp.getEmployeeName() );
			session.setAttribute("desg",emp.getDesignation() );

			
			
			if(emp.getRole().equals("USER"))
			{
				return "redirect:/user-dashbaord";
				
			}
			else if(emp.getRole().equals("ADMIN"))
			{
				
				
				
					return "redirect:/dash";
			}
			else
			{
				return "redirect:/login";
			}
		}
		else
		{
			model.addAttribute("failed","Invalid ID and Password Kindly Check !!");
			
			return "login";
		}
		
    }
	catch(Exception e)
		{
		  // e.printStackTrace();
		   System.err.println(e.getMessage());
		   return "redirect:/login";
		}
		
    }
	
	@GetMapping("/dash")
	public String dashBoard(Model model,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }

		
        List<UserCompose> findAll =(List<UserCompose>)composeRepository.findAll();
		
		
		//Write this logic because we want designation inside status (Admin Tab).
		// We use @Transcient annotation inside userCompose.html (to get position to Employee table )
		
		findAll.stream().forEach(a->
		{
			
			int id=a.getParentUkid();
			
			String designation = employeeRepository.findById(id).get().getDesignation();
			
			a.setPosition(designation);
		});
		
		
		model.addAttribute("allUserComposeDetail", findAll);
			
			
			// ***************************************************************
			
			// Counting Developemnt (Position)
			
			List<Employee>findAlls=(List<Employee>)employeeRepository.findAll();

			
			
			Map<String, Long> temp = findAlls.stream()
				    .map(a -> {
				        int id = a.getId();
				        String department = employeeRepository.findById(id).get().getDepartment();
				       
				        return department;
				    })
				    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			
			
				// Preserve custom order
				List<String> order = List.of("Development", "QA Testing", "Networking", "HR Team", "Security", "Sales Marketing");

				Map<String, Long> orderedMap = new LinkedHashMap<>();
				for (String key : order) {
				    orderedMap.put(key, temp.getOrDefault(key, 0L));
				}

				model.addAttribute("designationCounts", orderedMap);
			   
				//Pending Approve Cancelled 
				
				List<UserCompose> findAll2 = (List<UserCompose>) composeRepository.findAll();

				Map<String, Long> temp1 = findAll2.stream()
				    .map(a -> a.getStatus() == null ? "Unknown" : a.getStatus())
				    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

				
				long total=findAlls.size();
				
				List<String> order1 = List.of("PENDING", "APPROVE", "CANCELLED", "DENIED", "ALL", "REMINDER");

				Map<String, Long> orderedMap1 = new LinkedHashMap<>();
				for (String key : order1) {
				    orderedMap1.put(key, temp1.getOrDefault(key, 0L));
				}
				
				
				orderedMap1.put("ALL",total);
				orderedMap1.put("REMINDER",total-2);
				
				model.addAttribute("statusCounts", orderedMap1);

			
				
				List<Employee> l1=(List<Employee>) employeeRepository.findAll();
				model.addAttribute("details", l1);
		
		           return "dashboard";
		           
		           
	}
	
	@GetMapping("/add-employee")
	public String addEmployee(Model model, HttpSession session)
	{
		// By using of this automatically session expired 
		// whenever we call any method like /dash not call .
		
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
	    
	    
	    // End
		
		model.addAttribute("employee", new Employee());
		
		
		return "addemployee";
	}
	
	@GetMapping("/all-employee")
	public String allEmployee(Model model,HttpSession session)
	{
		//get all records
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		List<Employee> allEmpDetails = hrService.getAllEmpDetails();
		
		model.addAttribute("allEmpDetails", allEmpDetails);
		
		
			
		
		return "allemployee";
	}
	
	@GetMapping("/create-post")
	public String createPost(Model model,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		List<CreatePost> findAll = (List<CreatePost>)createPostRepository.findAll();
		
		model.addAttribute("post", findAll);
		return "createpost";
	}
	
	
	
	@GetMapping("/status")
	public String status(Model model,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		List<UserCompose> findAll =(List<UserCompose>)composeRepository.findAll();
		
		
		//Write this logic because we want designation inside status (Admin Tab).
		// We use @Transcient annotation inside userCompose.html (to get position to Employee table )
		
		findAll.stream().forEach(a->
		{
			
			int id=a.getParentUkid();
			
			String designation = employeeRepository.findById(id).get().getDesignation();
			
			a.setPosition(designation);
		});
		
		
		model.addAttribute("allUserComposeDetails", findAll);
		
		
		return "status";
	}
	
	
	
	@GetMapping("/my-profile")
	public String myProfile(HttpSession session,Model model)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		Object attribute = session.getAttribute("id");
		

		int userId = Integer.parseInt(attribute.toString());
		
		Employee employee = employeeRepository.findById(userId).get();
		
		
		model.addAttribute("emp",employee);
		
		return "myprofile";
	}
	
	@GetMapping("/setting")
	public String setting(HttpSession session)
	{
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		return "setting";
	}
	
	
	//To handle error we use valid,binding,model inside add employee form tab use th:object 
	//inside add-employee controller create employee object .( because employee.java we adding some annotation )
	//That why we handles exception whenever u click on submit but and field is empty encounter  exception.
	
	@PostMapping("/save-employee")
	public String saveEmployee(@Valid @ModelAttribute Employee emp, BindingResult result,Model model,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		//Handling exception  Start
		
        if(result.hasErrors()) {
	    result.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
	    model.addAttribute("employee", emp);
	    return "addemployee";
	}
	
	// End
		
		
		
		
		emp.setPassword(emp.getDateOfBirth()); // User first pass date of birth after they will forget the pass . ( company provide temporary pass to the user )
		
		hrService.addEmployeeDetails(emp);
		
		
       return "redirect:/all-employee";
		
		
		
	}
	
	@PostMapping("/save-post")
	public String savePost(@ModelAttribute CreatePost createPost,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		createPost.setAddedDate(LocalDate.now().toString());
		CreatePost addPostDetails = hrService.addPostDetails(createPost);
	
		return "redirect:/create-post";
		
	}
	
	// For setting (change password)
	
	@PostMapping("/update-password")
	public String updatePassword(@RequestParam ("password")String password,@RequestParam ("newPassword")String newPassword,@RequestParam ("re-enterPassword") String rePassword , HttpSession session,Model model)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
	    
	    
	    
		
		Object attribute = session.getAttribute("id");
		

		int userId = Integer.parseInt(attribute.toString());
		
		Employee employee = employeeRepository.findByIdAndPassword(userId, password);
		
		
		
		
		if(employee!=null &&  newPassword.equals(rePassword))
		{
			
		    employee.setPassword(rePassword);
		    employeeRepository.save(employee);
			
			return "redirect:/login";
		}
		else
		{
			
			model.addAttribute("failed","Please Match Password !!!");

			return "setting";
		}
		
	}
	
	
	@GetMapping("/edit-record") // allemployee.html edit button
	public String editRecord(@RequestParam("id") int id, Model model,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		

		Employee emp=employeeRepository.findById(id).get();
		
		model.addAttribute("employee", emp);
		
		return "edit-record";
	}
	
	@PostMapping("/edit-employee")
	public String addEditDetails(@ModelAttribute Employee emp,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		int id = emp.getId();
		Employee employee = employeeRepository.findById(id).get();
		
		if(employee!=null)
		{
			employeeRepository.save(emp);
			
        }
		
		return "redirect:/all-employee";
		
	}
	
	@GetMapping("/delete-record")
	public String deleteRecord(@RequestParam("id") int id,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
	    employeeRepository.deleteById(id);
	    
	   
		
		return "redirect:/all-employee";
	}
	
	
	
	//User Dashboard 
	
	@GetMapping("/user-dashbaord")
	public String userDashBoard(Model model,HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
         Object attribute = session.getAttribute("id");


         int userId = Integer.parseInt(attribute.toString());
         
         
         List<UserCompose> findAll= composeRepository.findByParentUkid(userId);
		
		
		findAll.stream().forEach(a->
		{
			
			int id=a.getParentUkid();
			
			String designation = employeeRepository.findById(id).get().getDesignation();
			
			a.setPosition(designation);
		});
		
		
		model.addAttribute("allUserComposeDetails", findAll);
		
		
		//return  "user-dashbaord";
		//******************************************************************
		//Counting details 
		
		

		List<Employee>findAlls=(List<Employee>)employeeRepository.findAll();

		
		
		Map<String, Long> temp = findAlls.stream()
			    .map(a -> {
			        int id = a.getId();
			        String department = employeeRepository.findById(id).get().getDepartment();
			       
			        return department;
			    })
			    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			// Preserve custom order
			List<String> order = List.of("Development", "QA Testing", "Networking", "HR Team", "Security", "Sales Marketing");

			Map<String, Long> orderedMap = new LinkedHashMap<>();
			for (String key : order) {
			    orderedMap.put(key, temp.getOrDefault(key, 0L));
			}

			model.addAttribute("designationCounts", orderedMap);
		   
	
			// Pending  Approved Denied Cancelled All
			
			List<UserCompose> findAll2 = (List<UserCompose>) composeRepository.findAll();

			Map<String, Long> temp1 = findAll2.stream()
			    .map(a -> a.getStatus() == null ? "Unknown" : a.getStatus())
			    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			long total=findAll2.size();
			
			List<String> order1 = List.of("PENDING", "APPROVE", "CANCELLED", "DENIED", "ALL", "REMINDER");

			Map<String, Long> orderedMap1 = new LinkedHashMap<>();
			for (String key : order1) {
			    orderedMap1.put(key, temp1.getOrDefault(key, 0L));
			}
			
			orderedMap1.put("ALL",total);
			orderedMap1.put("REMINDER",total-2);
			
			model.addAttribute("statusCounts", orderedMap1);

		
			// Latest Birthday :
			
			List<Employee> l1=(List<Employee>) employeeRepository.findAll();
			model.addAttribute("details", l1);
		   
	
			return  "user-dashbaord";

		
		
		
		
	}
	
	
	
//	@GetMapping("/user-dashbaord")
//	public String userDashBoard(Model model, HttpSession session) {
//	    Object attribute = session.getAttribute("id");
//
//	    if (attribute == null) {
//	        return "redirect:/login"; // if user not logged in
//	    }
//
//	    int userId = Integer.parseInt(attribute.toString());
//
//	    List<UserCompose> userComposes = composeRepository.findByParentUkid(userId);
//
//	    userComposes.forEach(compose -> {
//	        int id = compose.getParentUkid();
//	        String designation = employeeRepository.findById(id)
//	            .map(emp -> emp.getDesignation())
//	            .orElse("Unknown");
//
//	        compose.setPosition(designation);
//	    });
//
//	    model.addAttribute("allUserComposeDetails", userComposes);
//
//	    return "user-dashbaord";
//	}

	
	
	
	
	@GetMapping("/user-profiles")
	public String userProfile(HttpSession session,Model model)
	{
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
        Object attribute = session.getAttribute("id");
		

		int userId = Integer.parseInt(attribute.toString());
		
		Employee employee = employeeRepository.findById(userId).get();
		
		
		model.addAttribute("emp",employee);
		
		return "user-profile";
	}
	
	
	
	@GetMapping("/user-setting")
	public String userSetting(HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		return "user-setting";
	}
	
	
	@PostMapping("/update-user-password")
	public String updateUserPassword(@RequestParam ("password")String password,@RequestParam ("newPassword")String newPassword,@RequestParam ("re-enterPassword") String rePassword , HttpSession session,Model model)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		Object attribute = session.getAttribute("id");
		

		int userId = Integer.parseInt(attribute.toString());
		
		Employee employee = employeeRepository.findByIdAndPassword(userId, password);
		
		
		
		
		if(employee!=null &&  newPassword.equals(rePassword))
		{
			
		    employee.setPassword(rePassword);
		    employeeRepository.save(employee);
			
			return "redirect:/login";
		}
		else
		{
			
			model.addAttribute("failed","Please Match Password !!!");

			return "redirect:/user-setting";
		}
		
		
		
		
	}
	
	@GetMapping("/user-compose")
	public String userCompose(HttpSession session)
	{
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
		
		
		return"user-compose";
	}
	
	
	
	@PostMapping("/compose")
	public String addCompose(@RequestParam ("subject")String subject, @RequestParam("text") String text, HttpSession session)
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }
	    
	    
		
        try {
			Object attribute = session.getAttribute("id");
			

			int userId = Integer.parseInt(attribute.toString());
			
			Employee employee = employeeRepository.findById(userId).get();
			
			UserCompose com=new UserCompose();
					
					com.setEmpName(employee.getEmployeeName()); 
			       com.setSubject(subject);
			       com.setText(text);
			       com.setParentUkid(userId);
			       com.setAddedDate(new Date().toString());
			
			       com.setStatus("PENDING");
			       
			       composeRepository.save(com);
		} catch (NumberFormatException e) {
			
			System.out.println(e.getMessage());
		}
		       
		return "redirect:/user-compose";
	}
	
	
//Status.html (Status Tab admin )
	
	@GetMapping("/approved-record")
	public String approve(@RequestParam("id") int id,@RequestParam("type") String type,HttpSession session)
	
	{
		
		Object id4 = session.getAttribute("id");

	    if (id4 == null) {
	        return "redirect:/login?sessionExpired=true";
	    }


		UserCompose userCompose = composeRepository.findById(id).get();
		
		userCompose.setStatus(type);
		composeRepository.save(userCompose);
		return "redirect:/status";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();  // 🔴 This clears the session
	    return "redirect:/login?logout=true";
	}
	
	
}
