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
        ArrayList<ILtoloxaInstruction> output = new ArrayList<>();
        String[] buffer = string.split("\\R|\\n");
        for(String s : buffer) {
            String line = s;
            if(line.startsWith(":")) {
                line = line.substring(1);
                for(int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    if(ch == ':') {
                        output.add(new DirDownInstruction());
                    } else {
                        if(ch == '%') {
                            output.add(new VareditInstruction());
                        } else break;
                    }
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