package mipssim.types;

public enum INSTRUCTION_FORMAT {
    R, I, J, FORMAT_ERROR;

    public static INSTRUCTION_FORMAT getFormat(String i){
        switch(i){
            case "add": case "addu": case "sub": case "subu": case "and":
            case "or":  case "xor":  case "nor": case "slt":  case "sltu": 
            case "sll": case "srl":  case "jr":
                return R;

            case "beq": case "bne":  case "addi":  case "addiu": case "andi":
            case "ori": case "slti": case "sltiu": case "lui":   case "lw":
            case "sw":
                return I;
            
            case "j": case "jal":
                return J;
            
            default:
                return FORMAT_ERROR;
        }
    }
}