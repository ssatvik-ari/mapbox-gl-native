package com.mapbox.mapboxsdk.style.expressions;

import android.graphics.Color;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import org.junit.Test;

import java.util.Arrays;

import static com.mapbox.mapboxsdk.style.expressions.Expression.acos;
import static com.mapbox.mapboxsdk.style.expressions.Expression.all;
import static com.mapbox.mapboxsdk.style.expressions.Expression.any;
import static com.mapbox.mapboxsdk.style.expressions.Expression.array;
import static com.mapbox.mapboxsdk.style.expressions.Expression.asin;
import static com.mapbox.mapboxsdk.style.expressions.Expression.at;
import static com.mapbox.mapboxsdk.style.expressions.Expression.atan;
import static com.mapbox.mapboxsdk.style.expressions.Expression.bool;
import static com.mapbox.mapboxsdk.style.expressions.Expression.coalesce;
import static com.mapbox.mapboxsdk.style.expressions.Expression.concat;
import static com.mapbox.mapboxsdk.style.expressions.Expression.cos;
import static com.mapbox.mapboxsdk.style.expressions.Expression.cubicBezier;
import static com.mapbox.mapboxsdk.style.expressions.Expression.division;
import static com.mapbox.mapboxsdk.style.expressions.Expression.downcase;
import static com.mapbox.mapboxsdk.style.expressions.Expression.e;
import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.exponential;
import static com.mapbox.mapboxsdk.style.expressions.Expression.geometryType;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gte;
import static com.mapbox.mapboxsdk.style.expressions.Expression.has;
import static com.mapbox.mapboxsdk.style.expressions.Expression.heatmapDensity;
import static com.mapbox.mapboxsdk.style.expressions.Expression.id;
import static com.mapbox.mapboxsdk.style.expressions.Expression.interpolate;
import static com.mapbox.mapboxsdk.style.expressions.Expression.length;
import static com.mapbox.mapboxsdk.style.expressions.Expression.let;
import static com.mapbox.mapboxsdk.style.expressions.Expression.linear;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.ln;
import static com.mapbox.mapboxsdk.style.expressions.Expression.ln2;
import static com.mapbox.mapboxsdk.style.expressions.Expression.log10;
import static com.mapbox.mapboxsdk.style.expressions.Expression.log2;
import static com.mapbox.mapboxsdk.style.expressions.Expression.lt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.lte;
import static com.mapbox.mapboxsdk.style.expressions.Expression.match;
import static com.mapbox.mapboxsdk.style.expressions.Expression.max;
import static com.mapbox.mapboxsdk.style.expressions.Expression.min;
import static com.mapbox.mapboxsdk.style.expressions.Expression.mod;
import static com.mapbox.mapboxsdk.style.expressions.Expression.neq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.not;
import static com.mapbox.mapboxsdk.style.expressions.Expression.number;
import static com.mapbox.mapboxsdk.style.expressions.Expression.object;
import static com.mapbox.mapboxsdk.style.expressions.Expression.pi;
import static com.mapbox.mapboxsdk.style.expressions.Expression.pow;
import static com.mapbox.mapboxsdk.style.expressions.Expression.product;
import static com.mapbox.mapboxsdk.style.expressions.Expression.properties;
import static com.mapbox.mapboxsdk.style.expressions.Expression.rgb;
import static com.mapbox.mapboxsdk.style.expressions.Expression.rgba;
import static com.mapbox.mapboxsdk.style.expressions.Expression.sin;
import static com.mapbox.mapboxsdk.style.expressions.Expression.sqrt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.step;
import static com.mapbox.mapboxsdk.style.expressions.Expression.string;
import static com.mapbox.mapboxsdk.style.expressions.Expression.subtract;
import static com.mapbox.mapboxsdk.style.expressions.Expression.sum;
import static com.mapbox.mapboxsdk.style.expressions.Expression.switchCase;
import static com.mapbox.mapboxsdk.style.expressions.Expression.tan;
import static com.mapbox.mapboxsdk.style.expressions.Expression.toBool;
import static com.mapbox.mapboxsdk.style.expressions.Expression.toColor;
import static com.mapbox.mapboxsdk.style.expressions.Expression.toNumber;
import static com.mapbox.mapboxsdk.style.expressions.Expression.toRgba;
import static com.mapbox.mapboxsdk.style.expressions.Expression.typeOf;
import static com.mapbox.mapboxsdk.style.expressions.Expression.upcase;
import static com.mapbox.mapboxsdk.style.expressions.Expression.var;
import static com.mapbox.mapboxsdk.style.expressions.Expression.zoom;
import static junit.framework.Assert.assertTrue;

