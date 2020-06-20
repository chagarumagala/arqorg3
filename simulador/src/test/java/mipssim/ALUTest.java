package mipssim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mipssim.components.ConcreteALU;
import mipssim.interfaces.ALU;
import mipssim.types.ALU_OPS;

public class ALUTest {
    ALU alu;

    @BeforeEach
    public void preparation(){
        alu = new ConcreteALU();
    }
    @Test 
    public void and(){
        assertEquals(alu.process(ALU_OPS.AND, 2, 1), 0);
        assertEquals(alu.process(ALU_OPS.AND, 2, 2), 2);
    }
    @Test 
    public void or(){
        assertEquals(alu.process(ALU_OPS.OR, 2, 1), 3);
    }
    @Test
    public void sums(){
        assertEquals(alu.process(ALU_OPS.ADD, 2, 5), 7);
    }
    @Test
    public void sub(){
        assertEquals(alu.process(ALU_OPS.SUB,5,2), 3);
    }
    @Test
    public void slt(){
        assertEquals(alu.process(ALU_OPS.SLT, 1, 2), 1);
        assertEquals(alu.process(ALU_OPS.SLT, 1, 1), 0);
        assertEquals(alu.process(ALU_OPS.SLT, 3, 2), 0);
    }
    @Test
    public void nor(){
        assertEquals(alu.process(ALU_OPS.NOR, 2, 2), -3);
    }

}