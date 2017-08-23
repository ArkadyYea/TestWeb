package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public class Test {
	
	@TestAnnotation(quantity=123, value="abc")
	public Test() {
		Annotation a1 = new TestAnnotationLiteral() {
			private static final long serialVersionUID = 1L;
			public String value() {	return "Abc"; }
			public int quantity() { return 1; }
		};
		
		System.out.println("annotationType() -> "+a1.annotationType());
		System.out.println("a1.getClass() -> "+a1.getClass());
		System.out.println("TestAnnotation.class -> "+TestAnnotation.class);
		
		checkAnnotation();
	}
	
	public static void main(String[] args) {
		new Test();
	}
	
	private void checkAnnotation() {
		System.out.println("--------- Processing Annotation -----------");
		Constructor<?>[] cons = Test.class.getConstructors();
		for(Constructor<?> c : cons) {
			if(c.isAnnotationPresent(TestAnnotation.class)) {
				//TestAnnotation ta = c.getAnnotation(TestAnnotation.class);
				TestAnnotation[] annotationsByType = c.getAnnotationsByType(TestAnnotation.class);
				TestAnnotation ta = annotationsByType[0];
				System.out.println("Annotations available for the constructor: "+annotationsByType.length);
				System.out.println("value() of the annotation: "+ta.value());
				System.out.println("quantity() of the annotation: "+ta.quantity());
			}
		}
	}
	
}
