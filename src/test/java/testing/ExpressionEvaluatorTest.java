package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testing.Expression.*;
import testing.ExpressionEvaluatorTest.TestOutcome.Failure;
import testing.ExpressionEvaluatorTest.TestOutcome.Success;

import java.util.function.Supplier;
import java.util.stream.Stream;


class ExpressionEvaluatorTest {
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    private static Stream<TestCase> provideExpressionsForEvaluate() {
        return Stream.of(
                new TestCase("Valid constant", new Constant(5), new Success(5)),
                new TestCase("Valid addition", new Addition(new Constant(2), new Constant(3)), new Success(5)),
                new TestCase("Division by zero", new Division(new Constant(6), new Constant(0)), new Failure(ArithmeticException::new)),
                new TestCase("Valid subtraction", new Subtraction(new Constant(10), new Constant(3)), new Success(7)),
                new TestCase("Valid multiplication", new Multiplication(new Constant(3), new Constant(4)), new Success(12)),
                new TestCase("Negative result", new Subtraction(new Constant(5), new Constant(8)), new Success(-3)),
                new TestCase("Combined operations",
                        new Addition(new Constant(3), new Multiplication(new Constant(2), new Constant(5))),
                        new Success(13)),

                new TestCase("Nested division by zero",
                        new Division(new Constant(12), new Division(new Constant(6), new Constant(0))),
                        new Failure(ArithmeticException::new))
        );
    }

    @ParameterizedTest
    @MethodSource("provideExpressionsForEvaluate")
    void testEvaluateExpression(TestCase testCase) {
        switch (testCase.expectedOutcome()) {
            case Success(int expectedResult) ->
                    Assertions.assertEquals(expectedResult, evaluator.evaluate(testCase.expression()),
                            "Incorrect evaluation for: " + testCase.description());
            case Failure(Supplier<? extends RuntimeException> exceptionSupplier) ->
                    Assertions.assertThrows(exceptionSupplier.get().getClass(),
                            () -> evaluator.evaluate(testCase.expression()),
                            "Incorrect error handling for: " + testCase.description());
        }
    }
    record TestCase(String description, Expression expression, TestOutcome expectedOutcome) {}

    sealed interface TestOutcome {

        record Success(int value) implements TestOutcome {}

        record Failure(Supplier<? extends RuntimeException> exceptionSupplier) implements TestOutcome {}
    }
}