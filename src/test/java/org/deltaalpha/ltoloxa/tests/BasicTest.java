package org.deltaalpha.ltoloxa.tests;

import org.deltaalpha.ltoloxa.LtoloxaEnv;
import org.deltaalpha.ltoloxa.LtoloxaExec;
import org.deltaalpha.ltoloxa.instructions.ILtoloxaInstruction;

import java.util.List;

/*
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
\--not_comment = abc

-- variable test
var_thing = abcdf$f$hji
var xd = $d$<varname>$d$
copy2 = abc $eq$ abc2
 */

public class BasicTest {
    public static final String bundle = "-- basics\n" +
            "abc = abc2\n" +
            "abk = abk2\n" +
            "\n" +
            "-- directory\n" +
            ":ji.\n" +
            "a = b\n" +
            "c = d\n" +
            "-- variable mode\n" +
            "::%\n" +
            "f = g\n" +
            ":%\n" +
            "\n" +
            "-- not comment test\n" +
            "\\--not_comment = abc\n" +
            "\n" +
            "-- variable test\n" +
            "var_thing = abcdf$f$hji\n" +
            "var xd = $d$<varname>$d$\n" +
            "copy2 = abc $eq$ abc2";

    public static void main(String[] args) {
        List<ILtoloxaInstruction> output = LtoloxaExec.compile(bundle);
        LtoloxaEnv env = new LtoloxaEnv();
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