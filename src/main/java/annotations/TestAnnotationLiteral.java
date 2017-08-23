package annotations;

import javax.enterprise.util.AnnotationLiteral;

//MUST include 'implements TestAnnotation' otherwise:
//Exception in thread "main" java.lang.RuntimeException: class annotations.Test$1 does not implement the annotation type with members annotations.TestAnnotation
@SuppressWarnings("all")
abstract class TestAnnotationLiteral extends AnnotationLiteral<TestAnnotation> implements TestAnnotation {
    private static final long serialVersionUID = 1L;
}