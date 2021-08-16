package com.hdanske.protobuf;

import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.hdanske.protobuf.ProtobufTraining.*;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


//Разобраться, что с этим не так
//    @Bean
//    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
//        return new RestTemplate(Arrays.asList(hmc));
//    }
//---------------------------------//
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter(){
        return  new ProtobufHttpMessageConverter();
    }

    @Bean
    public CourseRepository createTestCourses(){
    Map<Integer, Course> courses = new HashMap<>();
    Course course1 = Course.newBuilder()
            .setId(1)
            .setCourseName("REST with Spring")
            .addAllStudent(createTestStudents())
            .build();
    Course course2 = Course.newBuilder()
            .setId(2)
            .setCourseName("SHUT DA FUCK UP!!!")
            .addAllStudent(new ArrayList<Student>()).build();

    courses.put(course1.getId(), course1);
    courses.put(course2.getId(), course1);

    return new CourseRepository(courses);
    }

    private List<Student> createTestStudents() {
        Student.PhoneNumber phone1 = createPhone("123456", Student.PhoneType.MOBILE);

        Student student1 = createStudent(1, "John", "Doe", "1@email.com", Arrays.asList(phone1));

        Student.PhoneNumber phone2 = createPhone("234567", Student.PhoneType.LANDLINE);
        Student student2 = createStudent(2, "Richard", "Roe", "2@email.com", Arrays.asList(phone2));

        Student.PhoneNumber phone3_1 = createPhone("345678", Student.PhoneType.MOBILE);
        Student.PhoneNumber phone3_2 = createPhone("456789", Student.PhoneType.LANDLINE);
        Student student3 = createStudent(3, "Jane", "Doe", "3@email.com", Arrays.asList(phone3_1, phone3_2));

        return  Arrays.asList(student1, student2, student3);
    }

    private Student createStudent(int id, String firstName, String lastName, String email, List<Student.PhoneNumber> phones) {
        return Student.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .addAllPhone(phones)
                .build();
    }

    private Student.PhoneNumber createPhone(String number, Student.PhoneType type) {
        return Student.PhoneNumber
                .newBuilder()
                .setNumber(number)
                .setType(type)
                .build();
    }
}
