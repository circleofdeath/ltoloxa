package org.deltaalpha.ltoloxa;

import org.deltaalpha.ltoloxa.instructions.ILtoloxaInstruction;
import org.deltaalpha.ltoloxa.instructions.LtoloxaInstructions.*;

import java.util.ArrayList;
import java.util.List;

public class LtoloxaExec {
    public static void run(List<ILtoloxaInstruction> instructionList, LtoloxaEnv env) {
        instructionList.forEach(inst -> inst.exec(env));
    }

    public static List<ILtoloxaInstruction> compile(String string) {
        string = string.trim();
        ArrayList<ILtoloxaInstruction> output = new ArrayList<>();
        String[] buffer = string.split("\\R|\\n");
        for(String line : buffer) {
            line = line.trim();

            if(line.isEmpty() || line.startsWith("--")) {
                continue;
            }

            if(line.startsWith("\\")) {
                line = line.substring(1);
            }

            if(line.startsWith(":")) {
                line = line.substring(1);
                while(true) {
                    if(line.isEmpty()) {
                        break;
                    }

                    char ch = line.charAt(0);
                    if(ch == ':') {
                        output.add(new DirDownInstruction());
                    } else {
                        if(ch == '%') {
                            output.add(new VareditInstruction());
                        } else break;
                    }

                    line = line.substring(1);
                }
                if(line.contains(":") || line.contains("%")) {
                    throw new IllegalArgumentException("Illegal syntax: no ':' or '%' allowed in names");
                } else {
                    if(!line.isEmpty()) {
                        output.add(new DirMoveInstruction(line));
                    }
                }
            } else {
                String[] sub = line.split(" = ", 2);
                if(sub.length < 2) {
                    throw new IllegalStateException("Illegal buffer length");
                } else {
                    if(line.contains(":") || line.contains("%")) {
                        throw new IllegalArgumentException("Illegal syntax: no ':' or '%' allowed in names");
                    } else {
                        output.add(new SetInstruction(sub[0], sub[1]));
                    }
                }
            }
        }
        return output;
    }
}