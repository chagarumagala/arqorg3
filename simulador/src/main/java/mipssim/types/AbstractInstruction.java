package mipssim.types;

import java.util.BitSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mipssim.interfaces.Instruction;

public abstract class AbstractInstruction implements Instruction{
    private BitSet bin;
    private INSTRUCTION_FORMAT iformat;
    private String name;

    public static AbstractInstruction InstructionFactory(String line) throws RuntimeException{
        // Figures out the instruction format
        Pattern op = Pattern.compile("(\\w+)"); //Find the first "word"
        Matcher m = op.matcher(line);
        // Couldn't find an operation
        if(!m.find()){
            throw new RuntimeException("No instruction found");
        }
        // Gets the name and format of the operation
        String name = m.group(1);
        INSTRUCTION_FORMAT iFormat = INSTRUCTION_FORMAT.getFormat(name);

        switch(iFormat){
            case R:
                return rBuilder(line, name);
            case I:
                return iBuilder(line, name);
            case J:
                return jBuilder(line, name);
            case FORMAT_ERROR:
                throw new RuntimeException(name + "is an unknown operation");
        }
        return null;

    }

    private static RInstruction rBuilder(String line, String name){
        Integer rd, rs, rt, shamt = 0;
        switch(name){
            case "add": case "addu": case "sub": case "subu": case "and":
            case "or":  case "nor":  case "slt": case "sltu":
                Matcher m1 = Pattern.compile("\\$(\\w+)").matcher(line);
                m1.find(); rd = normalizeReg(m1.group(1));
                m1.find(); rs = normalizeReg(m1.group(1));
                m1.find(); rt = normalizeReg(m1.group(1));
                return new RInstruction(name, rs, rt, rd, 0);

            case "sll": case "srl":
                Matcher m2 = Pattern.compile("\\$*(\\w+)").matcher(line);
                m2.find(); rd = normalizeReg(m2.group(1));
                m2.find(); rt = normalizeReg(m2.group(1));
                m2.find(); shamt = Integer.parseInt(m2.group(1));
                return new RInstruction(name, 0, rt, rd, shamt);

            case "jr":
                Matcher m3 = Pattern.compile("\\$(\\w+)").matcher(line);
                m3.find(); rs = normalizeReg(m3.group(1));
                return new RInstruction(name, rs, 0, 0, 0);
            default:
                return null;
        }
    }

    private static IInstruction iBuilder(String line, String name){
        Integer rs, rt, i = 0;
        Matcher m = Pattern.compile("\\$*(\\w+)").matcher(line);
        switch(name){
            case "beq": case "bne":
                m.find(); rs = normalizeReg(m.group(1));
                m.find(); rt = normalizeReg(m.group(1));
                m.find(); i = Integer.parseInt(m.group(1))/4;
                return new IInstruction(name, rs, rt, i);

            case "addi":  case "addiu": case "andi":
            case "ori":   case "slti":  case "sltiu":
                m.find(); rt = normalizeReg(m.group(1));
                m.find(); rs = normalizeReg(m.group(1));
                m.find(); i = Integer.parseInt(m.group(1));
                return new IInstruction(name, rs, rt, i);

            case "lui":
                m.find(); rt = normalizeReg(m.group(1));
                m.find(); i = Integer.parseInt(m.group(1));
                return new IInstruction(name, 0, rt, i);
            
            case "lw": case "sw":
                m.find(); rt = normalizeReg(m.group(1));
                m.find(); i = Integer.parseInt(m.group(1));
                m.find(); rs = normalizeReg(m.group(1));
                return new IInstruction(name, rs, rt, i);

        }
        return null;
    }

    private static JInstruction jBuilder(String line, String name){
        Integer address = 0;
        Matcher m = Pattern.compile("(\\d+)").matcher(line);
        m.find(); address = Integer.parseInt(m.group(1));
        return new JInstruction(name, address);
    }
    private static Integer normalizeReg(String s){
        String[] registers = {"zero", "at", "v0", "v1", //0-4
                                "a1", "a2", "a3", "t0", //5-8
                                "t1", "t2", "t3", "t4", //9-12
                                "t5", "t6", "t7", "s0", //13-16
                                "s1", "s2", "s3", "s4", //17-20
                                "s5", "s6", "s7", "t8", //21-24
                                "t9", "k0", "k1", "gp", //25-28
                                "sp", "fp", "ra"};      //29-31
        for(int i = 0; i < registers.length; i++){
            if(s.equals(registers[i])) return i;
        }
        return Integer.parseInt(s);       
    }
}