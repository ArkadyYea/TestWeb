package event.withqualifier;

import javax.enterprise.util.AnnotationLiteral;

@SuppressWarnings("all")
abstract class TestAnnotationLiteral extends AnnotationLiteral<Test> implements Test {
	private static final long serialVersionUID = 1L;
}