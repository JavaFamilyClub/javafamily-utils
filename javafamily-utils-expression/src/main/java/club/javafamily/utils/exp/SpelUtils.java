package club.javafamily.utils.exp;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public final class SpelUtils {

    public static final SpelExpressionParser spelParser = new SpelExpressionParser();

    public static <T> T calcValue(String expression,
                                  Class<T> valueType,
                                  Object targetObj)
    {
        return calcValue(expression, valueType, targetObj, null);
    }

    /**
     * calc spel value
     * @param expression expression
     * @param valueType type of return value
     * @param targetObj this object
     * @param fieldValue this object's field value
     * @param <T> return value type
     * @return value
     */
    public static <T> T calcValue(String expression,
                                  Class<T> valueType,
                                  Object targetObj,
                                  Object fieldValue)
    {
        StandardEvaluationContext context = new StandardEvaluationContext(fieldValue);
        context.setVariable("this", targetObj);
        context.setVariable("data", fieldValue);

        Expression expr = spelParser.parseExpression(expression);

        return expr.getValue(context, valueType);
    }

}
