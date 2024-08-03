package org.deltaalpha.ltoloxa.instructions;

import org.deltaalpha.ltoloxa.LtoloxaEnv;

public class LtoloxaInstructions {
    public static class DirMoveInstruction implements ILtoloxaInstruction {
        public String input;

        public DirMoveInstruction(String input) {
            this.input = input;
        }

        @Override
        public void exec(LtoloxaEnv env) {
            env.append(env.format(input));
        }
    }

    public static class DirDownInstruction implements ILtoloxaInstruction {
        @Override
        public void exec(LtoloxaEnv env) {
            env.prevdir();
        }
    }

    public static class SetInstruction implements ILtoloxaInstruction {
        public String key, value;

        public SetInstruction(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public void exec(LtoloxaEnv env) {
            env.handle(env.format(key), env.format(value));
        }
    }

    public static class VareditInstruction implements ILtoloxaInstruction {
        @Override
        public void exec(LtoloxaEnv env) {
            env.varedit = !env.varedit;
        }
    }
}