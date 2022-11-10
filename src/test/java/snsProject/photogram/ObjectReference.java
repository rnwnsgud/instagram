package snsProject.photogram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

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

    @Test
    void intVsInteger_num() {
        int a = 1;
        Integer b = 1;

        assertThat(a).isEqualTo(1);
        assertThat(b).isEqualTo(1);

    }

    @Test
    void intVsInteger_param() {
        int a = 1;
        Integer b = 1;

        assertThat(a).isEqualTo(b);

    }


}
