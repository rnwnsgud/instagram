package snsProject.photogram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhotogramApplicationTests {

	static class Mouse extends Animal{
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

	static class DoorMan  {
		void 쫓아내(Animal a) {
			System.out.println(a.getName() + " 쫓아내");
		}
	}


	static class DoorManProxy{

		private DoorMan doorMan;

		public DoorManProxy(DoorMan doorMan) {
			this.doorMan = doorMan;
		}

		void 쫓아내(Animal a) {
			System.out.println("안녕? ");
			doorMan.쫓아내(a);
		}
	}

	// 예전에 만들어진 레거시 코드 or 외부에서 만든 라이브러리
//	static class OuterTiger {
//		private String fullName = "호랑이";
//
//		public String getFullName() {
//			return fullName;
//		}
//	}
	static abstract class Animal {
		abstract String getName();
	}

	static class OuterTigerProxy extends Animal {
		private String fullName = "가짜호랑이";

		@Override
		String getName() {
			return fullName;
		}
	}

	static class TigerAdapter extends Animal {

//		private OuterTiger outerTiger;
//
//		public TigerAdapter(OuterTiger outerTiger) {
//			this.outerTiger = outerTiger;
//		}

		@Override
		String getName() {
			return null;
		}
	}


	/**
	 * 어댑터 패턴
	 * : 외부 요소를 기존 시스템에 재사용하고 싶지만 아직 만들어지지 않은 경우
	 * : 외부 요소를 기존 시스템에 재사용하고 싶지만 호환되지 않는 경우(실습)
	 * */
	@Test
	void design() {
		Mouse mouse = new Mouse();
		Cat cat = new Cat();
		Animal fakeTiger = new OuterTigerProxy();
//		Animal realTiger = new TigerAdapter();
		DoorMan doorMan = new DoorMan();
		DoorManProxy doorManProxy = new DoorManProxy(doorMan);
		doorManProxy.쫓아내(mouse);
		doorManProxy.쫓아내(cat);
		doorManProxy.쫓아내(fakeTiger);
	}



}
