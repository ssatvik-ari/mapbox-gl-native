package com.mapbox.mapboxsdk.style.expressions;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mapbox.mapboxsdk.style.layers.PropertyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The value for any layout property, paint property, or filter may be specified as an expression.
 * An expression defines a formula for computing the value of the property using the operators described below.
 * The set of expression operators provided by Mapbox GL includes:
 * <p>
 * <ul>
 * <li>Element</li>
 * <li>Mathematical operators for performing arithmetic and other operations on numeric values</li>
 * <li>Logical operators for manipulating boolean values and making conditional decisions</li>
 * <li>String operators for manipulating strings</li>
 * <li>Data operators, providing access to the properties of source features</li>
 * <li>Camera operators, providing access to the parameters defining the current map view</li>
 * </ul>
 * </p>
 * <p>
 * Expressions are represented as JSON arrays.
 * The first element of an expression array is a string naming the expression operator,
 * e.g. "*"or "case". Subsequent elements (if any) are the arguments to the expression.
 * Each argument is either a literal value (a string, number, boolean, or null), or another expression array.
 * </p>
 * <p>
 * Data expression: a data expression is any expression that access feature data -- that is,
 * any expression that uses one of the data operators:get,has,id,geometry-type, or properties.
 * Data expressions allow a feature's properties to determine its appearance.
 * They can be used to differentiate features within the same layer and to create data visualizations.
 * </p>
 * <p>
 * Camera expression: a camera expression is any expression that uses the zoom operator.
 * Such expressions allow the the appearance of a layer to change with the map's zoom level.
 * Camera expressions can be used to create the appearance of depth and to control data density.
 * </p>
 * <p>
 * Composition: a single expression may use a mix of data operators, camera operators, and other operators.
 * Such composite expressions allows a layer's appearance to be determined by
 * a combination of the zoom level and individual feature properties.
 * </p>
 */
public class Expression {

  private final String operator;
  private final Object[] arguments;

  /**
   * Creates an expression from its operator and varargs objects.
   *
   * @param operator the expression operator
   * @param arguments varargs to provide expressions and values
   */
  public Expression(@NonNull String operator, @Nullable Object... arguments) {
    this.operator = operator;
    this.arguments = arguments;
  }

  /**
   * Converts the expression to Object array representation.
   *
   * @return the converted expression
   */
  @NonNull
  public Object[] toArray() {
    List<Object> array = new ArrayList<>();
    array.add(operator);
    if (arguments != null) {
      for (Object o : arguments) {
        if (o instanceof Expression) {
          array.add(((Expression) o).toArray());
        } else {
          array.add(o);
        }
      }
    }
    return array.toArray();
  }

  //
  // Color
  //

  /**
   * Creates a color value from red, green, and blue components, which must range between 0 and 255,
   * and an alpha component of 1.
   * <p>
   * If any component is out of range, the expression is an error.
   * </p>
   *
   * @param expressions a color expression
   * @return expression
   */
  public static Expression rgb(Object... expressions) {
    return new Expression("rgb", expressions);
  }

  /**
   * Creates a color value from red, green, and blue components, which must range between 0 and 255,
   * and an alpha component of 1.
   * <p>
   * If any component is out of range, the expression is an error.
   * </p>
   *
   * @param red   red color value
   * @param green green color value
   * @param blue  blue color value
   * @return expression
   */
  public static Expression rgb(Number red, Number green, Number blue) {
    return new Expression("rgb", red, green, blue);
  }

  /**
   * Creates a color value from red, green, blue components, which must range between 0 and 255,
   * and an alpha component which must range between 0 and 1.
   * <p>
   * If any component is out of range, the expression is an error.
   * </p>
   *
   * @param expression a color expression
   * @return expression
   */
  public static Expression rgba(Expression expression) {
    return new Expression("rgba", expression);
  }

