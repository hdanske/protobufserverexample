package com.hdanske.protobuf;

import java.util.Map;
import com.hdanske.protobuf.ProtobufTraining.Course;


public class CourseRepository {
    private final Map<Integer, Course> cources;

    public CourseRepository (Map<Integer, Course> cources) {
        this.cources = cources;
    }

    public Course getCourse(int id){
        return cources.get(id);
    }

}