/**
 * Expression unit tests that validate the expression output with the expected Object[]array representation.
 */
public class ExpressionTest {

  @Test
  public void testRgb() throws Exception {
    Object[] expected = new Object[] {"rgb", 0, 0, 0};
    Object[] actual = rgb(0, 0, 0).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testRgba() throws Exception {
    Object[] expected = new Object[] {"rgba", 0, 0, 0, 1};
    Object[] actual = rgba(0, 0, 0, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToRgba() throws Exception {
    Object[] expected = new Object[] {"to-rgba", PropertyFactory.colorToRgbaString(Color.RED)};
    Object[] actual = toRgba(Color.RED).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testEq() throws Exception {
    Object[] expected = new Object[] {"==", 1, 1};
    Object[] actual = eq(1, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNeq() throws Exception {
    Object[] expected = new Object[] {"!=", 0, 1};
    Object[] actual = neq(0, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGt() throws Exception {
    Object[] expected = new Object[] {">", 0, 1};
    Object[] actual = gt(0, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLt() throws Exception {
    Object[] expected = new Object[] {"<", 1, 0};
    Object[] actual = lt(1, 0).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGte() throws Exception {
    Object[] expected = new Object[] {">=", 1, 1};
    Object[] actual = gte(1, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLte() throws Exception {
    Object[] expected = new Object[] {"<=", 1, 1};
    Object[] actual = lte(1, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAll() throws Exception {
    Object[] expected = new Object[] {"all", true, true, true};
    Object[] actual = all(true, true, true).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAny() throws Exception {
    Object[] expected = new Object[] {"any", true, false, false};
    Object[] actual = any(true, false, false).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNot() throws Exception {
    Object[] expected = new Object[] {"!", false};
    Object[] actual = not(false).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSwitchCase() throws Exception {
    Object[] expectedCaseOneGet = new Object[] {"get", "key1"};
    Object[] expectedCaseOne = new Object[] {"==", expectedCaseOneGet, "value1"};
    Object[] expectedCaseTwoGet = new Object[] {"get", "key2"};
    Object[] expectedCaseTwo = new Object[] {"!=", expectedCaseTwoGet, "value2"};
    Object[] expected = new Object[] {"case", expectedCaseOne, expectedCaseTwo};

    Object[] actual = switchCase(
      eq(get("key1"), "value1"),
      neq(get("key2"), "value2")
    ).toArray();

    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMatch() throws Exception {
    Object[] labelZero = new Object[] {"a", "output"};
    Object[] labelOne = new Object[] {"b", "output2"};
    Object[] labelTwo = new Object[] {"c", "output3"};

    Object[] expected = new Object[] {"match", labelZero, labelOne, labelTwo};
    Object[] actual = match(labelZero, labelOne, labelTwo).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCoalesce() throws Exception {
    Object[] expectedGetOne = new Object[] {"get", "invalidKey"};
    Object[] expectedGetTwo = new Object[] {"get", "validKey"};
    Object[] expected = new Object[] {"coalesce", expectedGetOne, expectedGetTwo};

    Object[] actual = coalesce(
      get("invalidKey"),
      get("validKey")
    ).toArray();

    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testProperties() throws Exception {
    Object[] expected = new Object[] {"properties"};
    Object[] actual = properties().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGeometryType() throws Exception {
    Object[] expected = new Object[] {"geometry-type"};
    Object[] actual = geometryType().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testId() throws Exception {
    Object[] expected = new Object[] {"id"};
    Object[] actual = id().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHeatmapDensity() throws Exception {
    Object[] expected = new Object[] {"heatmap-density"};
    Object[] actual = heatmapDensity().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAt() throws Exception {
    Object[] expected = new Object[] {"at", 3, new Object[] {"one", "two"}};
    Object[] actual = at(3, new Object[] {"one", "two"}).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAtExpression() throws Exception {
    Object[] expected = new Object[] {"at", 3, new Object[] {"properties"}};
    Object[] actual = at(3, properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGet() throws Exception {
    Object[] expected = new Object[] {"get", "key"};
    Object[] actual = get("key").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGetObject() throws Exception {
    Object[] expected = new Object[] {"get", "key", new Object[] {"properties"}};
    Object[] actual = get("key", properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHas() throws Exception {
    Object[] expected = new Object[] {"has", "key"};
    Object[] actual = has("key").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasObject() throws Exception {
    Object[] expected = new Object[] {"has", "key", new Object[] {"properties"}};
    Object[] actual = has("key", properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasExpression() throws Exception {
    Object[] expected = new Object[] {"has", new Object[] {"get", "key"}, new Object[] {"properties"}};
    Object[] actual = has(get("key"), properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLength() throws Exception {
    Object[] expected = new Object[] {"length", "key"};
    Object[] actual = length("key").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLengthExpression() throws Exception {
    Object[] expected = new Object[] {"length", new Object[] {"get", "key"}};
    Object[] actual = length(get("key")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLn2() throws Exception {
    Object[] expected = new Object[] {"ln2"};
    Object[] actual = ln2().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testPi() throws Exception {
    Object[] expected = new Object[] {"pi"};
    Object[] actual = pi().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testE() throws Exception {
    Object[] expected = new Object[] {"e"};
    Object[] actual = e().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSum() throws Exception {
    Object[] expected = new Object[] {"+", 1, 2};
    Object[] actual = sum(1, 2).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testProduct() throws Exception {
    Object[] expected = new Object[] {"*", 1, 2};
    Object[] actual = product(1, 2).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSubtract() throws Exception {
    Object[] expected = new Object[] {"-", 2, 1};
    Object[] actual = subtract(2, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDivision() throws Exception {
    Object[] expected = new Object[] {"/", 2, 1};
    Object[] actual = division(2, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDivisionWithNestedGet() throws Exception {
    Object nestedGet = new Object[] {"get", "key"};
    Object[] expected = new Object[] {"/", 2, nestedGet};
    Object[] actual = division(2, get("key")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMod() throws Exception {
    Object[] expected = new Object[] {"%", 1, 3};
    Object[] actual = mod(1, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testPow() throws Exception {
    Object[] expected = new Object[] {"^", 2, 3};
    Object[] actual = pow(2, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSqrt() throws Exception {
    Object[] expected = new Object[] {"sqrt", 4};
    Object[] actual = sqrt(4).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLog10() throws Exception {
    Object[] expected = new Object[] {"log10", 10};
    Object[] actual = log10(10).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLn() throws Exception {
    Object[] expected = new Object[] {"ln", 2};
    Object[] actual = ln(2).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLog2() throws Exception {
    Object[] expected = new Object[] {"log2", 16};
    Object[] actual = log2(16).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSin() throws Exception {
    Object[] expected = new Object[] {"sin", 45};
    Object[] actual = sin(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCos() throws Exception {
    Object[] expected = new Object[] {"cos", 45};
    Object[] actual = cos(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testTan() throws Exception {
    Object[] expected = new Object[] {"tan", 45};
    Object[] actual = tan(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAsin() throws Exception {
    Object[] expected = new Object[] {"asin", 45};
    Object[] actual = asin(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAcos() throws Exception {
    Object[] expected = new Object[] {"acos", 45};
    Object[] actual = acos(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAtan() throws Exception {
    Object[] expected = new Object[] {"atan", 45};
    Object[] actual = atan(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMin() throws Exception {
    Object[] expected = new Object[] {"min", 0, 1, 2, 3};
    Object[] actual = min(0, 1, 2, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMax() throws Exception {
    Object[] expected = new Object[] {"max", 0, 1, 2, 3};
    Object[] actual = max(0, 1, 2, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testUpcase() throws Exception {
    Object[] expected = new Object[] {"upcase", "string"};
    Object[] actual = upcase("string").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDowncase() throws Exception {
    Object[] expected = new Object[] {"downcase", "string"};
    Object[] actual = downcase("string").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testConcat() throws Exception {
    Object[] expected = new Object[] {"concat", "foo", "bar"};
    Object[] actual = concat("foo", "bar").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLiteral() throws Exception {
    Object object = new Object();
    Object[] expected = new Object[] {"literal", object};
    Object[] actual = literal(object).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testArray() throws Exception {
    Object[] get = new Object[] {"get", "keyToArray"};
    Object[] expected = new Object[] {"array", get};
    Object[] actual = array(get("keyToArray")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testTypeOf() throws Exception {
    Object[] expected = new Object[] {"typeof", "value"};
    Object[] actual = typeOf("value").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testString() throws Exception {
    Object[] expected = new Object[] {"string", "value"};
    Object[] actual = string("value").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNumber() throws Exception {
    Object[] expected = new Object[] {"number", 1};
    Object[] actual = number(1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testBool() throws Exception {
    Object[] expected = new Object[] {"boolean", true};
    Object[] actual = bool(true).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testObject() throws Exception {
    Object object = new Object();
    Object[] expected = new Object[] {"object", object};
    Object[] actual = object(object).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToString() throws Exception {
    Object[] expected = new Object[] {"to-string", 3};
    Object[] actual = Expression.toString(3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToNumber() throws Exception {
    Object[] expected = new Object[] {"to-number", "3"};
    Object[] actual = toNumber("3").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToBool() throws Exception {
    Object[] expected = new Object[] {"to-boolean", "true"};
    Object[] actual = toBool("true").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToColor() throws Exception {
    Object[] expected = new Object[] {"to-color", "value"};
    Object[] actual = toColor("value").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLet() throws Exception {
    Object[] expected = new Object[] {"let", "letName", "value"};
    Object[] actual = let("letName", "value").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testVar() throws Exception {
    Object[] expected = new Object[] {"var", "letName"};
    Object[] actual = var("letName").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testVarExpression() throws Exception {
    Object[] expected = new Object[] {"var", new Object[] {"get", "letName"}};
    Object[] actual = var(get("letName")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testZoom() throws Exception {
    Object[] expected = new Object[] {"zoom"};
    Object[] actual = zoom().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testStepBasic() throws Exception {
    Object[] expected = new Object[] {"step", 12, 11, 0, 111, 1, 1111};
    Object[] actual = step(12, 11, 0, 111, 1, 1111).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testStepExpression() throws Exception {
    Object[] input = new Object[] {"get", "key"};
    Object[] number = new Object[] {"number", input};
    Object[] expected = new Object[] {"step", number, 11, 0, 111, 1, 1111};
    Object[] actual = step(number(get("key")), 11, 0, 111, 1, 1111).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLinear() throws Exception {
    Object[] stopZero = new Object[] {0, 1};
    Object[] stopOne = new Object[] {1, 2};
    Object[] stopTwo = new Object[] {2, 3};
    Object[] expected = new Object[] {"interpolate", new Object[] {"linear"}, 12, stopZero, stopOne, stopTwo};
    Object[] actual = interpolate(linear(), 12, stopZero, stopOne, stopTwo).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testExponential() throws Exception {
    Object[] exponential = new Object[] {"exponential", 12};
    Object[] get = new Object[] {"get", "x"};
    Object[] expected = new Object[] {"interpolate", exponential, get, 0, 100, 200};
    Object[] actual = interpolate(exponential(12), get("x"), 0, 100, 200).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testExponentialExpression() throws Exception {
    Object[] getX = new Object[] {"get", "x"};
    Object[] exponential = new Object[] {"exponential", getX};
    Object[] getY = new Object[] {"get", "y"};
    Object[] expected = new Object[] {"interpolate", exponential, getY, 0, 100, 200};
    Object[] actual = interpolate(exponential(get("x")), get("y"), 0, 100, 200).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCubicBezier() throws Exception {
    Object[] cubicBezier = new Object[] {"cubic-bezier", 1, 1, 1, 1};
    Object[] get = new Object[] {"get", "x"};
    Object[] expected = new Object[] {"interpolate", cubicBezier, get, 0, 100, 200};
    Object[] actual = interpolate(cubicBezier(1, 1, 1, 1), get("x"), 0, 100, 200).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCubicBezierExpression() throws Exception {
    Object[] getX = new Object[] {"get", "x"};
    Object[] getY = new Object[] {"get", "y"};
    Object[] getZ = new Object[] {"get", "z"};
    Object[] cubicBezier = new Object[] {"cubic-bezier", getZ, 1, getY, 1};
    Object[] expected = new Object[] {"interpolate", cubicBezier, getX, 0, 100, 200};
    Object[] actual = interpolate(cubicBezier(get("z"), 1, get("y"), 1), get("x"), 0, 100, 200).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

}