  /**
   * Creates a color value from red, green, blue components, which must range between 0 and 255,
   * and an alpha component which must range between 0 and 1.
   * <p>
   * If any component is out of range, the expression is an error.
   * </p>
   *
   * @param red   red color value
   * @param green green color value
   * @param blue  blue color value
   * @param alpha alpha color value
   * @return expression
   */
  public static Expression rgba(Number red, Number green, Number blue, Number alpha) {
    return new Expression("rgba", red, green, blue, alpha);
  }

  /**
   * Returns a four-element array containing the input color's red, green, blue, and alpha components, in that order.
   *
   * @param expression an expression to convert to a color
   * @return expression
   */
  public static Expression toRgba(Expression expression) {
    return new Expression("to-rgba", expression);
  }

  /**
   * Returns a four-element array containing the input color's red, green, blue, and alpha components, in that order.
   *
   * @param color a color string value
   * @return expression
   */
  public static Expression toRgba(String color) {
    return new Expression("to-rgba", color);
  }

  /**
   * Returns a four-element array containing the input color's red, green, blue, and alpha components, in that order.
   *
   * @param color a color int value
   * @return expression
   */
  public static Expression toRgba(@ColorInt int color) {
    return new Expression("to-rgba", color(color));
  }

  //
  // Decision
  //

