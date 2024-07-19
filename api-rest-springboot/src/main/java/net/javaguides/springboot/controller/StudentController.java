package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/students
    @GetMapping("student")
    public Student getStudent(){
        return new Student(
            1, "Faby",
                "Pichardo"
        );
    }

    @GetMapping("studentV2")
    public ResponseEntity<Student> getStudentResponseEntity(){
        Student student= new Student(
                1, "Faby",
                "Pichardo Román"
        );
        //return new ResponseEntity<>(student,HttpStatus.OK);
       // return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "Fabyss")
                .body(student);
    }

    @GetMapping("studentsAll")
    public ResponseEntity<List<Student>> getStudents(){

        List<Student> students = new ArrayList<>();

        students.add(new Student(1,"StudentName1", "StudentLastName1"));
        students.add(new Student(2,"StudentName2", "StudentLastName2"));
        students.add(new Student(3,"StudentName3", "StudentLastName3"));
        students.add(new Student(4,"StudentName4", "StudentLastName4"));
        System.out.println(students);
        return ResponseEntity.ok(students);
    }

    // Spring BOOR REST API with Path varaible
    // {id} URI template variable
    // http://localhost:8080/students/1
    @GetMapping("{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("firstName") String firstName,
                                       @PathVariable("lastName") String lastName){
        //return new Student(studentId, firstName, lastName);
        Student student = new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API with Request Param
    // ñ

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){

        //return new Student(id, firstName, lastName);
        Student student =new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API that handles HTPP POST Request
    // @PostMapping and @RequestMBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent (@RequestBody Student student){

        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring BOOT REST API that handles HTTP PUT REQUEST - Updating existing resourse

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent (@RequestBody Student student,
                                  @PathVariable("id") int studentId){

        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that HTTP DELETE Request  - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){

        System.out.println(studentId);
        return ResponseEntity.ok("¡ Student deleted successfully ! : " + studentId);

    }
}
