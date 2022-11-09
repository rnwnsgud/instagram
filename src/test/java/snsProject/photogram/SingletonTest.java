package snsProject.photogram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SingletonTest {

    static class Mouse extends Animal {
        private String name = "쥐";

        @Override
        public String getName() {
            return name;
        }
    }

    static class Cat extends Animal {
        private String name = "고양이";

        @Override
        String getName() {
            return name;
        }
    }

    static abstract class Animal {
        abstract String getName();
    }

    static class DoorMan  {

        // 자바에서 static은 main메서드를 호출하기전에 JVM이 읽어서 메모리에 올라오는 친구
        private static DoorMan doorMan = new DoorMan();

        public static DoorMan getDoorMan() { // heap이 관리하는 메서드
           return doorMan;
        }

        private DoorMan() {
        }

        void 쫓아내(Animal a) {
            System.out.println(a.getName() + " 쫓아내");
        }
    }

    @Test
    void singleton() {
        DoorMan doorMan = DoorMan.getDoorMan();
        DoorMan doorMan1 = DoorMan.getDoorMan();
        doorMan.쫓아내(new Cat());
    }





}
