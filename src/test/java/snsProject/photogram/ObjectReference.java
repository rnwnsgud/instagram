package snsProject.photogram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObjectReference {

    class Student {
        public int age;

        public Student(int age) {
            this.age = age;
        }
    }

    Student st = new Student(13);
    Student newSt = st;


    @Test
    void test() {
        newSt.age = 23;
        System.out.println("st = " + st.age);
    }
}
