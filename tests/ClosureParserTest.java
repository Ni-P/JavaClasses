package tests;

import org.junit.gen5.gradle.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static nip.ClosureParser.*;

import org.junit.platform.commons.JUnitException;

public class ClosureParserTest {

    @Test
    void TestCountDepth() {
        System.out.println("Testing: countDepth...");
        Assertions.assertEquals(0, countDepth("{}", "{", "}"));
        Assertions.assertEquals(1, countDepth("{ }", "{", "}"));
        Assertions.assertEquals(1, countDepth("{ }", "{", "}"));
        Assertions.assertEquals(1, countDepth("{{}}", "{", "}"));
        Assertions.assertEquals(2, countDepth("{{ }}", "{", "}"));
        Assertions.assertEquals(2, countDepth("{{ }}", "{", "}"));
        Assertions.assertEquals(3, countDepth("{{{ }}}", "{", "}"));
        Assertions.assertEquals(3, countDepth("{{{ }}}", "{", "}"));
        Assertions.assertEquals(3, countDepth("{ { { } } }", "{", "}"));
        Assertions.assertEquals(3, countDepth("{a{2{ }4}g}", "{", "}"));
        Assertions.assertEquals(0, countDepth("fw uif29 3fweu gfuwfw wa ", "{", "}"));
        Assertions.assertEquals(0, countDepth("fw uif29 }3fweu gfuwfw {wa ", "{", "}"));
        Assertions.assertEquals(0, countDepth("}{", "{", "}"));
        Assertions.assertEquals(0, countDepth("} {", "{", "}"));
        Assertions.assertEquals(1, countDepth("{word}", "{", "}"));
        Assertions.assertEquals(2, countDepth("{two{word}s}", "{", "}"));
        Assertions.assertEquals(3, countDepth("{three{not{two}word}s}", "{", "}"));

    }

    @Test
    void TestParseDeep() {
        System.out.println("Testing: parseDeep...");
        Assertions.assertEquals("", parseDeep("test", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseDeep("}test{", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{test}", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{{test}}", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{{{test}}}", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{ { {test} } }", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{wefw{test}dasd}", new String[]{"{", "}"}));
        Assertions.assertEquals(" test ", parseDeep("{{ test }}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseDeep("{}test{}", new String[]{"{", "}"}));
        Assertions.assertEquals(" ", parseDeep("{{ }}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseDeep("{{}}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseDeep("{}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseDeep("}{", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{{test}asdasdasdasd}", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseDeep("{fsggggsg{test}}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseDeep("", new String[]{"{", "}"}));


    }

    @Test
    void TestParseShallow() {
        System.out.println("Testing: parseShallow...");
        Assertions.assertEquals("", parseShallow("{}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseShallow("}{", new String[]{"{", "}"}));
        Assertions.assertEquals("{}", parseShallow("{{}}", new String[]{"{", "}"}));
        Assertions.assertEquals("{ }", parseShallow("{{ }}", new String[]{"{", "}"}));
        Assertions.assertEquals(" ", parseShallow("{ }", new String[]{"{", "}"}));
        Assertions.assertEquals("test", parseShallow("{test}", new String[]{"{", "}"}));
        Assertions.assertEquals("test{}", parseShallow("{test{}}", new String[]{"{", "}"}));
        Assertions.assertEquals("{}test", parseShallow("{{}test}", new String[]{"{", "}"}));
        Assertions.assertEquals("test{test}test", parseShallow("{test{test}test}", new String[]{"{", "}"}));
        Assertions.assertEquals("test}{test", parseShallow("{test}{test}", new String[]{"{", "}"}));
        Assertions.assertEquals("", parseShallow("", new String[]{"{", "}"}));
//        Assertions.assertEquals("",parseShallow("", new String[]{"{","}"}));
    }

}
