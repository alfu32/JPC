package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class cmovo_Gd_Ed extends Executable
{
    final int op1Index;
    final int op2Index;

    public cmovo_Gd_Ed(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        op2Index = Processor.getRegIndex(parent.operand[1].toString());
    }


    public cmovo_Gd_Ed(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1Index = FastDecoder.Gd(modrm);
        op2Index = FastDecoder.Ed(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        Reg op2 = cpu.regs[op2Index];
        if (cpu.of())
            op1.set32(op2.get32());
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}