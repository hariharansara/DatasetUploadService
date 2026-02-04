package database.demo.service;

import database.demo.entity.Student;
import database.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public void insertCSV(MultipartFile file) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(file.getInputStream()));

        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            Student s = new Student();
            s.setStudent_id(Integer.parseInt(data[0]));
            s.setFirst_name(data[1]);
            s.setLast_name(data[2]);
            s.setGender(data[3]);
            s.setDate_of_birth(LocalDate.parse(data[4]));
            s.setEmail(data[5]);
            s.setPhone(data[6]);
            s.setDepartment(data[7]);
            s.setYear_of_study(Integer.parseInt(data[8]));
            s.setGpa(Double.parseDouble(data[9]));

            repo.save(s);
        }
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
