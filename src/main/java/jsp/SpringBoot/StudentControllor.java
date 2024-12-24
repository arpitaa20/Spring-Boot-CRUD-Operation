package jsp.SpringBoot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jsp")
public class StudentControllor {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//save the data
//	@PostMapping("/student")
//	public String saveStudent(@RequestBody Student s) {
//		studentRepository.save(s);
//		return "Student record saved";
		
//	}
//	resopnse structure
	@PostMapping("/student")
	public ResponseStructure<Student> saveStudent(@RequestBody Student s){
		studentRepository.save(s);
		ResponseStructure<Student> str= new ResponseStructure<Student>();
		str.setStatusCode(HttpStatus.CREATED.value());
		str.setMessage("Success");
		str.setData(s);
		return str;
	}
	
//	get or fetch
//	@GetMapping("/student")
//	public List<Student> getAllStudents(){
//		List<Student> s = studentRepository.findAll();
//		return s;
//	}
	
	@GetMapping("/student")
	public ResponseStructure<List<Student>> getAllStudents(){
		List<Student> s = studentRepository.findAll();
		ResponseStructure<List<Student>> str= new ResponseStructure<List<Student>>();
		str.setStatusCode(HttpStatus.OK.value());
		str.setMessage("Success");
		str.setData(s);
		return str;
	}
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {
		Optional<Student> opt = studentRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
			
		}else {
			return null;
		}
 	}
//	update
	@PutMapping("/student")
	public String updateStudent(@RequestBody Student s) {
		studentRepository.save(s);
		return "Student record updated";
	}
	
//	delete
	@DeleteMapping("/student/{id}")
	public String deleteStudentById(@PathVariable int id) {
		Optional<Student> opt = studentRepository.findById(id);
		if(opt.isPresent()) {
			studentRepository.delete(opt.get());
			return "Student record is delete";
		}else {
			return "No record delete";
		}
		
	}
}
