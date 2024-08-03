package org.deltaalpha.ltoloxa.tests;

import org.deltaalpha.ltoloxa.LtoloxaEnv;
import org.deltaalpha.ltoloxa.LtoloxaExec;

public class BasicTest {
    public static final String bundle = """
-- basics
abc = abc2
abk = abk2

-- directory
:ji.
a = b
c = d
-- variable mode
::%
f = g
:%

-- not comment test
\\--not_comment = abc

-- variable test
var_thing = abcdf$f$hji
var xd = $d$<varname>$d$
copy2 = abc $eq$ abc2
""";

    public static void main(String[] args) {
        var output = LtoloxaExec.compile(bundle);
        var env = new LtoloxaEnv();
        LtoloxaExec.run(output, env);
        System.out.println(env.pathCache);
        System.out.println(env.path);
        System.out.println(env.varedit);
        System.out.println("variables: ");
        env.variables.forEach((k, v) -> System.out.println(": " + k + " = " + v));
        System.out.println("output: ");
        env.output.forEach((k, v) -> System.out.println(": " + k + " = " + v));
        System.out.println("simple: ");
        System.out.println(env.get("abc"));
        System.out.println(env.get("abc3", "abc4"));
    }
}