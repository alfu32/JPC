package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class mov_Ew_Iw extends Executable
{
    final int op1Index;
    final int immw;

    public mov_Ew_Iw(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        immw = (short)parent.operand[1].lval;
    }


    public mov_Ew_Iw(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1Index = FastDecoder.Ew(modrm);
        immw = Modrm.Iw(input);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        op1.set16((short)immw);
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