  /**
   * Returns `true` if the input values are equal, `false` otherwise.
   * The inputs must be numbers, strings, or booleans, and both of the same type.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression eq(Object... expressions) {
    return new Expression("==", expressions);
  }

  /**
   * Returns `true` if the input values are equal, `false` otherwise.
   *
   * @param compareOne the first number
   * @param compareTwo the second number
   * @return expression
   */
  public static Expression eq(Number compareOne, Number compareTwo) {
    return new Expression("==", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the input values are equal, `false` otherwise.
   *
   * @param compareOne the first string
   * @param compareTwo the second string
   * @return expression
   */
  public static Expression eq(String compareOne, String compareTwo) {
    return new Expression("==", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the input values are equal, `false` otherwise.
   *
   * @param compareOne the first boolean
   * @param compareTwo the second boolean
   * @return expression
   */
  public static Expression eq(Boolean compareOne, Boolean compareTwo) {
    return new Expression("==", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the input values are not equal, `false` otherwise.
   * The inputs must be numbers, strings, or booleans, and both of the same type.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression neq(Object... expressions) {
    return new Expression("!=", expressions);
  }

  /**
   * Returns `true` if the input values are not equal, `false` otherwise.
   *
   * @param compareOne the first number
   * @param compareTwo the second number
   * @return expression
   */
  public static Expression neq(Number compareOne, Number compareTwo) {
    return new Expression("!=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the input values are not equal, `false` otherwise.
   *
   * @param compareOne the first string
   * @param compareTwo the second string
   * @return expression
   */
  public static Expression neq(String compareOne, String compareTwo) {
    return new Expression("!=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the input values are not equal, `false` otherwise.
   *
   * @param compareOne the first boolean
   * @param compareTwo the second boolean
   * @return expression
   */
  public static Expression neq(Boolean compareOne, Boolean compareTwo) {
    return new Expression("!=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is strictly greater than the second, `false` otherwise.
   * The inputs must be numbers or strings, and both of the same type.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression gt(Object... expressions) {
    return new Expression(">", expressions);
  }

  /**
   * Returns `true` if the first input is strictly greater than the second, `false` otherwise.
   *
   * @param compareOne the first number
   * @param compareTwo the second number
   * @return expression
   */
  public static Expression gt(Number compareOne, Number compareTwo) {
    return new Expression(">", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is strictly greater than the second, `false` otherwise.
   *
   * @param compareOne the first string
   * @param compareTwo the second string
   * @return expression
   */
  public static Expression gt(String compareOne, String compareTwo) {
    return new Expression(">", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is strictly less than the second, `false` otherwise.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression lt(Object... expressions) {
    return new Expression("<", expressions);
  }

  /**
   * Returns `true` if the first input is strictly less than the second, `false` otherwise.
   *
   * @param compareOne the first number
   * @param compareTwo the second number
   * @return expression
   */
  public static Expression lt(Number compareOne, Number compareTwo) {
    return new Expression("<", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is strictly less than the second, `false` otherwise.
   *
   * @param compareOne the first string
   * @param compareTwo the second string
   * @return expression
   */
  public static Expression lt(String compareOne, String compareTwo) {
    return new Expression("<", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is greater than or equal to the second, `false` otherwise.
   * The inputs must be numbers or strings, and both of the same type.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression gte(Object... expressions) {
    return new Expression(">=", expressions);
  }

  /**
   * Returns `true` if the first input is greater than or equal to the second, `false` otherwise..
   *
   * @param compareOne the first number
   * @param compareTwo the second number
   * @return expression
   */
  public static Expression gte(Number compareOne, Number compareTwo) {
    return new Expression(">=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is greater than or equal to the second, `false` otherwise.
   *
   * @param compareOne the first string
   * @param compareTwo the second string
   * @return expression
   */
  public static Expression gte(String compareOne, String compareTwo) {
    return new Expression(">=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is less than or equal to the second, `false` otherwise.
   * The inputs must be numbers or strings, and both of the same type.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression lte(Object... expressions) {
    return new Expression("<=", expressions);
  }

  /**
   * Returns `true` if the first input is less than or equal to the second, `false` otherwise.
   *
   * @param compareOne the first number
   * @param compareTwo the second number
   * @return expression
   */
  public static Expression lte(Number compareOne, Number compareTwo) {
    return new Expression("<=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if the first input is less than or equal to the second, `false` otherwise.
   *
   * @param compareOne the first string
   * @param compareTwo the second string
   * @return expression
   */
  public static Expression lte(String compareOne, String compareTwo) {
    return new Expression("<=", compareOne, compareTwo);
  }

  /**
   * Returns `true` if all the inputs are `true`, `false` otherwise.
   * <p>
   * The inputs are evaluated in order, and evaluation is short-circuiting:
   * once an input expression evaluates to `false`,
   * the result is `false` and no further input expressions are evaluated.
   * </p>
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression all(Object... expressions) {
    return new Expression("all", expressions);
  }

  /**
   * Returns `true` if any of the inputs are `true`, `false` otherwise.
   * <p>
   * The inputs are evaluated in order, and evaluation is short-circuiting:
   * once an input expression evaluates to `true`,
   * the result is `true` and no further input expressions are evaluated.
   * </p>
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression any(Object... expressions) {
    return new Expression("any", expressions);
  }

  /**
   * Logical negation. Returns `true` if the input is `false`, and `false` if the input is `true`.
   *
   * @param expression varargs to provide expressions and values
   * @return expression
   */
  public static Expression not(Expression expression) {
    return new Expression("!", expression);
  }

  /**
   * Logical negation. Returns `true` if the input is `false`, and `false` if the input is `true`.
   *
   * @param bool the boolean to logical negate
   * @return expression
   */
  public static Expression not(Boolean bool) {
    return new Expression("!", bool);
  }

  /**
   * Selects the first output whose corresponding test condition evaluates to true.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression switchCase(Object... expressions) {
    return new Expression("case", expressions);
  }

  /**
   * Selects the output whose label value matches the input value, or the fallback value if no match is found.
   * The `input` can be any string or number expression.
   * Each label can either be a single literal value or an array of values.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression match(Object... expressions) {
    return new Expression("match", expressions);
  }

  /**
   * Evaluates each expression in turn until the first non-null value is obtained, and returns that value.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression coalesce(Object... expressions) {
    return new Expression("coalesce", expressions);
  }

  //
  // FeatureData
  //

  /**
   * Gets the feature properties object.
   * <p>
   * Note that in some cases, it may be more efficient to use {@link #get(String)} instead.
   * </p>
   *
   * @return expression
   */
  public static Expression properties() {
    return new Expression("properties");
  }

  /**
   * Gets the feature's geometry type: Point, MultiPoint, LineString, MultiLineString, Polygon, MultiPolygon.
   *
   * @return expression
   */
  public static Expression geometryType() {
    return new Expression("geometry-type");
  }

  /**
   * Gets the feature's id, if it has one.
   *
   * @return expression
   */
  public static Expression id() {
    return new Expression("id");
  }

  //
  // Heatmap
  //

  /**
   * Gets the kernel density estimation of a pixel in a heatmap layer,
   * which is a relative measure of how many data points are crowded around a particular pixel.
   * Can only be used in the `heatmap-color` property.
   *
   * @return expression
   */
  public static Expression heatmapDensity() {
    return new Expression("heatmap-density");
  }

  //
  // Lookup
  //


  /**
   * Retrieves an item from an array.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression at(Object... expressions) {
    return new Expression("at", expressions);
  }

  /**
   * Retrieves an item from an array.
   *
   * @param number     the index
   * @param expression the array expression
   * @return expression
   */
  public static Expression at(Number number, Expression expression) {
    return new Expression("at", number, expression);
  }

  /**
   * Retrieves a property value from the current feature's properties,
   * or from another object if a second argument is provided.
   * Returns null if the requested property is missing.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression get(Object... expressions) {
    return new Expression("get", expressions);
  }

  /**
   * Retrieves a property value from the current feature's properties.
   * Returns null if the requested property is missing.
   *
   * @param key the property value key
   * @return expression
   */
  public static Expression get(String key) {
    return new Expression("get", key);
  }

  /**
   * Retrieves a property value from another object.
   * Returns null if the requested property is missing.
   *
   * @param key    a property value key
   * @param object an expression object
   * @return expression
   */
  public static Expression get(String key, Expression object) {
    return new Expression("get", key, object);
  }

  /**
   * Tests for the presence of an property value in the current feature's properties,
   * or from another object if a second argument is provided.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression has(Object... expressions) {
    return new Expression("has", expressions);
  }

  /**
   * Tests for the presence of an property value in the current feature's properties.
   *
   * @param key the property value key
   * @return expression
   */
  public static Expression has(String key) {
    return new Expression("has", key);
  }

  /**
   * Tests for the presence of an property value from another object.
   *
   * @param key    the property value key
   * @param object an expression object
   * @return expression
   */
  public static Expression has(String key, Expression object) {
    return new Expression("has", key, object);
  }

  /**
   * Gets the length of an array or string.
   *
   * @param expression an expression object or expression string
   * @return expression
   */
  public static Expression length(Expression expression) {
    return new Expression("length", expression);
  }

  /**
   * Gets the length of an array or string.
   *
   * @param string a string to get length from
   * @return expression
   */
  public static Expression length(String string) {
    return new Expression("length", string);
  }

  //
  // Math
  //

  /**
   * Returns mathematical constant ln(2).
   *
   * @return expression
   */
  public static Expression ln2() {
    return new Expression("ln2");
  }

  /**
   * Returns the mathematical constant pi.
   *
   * @return expression
   */
  public static Expression pi() {
    return new Expression("pi");
  }

  /**
   * Returns the mathematical constant e.
   *
   * @return expression
   */
  public static Expression e() {
    return new Expression("e");
  }

  /**
   * Returns the sum of the inputs.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression sum(Object... expressions) {
    return new Expression("+", expressions);
  }

  /**
   * Returns the sum of the inputs.
   *
   * @param numbers the numbers to calculate the sum for
   * @return expression
   */
  public static Expression sum(Number... numbers) {
    return new Expression("+", numbers);
  }

  /**
   * Returns the product of the inputs.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression product(Object... expressions) {
    return new Expression("*", expressions);
  }

  /**
   * Returns the product of the inputs.
   *
   * @param numbers the numbers to calculate the product for
   * @return expression
   */
  public static Expression product(Number... numbers) {
    return new Expression("*", numbers);
  }

  /**
   * Returns the result of subtracting a number from 0.
   *
   * @param number the number subtract from 0
   * @return expression
   */
  public static Expression subtract(Number number) {
    return new Expression("-", number);
  }

  /**
   * Returns the result of subtracting the second input from the first
   *
   * @param first  the first number
   * @param second the second number
   * @return expression
   */
  public static Expression subtract(Number first, Number second) {
    return new Expression("-", first, second);
  }

  /**
   * For two inputs, returns the result of subtracting the second input from the first.
   * For a single input, returns the result of subtracting it from 0.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression subtract(Object... expressions) {
    return new Expression("-", expressions);
  }

  /**
   * Returns the result of floating point division of the first input by the second.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression division(Object... expressions) {
    return new Expression("/", expressions);
  }

  /**
   * Returns the result of floating point division of the first input by the second.
   *
   * @param first  the first number
   * @param second the second number
   * @return expression
   */
  public static Expression division(Number first, Number second) {
    return new Expression("/", first, second);
  }

  /**
   * Returns the remainder after integer division of the first input by the second.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression mod(Object... expressions) {
    return new Expression("%", expressions);
  }

  /**
   * Returns the remainder after integer division of the first input by the second.
   *
   * @param first  the first number
   * @param second the second number
   * @return expression
   */
  public static Expression mod(Number first, Number second) {
    return new Expression("%", first, second);
  }

  /**
   * Returns the result of raising the first input to the power specified by the second.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression pow(Object... expressions) {
    return new Expression("^", expressions);
  }

  /**
   * Returns the result of raising the first input to the power specified by the second.
   *
   * @param first  the first number
   * @param second the second number
   * @return expression
   */
  public static Expression pow(Number first, Number second) {
    return new Expression("^", first, second);
  }

  /**
   * Returns the square root of the input
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression sqrt(Object... expressions) {
    return new Expression("sqrt", expressions);
  }

  /**
   * Returns the square root of the input
   *
   * @param number the number to take the square root from
   * @return expression
   */
  public static Expression sqrt(Number number) {
    return new Expression("sqrt", number);
  }

  /**
   * Returns the base-ten logarithm of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression log10(Object... expressions) {
    return new Expression("log10", expressions);
  }

  /**
   * Returns the base-ten logarithm of the input.
   *
   * @param number the number to take base-ten logarithm from
   * @return expression
   */
  public static Expression log10(Number number) {
    return new Expression("log10", number);
  }

  /**
   * Returns the natural logarithm of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression ln(Object... expressions) {
    return new Expression("ln", expressions);
  }

  /**
   * Returns the natural logarithm of the input.
   *
   * @param number the number to take natural logarithm from
   * @return expression
   */
  public static Expression ln(Number number) {
    return new Expression("ln", number);
  }

  /**
   * Returns the base-two logarithm of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression log2(Object... expressions) {
    return new Expression("log2", expressions);
  }

  /**
   * Returns the base-two logarithm of the input.
   *
   * @param number the number to take base-two logarithm from
   * @return expression
   */
  public static Expression log2(Number number) {
    return new Expression("log2", number);
  }

  /**
   * Returns the sine of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression sin(Object... expressions) {
    return new Expression("sin", expressions);
  }

  /**
   * Returns the sine of the input.
   *
   * @param number the number to calculate the sine for
   * @return expression
   */
  public static Expression sin(Number number) {
    return new Expression("sin", number);
  }

  /**
   * Returns the cosine of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression cos(Object... expressions) {
    return new Expression("cos", expressions);
  }

  /**
   * Returns the cosine of the input.
   *
   * @param number the number to calculate the cosine for
   * @return expression
   */
  public static Expression cos(Number number) {
    return new Expression("cos", number);
  }

  /**
   * Returns the tangent of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression tan(Object... expressions) {
    return new Expression("tan", expressions);
  }

  /**
   * Returns the tangent of the input.
   *
   * @param number the number to calculate the tangent for
   * @return expression
   */
  public static Expression tan(Number number) {
    return new Expression("tan", number);
  }

  /**
   * Returns the arcsine of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression asin(Object... expressions) {
    return new Expression("asin", expressions);
  }

  /**
   * Returns the arcsine of the input.
   *
   * @param number the number to calculate the arcsine for
   * @return expression
   */
  public static Expression asin(Number number) {
    return new Expression("asin", number);
  }

  /**
   * Returns the arccosine of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression acos(Object... expressions) {
    return new Expression("acos", expressions);
  }

  /**
   * Returns the arccosine of the input.
   *
   * @param number the number to calculate the arccosine for
   * @return expression
   */
  public static Expression acos(Number number) {
    return new Expression("acos", number);
  }

  /**
   * Returns the arctangent of the input.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression atan(Object... expressions) {
    return new Expression("atan", expressions);
  }

  /**
   * Returns the arctangent of the input.
   *
   * @param number the number to calculate the arctangent for
   * @return expression
   */
  public static Expression atan(Number number) {
    return new Expression("atan", number);
  }

  /**
   * Returns the minimum value of the inputs.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression min(Object... expressions) {
    return new Expression("min", expressions);
  }

  /**
   * Returns the minimum value of the inputs.
   *
   * @param numbers varargs of numbers to get the minimum from
   * @return expression
   */
  public static Expression min(Number... numbers) {
    return new Expression("min", numbers);
  }

  /**
   * Returns the maximum value of the inputs.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression max(Object... expressions) {
    return new Expression("max", expressions);
  }

  /**
   * Returns the maximum value of the inputs.
   *
   * @param numbers varargs of numbers to get the maximum from
   * @return expression
   */
  public static Expression max(Number... numbers) {
    return new Expression("max", numbers);
  }

  //
  // String
  //

  /**
   * Returns the input string converted to uppercase.
   * <p>
   * Follows the Unicode Default Case Conversion algorithm and
   * the locale-insensitive case mappings in the Unicode Character Database.
   * </p>
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression upcase(Object... expressions) {
    return new Expression("upcase", expressions);
  }

  /**
   * Returns the input string converted to uppercase.
   * <p>
   * Follows the Unicode Default Case Conversion algorithm
   * and the locale-insensitive case mappings in the Unicode Character Database.
   * </p>
   *
   * @param string the string to upcase
   * @return expression
   */
  public static Expression upcase(String string) {
    return new Expression("upcase", string);
  }

  /**
   * Returns the input string converted to lowercase.
   * <p>
   * Follows the Unicode Default Case Conversion algorithm
   * and the locale-insensitive case mappings in the Unicode Character Database.
   * </p>
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression downcase(Object... expressions) {
    return new Expression("downcase", expressions);
  }

  /**
   * Returns the input string converted to lowercase.
   * <p>
   * Follows the Unicode Default Case Conversion algorithm
   * and the locale-insensitive case mappings in the Unicode Character Database.
   * </p>
   *
   * @param string the string to downcase
   * @return expression
   */
  public static Expression downcase(String string) {
    return new Expression("downcase", string);
  }

  /**
   * Returns a string consisting of the concatenation of the inputs.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression concat(Object... expressions) {
    return new Expression("concat", expressions);
  }

  /**
   * Returns a string consisting of the concatenation of the inputs.
   *
   * @param strings varargs of strings to concatenate
   * @return expression
   */
  public static Expression concat(String... strings) {
    return new Expression("concat", strings);
  }

  //
  // Types
  //

  /**
   * Provides a literal array or object value.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression literal(Object... expressions) {
    return new Expression("literal", expressions);
  }

  /**
   * Asserts that the input is an array (optionally with a specific item type and length).
   * If, when the input expression is evaluated, it is not of the asserted type,
   * then this assertion will cause the whole expression to be aborted.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression array(Object... expressions) {
    return new Expression("array", expressions);
  }

  /**
   * Returns a string describing the type of the given value.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression typeOf(Object... expressions) {
    return new Expression("typeof", expressions);
  }

  /**
   * Asserts that the input value is a string.
   * If multiple values are provided, each one is evaluated in order until a string value is obtained.
   * If none of the inputs are strings, the expression is an error.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression string(Object... expressions) {
    return new Expression("string", expressions);
  }

  /**
   * Asserts that the input value is a number.
   * If multiple values are provided, each one is evaluated in order until a number value is obtained.
   * If none of the inputs are numbers, the expression is an error.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression number(Object... expressions) {
    return new Expression("number", expressions);
  }

  /**
   * Asserts that the input value is a boolean.
   * If multiple values are provided, each one is evaluated in order until a boolean value is obtained.
   * If none of the inputs are booleans, the expression is an error.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression bool(Object... expressions) {
    return new Expression("boolean", expressions);
  }

  /**
   * Asserts that the input value is an object. If it is not, the expression is an error
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression object(Object... expressions) {
    return new Expression("object", expressions);
  }

  /**
   * Converts the input value to a string.
   * If the input is null, the result is null.
   * If the input is a boolean, the result is true or false.
   * If the input is a number, it is converted to a string by NumberToString in the ECMAScript Language Specification.
   * If the input is a color, it is converted to a string of the form "rgba(r,g,b,a)",
   * where `r`, `g`, and `b` are numerals ranging from 0 to 255, and `a` ranges from 0 to 1.
   * Otherwise, the input is converted to a string in the format specified by the JSON.stringify in the ECMAScript
   * Language Specification.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression toString(Object... expressions) {
    return new Expression("to-string", expressions);
  }

  /**
   * Converts the input value to a number, if possible.
   * If the input is null or false, the result is 0.
   * If the input is true, the result is 1.
   * If the input is a string, it is converted to a number as specified by the ECMAScript Language Specification.
   * If multiple values are provided, each one is evaluated in order until the first successful conversion is obtained.
   * If none of the inputs can be converted, the expression is an error.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression toNumber(Object... expressions) {
    return new Expression("to-number", expressions);
  }

  /**
   * "Converts the input value to a boolean. The result is `false` when then input is an empty string, 0, false,
   * null, or NaN; otherwise it is true.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression toBool(Object... expressions) {
    return new Expression("to-boolean", expressions);
  }

  /**
   * Converts the input value to a color. If multiple values are provided,
   * each one is evaluated in order until the first successful conversion is obtained.
   * If none of the inputs can be converted, the expression is an error.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression toColor(Object... expressions) {
    return new Expression("to-color", expressions);
  }

  //
  // Variable binding
  //

  /**
   * Binds expressions to named variables,
   * which can then be referenced in the result expression using {@link #var(String)} or {@link #var(Expression)}.
   *
   * @param expressions varargs to provide expressions and values
   * @return expression
   */
  public static Expression let(Object... expressions) {
    return new Expression("let", expressions);
  }

  /**
   * References variable bound using let.
   *
   * @param expression the variable naming expression that was bound with using let
   * @return expression
   */
  public static Expression var(Expression expression) {
    return new Expression("var", expression);
  }

  /**
   * References variable bound using let.
   *
   * @param variableName the variable naming that was bound with using let
   * @return expression
   */
  public static Expression var(String variableName) {
    return new Expression("var", variableName);
  }

  //
  // Zoom
  //

  /**
   * Gets the current zoom level.
   * <p>
   * Note that in style layout and paint properties,
   * zoom may only appear as the input to a top-level step or interpolate expression.
   * </p>
   *
   * @return expression
   */
  public static Expression zoom() {
    return new Expression("zoom");
  }

  //
  // Ramps, scales, curves
  //

  /**
   * Produces discrete, stepped results by evaluating a piecewise-constant function defined by pairs of
   * input and output values (\"stops\"). The `input` may be any numeric expression (e.g., `[\"get\", \"population\"]`).
   * Stop inputs must be numeric literals in strictly ascending order.
   * Returns the output value of the stop just less than the input,
   * or the first input if the input is less than the first stop.
   *
   * @param input the input value
   * @param stops  pair of input and output values
   * @return expression
   */
  public static Expression step(Number input, Object... stops) {
    return new Expression("step", join(new Object[] {input}, stops));
  }

  /**
   * Produces discrete, stepped results by evaluating a piecewise-constant function defined by pairs of
   * input and output values (\"stops\"). The `input` may be any numeric expression (e.g., `[\"get\", \"population\"]`).
   * Stop inputs must be numeric literals in strictly ascending order.
   * Returns the output value of the stop just less than the input,
   * or the first input if the input is less than the first stop.
   *
   * @param expression the input expression
   * @param stops      pair of input and output values
   * @return expression
   */
  public static Expression step(Expression expression, Object... stops) {
    return new Expression("step", join(new Object[] {expression}, stops));
  }

  /**
   * Produces continuous, smooth results by interpolating between pairs of input and output values (\"stops\").
   * The `input` may be any numeric expression (e.g., `[\"get\", \"population\"]`).
   * Stop inputs must be numeric literals in strictly ascending order.
   * The output type must be `number`, `array&lt;number&gt;`, or `color`.
   *
   * @param interpolation type of interpolation
   * @param input        the input number
   * @param stops         pair of input and output values
   * @return expression
   */
  public static Expression interpolate(Expression interpolation, Number input, Object... stops) {
    return new Expression("interpolate", join(new Object[] {interpolation}, join(new Object[] {input}, stops)));
  }

  /**
   * Produces continuous, smooth results by interpolating between pairs of input and output values (\"stops\").
   * The `input` may be any numeric expression (e.g., `[\"get\", \"population\"]`).
   * Stop inputs must be numeric literals in strictly ascending order.
   * The output type must be `number`, `array&lt;number&gt;`, or `color`.
   *
   * @param interpolation type of interpolation
   * @param number        the input expression
   * @param stops         pair of input and output values
   * @return expression
   */
  public static Expression interpolate(Expression interpolation, Expression number, Object... stops) {
    return new Expression("interpolate", join(new Object[] {interpolation, number}, stops));
  }

  /**
   * interpolates linearly between the pair of stops just less than and just greater than the input.
   *
   * @return expression
   */
  public static Expression linear() {
    return new Expression("linear");
  }

  /**
   * Interpolates exponentially between the stops just less than and just greater than the input.
   * `base` controls the rate at which the output increases:
   * higher values make the output increase more towards the high end of the range.
   * With values close to 1 the output increases linearly.
   *
   * @param base value controlling the route at which the output increases
   * @return expression
   */
  public static Expression exponential(Number base) {
    return new Expression("exponential", base);
  }

  /**
   * Interpolates exponentially between the stops just less than and just greater than the input.
   * The parameter controls the rate at which the output increases:
   * higher values make the output increase more towards the high end of the range.
   * With values close to 1 the output increases linearly.
   *
   * @param expression base number expression
   * @return expression
   */
  public static Expression exponential(Expression expression) {
    return new Expression("exponential", expression);
  }

  /**
   * Interpolates using the cubic bezier curve defined by the given control points.
   *
   * @param x1 x value of the first point of a cubic bezier, ranges from 0 to 1
   * @param y1 y value of the first point of a cubic bezier, ranges from 0 to 1
   * @param x2 x value of the second point of a cubic bezier, ranges from 0 to 1
   * @param y2 y value fo the second point of a cubic bezier, ranges from 0 to 1
   * @return expression
   */
  public static Expression cubicBezier(Number x1, Number y1, Number x2, Number y2) {
    return new Expression("cubic-bezier", x1, y1, x2, y2);
  }

  /**
   * Interpolates using the cubic bezier curve defined by the given control points.
   *
   * @param expressions varags of expressions and values
   * @return expression
   */
  public static Expression cubicBezier(Object... expressions) {
    return new Expression("cubic-bezier", expressions);
  }


  /**
   * Converts a int color to a String following with contents equal to rgba(red, green, blue, alpha)
   *
   * @param color the int color to convert
   * @return rgba string
   */
  public static String color(@ColorInt int color) {
    return PropertyFactory.colorToRgbaString(color);
  }

  private static Object[] join(Object[] a, Object[] b) {
    Object[] output = new Object[a.length + b.length];
    System.arraycopy(a, 0, output, 0, a.length);
    System.arraycopy(b, 0, output, a.length, b.length);
    return output;
  }
}