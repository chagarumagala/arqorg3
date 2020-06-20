package mipssim.types;

public enum ALU_OPS {
    AND, OR, ADD, SUB, SLT, NOR;


    public String toString(){
        switch(this){
            case AND:
            return "0000";
            case OR:
            return "0001";
            case ADD:
            return "0010";
            case SUB:
            return "0110";
            case SLT:
            return "0111";
            case NOR:
            return "1100";
            default:
            return "1111"; // Erro
        }
    }
}